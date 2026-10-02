# UI and System Integration

Contents: layout/runtime model; existing text changes; topology changes;
new screens/widgets; the speed-system example; system integration checklist.
Baseline verified on 2026-10-02; recheck active overrides in the current checkout.

## Layout and runtime model

UI resources in `data/ui/*.ui` are recursive binary layouts. Parser `ao`
(TextRenderer) instantiates type 0 `al` (container), type 1 `af` (styled text/icon
widget), and type 2 `ac` (grid/box widget). Widget interface `w` provides rendering,
geometry, visibility and child operations. Styling object `k`, returned by
`w.h()`, holds text/background/icon properties. `m` renders UI sprites; `z`
holds selection/navigation configuration. A class called UIButton in reference
does not imply a separate binary layout type called Button.

Original view allocation holds 200 component slots; each `al` allocates 60 child
slots. The UI loader has a 20000-byte buffer. IDs looked up by `ao.a(id)` are not
automatically array offsets. Geometry targets the original 240×320 display.

`ab` maintains loaded views and the display stack:

| Operation | Runtime call |
| --- | --- |
| Singleton / active view | `ab.a()` / public field `ab.a` |
| Open | `ab.a(path, spriteId, listener)` |
| Close | `ab.a(path)` |
| Path is topmost / in stack | `ab.b(path)` / `ab.c(path)` |
| Widget lookup | `ao.a(componentId)` |
| Callback interface | `i.a(int[])` |

`dialog.ui` has special caching/reuse behavior. Screen controller modes and the
UI stack must agree when opening, confirming, cancelling, or returning to a parent.

## Change existing text or icon content

`help.ui` serves multiple views. ScriptEngine's options flow (`s()`) opens it
with sprite ID 257, sets component 5 to `Tùy chọn`, and uses component 8 as the
content area. The open parameter feeds sprite loading; it is not an arbitrary
flag bitmask. Check both path/topmost state and title before changing this shared UI.

This helper compiled against the original JAR; visible behavior was not tested:

```java
public final class ExampleUiText {
    public static boolean updateOptionsText(String text) {
        ab manager = ab.a();
        if (!manager.b("/data/ui/help.ui") || manager.a == null) return false;
        ao view = manager.a;
        w title = view.a(5), content = view.a(8);
        if (title == null || content == null) return false;
        k heading = title.h(), style = content.h();
        if (heading == null || style == null) return false;
        if (!"Tùy chọn".equals(heading.a)) return false;
        style.a = text;
        return true;
    }
}
```

The helper belongs in the default package and still needs a real caller after
UI initialization. `GameSpeedConfig` rewrites component 8 roughly every 30 ms;
merge with that updater or change its behavior if another feature uses the area.
The engine uses `#n` for line breaks. Verify font glyphs and text clipping. Icon
frame indices inside UI sprite 257 differ from image/sprite IDs.

## Add a component, button, or icon

Inspect the closest existing layout and its callers. Reuse a hidden component
only after checking every branch that owns its ID. A new node requires a layout
reader/writer preserving the recursive tree, type-specific fields, child counts,
and selection metadata; the repository currently provides none.

Choose supported type, unique component ID, parent, geometry, and appropriate
text/sprite/frame properties. Update navigation/focus as well as the caller that
handles confirmation. Rendering a button does not make it interactive. Canvas
pointer handling contains specific regions and does not automatically hit-test
all newly added widgets.

Keycodes and bit masks are different. Many confirm branches use mask 196640;
262144 is soft-right; other callers use combined masks. Inspect BaseInputHandler
and the exact screen rather than assuming a universal confirm/back mapping.

Test directional navigation, 5/Fire/softkeys, back, repeated open/close, nested
modals, empty/long lists, long/localized text, and pointer interaction when supported.

## Add a screen or widget type

For a screen, start with a understood layout at a new resource path and connect
entry/return states, input handlers, population, update/render, and cleanup.
Keep controller state consistent with the UI stack after close.

For a new widget type, implement `w` and extend layout parsing, input, rendering,
and release behavior together. There is no self-registering plugin registry.
Use the J2ME Graphics/Canvas UI mechanism; AWT in the speed example integrates
with the desktop emulator rather than providing an in-game widget toolkit.

## System integration routes

| Feature shape | Integration point |
| --- | --- |
| Independent calculation/configuration | Called helper in `src/main/java` |
| Loaded data adjustment | Verified loader/post-load hook; handle reload |
| World/battle menu behavior | World/battle modes and `game.h` |
| Top-level screen | `game.i` and Canvas hooks; original state setter rejects values >=24 |
| Story event | Scene commands and supporting interpreter |
| Persistence | Matching world/RecordStore writer+reader, or independent record |

Original classes are often final and cross default-package boundaries. If the
needed hook is unavailable, compatible class recovery or checked bytecode work
is part of implementing the feature, not a reason to add an uncalled helper.

## Existing example: GameSpeedConfig

`GameMIDLet` creates the Canvas/thread, then invokes `GameSpeedConfig.init()` by
reflection. Init loads config, sets frame delay through reflected `an.c`, registers
an AWT KeyEventDispatcher, and starts a daemon UI watcher. Initialization has a
once-only guard, but it occurs after the game thread starts, not at a guaranteed
database/UI-ready event.

`setMultiplier()` applies speed and saves config. Normal levels 1/2/3/4 use target
delays 66/33/22/16 ms, not guaranteed measured FPS. A valid `frame_delay` takes
precedence over `speed`. Config lookup is cwd-relative: `speed.conf`,
`../speed.conf`, `runtime/speed.conf`; `project.py run` changes cwd and synchronizes
root configuration separately.

AWT and filesystem integration targets desktop. The 30-ms watcher writes UI
off the game thread; it is an existing workaround, not a general synchronization
model. Prefer verified game-thread hooks when available. `handleJ2meKey()` exists,
but no call was found in the original Canvas; comments alone do not prove `*`/`#`
are connected. Recheck newer patches before repeating that historical observation.

## Checklist for a new system

Define user-visible behavior and state ownership (global/player/pet/room/battle),
defaults, assets and IDs, initialization timing, input/update/render hooks,
and closing/pause/transition behavior. Consume/reset input appropriately so one
confirmation does not also activate background gameplay. Release screen-scoped
resources/listeners and avoid duplicate registration.

For persistence, choose whether the feature needs no saved state, an independent
versioned record, or a migration of existing positional data. There is no existing
general migration framework. Test missing/invalid configuration, unavailable
initial data, reopen, scene/battle transitions, and save/reload as applicable.
Keep failures observable with contextual logs instead of silently swallowing the
hook error. Match validation to the actual changed behavior.
