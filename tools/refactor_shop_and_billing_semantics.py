#!/usr/bin/env python3
"""
Comprehensive Semantic Refactoring Engine:
Deciphers and renames obfuscated variables, fields, and methods into clear,
descriptive names across reference/decompiled/.

Focus areas:
1. Player & Pet: partyPetCount, petParty, getLevel(), hasBadge(), consumeBall(),
   gold, arenaPoints, getGold(), addGold(), resetGold(), hasGold(), dismount().
2. BaseInputHandler: isKeyPressed(), isKeyHeld(), isLeftSoftAreaTouched(), isRightSoftAreaTouched().
3. BaseScreen: billing fields (billingDialogState, selectedBillingPackageId, etc.),
   billing methods (getBillingPackageId, getBillingDialogState, confirmBillingPurchase, etc.),
   and setScreenMode().
4. ScriptEngine: openBodyShop(), handleBodyShopInput(), updateBodyShopDescription(),
   applyBodyShopPackage(), selectedShopIndex, dialogSubState, selectedSubMenuIndex,
   showSmsTipDialog(), closeSmsTipDialog(), handleSmsTipInput(), tickSaveDialog(),
   respawnPlayer(), openStandardDialog().
5. UIManager & TextRenderer: activeView, openUI(), closeUI(), isUILoaded(), isUIOpen(), navigateSelection().
6. WorldManager: levelUpPets, eligibleLevelUpPets, levelUpPetIndices, levelUpStatus.
"""

import os
import re
import glob
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
TARGET_DIR = ROOT / 'reference/decompiled'


def refactor_player_and_pet():
    print(">>> [1/6] Refactoring Player and Pet methods & currency fields...")
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)

    player_path = TARGET_DIR / 'game/Player.java'
    with open(player_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Rename hasBadge and consumeBall
    code = re.sub(r'\bpublic\s+final\s+boolean\s+k\s*\(\s*int\s+n2\s*\)', 'public final boolean hasBadge(int badgeId)', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+l\s*\(\s*int\s+n2\s*\)', 'public final boolean consumeBall(int ballId)', code)

    # Rename dismount
    code = re.sub(r'\bpublic\s+final\s+void\s+s\s*\(\s*\)\s*\{', 'public final void dismount() {', code)

    # Correct gold & arenaPoints fields and methods
    code = re.sub(r'\bprivate\s+int\s+primaryStepCounter\s*;', 'public int gold;', code)
    code = re.sub(r'\bprivate\s+int\s+secondaryStepCounter\s*;', 'public int arenaPoints;', code)
    code = re.sub(r'\bthis\.primaryStepCounter\b', 'this.gold', code)
    code = re.sub(r'\bthis\.secondaryStepCounter\b', 'this.arenaPoints', code)

    code = re.sub(r'\bpublic\s+final\s+int\s+getPrimaryStepCount\s*\(\s*\)', 'public final int getGold()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+addPrimarySteps\s*\(\s*int\s+delta\s*\)', 'public final void addGold(int delta)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+resetPrimarySteps\s*\(\s*\)', 'public final void resetGold()', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+hasExceededPrimarySteps\s*\(\s*int\s+threshold\s*\)', 'public final boolean hasGold(int threshold)', code)

    code = re.sub(r'\bpublic\s+final\s+int\s+getSecondaryStepCount\s*\(\s*\)', 'public final int getArenaPoints()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+addSecondarySteps\s*\(\s*int\s+delta\s*\)', 'public final void addArenaPoints(int delta)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+resetSecondarySteps\s*\(\s*\)', 'public final void resetArenaPoints()', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+hasExceededSecondarySteps\s*\(\s*int\s+threshold\s*\)', 'public final boolean hasArenaPoints(int threshold)', code)

    with open(player_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # 2. Update all callers across codebase for Player members:
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c

        # hasBadge / consumeBall on player references
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)k\(', r'\1hasBadge(', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)l\(', r'\1consumeBall(', c)

        # dismount() - call with no args
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)s\(\)', r'\1dismount()', c)

        # gold methods: s(int) -> addGold(int), t(int) -> hasGold(int)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)s\(', r'\1addGold(', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)t\(', r'\1hasGold(', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)addPrimarySteps\(', r'\1addGold(', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)hasExceededPrimarySteps\(', r'\1hasGold(', c)

        # arenaPoints methods: u(int) -> addArenaPoints(int), v(int) -> hasArenaPoints(int)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)u\((-?\d+|-?[a-zA-Z0-9_\.\[\]\(\)\+\-\*\/\s]+)\)', r'\1addArenaPoints(\2)', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)v\(', r'\1hasArenaPoints(', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)addSecondarySteps\(', r'\1addArenaPoints(', c)
        c = re.sub(r'(\b(?:(?:game\.)?Player\.getInstance\(\)|this\.player|this\.q|player)\.)hasExceededSecondarySteps\(', r'\1hasArenaPoints(', c)

        # partyPetCount (Player.A without parens)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)A\b(?!\s*[\(\w])', r'\1partyPetCount', c)
        c = re.sub(r'(\bthis\.player\.)A\b(?!\s*[\(\w])', r'\1partyPetCount', c)
        c = re.sub(r'(\bthis\.q\.)A\b(?!\s*[\(\w])', r'\1partyPetCount', c)

        # petParty (Player.z)
        c = re.sub(r'(\b(?:game\.)?Player\.getInstance\(\)\.)z\b(?!\s*[\(\w])', r'\1petParty', c)
        c = re.sub(r'(\bthis\.player\.)z\b(?!\s*[\(\w])', r'\1petParty', c)
        c = re.sub(r'(\bthis\.q\.)z\b(?!\s*[\(\w])', r'\1petParty', c)

        # Pet level getter .s() -> .getLevel()
        c = re.sub(r'(\bpetParty\[[^\]]+\]\.)s\(\)', r'\1getLevel()', c)
        c = re.sub(r'(\bz\[[^\]]+\]\.)s\(\)', r'\1getLevel()', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_base_input_handler():
    print(">>> [2/6] Refactoring BaseInputHandler input methods...")
    bih_path = TARGET_DIR / 'BaseInputHandler.java'
    with open(bih_path, 'r', encoding='utf-8') as f:
        code = f.read()

    code = re.sub(r'\bpublic\s+boolean\s+k\s*\(\s*int\s+n2\s*\)', 'public boolean isKeyPressed(int keyCodeMask)', code)
    code = re.sub(r'\bpublic\s+boolean\s+l\s*\(\s*int\s+n2\s*\)', 'public boolean isKeyHeld(int keyCodeMask)', code)
    code = re.sub(r'\bpublic\s+boolean\s+Q\s*\(\s*\)', 'public boolean isLeftSoftAreaTouched()', code)
    code = re.sub(r'\bpublic\s+boolean\s+R\s*\(\s*\)', 'public boolean isRightSoftAreaTouched()', code)

    with open(bih_path, 'w', encoding='utf-8') as f:
        f.write(code)

    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c

        # Calls on parentScreen, h2.o, this.parentScreen, var1_X:
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o|var1_\d+|var2_\d+)\.)k\(', r'\1isKeyPressed(', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o|var1_\d+|var2_\d+)\.)l\(', r'\1isKeyHeld(', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o|var1_\d+|var2_\d+)\.)Q\(\)', r'\1isLeftSoftAreaTouched()', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o|var1_\d+|var2_\d+)\.)R\(\)', r'\1isRightSoftAreaTouched()', c)

        if Path(jf).name in ('BaseScreen.java', 'OverworldScreen.java', 'BattleScreen.java', 'TitleScreen.java', 'GameStateController.java', 'WorldManager.java'):
            c = re.sub(r'(\bthis\.)k\(', r'\1isKeyPressed(', c)
            c = re.sub(r'(\bthis\.)l\(', r'\1isKeyHeld(', c)
            c = re.sub(r'(\bthis\.)Q\(\)', r'\1isLeftSoftAreaTouched()', c)
            c = re.sub(r'(\bthis\.)R\(\)', r'\1isRightSoftAreaTouched()', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_base_screen_billing():
    print(">>> [3/6] Refactoring BaseScreen billing fields and methods...")
    bs_path = TARGET_DIR / 'BaseScreen.java'
    with open(bs_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Fields
    code = re.sub(r'\bprivate\s+byte\s+l\s*;', 'private byte billingDialogState;', code)
    code = re.sub(r'\bprivate\s+static\s+byte\[\]\s+m\s*;', 'private static byte[] packagePurchaseCounts;', code)
    code = re.sub(r'\bprivate\s+SmsSender\s+n\s*=\s*null\s*;', 'private SmsSender smsSender = null;', code)
    code = re.sub(r'\bprivate\s+byte\s+o\s*;', 'private byte selectedBillingPackageId;', code)
    code = re.sub(r'\bprivate\s+byte\s+p\s*;', 'private byte currentSmsSuccessCount;', code)
    code = re.sub(r'\bprivate\s+byte\s+q\s*;', 'private byte requiredSmsCount;', code)
    code = re.sub(r'\bprivate\s+String\[\]\s+r\s*=', 'private String[] billingServiceCodes =', code)
    code = re.sub(r'\bprivate\s+byte\[\]\[\]\s+s\s*=', 'private byte[][] billingMetadata =', code)
    code = re.sub(r'\bprivate\s+String\[\]\[\]\s+t\s*=', 'private String[][] billingDescriptions =', code)
    code = re.sub(r'\bprivate\s+BillingCanvas\s+u\s*;', 'private BillingCanvas billingCanvas;', code)
    code = re.sub(r'\bpublic\s+static\s+boolean\s+W\s*;', 'public static boolean isGameStarted;', code)
    code = re.sub(r'\bpublic\s+static\s+boolean\s+X\s*;', 'public static boolean isVipUnlocked;', code)

    # Methods
    code = re.sub(r'\bpublic\s+abstract\s+void\s+a\s*\(\s*byte\s+val\s*\)\s*;', 'public abstract void setScreenMode(byte mode);', code)
    code = re.sub(r'\bpublic\s+final\s+int\s+L\s*\(\s*\)\s*\{[^}]*\}', 'public final int getBillingPackageId() {\n        return this.selectedBillingPackageId;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+M\s*\(\s*\)\s*\{[^}]*\}', 'public final boolean isPaymentComplete() {\n        return this.currentSmsSuccessCount >= this.requiredSmsCount;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+byte\s+N\s*\(\s*\)\s*\{[^}]*\}', 'public final byte getBillingDialogState() {\n        return this.billingDialogState;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+byte\s+O\s*\(\s*\)\s*\{[^}]*\}', 'public final byte getRequiredSmsCount() {\n        return this.requiredSmsCount;\n    }', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+g\s*\(\s*int\s+n2\s*\)', 'public final void confirmBillingPurchase(int option)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+h\s*\(\s*int\s+n2\s*\)', 'public final void handleBillingDialogAction(int action)', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+c\s*\(\s*byte\s+val\s*\)', 'public final boolean setBillingPackage(byte packageId)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+d\s*\(\s*byte\s+val\s*\)', 'public final void setBillingDialogState(byte state)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+b\s*\(\s*boolean\s+flag\s*\)', 'public final void onBillingResult(boolean success)', code)

    # Member accesses inside BaseScreen
    code = re.sub(r'\bthis\.l\b(?!\s*[\(\w])', 'this.billingDialogState', code)
    code = re.sub(r'\bthis\.o\b(?!\s*[\(\w])', 'this.selectedBillingPackageId', code)
    code = re.sub(r'\bthis\.p\b(?!\s*[\(\w])', 'this.currentSmsSuccessCount', code)
    code = re.sub(r'\bthis\.q\b(?!\s*[\(\w])', 'this.requiredSmsCount', code)
    code = re.sub(r'\bm\[', 'packagePurchaseCounts[', code)
    code = re.sub(r'\bthis\.n\b(?!\s*[\(\w])', 'this.smsSender', code)
    code = re.sub(r'\bthis\.r\[', 'this.billingServiceCodes[', code)
    code = re.sub(r'\bthis\.s\[', 'this.billingMetadata[', code)
    code = re.sub(r'\bthis\.t\[', 'this.billingDescriptions[', code)
    code = re.sub(r'\bthis\.u\b(?!\s*[\(\w])', 'this.billingCanvas', code)
    code = re.sub(r'\ban2\.p\b', 'an2.currentSmsSuccessCount', code)
    code = re.sub(r'\ban2\.q\b', 'an2.requiredSmsCount', code)
    code = re.sub(r'\ban2\.o\b', 'an2.selectedBillingPackageId', code)
    code = re.sub(r'\ban2\.d\(', 'an2.setBillingDialogState(', code)

    with open(bs_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # Subclasses implementing setScreenMode
    for sc in ('game/OverworldScreen.java', 'game/BattleScreen.java', 'game/TitleScreen.java'):
        sc_path = TARGET_DIR / sc
        with open(sc_path, 'r', encoding='utf-8') as f:
            c = f.read()
        c = re.sub(r'\bpublic\s+final\s+void\s+a\s*\(\s*byte\s+val\s*\)', 'public final void setScreenMode(byte mode)', c)
        with open(sc_path, 'w', encoding='utf-8') as f:
            f.write(c)

    # Cross-file synchronization for BaseScreen billing methods:
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c

        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)L\(\)', r'\1getBillingPackageId()', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)M\(\)', r'\1isPaymentComplete()', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)N\(\)', r'\1getBillingDialogState()', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)O\(\)', r'\1getRequiredSmsCount()', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)g\(', r'\1confirmBillingPurchase(', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)h\(', r'\1handleBillingDialogAction(', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)c\(', r'\1setBillingPackage(', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)d\(', r'\1setBillingDialogState(', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)b\(true\)', r'\1onBillingResult(true)', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)b\(false\)', r'\1onBillingResult(false)', c)
        c = re.sub(r'(\b(?:this\.parentScreen|parentScreen|h2\.o)\.)a\(\(byte\)', r'\1setScreenMode((byte)', c)

        c = re.sub(r'\bBaseScreen\.X\b', 'BaseScreen.isVipUnlocked', c)
        c = re.sub(r'\bBaseScreen\.W\b', 'BaseScreen.isGameStarted', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_script_engine_shop_and_dialogs():
    print(">>> [4/6] Refactoring ScriptEngine bodyShop, dialogs & state fields...")
    se_path = TARGET_DIR / 'game/ScriptEngine.java'
    with open(se_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Methods
    code = re.sub(r'\bpublic\s+final\s+void\s+az\s*\(\s*\)', 'public final void openBodyShop()', code)
    code = re.sub(r'\bprivate\s+void\s+bs\s*\(\s*\)', 'private void updateBodyShopDescription()', code)
    code = re.sub(r'\bprivate\s+void\s+bt\s*\(\s*\)', 'private void applyBodyShopPackage()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+aA\s*\(\s*\)', 'public final void handleBodyShopInput()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+aK\s*\(\s*\)', 'public final void showSmsTipDialog()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+aL\s*\(\s*\)', 'public final void closeSmsTipDialog()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+aM\s*\(\s*\)', 'public final void handleSmsTipInput()', code)
    code = re.sub(r'\bprivate\s+void\s+bw\s*\(\s*\)', 'private void openSmsInfoUi()', code)
    code = re.sub(r'\bprivate\s+void\s+bx\s*\(\s*\)', 'private void closeSmsInfoUi()', code)
    code = re.sub(r'\bprivate\s+boolean\s+bu\s*\(\s*\)', 'private boolean tickSaveDialog()', code)
    code = re.sub(r'\bprivate\s+boolean\s+isDialogTimerExpired\s*\(\s*\)', 'private boolean tickSaveDialog()', code)
    code = re.sub(r'\bprivate\s+void\s+bv\s*\(\s*\)', 'private void respawnPlayer()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+aB\s*\(\s*\)', 'public final void openStandardDialog()', code)

    # Dialog & Tip methods in ScriptEngine
    code = re.sub(r'\bpublic\s+final\s+void\s+H\s*\(\s*\)', 'public final void openMsgTipUi()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+I\s*\(\s*\)', 'public final void closeMsgTipUi()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+J\s*\(\s*\)', 'public final void hideMsgTipButtons()', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+a\s*\(\s*String\s+text\s*,\s*String\s+string2\s*\)', 'public final void setWarningDialogMessage(String text, String buttonHint)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+a\s*\(\s*String\s+text\s*\)', 'public final void setMsgTipText(String text)', code)

    # Call sites inside ScriptEngine
    code = re.sub(r'\bthis\.bs\(\)', 'this.updateBodyShopDescription()', code)
    code = re.sub(r'\bthis\.bt\(\)', 'this.applyBodyShopPackage()', code)
    code = re.sub(r'\bthis\.az\(\)', 'this.openBodyShop()', code)
    code = re.sub(r'\bthis\.aA\(\)', 'this.handleBodyShopInput()', code)
    code = re.sub(r'\bthis\.aK\(\)', 'this.showSmsTipDialog()', code)
    code = re.sub(r'\bthis\.aL\(\)', 'this.closeSmsTipDialog()', code)
    code = re.sub(r'\bthis\.aM\(\)', 'this.handleSmsTipInput()', code)
    code = re.sub(r'\bthis\.bw\(\)', 'this.openSmsInfoUi()', code)
    code = re.sub(r'\bthis\.bx\(\)', 'this.closeSmsInfoUi()', code)
    code = re.sub(r'\bthis\.bu\(\)', 'this.tickSaveDialog()', code)
    code = re.sub(r'\bthis\.isDialogTimerExpired\(\)', 'this.tickSaveDialog()', code)
    code = re.sub(r'\bthis\.bv\(\)', 'this.respawnPlayer()', code)
    code = re.sub(r'\bthis\.aB\(\)', 'this.openStandardDialog()', code)
    code = re.sub(r'\bthis\.H\(\)', 'this.openMsgTipUi()', code)
    code = re.sub(r'\bthis\.I\(\)', 'this.closeMsgTipUi()', code)
    code = re.sub(r'\bthis\.J\(\)', 'this.hideMsgTipButtons()', code)
    code = re.sub(r'\bthis\.a\(("Trong ba lô[^"]*",\s*"[^"]*")\)', r'this.setWarningDialogMessage(\1)', code)
    code = re.sub(r'\bthis\.a\(("Kim tiền[^"]*",\s*"[^"]*")\)', r'this.setWarningDialogMessage(\1)', code)
    code = re.sub(r'\bthis\.a\(("Đang lưu\.\.\.")\)', r'this.setMsgTipText(\1)', code)
    code = re.sub(r'\bthis\.a\(("Lưu thành công")\)', r'this.setMsgTipText(\1)', code)

    # Fields
    code = re.sub(r'\bprotected\s+int\s+c\s*;', 'protected int selectedShopIndex;', code)
    code = re.sub(r'\bprotected\s+int\s+f\s*;', 'protected int dialogSubState;', code)
    code = re.sub(r'\bprotected\s+int\s+b\s*;', 'protected int selectedSubMenuIndex;', code)
    code = re.sub(r'\bprotected\s+boolean\s+g\s*;', 'protected boolean isDialogActive;', code)

    code = re.sub(r'\bthis\.c\b(?!\s*[\(\w])', 'this.selectedShopIndex', code)
    code = re.sub(r'\bthis\.f\b(?!\s*[\(\w])', 'this.dialogSubState', code)
    code = re.sub(r'\bthis\.b\b(?!\s*[\(\w])', 'this.selectedSubMenuIndex', code)
    code = re.sub(r'\bthis\.g\b(?!\s*[\(\w])', 'this.isDialogActive', code)

    with open(se_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # Cross-file callers for ScriptEngine methods and fields (WITH negative lookahead for methods!)
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        if Path(jf).name == 'ScriptEngine.java':
            continue
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)az\(\)', r'\1openBodyShop()', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)aA\(\)', r'\1handleBodyShopInput()', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)aK\(\)', r'\1showSmsTipDialog()', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)aL\(\)', r'\1closeSmsTipDialog()', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)aM\(\)', r'\1handleSmsTipInput()', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)aB\(\)', r'\1openStandardDialog()', c)

        # Fields only - never match method invocations like .b(...) or .c(...)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)f\b(?!\s*[\(\w])', r'\1dialogSubState', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)c\b(?!\s*[\(\w])', r'\1selectedShopIndex', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)b\b(?!\s*[\(\w])', r'\1selectedSubMenuIndex', c)
        c = re.sub(r'(\b(?:this\.S|this\.scriptEngine|scriptEngine)\.)g\b(?!\s*[\(\w])', r'\1isDialogActive', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_ui_manager_and_text_renderer():
    print(">>> [5/6] Refactoring UIManager activeView and TextRenderer navigation...")
    uim_path = TARGET_DIR / 'UIManager.java'
    with open(uim_path, 'r', encoding='utf-8') as f:
        code = f.read()

    # Field a -> activeView
    code = re.sub(r'\bpublic\s+TextRenderer\s+a\s*;', 'public TextRenderer activeView;', code)
    code = re.sub(r'\bthis\.a\b(?!\s*[\(\w])', 'this.activeView', code)

    # Method openUI
    code = re.sub(r'\bpublic\s+final\s+void\s+a\s*\(\s*String\s+text\s*,\s*int\s+n2\s*,\s*ScriptEventListener\s+i2\s*\)', 'public final void openUI(String path, int flag, ScriptEventListener listener)', code)
    code = re.sub(r'\bpublic\s+final\s+void\s+a\s*\(\s*String\s+text\s*\)', 'public final void closeUI(String path)', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+b\s*\(\s*String\s+text\s*\)', 'public final boolean isUILoaded(String path)', code)
    code = re.sub(r'\bpublic\s+final\s+boolean\s+c\s*\(\s*String\s+text\s*\)', 'public final boolean isUIOpen(String path)', code)

    with open(uim_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # TextRenderer navigateSelection
    tr_path = TARGET_DIR / 'TextRenderer.java'
    with open(tr_path, 'r', encoding='utf-8') as f:
        code = f.read()
    code = re.sub(r'\bpublic\s+final\s+boolean\s+b\s*\(\s*int\s+n2\s*\)', 'public final boolean navigateSelection(int direction)', code)
    with open(tr_path, 'w', encoding='utf-8') as f:
        f.write(code)

    # Update callers across all files:
    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c

        # this.uiManager.a -> this.uiManager.activeView
        c = re.sub(r'(\b(?:this\.uiManager|uiManager|p)\.)a\b(?!\s*[\(\w])', r'\1activeView', c)

        # activeView.b(dir) -> activeView.navigateSelection(dir)
        c = re.sub(r'(\bactiveView\.)b\(', r'\1navigateSelection(', c)

        # openUI, closeUI, isUILoaded, isUIOpen calls
        c = re.sub(r'(\b(?:this\.uiManager|uiManager|p)\.)a\((["\'][^"\']+["\'],\s*\d+,\s*this)\)', r'\1openUI(\2)', c)
        c = re.sub(r'(\b(?:this\.uiManager|uiManager|p)\.)a\((["\'][^"\']+["\'])\)', r'\1closeUI(\2)', c)
        c = re.sub(r'(\b(?:this\.uiManager|uiManager|p)\.)b\((["\'][^"\']+["\'])\)', r'\1isUILoaded(\2)', c)
        c = re.sub(r'(\b(?:this\.uiManager|uiManager|p)\.)c\((["\'][^"\']+["\'])\)', r'\1isUIOpen(\2)', c)

        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def refactor_world_manager_level_vectors():
    print(">>> [6/6] Refactoring WorldManager levelUpPets and related vectors...")
    wm_path = TARGET_DIR / 'game/WorldManager.java'
    with open(wm_path, 'r', encoding='utf-8') as f:
        code = f.read()

    code = re.sub(r'\bpublic\s+static\s+Vector\s+H\s*;', 'public static Vector levelUpPets;', code)
    code = re.sub(r'\bpublic\s+static\s+Vector\s+E\s*;', 'public static Vector eligibleLevelUpPets;', code)
    code = re.sub(r'\bpublic\s+static\s+Vector\s+F\s*;', 'public static Vector levelUpPetIndices;', code)
    code = re.sub(r'\bpublic\s+static\s+byte\s+G\s*;', 'public static byte levelUpStatus;', code)

    with open(wm_path, 'w', encoding='utf-8') as f:
        f.write(code)

    all_java = glob.glob(str(TARGET_DIR / '**/*.java'), recursive=True)
    for jf in all_java:
        with open(jf, 'r', encoding='utf-8') as f:
            c = f.read()
        orig = c
        c = re.sub(r'\b(?:game\.)?WorldManager\.H\b(?!\s*[\(\w])', 'game.WorldManager.levelUpPets', c)
        c = re.sub(r'\b(?:game\.)?WorldManager\.E\b(?!\s*[\(\w])', 'game.WorldManager.eligibleLevelUpPets', c)
        c = re.sub(r'\b(?:game\.)?WorldManager\.F\b(?!\s*[\(\w])', 'game.WorldManager.levelUpPetIndices', c)
        c = re.sub(r'\b(?:game\.)?WorldManager\.G\b(?!\s*[\(\w])', 'game.WorldManager.levelUpStatus', c)
        if c != orig:
            with open(jf, 'w', encoding='utf-8') as f:
                f.write(c)


def run_all():
    print("==================================================")
    print("  VQSV SHOP & BILLING SEMANTIC REFACTORING ENGINE")
    print("==================================================")
    refactor_player_and_pet()
    refactor_base_input_handler()
    refactor_base_screen_billing()
    refactor_script_engine_shop_and_dialogs()
    refactor_ui_manager_and_text_renderer()
    refactor_world_manager_level_vectors()
    print("==================================================")
    print("  Refactoring complete!")
    print("==================================================")


if __name__ == '__main__':
    run_all()
