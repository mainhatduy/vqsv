#!/usr/bin/env python3
"""
Semantic Refactoring Engine for Core Game Entities, Screens, Combat, and Subsystems.
Transforms obfuscated single-character fields, methods, and variables into clean,
human-readable names across reference/decompiled/.
"""

import os
import re
import glob
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
TARGET_DIR = ROOT / 'reference/decompiled'


def refactor_base_and_world_entity():
    """
    Refactor BaseEntity and WorldEntity, and synchronize usages across all subclasses:
    Subclasses: WorldEntity, Pet, Player, NpcEntity, plus all callers.
    """
    print(">>> [1/8] Refactoring BaseEntity and WorldEntity...")

    # 1. BaseEntity.java
    base_path = TARGET_DIR / 'BaseEntity.java'
    with open(base_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Field declarations in BaseEntity
    code = re.sub(r'\bpublic\s+short\[\]\s+c\s*;', 'public short[] baseStats;', code)
    code = re.sub(r'\bpublic\s+short\[\]\s+d\s*;', 'public short[] currentStats;', code)
    code = re.sub(r'\bpublic\s+int\s+i\s*;', 'public int posX;', code)
    code = re.sub(r'\bpublic\s+int\s+j\s*;', 'public int posY;', code)
    code = re.sub(r'\bpublic\s+byte\s+n\s*;', 'public byte facingDirection;', code)
    code = re.sub(r'\bpublic\s+BaseEntity\s+p\s*;', 'public BaseEntity ownerEntity;', code)
    code = re.sub(r'\bprivate\s+short\s+a\s*=\s*\(short\)10\s*;', 'private short followDistance = (short)10;', code)
    code = re.sub(r'\bprivate\s+int\[\]\[\]\s+b\s*;', 'private int[][] followHistory;', code)
    code = re.sub(r'\bprivate\s+boolean\s+t\s*=\s*false\s*;', 'private boolean isFollowing = false;', code)

    # Method declarations and bodies in BaseEntity:
    code = re.sub(
        r'public\s+void\s+b\s*\(\s*int\s+n2\s*,\s*int\s+n3\s*\)\s*\{[^}]*\}',
        'public void setPosition(int x, int y) {\n        this.posX = x;\n        this.posY = y;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+void\s+b\s*\(\s*byte\s+val\s*\)\s*\{[^}]*\}',
        'public final void setFacingDirection(byte dir) {\n        this.facingDirection = dir;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+int\s+l\s*\(\s*\)\s*\{[^}]*\}',
        'public final int getPosX() {\n        return this.posX;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+int\s+m\s*\(\s*\)\s*\{[^}]*\}',
        'public final int getPosY() {\n        return this.posY;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+void\s+d\s*\(\s*int\s+n2\s*\)\s*\{[^}]*\}',
        'public final void movePosX(int dx) {\n        this.posX += dx;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+void\s+e\s*\(\s*int\s+n2\s*\)\s*\{[^}]*\}',
        'public final void movePosY(int dy) {\n        this.posY += dy;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+boolean\s+n\s*\(\s*\)\s*\{[^}]*\}',
        'public final boolean isFollowing() {\n        return this.isFollowing;\n    }',
        code
    )
    code = re.sub(
        r'public\s+final\s+void\s+a\s*\(\s*BaseEntity\s+n2\s*\)\s*\{[^}]*\}',
        'public final void setOwnerEntity(BaseEntity owner) {\n        this.ownerEntity = owner;\n    }',
        code
    )

    # Member accesses inside BaseEntity:
    code = re.sub(r'\bthis\.i(?!\s*\()\b', 'this.posX', code)
    code = re.sub(r'\bthis\.j(?!\s*\()\b', 'this.posY', code)
    code = re.sub(r'\bthis\.n(?!\s*\()\b', 'this.facingDirection', code)
    code = re.sub(r'\bthis\.p(?!\s*\()\b', 'this.ownerEntity', code)
    code = re.sub(r'\bthis\.t(?!\s*\()\b', 'this.isFollowing', code)
    code = re.sub(r'\bthis\.a\b(?!\s*[\(\[])', 'this.followDistance', code)
    code = re.sub(r'\bthis\.b\[', 'this.followHistory[', code)
    code = re.sub(r'\bthis\.b\s*=', 'this.followHistory =', code)

    code = re.sub(r'\bthis\.c(?!\s*\()\b', 'this.baseStats', code)
    code = re.sub(r'\bthis\.d(?!\s*\()\b', 'this.currentStats', code)
    code = re.sub(r'\bthis\.d\(([^)]+)\)', r'this.movePosX(\1)', code)
    code = re.sub(r'\bthis\.e\(([^)]+)\)', r'this.movePosY(\1)', code)
    code = re.sub(r'\bthis\.b\(([^,]+),\s*([^)]+)\)', r'this.setPosition(\1, \2)', code)

    with open(base_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # 2. WorldEntity.java
    world_path = TARGET_DIR / 'WorldEntity.java'
    with open(world_path, 'r', encoding='utf-8') as f:
        wcode = f.read()

    # Field declarations in WorldEntity
    wcode = re.sub(r'\bpublic\s+SpriteRenderer\s+a\s*=\s*new\s+SpriteRenderer\(\);', 'public SpriteRenderer spriteRenderer = new SpriteRenderer();', wcode)
    wcode = re.sub(r'\bpublic\s+WorldEntity\s+b\s*=\s*null\s*;', 'public WorldEntity targetEntity = null;', wcode)

    # Member accesses in WorldEntity:
    wcode = re.sub(r'\bthis\.a\.', 'this.spriteRenderer.', wcode)
    wcode = re.sub(r'\bthis\.b\.', 'this.targetEntity.', wcode)
    wcode = re.sub(r'\bthis\.b\s*!=', 'this.targetEntity !=', wcode)
    wcode = re.sub(r'\bthis\.b\s*==', 'this.targetEntity ==', wcode)
    wcode = re.sub(r'\bthis\.b\s*=(?!=)', 'this.targetEntity =', wcode)
    wcode = re.sub(r'\bthis\.i(?!\s*\()\b', 'this.posX', wcode)
    wcode = re.sub(r'\bthis\.j(?!\s*\()\b', 'this.posY', wcode)
    wcode = re.sub(r'\bthis\.n(?!\s*\()\b', 'this.facingDirection', wcode)
    wcode = re.sub(r'\bthis\.d\(([^)]+)\)', r'this.movePosX(\1)', wcode)
    wcode = re.sub(r'\bthis\.e\(([^)]+)\)', r'this.movePosY(\1)', wcode)
    wcode = re.sub(r'\bthis\.targetEntity\.b\(', 'this.targetEntity.setPosition(', wcode)

    with open(world_path, 'w', encoding='utf-8') as f:
        f.write(wcode)

    # 3. Synchronize BaseEntity & WorldEntity members in subclasses:
    # Subclasses: Pet, Player, NpcEntity
    for sub in ('game/Pet.java', 'game/Player.java', 'game/NpcEntity.java'):
        spath = TARGET_DIR / sub
        with open(spath, 'r', encoding='utf-8') as f:
            c = f.read()
        c = re.sub(r'\bthis\.i(?!\s*\()\b', 'this.posX', c)
        c = re.sub(r'\bthis\.j(?!\s*\()\b', 'this.posY', c)
        c = re.sub(r'\bthis\.n(?!\s*\()\b', 'this.facingDirection', c)
        c = re.sub(r'\bthis\.p(?!\s*\()\b', 'this.ownerEntity', c)
        c = re.sub(r'\bthis\.a\.a\(', 'this.spriteRenderer.a(', c)
        c = re.sub(r'\bthis\.a\.b\(', 'this.spriteRenderer.b(', c)
        c = re.sub(r'\bthis\.a\.c\(', 'this.spriteRenderer.c(', c)
        c = re.sub(r'\bthis\.a\.e\(', 'this.spriteRenderer.e(', c)
        c = re.sub(r'\bthis\.a\.onPointerEvent\(', 'this.spriteRenderer.onPointerEvent(', c)
        c = re.sub(r'\bthis\.a\.onKeyPressed\(', 'this.spriteRenderer.onKeyPressed(', c)
        c = re.sub(r'\bthis\.a\.onKeyReleased\(', 'this.spriteRenderer.onKeyReleased(', c)
        c = re.sub(r'\bthis\.b\b(?!\s*[\(\[])', 'this.targetEntity', c)
        if sub == 'game/Player.java':
            c = re.sub(r'\bif\s*\(\s*this\.n\(\)\s*&&\s*this\.p\.h\(\)\s*!=\s*0\s*\)', 'if (this.isFollowing() && this.ownerEntity.h() != 0)', c)
        with open(spath, 'w', encoding='utf-8') as f:
            f.write(c)

    # Synchronize Player coordinates & direction when accessed as Player.getInstance().posX / posY / facingDirection
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c

        # Player.getInstance().i / j / n
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)i(?!\s*\()\b', r'\1posX', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)j(?!\s*\()\b', r'\1posY', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)n(?!\s*\()\b', r'\1facingDirection', c)

        # Coordinate getters on known entity references:
        c = re.sub(r'(\b(?:this\.player|this\.x|npcList\[[^\]]+\]|d\[[^\]]+\]|\(\(Pet\)[^\)]+\)|\bpet)\.)l\(\)', r'\1getPosX()', c)
        c = re.sub(r'(\b(?:this\.player|this\.x|npcList\[[^\]]+\]|d\[[^\]]+\]|\(\(Pet\)[^\)]+\)|\bpet)\.)m\(\)', r'\1getPosY()', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_map_engine():
    """
    Refactor MapEngine (viewport, camera coordinates, map dimensions, singleton).
    """
    print(">>> [2/8] Refactoring MapEngine.java...")
    me_path = TARGET_DIR / 'MapEngine.java'
    with open(me_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Singleton MapEngine e -> instance
    code = re.sub(r'\bprivate\s+static\s+MapEngine\s+e\s*;', 'private static MapEngine instance;', code)
    code = re.sub(r'\bif\s*\(\s*e\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\be\s*=\s*new\s+MapEngine\(\);', 'instance = new MapEngine();', code)
    code = re.sub(r'\breturn\s+e\s*;', 'return instance;', code)

    # Coordinate fields:
    code = re.sub(r'\bpublic\s+int\s+a\s*;', 'public int cameraX;', code)
    code = re.sub(r'\bpublic\s+int\s+b\s*;', 'public int cameraY;', code)
    code = re.sub(r'\bpublic\s+int\s+c\s*;', 'public int mapTileWidth;', code)
    code = re.sub(r'\bpublic\s+int\s+d\s*;', 'public int mapTileHeight;', code)

    # Member accesses inside MapEngine:
    code = re.sub(r'\bthis\.a(?!\s*\()\b', 'this.cameraX', code)
    code = re.sub(r'\bthis\.b(?!\s*\()\b', 'this.cameraY', code)
    code = re.sub(r'\bthis\.c(?!\s*\()\b', 'this.mapTileWidth', code)
    code = re.sub(r'\bthis\.d(?!\s*\()\b', 'this.mapTileHeight', code)

    with open(me_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # Synchronize callers to MapEngine camera & map dimensions
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c
        c = re.sub(r'(\bMapEngine\.getInstance\(\)\.)a\b(?!\s*\()', r'\1cameraX', c)
        c = re.sub(r'(\bMapEngine\.getInstance\(\)\.)b\b(?!\s*\()', r'\1cameraY', c)
        c = re.sub(r'(\bMapEngine\.getInstance\(\)\.)c\b(?!\s*\()', r'\1mapTileWidth', c)
        c = re.sub(r'(\bMapEngine\.getInstance\(\)\.)d\b(?!\s*\()', r'\1mapTileHeight', c)

        # In WorldEntity.java line 126: viewport check uses MapEngine camera
        if Path(jf).name == 'WorldEntity.java':
            c = re.sub(
                r'EngineUtils\.a\(game\.WorldManager\.getInstance\(\)\.tileMapRenderer\.a,\s*game\.WorldManager\.getInstance\(\)\.tileMapRenderer\.b,',
                'EngineUtils.a(MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY,',
                c
            )

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_battle_screen():
    """
    Refactor BattleScreen internal state, combatants, singleton, and UI elements.
    """
    print(">>> [3/8] Refactoring BattleScreen.java...")
    fpath = TARGET_DIR / 'game/BattleScreen.java'
    with open(fpath, 'r', encoding='utf-8') as f:
        code = f.read()

    # Singleton BattleScreen o -> instance
    code = re.sub(r'\bprivate\s+static\s+BattleScreen\s+o\s*;', 'private static BattleScreen instance;', code)
    code = re.sub(r'\bif\s*\(\s*o\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bo\s*=\s*new\s+BattleScreen\(\);', 'instance = new BattleScreen();', code)
    code = re.sub(r'\bo\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+o\s*;', 'return instance;', code)

    # Static player reference: private static Player p; -> private static Player player;
    code = re.sub(r'\bprivate\s+static\s+Player\s+p\s*;', 'private static Player player;', code)
    code = re.sub(r'\bif\s*\(\s*p\s*==\s*null\s*\)', 'if (player == null)', code)
    code = re.sub(r'\bp\s*=\s*game\.Player\.getInstance\(\);', 'player = game.Player.getInstance();', code)
    code = re.sub(r'\bp\s*=\s*null\s*;', 'player = null;', code)

    # Combat mode fields:
    # public int a; -> public int battleType; (wild vs trainer)
    code = re.sub(r'\bpublic\s+int\s+a\s*;', 'public int battleType;', code)
    code = re.sub(r'\bthis\.a(?!\s*\()\b', 'this.battleType', code)

    # public byte b; -> public byte battleMode;
    code = re.sub(r'\bpublic\s+byte\s+b\s*;', 'public byte battleMode;', code)
    code = re.sub(r'\bthis\.b(?!\s*\()\b', 'this.battleMode', code)

    # private byte r; -> private byte battleResult;
    code = re.sub(r'\bprivate\s+byte\s+r\s*;', 'private byte battleResult;', code)
    code = re.sub(r'\bthis\.r(?!\s*\()\b', 'this.battleResult', code)

    # public Image c; -> public Image battleBgImage;
    code = re.sub(r'\bpublic\s+Image\s+c\s*;', 'public Image battleBgImage;', code)
    code = re.sub(r'\bthis\.c(?!\s*\()\b', 'this.battleBgImage', code)

    # public Pet[] d; -> public Pet[] enemyPets;
    code = re.sub(r'\bpublic\s+Pet\[\]\s+d\s*;', 'public Pet[] enemyPets;', code)
    code = re.sub(r'\bthis\.d(?!\s*\()\b', 'this.enemyPets', code)

    # public Pet h; -> public Pet activePlayerPet;
    code = re.sub(r'\bpublic\s+Pet\s+h\s*;', 'public Pet activePlayerPet;', code)
    code = re.sub(r'\bthis\.h(?!\s*\()\b', 'this.activePlayerPet', code)

    # private SkillEffect H; -> private SkillEffect activeSkillEffect;
    code = re.sub(r'\bprivate\s+SkillEffect\s+H\s*;', 'private SkillEffect activeSkillEffect;', code)
    code = re.sub(r'\bthis\.H(?!\s*\()\b', 'this.activeSkillEffect', code)

    # private Vector v; -> private Vector actionQueue;
    code = re.sub(r'\bprivate\s+Vector\s+v\s*;', 'private Vector actionQueue;', code)
    code = re.sub(r'\bthis\.v(?!\s*\()\b', 'this.actionQueue', code)

    # private String ay = null; -> private String battleMessage = null;
    code = re.sub(r'\bprivate\s+String\s+ay\s*=\s*null\s*;', 'private String battleMessage = null;', code)
    code = re.sub(r'\bthis\.ay\b', 'this.battleMessage', code)

    with open(fpath, 'w', encoding='utf-8') as f:
        f.write(code)

    # Cross-file updates for BattleScreen members:
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        if Path(jf).name == 'BattleScreen.java':
            continue
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c
        c = re.sub(r'(\b(?:game\.)?BattleScreen\.getInstance\(\)\.)c\b(?!\s*\()', r'\1battleBgImage', c)
        c = re.sub(r'(\b(?:game\.)?BattleScreen\.getInstance\(\)\.)d\b(?!\s*\()', r'\1enemyPets', c)
        c = re.sub(r'(\b(?:game\.)?BattleScreen\.getInstance\(\)\.)h\b(?!\s*\()', r'\1activePlayerPet', c)
        c = re.sub(r'(\b(?:game\.)?BattleScreen\.getInstance\(\)\.)a\b(?!\s*\()', r'\1battleType', c)
        c = re.sub(r'(\b(?:game\.)?BattleScreen\.getInstance\(\)\.)b\b(?!\s*\()', r'\1battleMode', c)
        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_pet():
    """
    Refactor Pet getters, level/exp progression, and skill effect slots.
    """
    print(">>> [4/8] Refactoring Pet.java...")
    fpath = TARGET_DIR / 'game/Pet.java'
    with open(fpath, 'r', encoding='utf-8') as f:
        code = f.read()

    # Method signatures in Pet:
    # public final int q() -> getPetId()
    code = re.sub(r'\bpublic\s+final\s+int\s+q\s*\(\s*\)', 'public final int getPetId()', code)
    # public final int s() -> getLevel()
    code = re.sub(r'\bpublic\s+final\s+int\s+s\s*\(\s*\)', 'public final int getLevel()', code)
    # public final boolean t() -> isMaxLevel()
    code = re.sub(r'\bpublic\s+final\s+boolean\s+t\s*\(\s*\)', 'public final boolean isMaxLevel()', code)
    # public final int u() -> getExpToNextLevel()
    code = re.sub(r'\bpublic\s+final\s+int\s+u\s*\(\s*\)', 'public final int getExpToNextLevel()', code)
    # public final void v() -> levelUp()
    code = re.sub(r'\bpublic\s+final\s+void\s+v\s*\(\s*\)', 'public final void levelUp()', code)
    # public final int z() -> getCurrentExp()
    code = re.sub(r'\bpublic\s+final\s+int\s+z\s*\(\s*\)', 'public final int getCurrentExp()', code)

    # Fields in Pet:
    # SkillEffect u; -> SkillEffect currentSpellEffect;
    code = re.sub(r'\bSkillEffect\s+u\s*;', 'SkillEffect currentSpellEffect;', code)
    code = re.sub(r'\bthis\.u(?!\s*\()\b', 'this.currentSpellEffect', code)

    # protected SkillEffect L = null; -> protected SkillEffect passiveAuraEffect = null;
    code = re.sub(r'\bprotected\s+SkillEffect\s+L\s*=\s*null\s*;', 'protected SkillEffect passiveAuraEffect = null;', code)
    code = re.sub(r'\bthis\.L\b', 'this.passiveAuraEffect', code)

    with open(fpath, 'w', encoding='utf-8') as f:
        f.write(code)

    # Synchronize Pet getter method calls across all files
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c
        # Replace Pet method calls:
        # e.g. petParty[i].s(), activePlayerPet.t(), enemyPets[i].q(), etc.
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)q\(\)', r'\1getPetId()', c)
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)s\(\)', r'\1getLevel()', c)
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)t\(\)', r'\1isMaxLevel()', c)
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)u\(\)', r'\1getExpToNextLevel()', c)
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)v\(\)', r'\1levelUp()', c)
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)z\(\)', r'\1getCurrentExp()', c)

        # Pet spell effect field
        c = re.sub(r'(\b(?:petParty\[[^\]]+\]|enemyPets\[[^\]]+\]|Pet|activePlayerPet|pet|[a-z0-9_]+Pet)\.)u(?!\s*\()\b', r'\1currentSpellEffect', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_player():
    """
    Refactor Player fields, bags, mount status, step counters, and quick slots.
    """
    print(">>> [5/8] Refactoring Player.java...")
    fpath = TARGET_DIR / 'game/Player.java'
    with open(fpath, 'r', encoding='utf-8') as f:
        code = f.read()

    # Singleton Player Y -> instance
    code = re.sub(r'\bprivate\s+static\s+Player\s+Y\s*;', 'private static Player instance;', code)
    code = re.sub(r'\bif\s*\(\s*Y\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bY\s*=\s*new\s+Player\(\);', 'instance = new Player();', code)
    code = re.sub(r'\bY\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+Y\s*;', 'return instance;', code)

    # Mount state: public int t; -> public int mountState;
    code = re.sub(r'\bpublic\s+int\s+t\s*;', 'public int mountState;', code)
    code = re.sub(r'\bthis\.t(?!\s*\()\b', 'this.mountState', code)

    # Party count: in Player, the field was previously misnamed 'gold' in Pass 1, but it stores party size!
    # public int gold; -> public int partyPetCount;
    code = re.sub(r'\bpublic\s+int\s+gold\s*;', 'public int partyPetCount;', code)
    code = re.sub(r'\bthis\.gold(?!\s*\()\b', 'this.partyPetCount', code)

    # Encounter / repel step counters:
    code = re.sub(r'\bpublic\s+int\s+w\s*;', 'public int repelSteps;', code)
    code = re.sub(r'\bthis\.w(?!\s*\()\b', 'this.repelSteps', code)
    code = re.sub(r'\bpublic\s+int\s+x\s*;', 'public int encounterSteps;', code)
    code = re.sub(r'\bthis\.x(?!\s*\()\b', 'this.encounterSteps', code)

    # Internal dual step tracking counters (af, ag):
    code = re.sub(r'\bprivate\s+int\s+af\s*;', 'private int primaryStepCounter;', code)
    code = re.sub(r'\bprivate\s+int\s+ag\s*;', 'private int secondaryStepCounter;', code)
    code = re.sub(r'\bthis\.af\b', 'this.primaryStepCounter', code)
    code = re.sub(r'\bthis\.ag\b', 'this.secondaryStepCounter', code)

    # Step accessor methods:
    code = re.sub(r'\bpublic\s+final\s+int\s+E\s*\(\s*\)\s*\{[^}]*\}', 'public final int getPrimaryStepCount() {\n        return this.primaryStepCounter;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+s\s*\(\s*int\s+n2\s*\)\s*\{[^}]*\}', 'public final void addPrimarySteps(int delta) {\n        this.primaryStepCounter += delta;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+F\s*\(\s*\)\s*\{[^}]*\}', 'public final void resetPrimarySteps() {\n        this.primaryStepCounter = 0;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+t\s*\(\s*int\s+n2\s*\)\s*\{[^}]*\}', 'public final boolean hasExceededPrimarySteps(int threshold) {\n        return this.primaryStepCounter >= threshold;\n    }', code)

    code = re.sub(r'\bpublic\s+final\s+int\s+G\s*\(\s*\)\s*\{[^}]*\}', 'public final int getSecondaryStepCount() {\n        return this.secondaryStepCounter;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+u\s*\(\s*int\s+n2\s*\)\s*\{[^}]*\}', 'public final void addSecondarySteps(int delta) {\n        this.secondaryStepCounter += delta;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+H\s*\(\s*\)\s*\{[^}]*\}', 'public final void resetSecondarySteps() {\n        this.secondaryStepCounter = 0;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+v\s*\(\s*int\s+n2\s*\)\s*\{[^}]*\}', 'public final boolean hasExceededSecondarySteps(int threshold) {\n        return this.secondaryStepCounter >= threshold;\n    }', code)

    # Update internal call sites in Player.java for step checks:
    code = re.sub(r'return\s+this\.t\(n3\);', 'return this.hasExceededPrimarySteps(n3);', code)
    code = re.sub(r'return\s+this\.v\(n3\);', 'return this.hasExceededSecondarySteps(n3);', code)
    code = re.sub(r'return\s+this\.isBadgeActive\(n3\);', 'return this.hasExceededSecondarySteps(n3);', code)

    # Bag vectors:
    # J -> bagItems
    code = re.sub(r'\bpublic\s+Vector\s+J\s*;', 'public Vector bagItems;', code)
    code = re.sub(r'\bthis\.J(?!\s*\()\b', 'this.bagItems', code)
    # K -> bagPotions
    code = re.sub(r'\bpublic\s+Vector\s+K\s*;', 'public Vector bagPotions;', code)
    code = re.sub(r'\bthis\.K(?!\s*\()\b', 'this.bagPotions', code)
    # L -> bagBalls
    code = re.sub(r'\bpublic\s+Vector\s+L\s*;', 'public Vector bagBalls;', code)
    code = re.sub(r'\bthis\.L(?!\s*\()\b', 'this.bagBalls', code)
    # M -> bagScrolls
    code = re.sub(r'\bpublic\s+Vector\s+M\s*;', 'public Vector bagScrolls;', code)
    code = re.sub(r'\bthis\.M(?!\s*\()\b', 'this.bagScrolls', code)
    # N -> bagBadges
    code = re.sub(r'\bpublic\s+Vector\s+N\s*;', 'public Vector bagBadges;', code)
    code = re.sub(r'\bthis\.N(?!\s*\()\b', 'this.bagBadges', code)
    # O -> bagQuestItems
    code = re.sub(r'\bpublic\s+Vector\s+O\s*;', 'public Vector bagQuestItems;', code)
    code = re.sub(r'\bthis\.O(?!\s*\()\b', 'this.bagQuestItems', code)

    # Quick item slots:
    code = re.sub(r'\bpublic\s+short\[\]\s+R\s*;', 'public short[] quickItemSlots;', code)
    code = re.sub(r'\bthis\.R(?!\s*\()\b', 'this.quickItemSlots', code)

    # Player status methods:
    code = re.sub(r'\bpublic\s+final\s+boolean\s+w\s*\(\s*\)', 'public final boolean hasActivePet()', code)
    code = re.sub(r'\bpublic\s+final\s+byte\s+y\s*\(\s*\)', 'public final byte getActivePetIndex()', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+z\s*\(\s*\)', 'public final boolean isPartyAlive()', code)

    with open(fpath, 'w', encoding='utf-8') as f:
        f.write(code)

    # Synchronize Player member calls across all files
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c

        # Player.getInstance() member accesses
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)w\(\)', r'\1hasActivePet()', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)y\(\)', r'\1getActivePetIndex()', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)z\(\)', r'\1isPartyAlive()', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)gold(?!\s*\()\b', r'\1partyPetCount', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)t(?!\s*\()\b', r'\1mountState', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)w(?!\s*\()\b', r'\1repelSteps', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)x(?!\s*\()\b', r'\1encounterSteps', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)J(?!\s*\()\b', r'\1bagItems', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)K(?!\s*\()\b', r'\1bagPotions', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)L(?!\s*\()\b', r'\1bagBalls', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)M(?!\s*\()\b', r'\1bagScrolls', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)N(?!\s*\()\b', r'\1bagBadges', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)O(?!\s*\()\b', r'\1bagQuestItems', c)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)R(?!\s*\()\b', r'\1quickItemSlots', c)

        # In OverworldScreen, on this.player:
        if Path(jf).name == 'OverworldScreen.java':
            c = re.sub(r'(\bthis\.player\.)w\(\)', r'\1hasActivePet()', c)
            c = re.sub(r'(\bthis\.player\.)y\(\)', r'\1getActivePetIndex()', c)
            c = re.sub(r'(\bthis\.player\.)z\(\)', r'\1isPartyAlive()', c)
            c = re.sub(r'(\bthis\.player\.)gold(?!\s*\()\b', r'\1partyPetCount', c)
            c = re.sub(r'(\bthis\.player\.)t(?!\s*\()\b', r'\1mountState', c)
            c = re.sub(r'(\bthis\.player\.)w(?!\s*\()\b', r'\1repelSteps', c)
            c = re.sub(r'(\bthis\.player\.)x(?!\s*\()\b', r'\1encounterSteps', c)

        # In ScriptEngine, when player is stored in a local variable `Player q;`:
        if Path(jf).name == 'ScriptEngine.java':
            c = re.sub(r'(\bthis\.player\.)A\b(?!\s*\()', r'\1partyPetCount', c)
            c = re.sub(r'(\bthis\.q\.)A\b(?!\s*\()', r'\1partyPetCount', c)
            c = re.sub(r'(\bthis\.player\.)z\b(?!\s*[\(\[])', r'\1petParty', c)
            c = re.sub(r'(\bthis\.q\.)z\b(?!\s*[\(\[])', r'\1petParty', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_world_manager_and_overworld():
    """
    Refactor WorldManager and OverworldScreen singletons and subsystem references.
    """
    print(">>> [6/8] Refactoring WorldManager.java & OverworldScreen.java...")

    # 1. WorldManager.java
    wm_path = TARGET_DIR / 'game/WorldManager.java'
    with open(wm_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Singleton Z -> instance
    code = re.sub(r'\bprivate\s+static\s+WorldManager\s+Z\s*;', 'private static WorldManager instance;', code)
    code = re.sub(r'\bif\s*\(\s*Z\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bZ\s*=\s*new\s+WorldManager\(\);', 'instance = new WorldManager();', code)
    code = re.sub(r'\bZ\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+Z\s*;', 'return instance;', code)

    # Subsystems:
    # TileMapRenderer a; -> TileMapRenderer tileMapRenderer;
    code = re.sub(r'\bpublic\s+TileMapRenderer\s+a\s*;', 'public TileMapRenderer tileMapRenderer;', code)
    code = re.sub(r'\bthis\.a(?!\s*\()\b', 'this.tileMapRenderer', code)

    # StringTable b; -> StringTable stringTable;
    code = re.sub(r'\bpublic\s+StringTable\s+b\s*;', 'public StringTable stringTable;', code)
    code = re.sub(r'\bthis\.b(?!\s*\()\b', 'this.stringTable', code)

    # Player c; -> Player player;
    code = re.sub(r'\bpublic\s+Player\s+c\s*;', 'public Player player;', code)
    code = re.sub(r'\bthis\.c(?!\s*\()\b', 'this.player', code)

    # NpcEntity[] d; -> NpcEntity[] npcList;
    code = re.sub(r'\bpublic\s+NpcEntity\[\]\s+d\s*;', 'public NpcEntity[] npcList;', code)
    code = re.sub(r'\bthis\.d(?!\s*\()\b', 'this.npcList', code)

    # Spawns and map metadata:
    code = re.sub(r'\bpublic\s+short\s+h\s*;', 'public short playerSpawnX;', code)
    code = re.sub(r'\bthis\.h(?!\s*\()\b', 'this.playerSpawnX', code)

    code = re.sub(r'\bpublic\s+short\s+i\s*;', 'public short playerSpawnY;', code)
    code = re.sub(r'\bthis\.i(?!\s*\()\b', 'this.playerSpawnY', code)

    code = re.sub(r'\bpublic\s+String\s+k\s*;', 'public String mapName;', code)
    code = re.sub(r'\bthis\.k(?!\s*\()\b', 'this.mapName', code)

    with open(wm_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # 2. OverworldScreen.java
    ow_path = TARGET_DIR / 'game/OverworldScreen.java'
    with open(ow_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Singleton v -> instance (only in declaration and getInstance / cleanup!)
    code = re.sub(r'\bprivate\s+static\s+OverworldScreen\s+v\s*=\s*null\s*;', 'private static OverworldScreen instance = null;', code)
    code = re.sub(r'\bif\s*\(\s*v\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bv\s*=\s*new\s+OverworldScreen\(\);', 'instance = new OverworldScreen();', code)
    code = re.sub(r'\bv\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+v\s*;', 'return instance;', code)

    # Subsystems on this:
    # private WorldManager w; -> private WorldManager worldManager;
    code = re.sub(r'\bprivate\s+WorldManager\s+w\s*;', 'private WorldManager worldManager;', code)
    code = re.sub(r'\bthis\.w(?!\s*\()\b', 'this.worldManager', code)

    # private Player x; -> private Player player;
    code = re.sub(r'\bprivate\s+Player\s+x\s*;', 'private Player player;', code)
    code = re.sub(r'\bthis\.x(?!\s*\()\b', 'this.player', code)

    # private BaseScreen y; -> private BaseScreen parentScreen;
    code = re.sub(r'\bprivate\s+BaseScreen\s+y\s*;', 'private BaseScreen parentScreen;', code)
    code = re.sub(r'\bthis\.y(?!\s*\()\b', 'this.parentScreen', code)

    # public ScriptSequence[] a; -> public ScriptSequence[] scriptSequences;
    code = re.sub(r'\bpublic\s+ScriptSequence\[\]\s+a\s*;', 'public ScriptSequence[] scriptSequences;', code)
    code = re.sub(r'\bthis\.a(?!\s*[\(\[])\b', 'this.scriptSequences', code)
    code = re.sub(r'\bthis\.a\[', 'this.scriptSequences[', code)
    code = re.sub(r'\bthis\.a\.length\b', 'this.scriptSequences.length', code)

    # private TileMapRenderer D = ... -> private TileMapRenderer mapRenderer = ...
    code = re.sub(r'\bprivate\s+TileMapRenderer\s+D\s*=\s*game\.TileMapRenderer\.getInstance\(\);', 'private TileMapRenderer mapRenderer = game.TileMapRenderer.getInstance();', code)
    code = re.sub(r'\bthis\.D(?!\s*\()\b', 'this.mapRenderer', code)

    with open(ow_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # 3. Synchronize WorldManager members across other files
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        if Path(jf).name in ('WorldManager.java',):
            continue
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c
        # Calls on WorldManager.getInstance():
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)a\b(?!\s*\()', r'\1tileMapRenderer', c)
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)b\b(?!\s*\()', r'\1stringTable', c)
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)c\b(?!\s*\()', r'\1player', c)
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)d\b(?!\s*\()', r'\1npcList', c)
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)k\b(?!\s*\()', r'\1mapName', c)
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)h\b(?!\s*\()', r'\1playerSpawnX', c)
        c = re.sub(r'(\b(?:game\.)?WorldManager\.getInstance\(\)\.)i\b(?!\s*\()', r'\1playerSpawnY', c)

        # In OverworldScreen, calls on this.worldManager:
        if Path(jf).name == 'OverworldScreen.java':
            c = re.sub(r'(\bthis\.worldManager\.)a\b(?!\s*\()', r'\1tileMapRenderer', c)
            c = re.sub(r'(\bthis\.worldManager\.)b\b(?!\s*\()', r'\1stringTable', c)
            c = re.sub(r'(\bthis\.worldManager\.)c\b(?!\s*\()', r'\1player', c)
            c = re.sub(r'(\bthis\.worldManager\.)d\b(?!\s*\()', r'\1npcList', c)
            c = re.sub(r'(\bthis\.worldManager\.)k\b(?!\s*\()', r'\1mapName', c)
            c = re.sub(r'(\bthis\.worldManager\.)h\b(?!\s*\()', r'\1playerSpawnX', c)
            c = re.sub(r'(\bthis\.worldManager\.)i\b(?!\s*\()', r'\1playerSpawnY', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_engine_subsystems():
    """
    Refactor TileMapRenderer, GameStateController, and ScriptEngine singletons and main fields.
    """
    print(">>> [7/8] Refactoring TileMapRenderer, GameStateController, ScriptEngine...")

    # 1. TileMapRenderer.java
    tmr_path = TARGET_DIR / 'game/TileMapRenderer.java'
    with open(tmr_path, 'r', encoding='utf-8') as f:
        code = f.read()

    code = re.sub(r'\bprivate\s+static\s+TileMapRenderer\s+u\s*;', 'private static TileMapRenderer instance;', code)
    code = re.sub(r'\bif\s*\(\s*u\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bu\s*=\s*new\s+TileMapRenderer\(\);', 'instance = new TileMapRenderer();', code)
    code = re.sub(r'\bu\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+u\s*;', 'return instance;', code)

    with open(tmr_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # 2. GameStateController.java
    gsc_path = TARGET_DIR / 'game/GameStateController.java'
    with open(gsc_path, 'r', encoding='utf-8') as f:
        code = f.read()

    code = re.sub(r'\bprivate\s+static\s+GameStateController\s+h\s*=\s*null\s*;', 'private static GameStateController instance = null;', code)
    code = re.sub(r'\bif\s*\(\s*h\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bh\s*=\s*new\s+GameStateController\(\);', 'instance = new GameStateController();', code)
    code = re.sub(r'\bh\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+h\s*;', 'return instance;', code)

    code = re.sub(r'\bprivate\s+BaseScreen\s+m\s*;', 'private BaseScreen currentScreen;', code)
    code = re.sub(r'\bthis\.m(?!\s*\()\b', 'this.currentScreen', code)

    code = re.sub(r'\bprivate\s+Player\s+t\s*;', 'private Player mediaPlayer;', code)
    code = re.sub(r'\bthis\.t(?!\s*\()\b', 'this.mediaPlayer', code)

    code = re.sub(r'\bprivate\s+VolumeControl\s+u\s*;', 'private VolumeControl volumeControl;', code)
    code = re.sub(r'\bthis\.u(?!\s*\()\b', 'this.volumeControl', code)

    with open(gsc_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # 3. ScriptEngine.java
    se_path = TARGET_DIR / 'game/ScriptEngine.java'
    with open(se_path, 'r', encoding='utf-8') as f:
        code = f.read()

    code = re.sub(r'\bprivate\s+static\s+ScriptEngine\s+n\s*;', 'private static ScriptEngine instance;', code)
    code = re.sub(r'\bif\s*\(\s*n\s*==\s*null\s*\)', 'if (instance == null)', code)
    code = re.sub(r'\bn\s*=\s*new\s+ScriptEngine\(\);', 'instance = new ScriptEngine();', code)
    code = re.sub(r'\bn\s*=\s*null\s*;', 'instance = null;', code)
    code = re.sub(r'\breturn\s+n\s*;', 'return instance;', code)

    code = re.sub(r'\bprivate\s+BaseScreen\s+o\s*;', 'private BaseScreen parentScreen;', code)
    code = re.sub(r'\bthis\.o(?!\s*\()\b', 'this.parentScreen', code)

    code = re.sub(r'\bprivate\s+UIManager\s+p\s*=\s*UIManager\.getInstance\(\);', 'private UIManager uiManager = UIManager.getInstance();', code)
    code = re.sub(r'\bthis\.p(?!\s*\()\b', 'this.uiManager', code)

    code = re.sub(r'\bprivate\s+Player\s+q\s*;', 'private Player player;', code)
    code = re.sub(r'\bthis\.q(?!\s*\()\b', 'this.player', code)

    with open(se_path, 'w', encoding='utf-8') as f:
        f.write(code)


def sanitize_syntax_artifacts():
    """
    Sweep across all java files to clean any double-equals spaces '= ='
    or invalid artifacts from regex passes.
    """
    print(">>> [8/8] Sanitizing syntax and spacing across reference codebase...")
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    fixed_count = 0
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            content = f.read()
        orig = content
        # Fix any '= =' to '=='
        content = re.sub(r'=\s+=', '==', content)
        # Fix any '! =' to '!='
        content = re.sub(r'!\s+=', '!=', content)
        # Fix any '.instance' if mistakenly attached to an NPC or item
        content = re.sub(r'(\bnpcList\[[^\]]+\])\.instance\b', r'\1.v', content)
        if content != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(content)
            fixed_count += 1
    print(f"    Sanitized {fixed_count} file(s).")


def run_all():
    print("==================================================")
    print("  VQSV CORE SEMANTIC REFACTORING ENGINE")
    print("==================================================")
    refactor_base_and_world_entity()
    refactor_map_engine()
    refactor_battle_screen()
    refactor_pet()
    refactor_player()
    refactor_world_manager_and_overworld()
    refactor_engine_subsystems()
    sanitize_syntax_artifacts()
    print("==================================================")
    print("  Semantic Refactoring Completed Successfully!")
    print("==================================================")


if __name__ == '__main__':
    run_all()
