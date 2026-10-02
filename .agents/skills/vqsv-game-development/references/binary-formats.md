# Binary Formats and Asset Tools

Contents: inspection tools; scalar/table conventions; sprites/images; room/event
and map data; UI layout; a staged database writer. Baseline: 2026-10-02.
All project paths are relative to the current checkout. Revalidate the loader
before treating any saved format description as current behavior.

## Available tools and boundaries

`tools/recover_assets.py` decodes supported assets to PNG/JSON and a source hash
manifest. It provides strict Reader helpers and several end-of-input checks.
Image extraction uses Pillow. If the project already has `build/asset-venv`:

```bash
vqsv_asset_preview=$(mktemp -d)
build/asset-venv/bin/python -B tools/recover_assets.py --output "$vqsv_asset_preview"
```

If absent and needed, create that environment and install Pillow. `build/` is
ignored and may be missing in a fresh checkout. Simple binary inspection imports
the reader functions using `python3 -B` without Pillow.

Outputs include `images/`, `sprites/`, `tables/database.json`,
`tables/sprites.json`, `tables/chs.json`, maps, tilesets, events, and source hashes.
The tool has no writer/import path and no general UI decoder. It does not have
dedicated extraction branches for every script table, including `petArea`,
`petRide`, `bqTask` and some effects. `scene_13.mib` is not processed by the `.mid`
scene extraction branch. JSON changes in `build/recovered` do not affect builds.

`tools/ReferenceProbe.java` uses original bytecode as an oracle for stats/damage
and sprite rendering. It is not a mod spawn tool or complete gameplay suite.
Refactor/rename scripts mutate reference code; they do not reliably reconstruct
compilable runtime replacements.

## Scalars and table encodings

Multi-byte numbers are big-endian. Java short/int are signed; bytes remain signed
unless a particular loader masks or treats them as unsigned. Preserve -1 sentinels
and use the loader's length units. The `.mid` suffix does not distinguish an image,
table, event, or actual MIDI stream.

| Structure | Encoding | Evidence |
| --- | --- | --- |
| `short[][]` | i16 row count; each row: i16 value count, then i16 values | `ae.a(InputStream)`, Python `short_rows()` |
| `byte[][]` | i16 row count; each row: i16 value count, then i8 values | `ae.b(InputStream)` |
| `String[][]` | i16 row count; each row: i16 string count; each string: u8 length, or 255 followed by i16 length, then UTF-16BE units | `ae.c(InputStream)`, `string_rows()` |
| `db.mid` | Nine consecutive `short[][]` tables; no leading table-count header | `aq` |
| `sprite.mid` | One `short[][]`; each row is animation ID followed by image IDs | `d` |
| `bqTask.mid` | Two consecutive `byte[][]` tables | OverworldScreen constructor |

Count UTF-16 code units, not Unicode code points or UTF-8 bytes. The text loader
joins fragments in a `chs.mid` row to form one displayed string. Keep row and
fragment structure unless the intended edit changes it.

| Database table | Baseline rows | Main role |
| --- | ---: | --- |
| 0 | 100, each 23 values | Species; see `pets.md` for all columns |
| 1 | 70, each 10 values | Skills; name at column 1 |
| 2 | 8 | Badges and effects |
| 3 | 18 | Equipment/attribute-related item group |
| 4 | 15, varying lengths | Consumables, capture balls, medicine |
| 5 | 11 | Special/quest items |
| 6 | 15 | Buff states |
| 7 | 11 | Debuff states |
| 8 | 4, each 5 values | Learnability thresholds/groups |

This is a role map, not a complete schema for every non-species field. Do not
assume every table's column 0 is the name string.

## Sprite metadata and images

`spr_N_all(r)` has five blocks: modules, frames, animations, collision, attack.
Modules/collision/attack are flat blocks: i16 rows, i16 columns, then rows*columns
i16 values. Frames/animations are row blocks: i16 row count and column width,
then each row's i16 group count and groupCount*columnWidth i16 values.

- Module width 5: image-list index, source x, source y, width, height.
- Frame groups width 4: module index, offset x, offset y, transform.
- Ordinary renderer mode `false`: animation duration/frame pairs. Mode `true`:
  groups of four; follow the specific caller before editing those values.
- Collision/attack groups width 5 start with a frame index and region data. The
  cache reshapes them into per-frame rows.

Python `sprite_data(reader, animationId)` applies the 86–185 synthesis rule.
Exported frames are effective runtime data, not a lossless raw copy. For a
round-trip writer, retain all original blocks before that transformation.
Sprite metadata and UI parsers allocate 20000-byte buffers.

`img_*.mid` may contain ordinary image bytes; `img_540.mid` is PNG. Other textures
use a packed width/height/depth/color/palette/IDAT structure reconstructed into
PNG by EngineUtils. `texture_png()` decodes it but does not encode the reverse
format. Preserve requested filename/extension and loader expectations.

## Scenes, rooms, and event commands

`scene_*.mid`: i16 room count, then i16 byte size for each room, then room payloads.
A room includes a string pool, name, map ID, an unresolved field, kind-dependent
actors, actor names, and events. Recompute byte sizes and counts after changing
text/actors/commands. The authoritative field order is WorldManager's loader,
mirrored in `recover_assets.room_data()`; do not use one actor kind's payload for
another kind.

Each event begins with i16 command count. A ScriptCommand is:

```text
i16 opcode
i8  total parameter count
i8  numeric parameter count
i16 numeric parameters
i16 string-pool indices (total minus numeric count)
```

Python represents counts as unsigned in this reader, but Java reads signed byte.
Do not infer that count 255 is supported. ScriptSequence casts its i16 command
count to byte and keeps a byte program counter. Opcode meanings, waiting, and
completion come from the interpreter, not from the binary shape.

## Maps and tilesets

Map header: i8 version, i8 tileset ID, width/height, i8 tile size, i8 layer count.
When version=1, dimensions and cell coordinates are i8; otherwise they are i16.
Each layer has i8 index, i8 kind, i16 cell count, then x/y coordinates and i16
tile indices. Preserve trailing bytes; the exporter labels them `trailing_bytes`
without assigning unverified semantics.

Tileset-image mapping is in `modInfo.mid`; individual `mod_*.mid` module data and
their image references must remain consistent with map layer/tile references.
Changing a map does not create a scene/room transition.

## UI layout encoding

`ao.a(String,int)` reads two initial shorts, root type byte, root ID and four
short geometry values, then calls a recursive parser. Payload includes selection/
navigation groups, child counts, and distinct structures for types 0/1/2.
Type-1 text has an i16 byte length followed by UTF-16BE bytes, unlike the
UTF-16-unit counts in `chs.mid`; it also has i32 color and sprite/frame styling.

A UI writer must cover every branch of
`ao.a(byte[],int[],al,int,boolean)`, preserve unexplained fields, and round-trip
unchanged layouts byte-for-byte. There is currently no `pack-ui` command. For
simple text changes, an existing-widget runtime update may satisfy the request
without changing layout topology; see `ui-and-systems.md`.

## Inspection example

Run from the checkout with `python3 -B`. It reads files without modifying them.

```python
from tools.recover_assets import Reader, SOURCE, short_rows, string_rows, sprite_data

def reader(path):
    return Reader((SOURCE / path).read_bytes(), path)

r = reader("data/script/db.mid")
database = [short_rows(r) for _ in range(9)]
r.end()
r = reader("data/script/chs.mid")
text = string_rows(r)
r.end()
r = reader("data/script/sprite.mid")
sprites = short_rows(r)
r.end()
row = database[0][68]
sprite_id = row[17]
animation_id, *images = sprites[sprite_id]
animation = sprite_data(reader(f"data/spr/spr_{animation_id}_all(r)"), animation_id)
print("".join(text[row[0]]), sprite_id, animation_id, images)
print(animation["frames"], animation["animations"])
```

## Staged database writer example

This example reproduces the original file first, changes one known field, then
only writes a candidate to a temporary directory. It is not an editor for other
formats or a semantic ID validator. Move an inspected candidate to the source
resource only when implementing the requested data change.

```python
from pathlib import Path
from tempfile import mkdtemp
import struct
from tools.recover_assets import Reader, SOURCE, short_rows

source = (SOURCE / "data/script/db.mid").read_bytes()
r = Reader(source)
tables = [short_rows(r) for _ in range(9)]
r.end()

def encode(database):
    output = bytearray()
    def put(value):
        output.extend(struct.pack(">h", value))
    for table in database:
        put(len(table))
        for row in table:
            put(len(row))
            for value in row:
                put(value)
    return bytes(output)

assert encode(tables) == source
assert tables[0][68][8] == 4  # Fixture precondition; recheck for a modified checkout.
tables[0][68][8] = 5
candidate = encode(tables)
check = Reader(candidate)
assert [short_rows(check) for _ in range(9)] == tables
check.end()
destination = Path(mkdtemp(prefix="vqsv-db-example-")) / "db.mid"
destination.write_bytes(candidate)
print(destination)
```

Both Python examples ran against the surveyed resources; unchanged database
round-trip matched original bytes. Asset extraction also succeeded. A new writer
or fixture still needs validation for the current checkout.
