# Pets and Sprites

Contents: verified fixture; artwork/animation; species schema; runtime instances;
replacement workflow; append workflow; acceptance checks. Baseline: 2026-10-02.
Project-relative evidence: `game/Pet.java`, `game/Player.java`,
`game/ScriptEngine.java`, `AnimationCache.java`, `SpriteRenderer.java` under
`reference/decompiled/`, original bytecode, and `src/main/resources/data/`.

## Verified fixture: Dien Mieu (Điện Miêu), species 68

```text
db.mid table 0, row 68
  column 0  = 78  -> chs.mid row 78 -> Điện Miêu
  column 17 = 154 -> sprite.mid row 154 = [154, 540]
                     animation 154 -> spr/spr_154_all(r)
                     image 540     -> img/img_540.mid
```

The image contains ordinary PNG bytes, dimensions 43×46. Renaming a replacement
to `img_540.png` does not affect the loader that requests `img_540.mid`.
Sprite 154 loads both battle presentation and the collection view through the
species sprite field. In the surveyed sprite table, only row 154 references image
540 and animation 154; still check other direct callers before editing shared IDs.

## Artwork and frame requirements

Original animation IDs 86–185 use one source module and one source frame. Cache
`aa.a(int)` creates five frames and overwrites the animation sequences. All 100
surveyed pet metadata files had one module/frame; 96 had one raw animation,
four had none. A single drawn image preserves this original movement mechanism.
Five runtime frames do not require five independently drawn poses.

For species 68:

- Module: `[0,0,0,43,46]` = image-list index 0, source x/y 0/0, width/height 43/46.
- Raw frame: `[0,-22,-46,0]` = module 0, placement offsets -22/-46, transform 0.
- Generated x shifts: `[0,10,3,7,-10]`; resulting x offsets are
  `[-22,-12,-19,-15,-32]`, all with y=-46. The anchor is near the middle of the feet.

| Animation | Duration/frame pairs | Observed caller |
| --- | --- | --- |
| 0 | `(2,0)` | Base state, can loop |
| 1 | `(1,0),(1,1),(1,2),(1,3),(1,2)` | Attack state, then animation 0 |
| 2 | `(5,0),(5,4)` | `Pet.d((byte)2)`, then animation 0 |

Durations count renderer update ticks, not milliseconds. State 3 has separate
effect behavior; some species have hardcoded attack effects keyed by pet ID.
Pet cases numbered 3/4 are not evidence that frame animations 3/4 must be drawn.
Facing can mirror the sprite; preserve the original anchor/crop and test both
sides before creating a separate opponent pose.

The renderer boolean called `loop` in the renamed reference selects two-value
versus four-value animation steps. Transition arguments govern looping/holding/
returning. To use hand-drawn poses for original IDs, change the cache override
or select an animation outside that range with complete metadata.

This budget does not apply to NPC/player/mount sprites. For comparison, sprite 0
had 44 modules, 33 frames, 12 animations; sprite 8 had 32, 16, 6. Inspect the actual
sprite for those actors.

## Species definition: table 0, 23 signed shorts

```text
[78,4,0,2,2,26,18,6,4,4,4,3,20,3,4,4,0,154,2,69,0,1,0]
```

Use `aq.c[0][petId]`. The field meanings below are observed effects, not recovered
original schema names; preserve the uncertainty where noted.

| Column | Fixture | Meaning / evidence |
| --- | ---: | --- |
| 0 | 78 | Name string ID in `chs.mid`; UI and `Pet.J()` |
| 1 | 4 | Element: 0 Fire, 1 Wood, 2 Earth, 3 Water, 4 Electric, 5 Ghost, 6 Wind; skill selection and affinity |
| 2 | 0 | Form/evolution code; target codes 1/2/3 select branches and level thresholds in `Pet.R()/J()`; other values not fully named |
| 3 | 2 | Default quality when initialization quality=-1; original call uses this column for both RNG endpoints |
| 4 | 2 | Participates in breeding/hatching conditions in ScriptEngine, including comparison with 5; complete semantics unknown |
| 5 | 26 | HP base coefficient |
| 6 | 18 | HP coefficient multiplied by level |
| 7 | 6 | HP additive term |
| 8 | 4 | Attack base coefficient |
| 9 | 4 | Attack coefficient multiplied by level |
| 10 | 4 | Attack additive term |
| 11 | 3 | Defense base coefficient |
| 12 | 20 | Defense coefficient multiplied by level, divided by 10 |
| 13 | 3 | Defense additive term |
| 14 | 4 | Agility/speed-like stat base coefficient (`c[4]/d[4]`) |
| 15 | 4 | That stat's coefficient multiplied by level, divided by 10 |
| 16 | 0 | That stat's additive term |
| 17 | 154 | Sprite ID, stored in runtime `game.b.C`; misleading alias `growthRate` |
| 18 | 2 | Row selector in table 8 for learnable-skill filtering |
| 19 | 69 | Next evolution species ID; -1 means no target |
| 20 | 0 | Evolution material code; requirement lookup uses code +12 |
| 21 | 1 | Material requirement quantity |
| 22 | 0 | Category affecting capture, affinity, collection counters; complete enum names unknown |

Quality 1–5 scales stats by `[90,95,100,110,125]` percent. HP is
`(column5 + column6 * level + column7) * qualityScale / 100` before other effects.
Attack uses columns 8–10. Defense/speed divide the level contribution by 10 with
integer arithmetic. Values are cast to signed short; check overflow. Original
maximum level is hardcoded to 50.

## Runtime instance creation and serialization

After loading all nine database tables into `aq.c`:

```java
game.b pet = new game.b();
pet.a(68, 7, (short)-1, (byte)2, (short)2, (byte)-1);
```

Parameters: species ID; level; initial `c[5]` (-1 leaves the associated attribute
unset); initial `c[6]` (existing flows often pass 2); quality 1–5 or -1 for
default; variant modifier (-1 avoids the branches for 7/8/9). Do not mistake
the third argument for the learned active-skill list or assume the reference
parameter aliases `skillId`, `rarity`, `nature` establish their full semantics.

`q()` returns species ID; `s()` returns level; `P()` emits instance save data.
The original-bytecode fixture with a fresh player produced:

```text
[68,7,-1,2,2,-1,150,0,0,0]
```

Layout: species, level, `c[5]`, `d[6]`, quality, variant, HP, experience, `E`,
skill count, then skill IDs followed by their remaining-use values. `E` retains
its runtime name because its full meaning was not established. The HP=150
fixture assumes no player/badge effects. Initialization alone does not add the
instance to the party, load its sprite, or register collection. Trace Player's
party flows; the original party has six slots, each pet up to five learned
skills. `g(byte)` adds a skill; `G()/F()` handle learning/eligibility.

## Replace an existing slot

1. Trace species, text, sprite, animation, image, evolution, and encounter links.
2. For the fixture, use transparent 43×46 PNG bytes at the `.mid` resource path
   to retain existing metadata. If dimensions change, update crop/anchor and
   applicable collision/attack areas.
3. Update string row 78 with correct UTF-16 lengths and species row 68 with 23
   fields. Retain unknown enum values unless the requested change needs them.
4. Write all nine database tables, preserving unaffected data. Stage and decode
   the candidate before placing it in `src/main/resources/`.
5. Connect an encounter/gift/test hook if the user needs an acquisition route.
   Example `petArea.mid` row: `[1,4,2,6,7,76,0,1,2,68,0,2,2]`. WorldManager takes
   groups of four from index 5, classifying by the second value in each group;
   trace the caller before assigning meanings to all remaining values.
6. Build and test acquisition, both battle sides, party/collection, level-up,
   evolution, and reload. Existing saves with species 68 acquire its new definition.

## Append a genuinely new species

Appending requires engine/data integration; the project has no `add-pet` command.
The baseline next species index is 100 and next sprite-table row is 345. These
are table indices, not proof that image/animation/file IDs are free.

- Append a new name and 23-field species row without shifting old IDs. Allocate
  sprite/animation/image IDs after inspecting all references. Prefer matching
  sprite and animation IDs for a new sprite: loading uses the mapping's first
  value, but release decrements animation cache using the sprite ID.
- Do not compute `86 + speciesId` beyond the original range. Animation 186 lies
  outside the synthesis rule and can already belong to other content. Supply
  complete animations 0/1/2 for metadata outside the range.
- Respect 1000 animation-cache slots, 50000 image-cache slots, signed-short
  mappings, and 20000-byte sprite metadata buffers. Cache capacity alone does
  not make image ID 40000 representable as a nonnegative signed short.
- Collection uses contiguous element ranges:
  `W=[0,16,32,48,64,76,88]`, `X=[16,16,16,16,12,12,12]`, plus `C/D` arrays.
  Collection UI indexes `W[element] + position`. Appending an Electric species
  at 100 does not put it inside Electric's range 64–75. Keeping old IDs while
  allowing append into any element requires replacing these contiguous-index
  assumptions with explicit per-element ID lists across all affected callers.
- Audit byte casts in evolution, collection, and events. ID 100 fits a signed
  byte, but existing arrays may still reject it; crossing 127 requires compatible
  serialization and comparison changes.
- Add collection counters, acquisition, evolution, and relevant skill links.
  Skills currently use seven blocks of ten IDs, a separate limitation.
- Restore compatible source or implement checked patches for the affected
  original classes, preserving active world/script patches.
- Design old-save handling before changing `C/D` lengths: their positional
  writer does not store each row's length. Pet instance int IDs do not fix that.

Acceptance: old species retain identity; the new species is obtainable,
rendered, playable, collected in its correct element, and reloadable under the
chosen save policy. Historical fixtures do not prove this extension was built.
