#!/usr/bin/env python3
"""
Automated Bytecode Patcher:
1. Bypasses SMS billing across the game (auto-succeeds transactions instantly).
2. Unlocks VIP / Portable Shop (BaseScreen.X = true) permanently.
3. Fixes shortcut key handling in ScriptEngine (aA and aM) so players can use
   Num 5 / Enter / Fire (196640) in addition to Soft Left (131072).
4. Skips the "Tin nhắn gửi đi thành công" notification popup and wait delay so
   purchases apply immediately without annoying prompts.
"""

import os
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
ORIGINAL = ROOT / 'original/game.jar'
RESOURCES = ROOT / 'src/main/resources'


def patch_classes():
    print("==================================================")
    print("  VQSV SMS BYPASS & NOTIFICATION SKiP PATCHER")
    print("==================================================")

    with zipfile.ZipFile(ORIGINAL) as z:
        an_data = bytearray(z.read('an.class'))
        h_data = bytearray(z.read('game/h.class'))
        k_data = bytearray(z.read('game/k.class'))

    # -------------------------------------------------------------
    # 1. Patch an.class (BaseScreen): Bypass SMS & Skip Success Noti
    # -------------------------------------------------------------
    # In g(int): trigger b(true) directly without SMS
    idx_an = an_data.find(bytes([0x2a, 0x07, 0xb6, 0x00, 0x6a, 0x2a, 0xb7, 0x00, 0x66]))
    assert idx_an != -1, "Could not find SMS pattern in an.class"
    repl_an = bytes([0x2a, 0x04, 0xb6, 0x00, 0x69, 0xb1, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00])
    an_data[idx_an + 5 : idx_an + 5 + len(repl_an)] = repl_an
    print("  [OK] Patched an.class: g(int) triggers b(true) directly.")

    # In d(byte): Skip "Tin nhắn gửi đi thành công" notification in case 2 (jump straight to end)
    p_d2 = bytes([0x2a, 0xb6, 0x00, 0x65, 0x99, 0x00, 0x40, 0x2a, 0xb4, 0x00, 0x50])
    idx_d2 = an_data.find(p_d2)
    assert idx_d2 != -1, "Could not find case 2 pattern in an.d(byte)"
    an_data[idx_d2 : idx_d2 + 3] = bytes([0xa7, 0x00, 0xa5])  # goto 337
    for i in range(idx_d2 + 3, idx_d2 + 68):
        an_data[i] = 0x00  # nop out the text display logic
    print("  [OK] Patched an.class: skipped 'Tin nhắn gửi đi thành công' popup display.")

    # In an.class: Make field 'c' (frameDelay) public (ACC_PUBLIC | ACC_STATIC = 0x0009)
    assert an_data[4720:4722] == bytes([0x00, 0x0a]), "Mismatch at an.class field c access flags"
    an_data[4720:4722] = bytes([0x00, 0x09])
    print("  [OK] Patched an.class: field 'c' (frameDelay) made public static.")

    # In an.class: Default an.A() resetFrameDelay to 33ms (2x speed) instead of 66ms
    p_reset_fps = bytes([0x10, 0x42, 0xb3, 0x00, 0x44, 0xb1])
    idx_rf = an_data.find(p_reset_fps)
    assert idx_rf != -1, "Could not find resetFrameDelay pattern in an.class"
    an_data[idx_rf + 1] = 0x21  # bipush 33 (2x speed: 30 FPS)
    print("  [OK] Patched an.class: A() (resetFrameDelay) defaults to 33ms (~30 FPS / 2x speed).")

    # -------------------------------------------------------------
    # 2. Patch game/k.class (WorldManager): Force BaseScreen.X = true
    # -------------------------------------------------------------
    idx_k = k_data.find(bytes([0x59, 0x4c, 0xb6, 0x02, 0x79, 0xb3, 0x01, 0x1b]))
    assert idx_k != -1, "Could not find RMS readBoolean pattern in game/k.class"
    k_data[idx_k + 2 : idx_k + 5] = bytes([0x57, 0x04, 0x00])  # pop, iconst_1, nop

    idx_k2 = k_data.find(bytes([0x57, 0x03, 0xac]), idx_k)
    assert idx_k2 != -1, "Could not find exception return pattern in game/k.class"
    k_data[idx_k2 + 1] = 0x04  # iconst_1
    print("  [OK] Patched game/k.class: ac() permanently enforces BaseScreen.X = true.")

    # -------------------------------------------------------------
    # 3. Patch game/h.class (ScriptEngine): Shortcut keys & instant dismiss
    # -------------------------------------------------------------
    # Replace ldc #4 (131072) with ldc #6 (196640)
    offsets = [84034, 84221, 84445, 89669, 89749, 21200]
    for off in offsets:
        expected = bytes([0x2a, 0xb4, 0x01, 0x81, 0x12, 0x04, 0xb6, 0x01])
        assert h_data[off : off + 8] == expected, f"Pattern mismatch at offset {off} in game/h.class"
        h_data[off + 5] = 0x06  # ldc #6 (196640)
    print(f"  [OK] Patched game/h.class: {len(offsets)} key checks updated to mask 196640 (Soft Left + Fire + 5).")

    # In aA() (bodyShop): no-op bu() && k(917504) in case 2 so dialog finishes instantly
    p_aA_case2 = bytes([0x2a, 0xb7, 0x02, 0x83, 0x99, 0x00, 0x5a, 0x2a, 0xb4, 0x01, 0x81, 0x12, 0x0a, 0xb6, 0x01, 0xde, 0x99, 0x00, 0x4e])
    idx_aA_c2 = h_data.find(p_aA_case2)
    assert idx_aA_c2 != -1, "Could not find bu() check in ScriptEngine.aA()"
    for i in range(idx_aA_c2, idx_aA_c2 + len(p_aA_case2)):
        h_data[i] = 0x00
    print("  [OK] Patched game/h.class: aA() instant finish without notification delay.")

    # In aM() (smsTip): no-op bu() && k(917504) in case 2 so overworld resumes instantly
    p_aM_case2 = bytes([0x2a, 0xb7, 0x02, 0x83, 0x99, 0x00, 0x59, 0x2a, 0xb4, 0x01, 0x81, 0x12, 0x0a, 0xb6, 0x01, 0xde, 0x99, 0x00, 0x4d])
    idx_aM_c2 = h_data.find(p_aM_case2)
    assert idx_aM_c2 != -1, "Could not find bu() check in ScriptEngine.aM()"
    for i in range(idx_aM_c2, idx_aM_c2 + len(p_aM_case2)):
        h_data[i] = 0x00
    print("  [OK] Patched game/h.class: aM() instant finish without notification delay.")

    # Save to src/main/resources
    out_an = RESOURCES / 'an.class'
    out_h = RESOURCES / 'game/h.class'
    out_k = RESOURCES / 'game/k.class'

    out_an.parent.mkdir(parents=True, exist_ok=True)
    out_h.parent.mkdir(parents=True, exist_ok=True)
    out_k.parent.mkdir(parents=True, exist_ok=True)

    out_an.write_bytes(an_data)
    out_h.write_bytes(h_data)
    out_k.write_bytes(k_data)

    print(f"Saved patched classes to {RESOURCES.relative_to(ROOT)}:")
    print(f"  - {out_an.relative_to(ROOT)}")
    print(f"  - {out_h.relative_to(ROOT)}")
    print(f"  - {out_k.relative_to(ROOT)}")
    print("==================================================")


if __name__ == '__main__':
    patch_classes()
