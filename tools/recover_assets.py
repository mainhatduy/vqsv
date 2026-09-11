#!/usr/bin/env python3
"""Lossless readers for the original J2ME assets. No original files are modified.

Format evidence: reference/decompiled/{ae,aa,ad,p,j,d,aq}.java and game/k.java.
Run with build/asset-venv/bin/python (Pillow is used only for raster composition).
"""
from __future__ import annotations

import argparse
import hashlib
import io
import json
from pathlib import Path
import struct
import zlib

ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / "src/main/resources"
PNG = b"\x89PNG\r\n\x1a\n"


class Reader:
    def __init__(self, data: bytes, label="data"):
        self.data, self.pos, self.label = data, 0, label

    def take(self, count):
        if count < 0 or self.pos + count > len(self.data):
            raise ValueError(f"{self.label}: truncated/invalid read at {self.pos}, size {count}")
        value = self.data[self.pos:self.pos + count]
        self.pos += count
        return value

    def number(self, fmt):
        return struct.unpack(">" + fmt, self.take(struct.calcsize(fmt)))[0]

    def u8(self): return self.number("B")
    def i8(self): return self.number("b")
    def i16(self): return self.number("h")
    def i32(self): return self.number("i")
    def text(self, length): return self.take(length * 2).decode("utf-16-be")
    def end(self):
        if self.pos != len(self.data):
            raise ValueError(f"{self.label}: {len(self.data)-self.pos} unexpected trailing bytes")


def read(path):
    return Reader(path.read_bytes(), str(path.relative_to(SOURCE)))


def short_rows(r):
    return [[r.i16() for _ in range(r.i16())] for _ in range(r.i16())]


def string_rows(r):
    rows = []
    for _ in range(r.i16()):
        row = []
        for _ in range(r.i16()):
            length = r.u8()
            row.append(r.text(r.i16() if length == 255 else length))
        rows.append(row)
    return rows


def chunk(kind, data):
    return struct.pack(">I", len(data)) + kind + data + struct.pack(">I", zlib.crc32(kind + data))


def texture_png(data):
    if data.startswith(PNG):
        return data
    r = Reader(data, "packed texture")
    width, height, depth, color = r.i32(), r.i32(), r.u8(), r.u8()
    if not (0 < width < 8192 and 0 < height < 8192 and color in (3, 6)):
        raise ValueError("Unsupported packed texture header")
    result = PNG + chunk(b"IHDR", struct.pack(">IIBBBBB", width, height, depth, color, 0, 0, 0))
    if color == 3:
        result += chunk(b"PLTE", r.take(r.i32()))
        size = r.i32()
        if size == 0x74524E53:  # tRNS: palette size, transparent palette entry
            count = r.u8() or 256
            transparent = r.u8()
            result += chunk(b"tRNS", bytes(0 if i == transparent else 255 for i in range(count)))
            size = r.i32()
    else:
        size = r.i32()
    result += chunk(b"IDAT", r.take(size)) + chunk(b"IEND", b"")
    r.end()
    return result


def sprite_data(r, sprite_id):
    def flat():
        rows, columns = r.i16(), r.i16()
        return [r.i16() for _ in range(rows * columns)]

    def rows():
        count, columns = r.i16(), r.i16()
        return [[r.i16() for _ in range(r.i16() * columns)] for _ in range(count)]

    modules, frames, animations, collision, attack = flat(), rows(), rows(), flat(), flat()
    r.end()
    if 86 <= sprite_id <= 185:
        frames = [[frames[0][0], frames[0][1] + shift, *frames[0][2:4]] for shift in [0, 10, 3, 7, -10]]
        animations = [[2, 0], [1, 0, 1, 1, 1, 2, 1, 3, 1, 2], [5, 0, 5, 4]]
    return dict(modules=[modules[i:i+5] for i in range(0, len(modules), 5)],
                frames=[ [f[i:i+4] for i in range(0, len(f), 4)] for f in frames],
                animations=animations,
                collision=[collision[i:i+5] for i in range(0, len(collision), 5)],
                attack=[attack[i:i+5] for i in range(0, len(attack), 5)])


def map_data(r):
    version, tileset = r.i8(), r.i8()
    coord = r.i8 if version == 1 else r.i16
    width, height, tile_size = coord(), coord(), r.i8()
    layers = []
    for _ in range(r.i8()):
        index, kind, count = r.i8(), r.i8(), r.i16()
        cells = [[coord(), coord(), r.i16()] for _ in range(count)]
        layers.append(dict(index=index, kind=kind, cells=cells))
    trailing_bytes = r.take(len(r.data) - r.pos).hex()  # j.a(int) closes here; preserve unused tail.
    return dict(version=version, tileset=tileset, width=width, height=height, trailing_bytes=trailing_bytes,
                tile_size=tile_size, layers=layers)


def room_data(r):
    strings = [r.text(r.i16()) for _ in range(r.i16())]
    name = r.text(r.u8())
    map_id, unknown = r.i16(), r.i16()
    actors = []
    for index in range(r.i16()):
        count = r.i16()
        a = [r.i8(), r.i16(), r.i16(), r.i16(), r.i16(), r.i8(), r.i8()]
        if a[0] == 0:
            a += [r.i8(), r.i8(), r.i8(), r.i8(), r.i16(), r.i16()]
        elif a[0] == 1:
            a += [r.i8(), r.i16(), r.i16(), r.i16()]
        elif a[0] == 2:
            a += [r.i16()]
            if a[7] == 1:
                a += [r.i8() for _ in range(5)]
        elif a[0] == 3:
            a += [r.i8(), r.i8(), r.i8(), r.i16(), r.i16()]
        else:
            raise ValueError(f"Unknown actor kind {a[0]} at {r.pos}")
        actors.append(dict(index=index, fields=a, declared_fields=count))
    names = [r.text(r.u8()) for _ in range(r.i16())] if actors else []
    events = []
    for index in range(r.i16()):
        commands = []
        for _ in range(r.i16()):
            offset = r.pos
            opcode, total, numbers = r.i16(), r.u8(), r.u8()
            args = [r.i16() for _ in range(numbers)]
            text_ids = [r.i16() for _ in range(total - numbers)]
            commands.append(dict(opcode=opcode, args=args, text_ids=text_ids,
                                 texts=[strings[i] for i in text_ids], offset=offset))
        events.append(dict(id=index, commands=commands))
    r.end()
    return dict(name=name, map_id=map_id, unknown=unknown, actors=actors,
                actor_names=names, strings=strings, events=events)


def scene_data(r):
    sizes = [r.i16() for _ in range(r.i16())]
    rooms = [room_data(Reader(r.take(size), f"{r.label}:room {i}")) for i, size in enumerate(sizes)]
    r.end()
    return rooms


def dump(path, value):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(value, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


def extract(destination):
    from PIL import Image
    destination.mkdir(parents=True, exist_ok=True)
    manifest = {}
    for path in sorted(SOURCE.rglob("*")):
        if not path.is_file(): continue
        relative = path.relative_to(SOURCE).as_posix()
        manifest[relative] = hashlib.sha256(path.read_bytes()).hexdigest()
        if path.parent.name in ("img", "tex", "logo") or path.name == "icon.png":
            content = path.read_bytes()
            target = destination / "images" / Path(relative).with_suffix(".png")
            target.parent.mkdir(parents=True, exist_ok=True)
            if content.startswith(b"\xff\xd8"):
                Image.open(io.BytesIO(content)).save(target)
            else:
                target.write_bytes(texture_png(content))
            with Image.open(target) as img: img.load()
        elif path.parent.name == "spr":
            sprite_id = int(path.name.split("_")[1])
            dump(destination / f"sprites/{sprite_id}.json", sprite_data(read(path), sprite_id))
        elif path.parent.name == "map":
            dump(destination / f"maps/{path.stem}.json", map_data(read(path)))
        elif path.parent.name == "event" and path.suffix == ".mid":
            dump(destination / f"events/{path.stem}.json", scene_data(read(path)))
    for name in ("chs", "npcDialog", "mTask", "bTask"):
        r = read(SOURCE / f"data/script/{name}.mid")
        dump(destination / f"tables/{name}.json", string_rows(r)); r.end()
    r = read(SOURCE / "data/script/sprite.mid")
    dump(destination / "tables/sprites.json", short_rows(r)); r.end()
    r = read(SOURCE / "data/script/db.mid")
    dump(destination / "tables/database.json", [short_rows(r) for _ in range(9)]); r.end()
    r = read(SOURCE / "data/mod/modInfo.mid")
    dump(destination / "tables/tileset_images.json", [[r.i16() for _ in range(r.u8())] for _ in range(r.u8())])
    if r.take(len(r.data) - r.pos) not in (b"", b"\xff"):
        raise ValueError("Unexpected modInfo padding")
    for path in sorted((SOURCE / "data/mod").glob("mod_*.mid")):
        r = read(path)
        dump(destination / f"tilesets/{path.stem}.json", [[r.i8(), r.i16(), r.i16(), r.i16(), r.i16()] for _ in range(r.i16())]); r.end()
    dump(destination / "source_manifest.json", manifest)
    print(f"Recovered assets: {destination}")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--output", type=Path, default=ROOT / "build/recovered")
    extract(parser.parse_args().output)
