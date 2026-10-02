---
name: vqsv-game-development
description: Inspect, modify, and document the recovered Vuong Quoc Sung Vat (VQSV) J2ME game using its original-bytecode overlay build and binary assets. Use for work in this game repository, including pet content, UI, gameplay systems, and save compatibility.
---

# VQSV Game Development

Use the preserved project knowledge to make changes in the recovered VQSV game
without rediscovering its binary formats or confusing readable reference names
with runtime APIs. Adapt the depth of work to the user's request.

## Establish the current checkout

Locate the checkout from the working directory or the user's supplied path.
Confirm `project.py`, `original/game.jar`, `src/main/resources/data/script/db.mid`,
and `reference/decompiled/`. All project paths in this skill are relative to that
checkout. The repository-local skill lives in
`.agents/skills/vqsv-game-development/`; resolve its reference links relative to
the skill directory.

Check current source files, resource overrides, and existing user changes before
editing. The references record the original-game findings verified on
2026-10-02. Recheck affected facts against current source, assets, and bytecode;
new patches can change behavior without changing the readable reference code.
The project's `docs/EXTENDING-GAME.md` and `docs/SOURCE-MAP.md` provide the full
Vietnamese handbook and source map when available. This skill's references retain
the working knowledge in English.

## Read only the relevant references

| Task | Reference |
| --- | --- |
| Locate runtime code, integrate a patch, or understand build/save behavior | [Architecture and runtime APIs](references/architecture.md) |
| Replace/add a pet, prepare artwork, create instances, or extend collection | [Pets and sprites](references/pets.md) |
| Add UI content, widgets, screens, input, settings, or a system feature | [UI and system integration](references/ui-and-systems.md) |
| Add items, skills, NPCs, dialogue, quests, maps, or rooms | [Content extension recipes](references/content.md) |
| Decode, inspect, or write assets; design a packer | [Binary formats and asset tools](references/binary-formats.md) |

For changes that span subsystems, read the corresponding references together.
Read architecture before replacing a runtime class and binary formats before
writing packed assets.

## Invariants that affect implementation

- Build precedence is compiled Java, then resource overrides, then the original
  JAR. Only `src/main/java/` is compiled. Readable files in
  `reference/decompiled/` are reference material, with unresolved decompiler and
  renaming errors. Replacing `game.b` requires its binary identity and compatible
  signatures; adding `game.Pet` does not replace it.
- Compilation uses the original JAR and emulator as classpath, not patched
  resource classes. Inspect signatures with `javap`; inspect the output JAR when
  the patch differs from the original.
- Build & environment execution:
  - macOS OpenJDK: If `/usr/bin/java` or `javac` errors with "Unable to locate a Java Runtime",
    ensure `/opt/homebrew/opt/openjdk/bin` (or `$JAVA_HOME/bin`) is prepended to `PATH`.
  - Terminal sandbox & emulator access: The emulator JAR lives at `../emulator/freej2me_plus.jar`
    (outside the repository directory). Commands executing `project.py build`, `project.py run`,
    or `tools/rebuild_reference.py` must run unsandboxed (`BypassSandbox: true`) to avoid sandbox permission errors.
- Two-step reference refactoring workflow:
  1. Symbol Registry: Register classes, fields, methods, and parameters in `tools/reference-names.json`
     (preserving 1-line compact array format), then run `python3 tools/rebuild_reference.py --apply`.
     This propagates renames across all callers in `reference/decompiled/` with round-trip bytecode verification.
  2. Local Variables & Decompiler Fixes: Decompilers (CFR) produce synthetic local names (`n`, `s`, `by`)
     and occasional invalid syntax (`void var2_2;`). Once symbol renames are applied, polish the target
     file directly in `reference/decompiled/` with clean local variables, docstrings, and verified syntax.
- Pet ID, text ID, sprite ID, animation ID, image ID, UI component ID, and icon
  frame index are distinct. Trace references rather than replacing equal numbers
  globally.
- Original pet animation IDs 86–185 synthesize five frames and three animations
  from one source frame. Their exported JSON contains synthesized runtime data;
  preserve raw data when implementing a lossless writer.
- `.mid` does not identify a format. UI layouts are binary; JSON exports are
  inspection artifacts. Confirm the loader before writing bytes or changing a
  resource extension.
- State transitions, input, caller registration, resource lifecycle, and save
  integration must be connected for the requested feature to become usable.
  Merely packaging a class, sprite, layout, or database row does not register it.
- Several persisted arrays have implicit fixed lengths. Extending collection,
  rooms, or progression can change the save layout even when pet instances use
  integer IDs. Trace both writer and reader for affected data.

## Apply and verify the requested change

Choose the smallest integration route that fulfills the request: resource/data
replacement, a called Java helper, compatible original-class replacement, or a
checked bytecode patch. Preserve active patches when replacing a class. Determine
required asset writers and runtime hooks from the affected format and caller;
implement them if the requested change needs them, rather than inventing a command
that the repository does not provide.

For binary writers, first verify an unchanged decode/encode reproduces original
bytes, then inspect the intentional field changes and referenced IDs. Check
counts, signed ranges, sentinel values, buffer limits, and string length units.

Use `python3 project.py build` for compilation and packaging checks. Use
`python3 project.py run` for gameplay verification when appropriate; it also
builds, launches the emulator, and can update development configuration and saves.
For UI/pet/map changes, inspect visible behavior and interactions in the relevant
screen. For persisted changes, test save/reload and the chosen old-save policy.

Report what was changed and which checks actually ran. Distinguish a compiled
example, an original-bytecode fixture, and a manual gameplay check. The historical
checks stored in the references are evidence about the baseline, not automatic
proof that a new change works.

## Maintain the knowledge when requested

Record newly verified mappings, schemas, hooks, and limits in the appropriate
focused reference and relevant project documentation. Keep uncertain field
semantics marked as uncertain; cite the loader/caller used to establish a fact.
Retire stale claims when runtime patches or data layouts change. Preserve the
boundary between known mechanisms and extension work that still needs an
implementation.
