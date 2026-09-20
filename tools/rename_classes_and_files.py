#!/usr/bin/env python3
"""
Automated Class and File Renaming Engine for Decompiled Reference Codebase.
Renames all 68 Java classes and their respective .java files to meaningful,
human-readable PascalCase names and updates all cross-references across the codebase.
"""

import os
import re
import glob
import shutil
import subprocess
from pathlib import Path
from typing import Dict, Tuple, Set, List

ROOT = Path(__file__).resolve().parent.parent
TARGET_DIR = ROOT / 'reference/decompiled'

# Canonical mapping for all 68 classes: (package, old_name) -> new_name
CLASS_RENAME_MAP: Dict[Tuple[str, str], str] = {
    # Package game (12 classes)
    ('game', 'GameMIDLet'): 'GameMIDLet',
    ('game', 'a'): 'NpcEntity',
    ('game', 'b'): 'Pet',
    ('game', 'c'): 'OverworldScreen',
    ('game', 'd'): 'BattleScreen',
    ('game', 'e'): 'GameCanvas',
    ('game', 'f'): 'TitleScreen',
    ('game', 'g'): 'Player',
    ('game', 'h'): 'ScriptEngine',
    ('game', 'i'): 'GameStateController',
    ('game', 'j'): 'TileMapRenderer',
    ('game', 'k'): 'WorldManager',

    # Package a (10 classes)
    ('a', 'a'): 'BillingClient',
    ('a', 'b'): 'BillingListener',
    ('a', 'c'): 'NetworkConnection',
    ('a', 'd'): 'NetworkConfig',
    ('a', 'e'): 'BillingException',
    ('a', 'f'): 'TransactionRecord',
    ('a', 'g'): 'ProtocolEncoder',
    ('a', 'h'): 'SecurityHelper',
    ('a', 'i'): 'SessionData',
    ('a', 'j'): 'PropertyRef',

    # Package lavax.wireless.messaging (1 class)
    ('lavax.wireless.messaging', 'MessageConnection'): 'MessageConnection',

    # Default package (45 classes)
    ('', 'a'): 'UIHotspot',
    ('', 'b'): 'ScreenView',
    ('', 'c'): 'Renderable',
    ('', 'd'): 'SpriteRenderer',
    ('', 'e'): 'PixelBuffer',
    ('', 'f'): 'WorldEntity',
    ('', 'g'): 'FormattedTextBuffer',
    ('', 'h'): 'DebugLogger',
    ('', 'i'): 'ScriptEventListener',
    ('', 'j'): 'MapEngine',
    ('', 'k'): 'UIButton',
    ('', 'l'): 'ImageTransformer',
    ('', 'm'): 'SpriteWidget',
    ('', 'n'): 'BaseEntity',
    ('', 'o'): 'SpriteData',
    ('', 'p'): 'ScriptSequence',
    ('', 'q'): 'BillingCanvas',
    ('', 'r'): 'BillingCallback',
    ('', 's'): 'TextLayoutHelper',
    ('', 't'): 'StringTable',
    ('', 'u'): 'SmsConfig',
    ('', 'v'): 'CarrierHelper',
    ('', 'w'): 'UIComponent',
    ('', 'x'): 'TimerCallback',
    ('', 'y'): 'TextPainter',
    ('', 'z'): 'NumericInputWidget',
    ('', 'aa'): 'AnimationCache',
    ('', 'ab'): 'UIManager',
    ('', 'ac'): 'MessageBox',
    ('', 'ad'): 'ScriptCommand',
    ('', 'ae'): 'EngineUtils',
    ('', 'af'): 'ItemListWidget',
    ('', 'ag'): 'SmsSender',
    ('', 'ah'): 'SkillEffect',
    ('', 'ai'): 'ParticleEffect',
    ('', 'aj'): 'ResourceStream',
    ('', 'ak'): 'Rectangle',
    ('', 'al'): 'MenuWidget',
    ('', 'am'): 'ImageCache',
    ('', 'an'): 'BaseScreen',
    ('', 'ao'): 'TextRenderer',
    ('', 'ap'): 'BaseInputHandler',
    ('', 'aq'): 'GameDatabase',
    ('', 'ar'): 'SaveStorage',
    ('', 'as'): 'CanvasRepaintTimerTask',
}

# Accurate architectural descriptions for class Javadocs
CLASS_DESCRIPTIONS: Dict[Tuple[str, str], str] = {
    ('game', 'GameMIDLet'): 'Main J2ME MIDlet entry point; initializes Display and manages application lifecycle.',
    ('game', 'a'): 'NpcEntity - Non-player character and interactive map entity extending WorldEntity.',
    ('game', 'b'): 'Pet - Monster entity managing base/current stats, leveling, evolution, exp, skills, and battle calculations.',
    ('game', 'c'): 'OverworldScreen - Overworld map gameplay screen managing map rendering, player movement, and script triggers.',
    ('game', 'd'): 'BattleScreen - Turn-based combat engine handling turns, attack animations, damage resolution, and pet capture.',
    ('game', 'e'): 'GameCanvas - Primary LCDUI Canvas, user input dispatcher, and main animation/game loop thread.',
    ('game', 'f'): 'TitleScreen - Start menu, save/load slot management, options, and new game initialization.',
    ('game', 'g'): 'Player - Player entity state, bag inventory, pet party, gold/currency, badges, and world coordinates.',
    ('game', 'h'): 'ScriptEngine - Story event interpreter, quest script execution, NPC dialogue, and shop logic.',
    ('game', 'i'): 'GameStateController - Central state machine coordinating transitions between gameplay, battle, title, and menus.',
    ('game', 'j'): 'TileMapRenderer - Map tile grid decoder, layer compositor, and camera scrolling engine.',
    ('game', 'k'): 'WorldManager - Map/scene navigator, room transitions, actor spawning, and script event executor.',

    ('a', 'a'): 'BillingClient - Network client managing billing sessions and protocol requests.',
    ('a', 'b'): 'BillingListener - Interface for network billing transaction callbacks.',
    ('a', 'c'): 'NetworkConnection - Low-level HTTP socket connection handler for SMS/billing gateway.',
    ('a', 'd'): 'NetworkConfig - Telecom carrier endpoints, APN configuration, and gateway URLs.',
    ('a', 'e'): 'BillingException - Runtime exception indicating middleware/billing network protocol failures.',
    ('a', 'f'): 'TransactionRecord - Encrypted transaction log and purchase receipt record.',
    ('a', 'g'): 'ProtocolEncoder - Binary payload encoder and cryptographic packet serializer.',
    ('a', 'h'): 'SecurityHelper - Bytecode obfuscation and packet encryption helper utilities.',
    ('a', 'i'): 'SessionData - Runtime session context storage for billing requests.',
    ('a', 'j'): 'PropertyRef - Dynamic property reference holder with reflection-like accessors.',

    ('lavax.wireless.messaging', 'MessageConnection'): 'MessageConnection - Simulated J2ME wireless messaging interface for offline execution.',

    ('', 'a'): 'UIHotspot - Interactive rectangular region and button hotspot with optional sprite icon.',
    ('', 'b'): 'ScreenView - Screen overlay presenter, battle animations, camera shake, and dialog UI.',
    ('', 'c'): 'Renderable - Interface for drawable game components requiring Graphics paint callbacks.',
    ('', 'd'): 'SpriteRenderer - Animated sprite renderer; draws frame clips and animation sequences.',
    ('', 'e'): 'PixelBuffer - Low-level ARGB pixel array buffer with width, height, and cloning support.',
    ('', 'f'): 'WorldEntity - World map actor entity extending BaseEntity; handles facing direction, walking speed, and pathing.',
    ('', 'g'): 'FormattedTextBuffer - Text layout buffer storing parsed text lines, colors, and line coordinates.',
    ('', 'h'): 'DebugLogger - Static exception and diagnostic message logger.',
    ('', 'i'): 'ScriptEventListener - Callback interface for receiving script and dialogue event notifications.',
    ('', 'j'): 'MapEngine - Tile map data loader, layer compositor, and viewport clipping manager.',
    ('', 'k'): 'UIButton - Interactive UI button widget with border, fill color, text label, and icon.',
    ('', 'l'): 'ImageTransformer - Pixel-level image scaling, color transformations, and sprite rasterizer.',
    ('', 'm'): 'SpriteWidget - Sprite frame presenter and animated icon wrapper for UI layouts.',
    ('', 'n'): 'BaseEntity - Abstract movable entity with position coordinates, bounding box, and velocity.',
    ('', 'o'): 'SpriteData - Sprite frame dimensions, bounding box data, and action frame indices.',
    ('', 'p'): 'ScriptSequence - Ordered sequence of script commands executed by the game engine.',
    ('', 'q'): 'BillingCanvas - J2ME SMS payment screen, purchase confirmation UI, and countdown timer.',
    ('', 'r'): 'BillingCallback - Transaction callback bridge connecting payment UI with network client.',
    ('', 's'): 'TextLayoutHelper - Text measurement, line wrapping, string slicing, and font metric utilities.',
    ('', 't'): 'StringTable - Game string indexer and localization text lookup table.',
    ('', 'u'): 'SmsConfig - Billing configuration parser, SMS shortcode table, and carrier fee mapping.',
    ('', 'v'): 'CarrierHelper - Mobile telecom carrier detector (Viettel, Vinaphone, Mobifone).',
    ('', 'w'): 'UIComponent - Interface for interactive UI widgets supporting key handling and rendering.',
    ('', 'x'): 'TimerCallback - Interface for periodic timer tick listeners.',
    ('', 'y'): 'TextPainter - Text drawing helper with color formatting and clipping delegation.',
    ('', 'z'): 'NumericInputWidget - Numeric quantity picker and amount selector widget.',
    ('', 'aa'): 'AnimationCache - Cache and resource loader for sprite animations and sound data.',
    ('', 'ab'): 'UIManager - UI dialog window manager, layout loader, and focus controller.',
    ('', 'ac'): 'MessageBox - Modal message dialog box and user confirmation popup.',
    ('', 'ad'): 'ScriptCommand - Single script opcode, numeric parameters, and text argument holder.',
    ('', 'ae'): 'EngineUtils - Core utility functions for RNG, resource streams, trigonometric tables, and array math.',
    ('', 'af'): 'ItemListWidget - Scrollable item list selector widget for inventory and shop menus.',
    ('', 'ag'): 'SmsSender - Asynchronous background SMS dispatching thread for telecom billing.',
    ('', 'ah'): 'SkillEffect - Battle skill projectile, visual effect particle, and animated spell renderer.',
    ('', 'ai'): 'ParticleEffect - Visual particle effect manager for explosions, sparks, and status auras.',
    ('', 'aj'): 'ResourceStream - Custom DataInputStream wrapper with endian conversions and memory pooling.',
    ('', 'ak'): 'Rectangle - 2D bounding box with x, y coordinates and dimensions.',
    ('', 'al'): 'MenuWidget - Context menu and option selection widget with cursor navigation.',
    ('', 'am'): 'ImageCache - Resource manager for loaded image tilesets, icons, and background images.',
    ('', 'an'): 'BaseScreen - Abstract game screen/canvas base class extending BaseInputHandler; manages screen resolution, timer, and fonts.',
    ('', 'ao'): 'TextRenderer - Font metrics, multiline text formatting, dialog text box renderer, and RMS backup helpers.',
    ('', 'ap'): 'BaseInputHandler - Low-level key press/release bitmask manager and key event dispatcher.',
    ('', 'aq'): 'GameDatabase - Global database tables for pet stats, growth curves, item definitions, and skill costs.',
    ('', 'ar'): 'SaveStorage - Persistent RecordStore (RMS) storage manager for player save files and settings.',
    ('', 'as'): 'CanvasRepaintTimerTask - Periodic TimerTask scheduling canvas repaints.',
}


def get_file_package(code: str) -> str:
    m = re.search(r'^\s*package\s+([\w\.]+);', code, re.MULTILINE)
    return m.group(1) if m else ''


def collect_static_members() -> Dict[Tuple[str, str], Set[str]]:
    """Scan all files to find exact static members of each class."""
    statics: Dict[Tuple[str, str], Set[str]] = {}
    for fpath in glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True):
        with open(fpath, 'r', encoding='utf-8') as f:
            c = f.read()
        pkg = get_file_package(c)
        stem = Path(fpath).stem
        members = set()
        for m in re.finditer(r'static\s+(?:final\s+)?(?:[\w\[\]]+\s+)+([a-zA-Z0-9_]+)\s*(?:=|\(|;)', c):
            members.add(m.group(1))
        statics[(pkg, stem)] = members
    return statics


def get_visible_candidates(pkg: str, stem: str) -> List[Tuple[str, str, str]]:
    """Returns (old_cls, new_cls, original_pkg) ordered by length descending."""
    candidates = []
    if pkg == 'game':
        # 1. Own package classes first!
        for (p, old), new in CLASS_RENAME_MAP.items():
            if p == 'game':
                if old == 'f':
                    if stem == 'f':
                        candidates.append(('f', 'TitleScreen', 'game'))
                else:
                    candidates.append((old, new, 'game'))
        # 2. Default package classes (only if not shadowed by game package!)
        game_old_names = {old for (p, old) in CLASS_RENAME_MAP if p == 'game'}
        for (p, old), new in CLASS_RENAME_MAP.items():
            if p == '':
                if old == 'f' and stem != 'f':
                    candidates.append(('f', 'WorldEntity', ''))
                elif old not in game_old_names:
                    candidates.append((old, new, ''))
    elif pkg == 'a':
        # 1. Own package classes first
        for (p, old), new in CLASS_RENAME_MAP.items():
            if p == 'a':
                candidates.append((old, new, 'a'))
        # 2. Default package classes not shadowed by package a
        a_old_names = {old for (p, old) in CLASS_RENAME_MAP if p == 'a'}
        for (p, old), new in CLASS_RENAME_MAP.items():
            if p == '' and old not in a_old_names:
                candidates.append((old, new, ''))
    else:
        # Default package
        for (p, old), new in CLASS_RENAME_MAP.items():
            if p == '':
                candidates.append((old, new, ''))

    # Sort descending by length of old_name to avoid substring collisions
    candidates.sort(key=lambda x: len(x[0]), reverse=True)
    return candidates


def update_javadoc(code: str, pkg: str, stem: str) -> str:
    """Ensures class header Javadoc matches the accurate class description."""
    desc = CLASS_DESCRIPTIONS.get((pkg, stem))
    if not desc:
        return code

    javadoc_block = f"/**\n * {desc}\n */\n"
    # Replace existing Javadoc right before class/interface declaration
    decl_pattern = re.compile(
        r'(?:/\*\*.*?\*/\s*)?((?:public\s+|abstract\s+|final\s+)*(?:class|interface)\s+\w+)',
        re.DOTALL
    )
    m = decl_pattern.search(code)
    if m:
        # Check if Javadoc is already present
        prefix = code[:m.start()]
        decl = m.group(1)
        suffix = code[m.end():]
        return prefix + javadoc_block + decl + suffix
    return code


def refactor_code(code: str, fpath: str, statics: Dict[Tuple[str, str], Set[str]]) -> str:
    pkg = get_file_package(code)
    stem = Path(fpath).stem
    new_code = code

    # 1. Update Javadoc header
    new_code = update_javadoc(new_code, pkg, stem)

    # 2. Update imports
    for (p, old_cls), new_cls in CLASS_RENAME_MAP.items():
        if p and old_cls != new_cls:
            new_code = re.sub(
                r'\bimport\s+' + re.escape(p) + r'\.' + re.escape(old_cls) + r'\s*;',
                f'import {p}.{new_cls};',
                new_code
            )

    # 3. Update fully-qualified references: game.old_cls, a.old_cls
    for (p, old_cls), new_cls in CLASS_RENAME_MAP.items():
        if p and old_cls != new_cls:
            new_code = re.sub(
                r'(?<![.\w])' + re.escape(p) + r'\.' + re.escape(old_cls) + r'\b',
                f'{p}.{new_cls}',
                new_code
            )

    # 4. Class declaration & constructors of current file
    file_new_cls = CLASS_RENAME_MAP.get((pkg, stem))
    if file_new_cls and stem != file_new_cls:
        # public/abstract/final class/interface stem
        new_code = re.sub(
            r'(\b(?:class|interface)\s+)' + re.escape(stem) + r'\b',
            r'\1' + file_new_cls,
            new_code
        )
        # public/protected/private stem(
        new_code = re.sub(
            r'(\b(?:public|protected|private)\s+)' + re.escape(stem) + r'(\s*\()',
            r'\1' + file_new_cls + r'\2',
            new_code
        )
        # Package-private constructor: ^    stem(
        new_code = re.sub(
            r'(^\s*)' + re.escape(stem) + r'(\s*\([^)]*\)\s*\{)',
            r'\1' + file_new_cls + r'\2',
            new_code,
            flags=re.MULTILINE
        )

    # Get candidates for this file
    candidates = get_visible_candidates(pkg, stem)

    # 5. Extends & Implements
    # 5.1 Extends clause
    for old_cls, new_cls, p in candidates:
        new_code = re.sub(
            r'(\bextends\s+)' + re.escape(old_cls) + r'\b',
            r'\1' + new_cls,
            new_code
        )

    # 5.2 Implements clause
    def replace_implements(m):
        header = m.group(1) # 'implements '
        body = m.group(2)
        for old_cls, new_cls, p in candidates:
            body = re.sub(r'\b' + re.escape(old_cls) + r'\b', new_cls, body)
        return header + body

    new_code = re.sub(r'(\bimplements\s+)([^\{]+)', replace_implements, new_code)

    # Special case: Pet.java line 105 has "this.a = new d();" which is SpriteRenderer
    if pkg == 'game' and stem == 'b':
        new_code = re.sub(r'(\bthis\.a\s*=\s*new\s+)d(\s*\(\s*\))', r'\1SpriteRenderer\2', new_code)

    # 6. Instantiations: new old_cls( and new old_cls[
    for old_cls, new_cls, p in candidates:
        new_code = re.sub(
            r'\bnew\s+' + re.escape(old_cls) + r'(\s*[\(\[])',
            r'new ' + new_cls + r'\1',
            new_code
        )

    # 7. Type casts: STRICT regex
    # Must NOT be preceded by an identifier: (?<![a-zA-Z0-9_\$])
    # Must be followed by an expression: (?=\s*(?:[a-zA-Z0-9_\(\"]|this\b|super\b))
    for old_cls, new_cls, p in candidates:
        cast_re = re.compile(
            r'(?<![a-zA-Z0-9_\$])\(\s*' + re.escape(old_cls) + r'\s*\)(?=\s*(?:[a-zA-Z0-9_\(\"]|this\b|super\b))'
        )
        new_code = cast_re.sub(f'({new_cls})', new_code)

    # 8. Instanceof: instanceof old_cls
    for old_cls, new_cls, p in candidates:
        new_code = re.sub(
            r'\binstanceof\s+' + re.escape(old_cls) + r'\b',
            f'instanceof {new_cls}',
            new_code
        )

    # 9. Array types: old_cls[] or old_cls[][] (empty brackets only!)
    for old_cls, new_cls, p in candidates:
        new_code = re.sub(
            r'(?<![.\w])' + re.escape(old_cls) + r'(\s*\[\s*\]+)',
            new_cls + r'\1',
            new_code
        )

    # 10. Class literal: old_cls.class
    for old_cls, new_cls, p in candidates:
        new_code = re.sub(
            r'(?<![.\w])' + re.escape(old_cls) + r'\.class\b',
            f'{new_cls}.class',
            new_code
        )

    # 11. Static calls on known static members
    # Also handle ScreenView.getInstance() from package game when called as b.getInstance()
    if pkg == 'game':
        new_code = re.sub(r'(?<![.\w])b\.getInstance\(\)', 'ScreenView.getInstance()', new_code)
        new_code = re.sub(r'(?<![.\w])j\.getInstance\(\)', 'MapEngine.getInstance()', new_code)

    for old_cls, new_cls, p in candidates:
        member_set = statics.get((p, old_cls), set())
        for member in member_set:
            new_code = re.sub(
                r'(?<![.\w])' + re.escape(old_cls) + r'\.' + re.escape(member) + r'\b',
                f'{new_cls}.{member}',
                new_code
            )

    # 12. Type declarations (Field, method return type, parameter, local variable)
    for old_cls, new_cls, p in candidates:
        type_pattern = re.compile(
            r'(?P<prefix>(?:public|protected|private|static|final|abstract|synchronized)\s+|[\(\,\{\;]\s*|^\s*)'
            + re.escape(old_cls)
            + r'(?P<suffix>\s+[a-zA-Z_][a-zA-Z0-9_]*\s*[;=,:\(\)])',
            re.MULTILINE
        )
        new_code = type_pattern.sub(r'\g<prefix>' + new_cls + r'\g<suffix>', new_code)

    return new_code


def execute_renaming():
    print("==================================================")
    print("  VQSV CLASS AND FILE RENAMING ENGINE")
    print(f"  Total mapped classes: {len(CLASS_RENAME_MAP)}")
    print("==================================================")

    # 1. Collect exact static members
    statics = collect_static_members()
    print(f"Collected static member indices for {len(statics)} classes.")

    # 2. Read and transform all 68 files in memory
    files = sorted(glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True))
    transformed: Dict[str, str] = {}
    for fpath in files:
        with open(fpath, 'r', encoding='utf-8') as f:
            code = f.read()
        new_code = refactor_code(code, fpath, statics)
        transformed[fpath] = new_code

    # 3. Write transformed code back to original files before renaming
    for fpath, new_code in transformed.items():
        with open(fpath, 'w', encoding='utf-8') as f:
            f.write(new_code)
    print("Successfully updated all class references across all 68 source files.")

    # 4. Rename files using git mv
    renamed_count = 0
    for fpath in files:
        path_obj = Path(fpath)
        old_stem = path_obj.stem
        rel = path_obj.relative_to(TARGET_DIR)
        parts = rel.parts

        pkg = '' if len(parts) == 1 else '.'.join(parts[:-1])
        new_name = CLASS_RENAME_MAP.get((pkg, old_stem))
        if new_name and new_name != old_stem:
            new_path = path_obj.parent / f"{new_name}.java"
            print(f"  [Renaming] {rel.as_posix():35} -> {new_name}.java")
            # Try git mv first, fallback to os.rename
            try:
                subprocess.run(['git', 'mv', str(path_obj), str(new_path)], check=True, cwd=str(ROOT))
            except Exception:
                shutil.move(str(path_obj), str(new_path))
            renamed_count += 1

    print("==================================================")
    print(f"  Successfully renamed {renamed_count} files and classes!")
    print("==================================================")


if __name__ == '__main__':
    execute_renaming()
