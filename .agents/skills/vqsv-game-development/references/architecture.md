# Architecture and Runtime APIs

Baseline: original-game investigation verified on 2026-10-02. Recheck affected
classes and overrides in the current checkout. Paths here are project-relative.

## Build and execution model

| Location | Actual role |
| --- | --- |
| `src/main/java/game/GameMIDLet.java` | Editable entry point |
| `src/main/java/GameSpeedConfig.java` | Editable desktop speed/configuration integration |
| `src/main/resources/` | Assets and binary class overrides |
| `original/game.jar` | Original classes/assets used as the build baseline |
| `reference/decompiled/` | 68 renamed Java files for inspection; not compiler inputs |
| `runtime/` | Development emulator configuration and RMS saves |

`project.py build` checks `original/SHA256.txt`, compiles source with
`javac --release 8 -encoding UTF-8`, using `original/game.jar` and
`../emulator/freej2me_plus.jar` as classpath, then assembles
`build/vuong-quoc-sung-vat-dev.jar`.

For the same JAR entry, precedence is **compiled class > resource override >
original entry**. Deleting an extracted resource lets the original entry return.
The builder does not run patch scripts or import recovered JSON. This build
targets FreeJ2ME on desktop; it has no CLDC preverification/device toolchain.

`project.py run` builds, switches cwd to `runtime`, synchronizes root speed
configuration when newer, and opens the emulator at 240×320. Close the prior
game instance before launching the new JAR. This command can update config/saves.
`project.py decompile` writes `build/decompiled` and stops if it already exists.

## Runtime flow

```text
GameMIDLet -> game.e (Canvas/thread) -> game.i (top-level state)
    -> game.f (title)
    -> game.k / game.c (world / overworld events)
    -> game.d (battle)
game.h (interaction/menu control) -> ab / ao (UI stack/layout)
game.g / game.b (player/pet) -> game.k / ar (save serialization/RMS)
```

Canvas dispatches key/pointer events, calls update, repaint/serviceRepaints,
and sleeps according to frame delay `an.c`. Animation durations and many counters
advance in update ticks. Top-level game states and world/battle/menu modes are
separate state spaces; equal case numbers need not describe the same mode.

## Readable names versus binary names

In `reference/decompiled/game/`:

| Readable class | Binary class |
| --- | --- |
| NpcEntity | `game.a` |
| Pet | `game.b` |
| OverworldScreen | `game.c` |
| BattleScreen | `game.d` |
| GameCanvas | `game.e` |
| TitleScreen | `game.f` |
| Player | `game.g` |
| ScriptEngine | `game.h` |
| GameStateController | `game.i` |
| TileMapRenderer | `game.j` |
| WorldManager | `game.k` |

At the root of `reference/decompiled/` (binary classes are in the default package):

| Readable class | Binary class | Responsibility |
| --- | --- | --- |
| GameDatabase | `aq` | Database, text, sprite/image mappings |
| EngineUtils / ResourceStream | `ae` / `aj` | Binary readers, utilities / resource streams |
| ImageCache / AnimationCache | `am` / `aa` | Image / animation metadata caches |
| SpriteData / SpriteRenderer | `o` / `d` | Metadata / animated rendering |
| BaseEntity / WorldEntity | `n` / `f` | Entity state/position / sprite and facing |
| BaseInputHandler / BaseScreen | `ap` / `an` | Key masks / screen infrastructure |
| UIManager / TextRenderer | `ab` / `ao` | UI stack / recursive layout parser and rendering |
| UIComponent | `w` | Widget interface |
| MenuWidget / ItemListWidget / MessageBox | `al` / `af` / `ac` | Layout types 0 / 1 / 2 |
| UIButton / NumericInputWidget | `k` / `z` | Widget styling / selection/navigation configuration |
| SpriteWidget | `m` | Sprite presentation inside UI |
| ScriptCommand / ScriptSequence / ScriptEventListener | `ad` / `p` / `i` | Event command / sequence / callback |
| SkillEffect / ParticleEffect | `ah` / `ai` | Skill visuals / particle effects |
| MapEngine | `j` | Map data loader (/data/map/map_*.mid), tileset modules (/data/mod/mod_*.mid), layer compositor, double-buffered scrolling, and tile collision |
| SaveStorage | `ar` | RecordStore persistence |

The complete 68-class map is `CLASS_RENAME_MAP` in
`tools/rename_classes_and_files.py`; read it without executing that mutating script.
World/overworld also interpret event commands: the class named ScriptEngine is
not the sole event interpreter.

## API lookup

```bash
javap -classpath original/game.jar -p game.b aq aa ab ao
javap -classpath original/game.jar -p -c game.b
javap -classpath build/vuong-quoc-sung-vat-dev.jar -p an
rg --files src/main/resources -g '*.class'
```

| Operation | Original binary API |
| --- | --- |
| Pet initialization | `new game.b()`; `a(int,int,short,byte,short,byte)` |
| Pet ID / level / save vector | `game.b.q()` / `s()` / `P()` |
| Species / sprite mapping | `aq.c[0][petId]` / `aq.a[spriteId]` |
| Load renderer | `d.a(int,boolean)` |
| UI singleton | `ab.a()` |
| Open / close UI | `ab.a(String,int,i)` / `ab.a(String)` |
| Top UI / present in stack | `ab.b(String)` / `ab.c(String)` |
| Active view / widget lookup | `ab.a` / `ao.a(int)` |
| Styled widget text | `w.h().a`, through the returned `k` object |
| Top-level state / set state | `game.i.e()` / `game.i.a(byte)` |

Java named packages cannot import default-package types normally. Existing
integration places the helper in the default package and calls it from the MIDlet
through reflection. Many original classes are final. Preserving a replacement
requires binary name, superclass/interfaces, and externally referenced field and
method signatures, including overloads. A renamed reference file is not ready
source for a compatible replacement.

CFR reports unresolved control flow/types in `ao`, `b`, `q`, `a.h`, `game.b`,
`game.c`, `game.d`, `game.j`, `game.k`; these map to TextRenderer, ScreenView,
BillingCanvas, SecurityHelper, Pet, OverworldScreen, BattleScreen, TileMapRenderer,
and WorldManager. Unflagged files can still contain renaming/type errors.

## Existing patch and save constraints

`tools/patch_sms_and_shortcuts.py` recreates `an.class`, `game/h.class`, and
`game/k.class` from the original JAR. It changes billing, VIP/shop state,
confirmation handling, and delay/access behavior, using byte-pattern assertions.
Running it overwrites those outputs; inspect current overrides before preserving
or rebuilding a patch. Additional overrides may exist, such as `game/i.class`.

WorldManager serializes multiple records through SaveStorage. Pet instance
vectors use ints, but collection/progression arrays include bytes and implicit
fixed lengths. Increasing such a length makes later fields shift when loading
an old record. Trace matching readers/writers and either migrate versioned data
or add an independent record when the change requires persistence.

Historical validation established successful baseline build and editable entry
point execution, not complete gameplay equivalence. Inspect output entries and
check affected interactions before claiming a new feature works.
