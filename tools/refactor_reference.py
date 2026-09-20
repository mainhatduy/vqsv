#!/usr/bin/env python3
"""
Automated Refactoring Suite for Decompiled Reference Java Codebase.
Processes all 68 Java files under reference/decompiled/ using lexical, AST,
and semantic refactoring algorithms to make the codebase clean, legible,
and human-understandable.
"""

import os
import re
import glob
from pathlib import Path
from typing import Dict, List, Tuple, Set, Optional

ROOT = Path(__file__).resolve().parent.parent
TARGET_DIR = ROOT / 'reference/decompiled'

CLASS_DESCRIPTIONS = {
    'game.GameMIDLet': 'Main J2ME MIDlet entry point; initializes Display and bootstraps GameCanvas.',
    'game.e': 'GameCanvas - Primary LCDUI Canvas, user input dispatcher, and main animation/game loop thread.',
    'game.i': 'GameStateController - Central state machine coordinator (Title, World, Battle, Menu, Dialog, etc.).',
    'game.k': 'WorldManager - Map/scene navigator, room transitions, actor spawning, and script event executor.',
    'game.b': 'Pet - Monster entity managing base/current stats, leveling, evolution, exp, skills, and battle calculations.',
    'game.d': 'BattleManager - Turn-based combat engine handling turns, attack animations, damage resolution, and pet capture.',
    'game.c': 'NpcManager - NPC entities, conversation dialogs, quest triggers, and interactable map objects.',
    'game.f': 'TitleScreen - Start menu, save/load slot management, options, and new game initialization.',
    'game.g': 'Player - Player entity state, bag inventory, pet party, gold/currency, badges, and world coordinates.',
    'game.h': 'ScriptEngine - Story event interpreter, quest script executor, cutscenes, and action dispatch table.',
    'game.j': 'TileMapRenderer - Map tile grid decoder, layer compositor, collision checker, and camera viewport.',
    'game.a': 'GameMenu - In-game bag/inventory UI, item shop, pet status sheet, and skill assignment menu.',
    'ap': 'BaseInputHandler - Low-level key press/release bitmasks, touch/pointer coordinates, and keycode mapping.',
    'an': 'BaseScreen - Abstract game screen/canvas base class extending ap; manages screen resolution, timer, and fonts.',
    'ae': 'EngineUtils - Core utility functions for RNG, resource loading, text drawing, and math helpers.',
    'd': 'SpriteRenderer - Animated sprite renderer; draws frame sequences, directions, and transformation matrices.',
    'o': 'SpriteData - Sprite frame dimensions, bounding boxes, animation sequences, and timing definitions.',
    'aa': 'AnimationCache - Cache and resource loader for sprite animation definitions (sprite.mid data).',
    'am': 'ImageCache - Resource manager for loaded image tiles, textures, and spritesheet icons.',
    'ab': 'UIManager - UI dialog window manager, layout loader for .ui binary definitions, and UI layer compositor.',
    'ao': 'TextRenderer - Font metrics, multiline text formatting, dialog text box renderer, and RMS backup helpers.',
    'aj': 'ResourceStream - Custom DataInputStream wrapper with resource pooling, stream auto-closing, and cache.',
    'aq': 'GameDatabase - Global database tables for pet stats, skill tables, evolution chains, and sprite mappings.',
    'ar': 'SaveStorage - Persistent RecordStore (RMS) storage manager for player save files and settings.',
    'as': 'CanvasRepaintTimerTask - Periodic TimerTask scheduling canvas repaints and screen refresh.',
    'ak': 'Rectangle - 2D bounding box with x, y coordinates and width, height dimensions.',
    'g': 'FormattedTextBuffer - Text layout buffer storing parsed text lines, colors, and line coordinates.',
    'i': 'ScriptEventListener - Callback interface for receiving dispatched game script and cutscene events.',
    'n': 'BaseEntity - Abstract movable entity with position coordinates (x, y) and basic state flags.',
    'f': 'WorldEntity - World map actor entity extending BaseEntity; includes direction, sprite, and movement speed.',
    'ah': 'SkillEffect - Battle skill projectile, visual effect entity, animation frames, and target trajectory.',
    'ai': 'ParticleEffect - Visual particle effect manager for hit sparks, dust, water splashes, and combat impacts.',
    'u': 'SmsConfig - Billing configuration parser, SMS shortcode builder, carrier detection, and payment tokens.',
    'q': 'BillingCanvas - J2ME SMS payment screen, purchase confirmation UI, and network transaction status.',
    'r': 'BillingCallback - Transaction callback bridge connecting billing actions to BillingCanvas.',
    'w': 'UIComponent - Interface for interactive UI widgets, handling focus, selection, and rendering.',
    'ac': 'MessageBox - Modal message dialog box and user confirmation popup.',
    'af': 'ItemListWidget - Scrollable item list selector widget for inventory, shops, and menus.',
    'al': 'MenuWidget - Context menu and option selection widget.',
    'z': 'NumericInputWidget - Numeric quantity picker and amount slider widget.',
    's': 'TextLayoutHelper - Text measurement, line wrapping, and font styling helper.',
    't': 'StringTable - Game string indexer and localization text provider.',
    'v': 'CarrierHelper - Mobile telecom carrier detector (Viettel, Mobifone, Vinaphone).',
    'x': 'TimerCallback - Interface for periodic timer tick listeners.',
    'c': 'Renderable - Interface for drawable game components.',
    'a.a': 'BillingClient - Network client managing billing sessions and protocol requests.',
    'a.b': 'BillingListener - Interface for network billing transaction response callbacks.',
    'a.c': 'NetworkConnection - Low-level HTTP socket connection and payload streaming wrapper.',
    'a.d': 'NetworkConfig - Telecom carrier endpoints, APN configuration, and connection timeouts.',
    'a.e': 'BillingException - Runtime exception indicating missing or nil billing transaction state.',
    'a.f': 'TransactionRecord - Encrypted transaction log and billing event descriptor.',
    'a.g': 'ProtocolEncoder - Binary payload encoder and cryptographic signature generator for transactions.',
    'a.h': 'SecurityHelper - Bytecode obfuscation and packet encryption helper.',
    'a.i': 'SessionData - Runtime session context storage for billing parameters.',
    'a.j': 'PropertyRef - Dynamic property reference holder with getter and setter accessors.',
    'lavax.wireless.messaging.MessageConnection': 'MessageConnection - Simulated J2ME wireless messaging connection for SMS dispatch.',
}


def unescape_unicode(content: str) -> Tuple[str, int]:
    """Pass 1: Unescapes unicode sequences \\uXXXX in string and char literals into readable UTF-8."""
    count = 0
    def repl(match):
        nonlocal count
        hex_val = match.group(1)
        code = int(hex_val, 16)
        if code < 32 or code in (0x22, 0x27, 0x5C):
            return match.group(0)
        count += 1
        return chr(code)

    result = re.sub(r'\\u([0-9a-fA-F]{4})', repl, content)
    return result, count


def annotate_keycodes(content: str) -> Tuple[str, int]:
    """Pass 2: Annotates magic J2ME key codes and bitmasks with descriptive constants."""
    count = 0
    key_annotations = [
        (r'case -1\s*:(?!\s*/\*)', r'case -1: /* KEY_UP */'),
        (r'case -2\s*:(?!\s*/\*)', r'case -2: /* KEY_DOWN */'),
        (r'case -3\s*:(?!\s*/\*)', r'case -3: /* KEY_LEFT */'),
        (r'case -4\s*:(?!\s*/\*)', r'case -4: /* KEY_RIGHT */'),
        (r'case -5\s*:(?!\s*/\*)', r'case -5: /* KEY_FIRE / OK */'),
        (r'case -6\s*:(?!\s*/\*)', r'case -6: /* KEY_SOFT_LEFT */'),
        (r'case -7\s*:(?!\s*/\*)', r'case -7: /* KEY_SOFT_RIGHT */'),
        (r'case -21\s*:(?!\s*/\*)', r'case -21: /* KEY_SOFT_LEFT (Moto) */'),
        (r'case -22\s*:(?!\s*/\*)', r'case -22: /* KEY_SOFT_RIGHT (Moto) */'),
        (r'\b4096\b(?!\s*/\*)', r'4096 /* MASK_UP */'),
        (r'\b8192\b(?!\s*/\*)', r'8192 /* MASK_DOWN */'),
        (r'\b16384\b(?!\s*/\*)', r'16384 /* MASK_LEFT */'),
        (r'\b32768\b(?!\s*/\*)', r'32768 /* MASK_RIGHT */'),
        (r'\b65536\b(?!\s*/\*)', r'65536 /* MASK_FIRE */'),
        (r'\b131072\b(?!\s*/\*)', r'131072 /* MASK_SOFT_LEFT */'),
        (r'\b262144\b(?!\s*/\*)', r'262144 /* MASK_SOFT_RIGHT */'),
    ]
    for pattern, replacement in key_annotations:
        matches = len(re.findall(pattern, content))
        if matches > 0:
            content = re.sub(pattern, replacement, content)
            count += matches
    return content, count


def refactor_singletons_and_methods(content: str, rel_path: str) -> Tuple[str, int]:
    """Pass 3: Renames singleton factories and high-value engine functions across files."""
    count = 0
    is_game_pkg = rel_path.startswith('game/')

    replacements = [
        # Singleton calls across codebase
        (r'\bgame\.k\.a\(\)', 'game.k.getInstance()'),
        (r'\bgame\.h\.a\(\)', 'game.h.getInstance()'),
        (r'\bgame\.i\.a\(\)', 'game.i.getInstance()'),
        (r'\bgame\.d\.a\(\)', 'game.d.getInstance()'),
        (r'\bgame\.c\.a\(\)', 'game.c.getInstance()'),
        (r'\bgame\.f\.a\(\)', 'game.f.getInstance()'),
        (r'\bgame\.g\.o\(\)', 'game.g.getInstance()'),
        (r'\bgame\.j\.a\(\)', 'game.j.getInstance()'),
        (r'\bab\.a\(\)', 'ab.getInstance()'),
        (r'\bai\.a\(\)', 'ai.getInstance()'),
        (r'\bb\.a\(\)', 'b.getInstance()'),
        (r'\bj\.a\(\)', 'j.getInstance()'),
        (r'\be\.a\(\)', 'e.getInstance()'),

        # Input handler calls
        (r'\bthis\.a\.P\(\)', 'this.a.resetInputState()'),
        (r'\bthis\.P\(\)', 'this.resetInputState()'),
        (r'\bthis\.a\.i\(', 'this.a.onKeyPressed('),
        (r'\bthis\.a\.j\(', 'this.a.onKeyReleased('),
        (r'\bthis\.a\.d\(', 'this.a.onPointerEvent('),
        (r'\bap\.a\(', 'ap.getKeyBitmask('),

        # Screen dimensions, delay, fonts on an
        (r'\ban\.w\(\)', 'an.getScreenWidth()'),
        (r'\ban\.x\(\)', 'an.getScreenHeight()'),
        (r'\ban\.y\(\)', 'an.getHalfWidth()'),
        (r'\ban\.z\(\)', 'an.getHalfHeight()'),
        (r'\ban\.B\(\)', 'an.getFrameDelay()'),
        (r'\ban\.A\(\)', 'an.resetFrameDelay()'),
        (r'\ban\.C\(\)', 'an.getKeyDelay()'),
        (r'\ban\.D\(\)', 'an.getSmallFont()'),
        (r'\ban\.E\(\)', 'an.getMediumFont()'),
        (r'\ban\.F\(\)', 'an.getFontCharWidth()'),
        (r'\ban\.G\(\)', 'an.getFontHeight()'),
        (r'\ban\.v\(\)', 'an.isPaused()'),
        (r'\ban\.u\(\)', 'an.resumeGame()'),
        (r'\ban\.t\(\)', 'an.pauseGame()'),
        (r'\ban\.s\(\)', 'an.startTimer()'),
        (r'\ban\.a\(\(short\)', 'an.setScreenSize((short)'),

        # Image and Sprite Cache helpers
        (r'\bam\.a\(', 'am.getImage('),
        (r'\bam\.b\(', 'am.releaseImage('),
        (r'\baa\.a\(', 'aa.getAnimationData('),
        (r'\baa\.b\(', 'aa.releaseAnimationData('),

        # Database tables
        (r'\baq\.a\b', 'aq.spriteTable'),
        (r'\baq\.c\b', 'aq.gameDatabase'),
        (r'\baq\.b\b', 'aq.mapTable'),

        # Utility methods on ae
        (r'\bae\.a\(([a-zA-Z0-9_]+)\)', r'ae.randomInt(\1)'),
        (r'\bae\.b\(([a-zA-Z0-9_]+),\s*([a-zA-Z0-9_]+)\)', r'ae.randomRange(\1, \2)'),
        (r'\bae\.b\("(/[^"]+)",\s*("?[^,)]+"?)\)', r'ae.loadImage("\1", \2)'),
    ]

    # Package-local replacements inside game/
    if is_game_pkg:
        replacements.extend([
            (r'(?<![.\w])k\.a\(\)', 'k.getInstance()'),
            (r'(?<![.\w])h\.a\(\)', 'h.getInstance()'),
            (r'(?<![.\w])i\.a\(\)', 'i.getInstance()'),
            (r'(?<![.\w])d\.a\(\)', 'd.getInstance()'),
            (r'(?<![.\w])c\.a\(\)', 'c.getInstance()'),
            (r'(?<![.\w])f\.a\(\)', 'f.getInstance()'),
            (r'(?<![.\w])g\.o\(\)', 'g.getInstance()'),
            (r'(?<![.\w])j\.a\(\)', 'j.getInstance()'),
        ])

    # Declarations inside specific classes
    if rel_path == 'game/k.java':
        replacements.append((r'public static k a\(\)', 'public static k getInstance()'))
    elif rel_path == 'game/h.java':
        replacements.append((r'public static h a\(\)', 'public static h getInstance()'))
    elif rel_path == 'game/i.java':
        replacements.extend([
            (r'public static i a\(\)', 'public static i getInstance()'),
            (r'public final byte e\(\)', 'public final byte getState()'),
            (r'public final void a\(byte ', 'public final void setState(byte '),
        ])
    elif rel_path == 'game/d.java':
        replacements.append((r'public static d a\(\)', 'public static d getInstance()'))
    elif rel_path == 'game/c.java':
        replacements.append((r'public static c a\(\)', 'public static c getInstance()'))
    elif rel_path == 'game/f.java':
        replacements.append((r'public static f a\(\)', 'public static f getInstance()'))
    elif rel_path == 'game/g.java':
        replacements.append((r'public static g o\(\)', 'public static g getInstance()'))
    elif rel_path == 'game/j.java':
        replacements.append((r'public static j a\(\)', 'public static j getInstance()'))
    elif rel_path == 'ab.java':
        replacements.append((r'public static ab a\(\)', 'public static ab getInstance()'))
    elif rel_path == 'ai.java':
        replacements.append((r'public static ai a\(\)', 'public static ai getInstance()'))
    elif rel_path == 'b.java':
        replacements.append((r'public static b a\(\)', 'public static b getInstance()'))
    elif rel_path == 'j.java':
        replacements.append((r'public static j a\(\)', 'public static j getInstance()'))
    elif rel_path == 'an.java':
        replacements.extend([
            (r'public static short w\(\)', 'public static short getScreenWidth()'),
            (r'public static short x\(\)', 'public static short getScreenHeight()'),
            (r'public static short y\(\)', 'public static short getHalfWidth()'),
            (r'public static short z\(\)', 'public static short getHalfHeight()'),
            (r'public static int B\(\)', 'public static int getFrameDelay()'),
            (r'public static void A\(\)', 'public static void resetFrameDelay()'),
            (r'public static int C\(\)', 'public static int getKeyDelay()'),
            (r'public static Font D\(\)', 'public static Font getSmallFont()'),
            (r'public static Font E\(\)', 'public static Font getMediumFont()'),
            (r'public static int F\(\)', 'public static int getFontCharWidth()'),
            (r'public static int G\(\)', 'public static int getFontHeight()'),
            (r'public static boolean v\(\)', 'public static boolean isPaused()'),
            (r'public static void u\(\)', 'public static void resumeGame()'),
            (r'public static void t\(\)', 'public static void pauseGame()'),
            (r'public final void s\(\)', 'public final void startTimer()'),
        ])
    elif rel_path == 'am.java':
        replacements.extend([
            (r'public static Image a\(int ', 'public static Image getImage(int '),
            (r'public static void b\(int ', 'public static void releaseImage(int '),
        ])
    elif rel_path == 'aa.java':
        replacements.extend([
            (r'public static o a\(int ', 'public static o getAnimationData(int '),
            (r'public static void b\(int ', 'public static void releaseAnimationData(int '),
        ])
    elif rel_path == 'aq.java':
        replacements.extend([
            (r'public static short\[\]\[\] a;', 'public static short[][] spriteTable;'),
            (r'public static short\[\]\[\]\[\] c;', 'public static short[][][] gameDatabase;'),
            (r'public static short\[\]\[\] b;', 'public static short[][] mapTable;'),
        ])
    elif rel_path == 'ae.java':
        replacements.extend([
            (r'public static int a\(int ([a-zA-Z0-9_]+)\)', r'public static int randomInt(int max)'),
            (r'public static int b\(int ([a-zA-Z0-9_]+),\s*int ([a-zA-Z0-9_]+)\)', r'public static int randomRange(int min, int max)'),
        ])
    elif rel_path == 'game/e.java':
        replacements.extend([
            (r'public static e a\(\)', 'public static e getInstance()'),
            (r'this\.e\.e\(\)', 'this.e.getState()'),
            (r'this\.e\.b\((g|graphics)\)', r'this.e.render(\1)'),
            (r'this\.e\.b\(\)', 'this.e.update()'),
            (r'this\.e\.i\((keyCode|n2)\)', r'this.e.onKeyPressed(\1)'),
            (r'this\.e\.j\((keyCode|n2)\)', r'this.e.onKeyReleased(\1)'),
        ])
    elif rel_path == 'game/b.java':
        replacements.extend([
            (r'public final boolean S\(\)', 'public final boolean isAlive()'),
            (r'public final short r\(\)', 'public final short getHp()'),
            (r'public final short q\(\)', 'public final short getMaxHp()'),
            (r'public final short p\(\)', 'public final short getMp()'),
            (r'public final short o\(\)', 'public final short getMaxMp()'),
            (r'public final int n\(\)', 'public final int getLevel()'),
        ])
    elif rel_path == 'a/j.java':
        replacements.extend([
            (r'public final Object a\(\)', 'public final Object getValue()'),
            (r'public final void a\(Object object\)', 'public final void setValue(Object value)'),
            (r'this\.c = object;', 'this.c = value;'),
            (r'this\.a\.e\[this\.b\] = object;', 'this.a.e[this.b] = value;'),
        ])

    for pattern, replacement in replacements:
        matches = len(re.findall(pattern, content))
        if matches > 0:
            content = re.sub(pattern, replacement, content)
            count += matches

    return content, count


def refactor_fields(content: str, rel_path: str) -> Tuple[str, int]:
    """Pass 4: Renames key semantic fields for input, world coordinates, pet stats, and sprites."""
    count = 0
    replacements = []

    if rel_path == 'ap.java':
        replacements.extend([
            (r'\bthis\.b\b', 'this.keyHeldMask'),
            (r'\bthis\.c\b', 'this.keyPressedMask'),
            (r'\bthis\.d\b', 'this.keyReleasedMask'),
            (r'\bthis\.h\b', 'this.pointerX'),
            (r'\bthis\.i\b', 'this.pointerY'),
            (r'private int b;', 'private int keyHeldMask;'),
            (r'private int c;', 'private int keyPressedMask;'),
            (r'private int d;', 'private int keyReleasedMask;'),
            (r'private int h\s*=\s*-1;', 'private int pointerX = -1;'),
            (r'private int i\s*=\s*-1;', 'private int pointerY = -1;'),
        ])
    elif rel_path == 'an.java':
        replacements.extend([
            (r'\bthis\.P\b', 'this.screenId'),
            (r'\bthis\.Q\b', 'this.previousScreenId'),
            (r'public byte P;', 'public byte screenId;'),
            (r'public byte Q;', 'public byte previousScreenId;'),
            (r'private static short a;', 'private static short screenWidth;'),
            (r'private static short b;', 'private static short screenHeight;'),
            (r'private static int c;', 'private static int frameDelay;'),
            (r'private static Font d;', 'private static Font smallFont;'),
            (r'private static Font e;', 'private static Font mediumFont;'),
            (r'private static int f;', 'private static int keyDelay;'),
            (r'public static void a\(short ([a-zA-Z0-9_]+),\s*short ([a-zA-Z0-9_]+)\)\s*\{[^}]*\}',
             'public static void setScreenSize(short width, short height) {\n        screenWidth = width;\n        screenHeight = height;\n    }'),
            (r'\breturn a;', 'return screenWidth;'),
            (r'\breturn b;', 'return screenHeight;'),
            (r'\breturn c;', 'return frameDelay;'),
            (r'\breturn d;', 'return smallFont;'),
            (r'\breturn e;', 'return mediumFont;'),
            (r'\breturn f;', 'return keyDelay;'),
            (r'\(short\)\(a\s*/\s*2\)', '(short)(screenWidth / 2)'),
            (r'\(short\)\(b\s*/\s*2\)', '(short)(screenHeight / 2)'),
            (r'c\s*=\s*66;', 'frameDelay = 66;'),
            (r'f\s*=\s*n2;', 'keyDelay = n2;'),
        ])
    elif rel_path == 'game/k.java':
        replacements.extend([
            (r'\bthis\.f\b', 'this.sceneId'),
            (r'\bthis\.g\b', 'this.roomId'),
            (r'\bthis\.M\b', 'this.eventManager'),
            (r'public int f;', 'public int sceneId;'),
            (r'public int g;', 'public int roomId;'),
        ])
    elif rel_path == 'game/i.java':
        replacements.extend([
            (r'\bthis\.i\b', 'this.currentState'),
            (r'\bthis\.j\b', 'this.previousState'),
            (r'private byte i;', 'private byte currentState;'),
            (r'private byte j;', 'private byte previousState;'),
        ])
    elif rel_path == 'game/b.java':
        replacements.extend([
            (r'\bthis\.V\b', 'this.petId'),
            (r'\bthis\.T\b', 'this.level'),
            (r'\bthis\.C\b', 'this.growthRate'),
            (r'\bthis\.S\b', 'this.currentExp'),
            (r'private int V\s*=\s*0;', 'private int petId = 0;'),
            (r'private int T\s*=\s*0;', 'private int level = 0;'),
            (r'protected short C;', 'protected short growthRate;'),
            (r'private int S\s*=\s*0;', 'private int currentExp = 0;'),
        ])
    elif rel_path == 'game/g.java':
        replacements.extend([
            (r'\bthis\.z\b', 'this.petParty'),
            (r'\bthis\.A\b', 'this.gold'),
            (r'\bthis\.v\b', 'this.badges'),
            (r'public game\.b\[\] z;', 'public game.b[] petParty;'),
            (r'public int A;', 'public int gold;'),
            (r'public int v;', 'public int badges;'),
        ])
    elif rel_path == 'game/d.java':
        replacements.extend([
            (r'\bthis\.P\b', 'this.battlePhase'),
            (r'public byte P;', 'public byte battlePhase;'),
        ])
    elif rel_path == 'd.java':
        replacements.extend([
            (r'\bthis\.a\b', 'this.spriteId'),
            (r'\bthis\.k\b', 'this.frameImages'),
            (r'\bthis\.l\b', 'this.animationData'),
            (r'public int a;', 'public int spriteId;'),
            (r'private Image\[\] k;', 'private Image[] frameImages;'),
            (r'private o l;', 'private o animationData;'),
        ])
    elif rel_path == 'ak.java':
        replacements.extend([
            (r'public int a;', 'public int x;'),
            (r'public int b;', 'public int y;'),
            (r'public int c;', 'public int width;'),
            (r'public int d;', 'public int height;'),
            (r'this\.a = 0;', 'this.x = 0;'),
            (r'this\.b = 0;', 'this.y = 0;'),
            (r'this\.c = 0;', 'this.width = 0;'),
            (r'this\.d = 0;', 'this.height = 0;'),
            (r'public ak\(int n2, int n3, int n4, int n5\)\s*\{[^}]*\}',
             'public ak(int x, int y, int width, int height) {\n        this.x = x;\n        this.y = y;\n        this.width = width;\n        this.height = height;\n    }'),
        ])

    for pattern, replacement in replacements:
        matches = len(re.findall(pattern, content))
        if matches > 0:
            content = re.sub(pattern, replacement, content)
            count += matches

    return content, count


def refactor_methods_and_bodies(content: str, rel_path: str) -> Tuple[str, int]:
    """Pass 5: Comprehensive method parsing, parameter renaming, loop counter cleanup, and body refactoring."""
    count = 0
    keywords = {'if', 'for', 'while', 'catch', 'switch', 'synchronized', 'return'}

    # 1. Abstract & interface methods ending in ;
    abstract_decl_pattern = re.compile(
        r'((?:public|protected|private|static|final|abstract|synchronized|\s)+[\w<>\[\],\s]+?\s+(\w+)\s*\(([^)]*)\)\s*(?:throws\s+[\w\s,]+)?\s*)(;)',
        re.MULTILINE
    )
    for m in reversed(list(abstract_decl_pattern.finditer(content))):
        name = m.group(2)
        if name in keywords:
            continue
        params_str = m.group(3)
        if not params_str.strip():
            continue
        raw_params = [p.strip() for p in params_str.split(',') if p.strip()]
        new_params = []
        changed = False
        for p_idx, p in enumerate(raw_params):
            parts = p.split()
            if len(parts) >= 2:
                p_type = ' '.join(parts[:-1])
                p_name = parts[-1]
                new_name = p_name
                if p_type == 'Graphics' and p_name.startswith(('var', 'g')):
                    new_name = 'g'
                elif p_type == 'int[]' and p_name.startswith(('var', 'nArray')):
                    new_name = 'intArray'
                elif p_type == 'byte' and p_name.startswith('var'):
                    new_name = 'val'
                elif p_type == 'c' and p_name.startswith('var'):
                    new_name = 'conn'
                elif p_type == 'int' and p_name.startswith('var'):
                    new_name = 'code' if p_idx == 1 else 'id'

                if new_name != p_name:
                    new_params.append(f"{p_type} {new_name}")
                    changed = True
                    count += 1
                else:
                    new_params.append(p)
            else:
                new_params.append(p)
        if changed:
            new_params_str = ', '.join(new_params)
            decl_part = content[m.start(1):m.start(4)]
            new_decl_part = decl_part.replace(f"({params_str})", f"({new_params_str})", 1)
            content = content[:m.start(1)] + new_decl_part + ';' + content[m.end(4):]

    # 2. Methods with body ending in { ... }
    method_decl_pattern = re.compile(
        r'((?:public|protected|private|static|final|abstract|synchronized|\s)+[\w<>\[\],\s]+?\s+(\w+)\s*\(([^)]*)\)\s*(?:throws\s+[\w\s,]+)?\s*)(\{)',
        re.MULTILINE
    )

    methods_found = []
    for m in method_decl_pattern.finditer(content):
        name = m.group(2)
        if name in keywords:
            continue
        params_str = m.group(3)
        start_brace = m.start(4)

        depth = 0
        end_brace = -1
        for i in range(start_brace, len(content)):
            if content[i] == '{':
                depth += 1
            elif content[i] == '}':
                depth -= 1
                if depth == 0:
                    end_brace = i
                    break

        if end_brace != -1:
            methods_found.append({
                'decl_start': m.start(1),
                'name': name,
                'params_str': params_str,
                'start_brace': start_brace,
                'end_brace': end_brace,
            })

    # Process each method from bottom to top to preserve string indexing
    for m in reversed(methods_found):
        name = m['name']
        params_str = m['params_str']
        start_brace = m['start_brace']
        end_brace = m['end_brace']
        body = content[start_brace + 1:end_brace]
        body_modified = False
        new_method_name = name

        param_map = {}
        new_params = []

        # Special class-specific methods
        if rel_path == 'ap.java' and name == 'i' and 'int' in params_str:
            new_method_name = 'onKeyPressed'
            new_params = ['int keyCode']
            param_map = {'n2': 'keyCode', 'var1': 'keyCode'}
        elif rel_path == 'ap.java' and name == 'j' and 'int' in params_str:
            new_method_name = 'onKeyReleased'
            new_params = ['int keyCode']
            param_map = {'n2': 'keyCode', 'var1': 'keyCode'}
        elif rel_path == 'ap.java' and name == 'd' and params_str.count(',') == 1:
            new_method_name = 'onPointerEvent'
            new_params = ['int x', 'int y']
            param_map = {'n2': 'x', 'n3': 'y', 'var1': 'x', 'var2': 'y'}
        elif rel_path == 'ap.java' and name == 'a' and 'int' in params_str:
            new_method_name = 'getKeyBitmask'
            new_params = ['int keyCode']
            param_map = {'n2': 'keyCode', 'var1': 'keyCode'}
        elif rel_path == 'ap.java' and name == 'P':
            new_method_name = 'resetInputState'
        elif rel_path == 'd.java' and name == 'a' and 'int ' in params_str and 'boolean ' in params_str:
            new_method_name = 'loadSprite'
            new_params = ['int spriteId', 'boolean loop']
            param_map = {'n2': 'spriteId', 'bl': 'loop', 'var1': 'spriteId', 'var2': 'loop'}
        elif rel_path == 'd.java' and name == 'a' and not params_str.strip():
            new_method_name = 'releaseSprite'
        elif rel_path == 'd.java' and name == 'b' and not params_str.strip():
            new_method_name = 'updateAnimation'
        elif rel_path == 'game/b.java' and name == 'a' and params_str.count(',') == 5:
            new_method_name = 'initPet'
            new_params = ['int petId', 'int level', 'short skillId', 'byte rarity', 'short quality', 'byte nature']
            param_map = {
                'n2': 'petId', 'n3': 'level', 's2': 'skillId', 'sVal3': 'skillId',
                'by': 'rarity', 'val4': 'rarity', 's3': 'quality', 'sVal5': 'quality',
                'by2': 'nature', 'val6': 'nature'
            }
        elif rel_path == 'an.java' and name == 'e' and params_str.strip() in ('int n2', 'int delay'):
            new_method_name = 'setKeyDelay'
            new_params = ['int delay']
            param_map = {'n2': 'delay'}
        elif rel_path == 'game/e.java' and name == 'a' and 'GameMIDLet' in params_str:
            new_method_name = 'create'
            new_params = ['GameMIDLet midlet']
            param_map = {'gameMIDLet': 'midlet', 'var0': 'midlet', 'object': 'midlet'}
        elif params_str.strip():
            raw_params = [p.strip() for p in params_str.split(',') if p.strip()]
            for p_idx, p in enumerate(raw_params):
                parts = p.split()
                if len(parts) >= 2:
                    p_type = ' '.join(parts[:-1])
                    p_name = parts[-1]
                    new_name = p_name

                    if name in ('keyPressed', 'keyReleased') and p_type == 'int':
                        new_name = 'keyCode'
                    elif name in ('pointerPressed', 'pointerReleased', 'pointerDragged') and p_type == 'int':
                        new_name = 'x' if p_idx == 0 else 'y'
                    elif p_type == 'Graphics' and p_name in ('var1', 'graphics', 'object'):
                        new_name = 'g'
                    elif p_type == 'Image' and p_name in ('var1', 'image', 'object'):
                        new_name = 'img'
                    elif p_type == 'Font' and p_name in ('var1', 'font', 'object'):
                        new_name = 'font'
                    elif p_type in ('GameMIDLet', 'MIDlet') and p_name in ('var0', 'var1', 'object', 'gameMIDLet'):
                        new_name = 'midlet'
                    elif p_type == 'String' and p_name in ('var1', 'object', 'string'):
                        new_name = 'text' if 'text' not in params_str else 'str'
                    elif p_type == 'Vector' and p_name in ('var1', 'vector', 'object'):
                        new_name = 'list'
                    elif p_type == 'boolean' and p_name.startswith(('var', 'bl')):
                        new_name = 'flag' if p_idx == 0 else f'flag{p_idx+1}'
                    elif p_type == 'byte' and p_name.startswith(('var', 'by')):
                        new_name = 'val' if p_idx == 0 else f'val{p_idx+1}'
                    elif p_type == 'short' and p_name.startswith(('var', 's')):
                        new_name = 'sVal' if p_idx == 0 else f'sVal{p_idx+1}'
                    elif p_type == 'int[]' and p_name in ('nArray', 'var1'):
                        new_name = 'intArray'
                    elif p_type == 'short[]' and p_name in ('sArray', 'var1'):
                        new_name = 'shortArray'
                    elif p_type == 'byte[]' and p_name in ('byArray', 'var1'):
                        new_name = 'byteArray'
                    elif p_type == 'String[]' and p_name in ('stringArray', 'var1'):
                        new_name = 'strArray'

                    if new_name != p_name:
                        param_map[p_name] = new_name
                        new_params.append(f"{p_type} {new_name}")
                        count += 1
                    else:
                        new_params.append(p)
                else:
                    new_params.append(p)

        # Replace mapped parameter names in method body
        for old_p, new_p in param_map.items():
            pattern = r'(?<![.\w])' + re.escape(old_p) + r'\b'
            if re.search(pattern, body):
                body = re.sub(pattern, new_p, body)
                body_modified = True

        # Nested balanced loop counters: for (int i2 = 0; ...; ++i2) -> for (int i = 0; ...; ++i)
        for_loop_pat = re.compile(
            r'for\s*\(\s*int\s+(?P<var>[ijk])2\s*=\s*(?P<init>[^;]+);\s*(?P=var)2\s*(?P<op>[<>=!]+)\s*(?P<limit>[^;]+);\s*(?:\+\+(?P=var)2|(?P=var)2\+\+)\s*\)\s*\{'
        )
        l_pos = 0
        while True:
            lm = for_loop_pat.search(body, l_pos)
            if not lm:
                break
            var_letter = lm.group('var')
            old_var = f"{var_letter}2"
            new_var = var_letter
            init_val = lm.group('init')
            op = lm.group('op')
            limit = lm.group('limit')

            l_start = lm.end() - 1
            l_depth = 0
            l_end = -1
            for l_idx in range(l_start, len(body)):
                if body[l_idx] == '{':
                    l_depth += 1
                elif body[l_idx] == '}':
                    l_depth -= 1
                    if l_depth == 0:
                        l_end = l_idx
                        break
            if l_end == -1:
                l_pos = lm.end()
                continue

            lbody = body[l_start + 1:l_end]
            if not re.search(r'(?<![.\w])' + new_var + r'\b', lbody):
                count += 1
                body_modified = True
                new_lbody = re.sub(r'(?<![.\w])' + old_var + r'\b', new_var, lbody)
                new_for = f"for (int {new_var} = {init_val}; {new_var} {op} {limit}; ++{new_var}) {{{new_lbody}}}"
                body = body[:lm.start()] + new_for + body[l_end + 1:]
                l_pos = lm.start() + len(new_for)
            else:
                l_pos = l_end + 1

        # Local array names inside method body
        array_cleanups = [
            (r'\bstringArray\b', 'strArray'),
            (r'\bnArray2\b', 'intArray2'),
            (r'\bnArray\b', 'intArray'),
            (r'\bsArray2\b', 'shortArray2'),
            (r'\bsArray\b', 'shortArray'),
            (r'\bbyArray2\b', 'byteArray2'),
            (r'\bbyArray\b', 'byteArray'),
        ]
        for pat, repl in array_cleanups:
            if re.search(pat, body):
                body = re.sub(pat, repl, body)
                body_modified = True
                count += 1

        # Reconstruct method declaration and body
        new_params_str = ', '.join(new_params) if new_params else params_str
        if new_params_str != params_str or new_method_name != name or body_modified:
            decl_part = content[m['decl_start']:start_brace]
            if new_method_name != name:
                decl_part = re.sub(r'\b' + re.escape(name) + r'\s*\(', f"{new_method_name}(", decl_part, count=1)
            decl_part = decl_part.replace(f"({params_str})", f"({new_params_str})", 1)
            content = content[:m['decl_start']] + decl_part + '{' + body + content[end_brace:]

    return content, count


def add_class_headers(content: str, rel_path: str) -> Tuple[str, int]:
    """Pass 6: Adds architectural docstrings to top-level class declarations."""
    dot_name = rel_path.replace('.java', '').replace('/', '.')
    base_name = Path(rel_path).stem
    desc = CLASS_DESCRIPTIONS.get(dot_name) or CLASS_DESCRIPTIONS.get(base_name)

    if not desc:
        return content, 0

    header = f"/**\n * {desc}\n */\n"
    decl_match = re.search(r'((?:public\s+|final\s+|abstract\s+)*(?:class|interface)\s+' + re.escape(base_name) + r'\b)', content)
    if decl_match:
        pos = decl_match.start()
        if '/**\n * ' not in content[max(0, pos-100):pos]:
            content = content[:pos] + header + content[pos:]
            return content, 1

    return content, 0


def process_all_files():
    """Main execution orchestrator."""
    files = sorted(glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True))
    print(f"==================================================")
    print(f"  VQSV REFERENCE AUTOMATED REFACTORING ENGINE")
    print(f"  Target: {TARGET_DIR}")
    print(f"  Found {len(files)} Java source files")
    print(f"==================================================")

    stats = {
        'unicode': 0,
        'keycodes': 0,
        'methods': 0,
        'fields': 0,
        'method_bodies': 0,
        'headers': 0,
    }

    for file_path in files:
        rel = os.path.relpath(file_path, TARGET_DIR)
        with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
            code = f.read()

        code, c1 = unescape_unicode(code)
        code, c2 = annotate_keycodes(code)
        code, c3 = refactor_singletons_and_methods(code, rel)
        code, c4 = refactor_fields(code, rel)
        code, c5 = refactor_methods_and_bodies(code, rel)
        code, c6 = add_class_headers(code, rel)

        stats['unicode'] += c1
        stats['keycodes'] += c2
        stats['methods'] += c3
        stats['fields'] += c4
        stats['method_bodies'] += c5
        stats['headers'] += c6

        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(code)

        total_file_changes = c1 + c2 + c3 + c4 + c5 + c6
        if total_file_changes > 0:
            print(f"  [Refactored] {rel:35} -> {total_file_changes:4d} transformations")

    print(f"==================================================")
    print(f"  REFACTORING SUMMARY STATISTICS:")
    print(f"  - Unicode strings decoded to UTF-8: {stats['unicode']}")
    print(f"  - Keycodes and bitmasks annotated:  {stats['keycodes']}")
    print(f"  - Singleton & method calls renamed: {stats['methods']}")
    print(f"  - Key engine fields renamed:        {stats['fields']}")
    print(f"  - Method signatures & bodies fixed: {stats['method_bodies']}")
    print(f"  - Architectural headers attached:   {stats['headers']}")
    total_transforms = sum(stats.values())
    print(f"  TOTAL TRANSFORMATIONS:              {total_transforms}")
    print(f"==================================================")


if __name__ == '__main__':
    process_all_files()
