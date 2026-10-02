# Content Extension Recipes

These recipes preserve the observed integration paths, not completed features.
Baseline fixtures were verified on 2026-10-02. For code edits also read
`architecture.md`; for packed resource changes read `binary-formats.md`.
Run project commands from the checkout, not this installed skill directory.

## Items

**Fixture:** Bánh Sandwich, database table 4, row 4:
`[265,29,282,100,0,1,50,50]`. Early fields cover name, icon, description, price.
Effect kind 1 in `Pet.w(int)` heals HP by percentage plus a fixed amount, using
50 and 50 in this row. Rows in this table have different lengths.

**Prepare:** name/description string entries, UI icon/frame, an appropriately
shaped row, and an acquisition/shop path. Reuse a known effect when it fulfills
the request; a new effect kind also needs execution and use-condition branches.

**Integrate:** trace Player's inventory category, ScriptEngine's bag/shop views,
and Pet's item effects. Categories and certain IDs are hardcoded. Appending a
row does not automatically put the item into a shop or the correct inventory.

**Verify:** build/run; obtain, buy, stack, use at full HP/depleted PP where relevant,
consume the correct quantity, sell, and reload. Typical mistakes: assuming every
item shares one table/row length, confusing icon frame with sprite ID, and
adding an enum value without its behavior branch.

## Skills and battle effects

**Fixture:** Điện giật, skill 40, table 1:
`[4,157,569,100,0,45,0,-1,-1,0]`; its name is text row 157. Baseline table has
70 rows of ten numbers, arranged as seven element blocks of ten skill IDs.
Pet's `F()` filters learnability using table 8; damage calculations also switch
on specific skill IDs.

**Prepare:** name/description, parameters, learning rules, remaining-use limits,
and required animation/effect metadata. Inspect Pet, BattleScreen, SkillEffect,
and the loaders for `effect.mid`, `speffect.mid`, and `bufDebuf.mid`.

**Integrate:** connect learning/equipping, target handling, damage/status behavior,
and visual-effect initialization/update/completion. Appending skill 70 does not
make it an eleventh Electric skill: the original enumeration still visits
`element*10` through `element*10+9`. Effect types and some skill behaviors are
hardcoded; preserving the row alone may not preserve behavior.

**Verify:** learning, target selection, use consumption, damage, status duration,
dead targets, both battle sides, completion, and saved skill sets. Missing effect
completion can stall the battle. CFR `GOTO` output is not executable Java.

## NPCs and dialogue

**Fixture:** `scene_1.mid`, room 0 is Thủy Mộc Thôn and references map 2; the actor
name list begins Hart, Emily, Dodo. First actor fields are
`[0,208,1,354,150,1,0,1,0,0,0,0,-1]`. This is a loader-tracing fixture, not a
universal speaking-NPC record.

**Prepare:** actor sprite/directions/animations, position, kind-specific fields,
name, interaction conditions, dialogue, and a trigger/event. Trace NpcEntity,
WorldManager's room loader, and OverworldScreen's interpreter. Actor coordinates
follow world-space loader semantics; do not assume tile indices.

**Integrate:** insert the correct actor-kind payload, update counts, maintain
actor/event references, and place text in the pool the caller uses. Some dialogue
comes from `npcDialog.mid`; event text may be in the room's string pool. Changing
actor indices can redirect existing scripts to a different character.

**Verify:** write updated room sizes, build/run, approach/interact, facing/movement,
collision, repeated dialogue, before/after quest conditions, and reload/return.
An added sprite alone does not create a world actor.

## Quests and scripted events

**Fixtures:** row 0 of `mTask.mid` is “Vòng loại sơ khảo”; row 0 of `bTask.mid` is
“Bắt sủng vật 1”. These are text tables, not complete quest definitions.
OverworldScreen also reads two consecutive byte tables from `bqTask.mid` and
executes room event commands with progression state.

**Prepare:** prerequisites, acceptance, progress/completion conditions, rewards,
dialogue, IDs, and re-entry/load behavior. A comparable existing quest is the
best source for command sequencing; preserve unexplained opcode semantics.

**Integrate:** connect text, relationship tables, trigger, command sequence,
and saved state. Trace ScriptCommand -> ScriptSequence -> actual interpreter.
For a new opcode, support initiation, waiting, and completion; many commands
span multiple ticks. Do not advance the program counter past an unfinished step.

**Verify:** unmet prerequisites, first acceptance, progress, reward once, repeat
interaction, abandoned/in-progress save and reload. Distinguish quest ID from
room-local event ID. Original allocations include 127 state positions and a
`short[200][2]` progression table; inspect indexing before extending them.

## Maps, tilesets, and rooms

**Fixture:** map 2 uses version 1, tileset 2, 26×20 cells, tile size 16, giving
416×320 pixels. The exporter preserves trailing byte `ff` as `trailing_bytes`.

**Prepare:** tileset images, modules in `mod_*.mid`, image mapping in `modInfo.mid`,
map cells/layers, room actors/events, entry/exit coordinates. Inspect MapEngine,
TileMapRenderer, and WorldManager.

**Integrate:** replacing a known map can validate an artwork/data change before
expanding world topology. A new map needs a referencing room and a reachable
transition. Map ID, scene ID, and room index are different. Added scenes/rooms
may require updates to fixed count/offset tables, encounter/mount data, and
saved event state. Layer kind determines interpretation; not every layer is a
background image or every tile entry a collision value.

**Verify:** edges, scrolling camera, occlusion, collision, NPCs, both transition
directions, encounters, mounts, and coordinate reload. Check tile/pixel units,
coordinate byte/short version, atlas references, and room/table offsets.

## Build and evidence

Use `python3 project.py build` after the requested data/code is placed in the
actual source/resource locations, then `python3 project.py run` for relevant
gameplay checks. A required missing packer or hook must be implemented for a
requested working feature; inspection exports alone do not finish it.
Clearly distinguish candidate data, compilation success, original-bytecode
fixtures, and observed gameplay when reporting results.
