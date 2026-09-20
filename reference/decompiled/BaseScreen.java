/*
 * Decompiled with CFR 0.152.
 */
import game.GameMIDLet;
import game.GameCanvas;
import game.Player;
import game.ScriptEngine;
import game.WorldManager;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * BaseScreen - Abstract game screen/canvas base class extending BaseInputHandler; manages screen resolution, timer, and fonts.
 */
public abstract class BaseScreen
extends BaseInputHandler
implements Renderable,
TimerCallback {
    private static short screenWidth;
    private static short screenHeight;
    private static int frameDelay;
    private static Font smallFont;
    private static Font mediumFont;
    private static int keyDelay;
    public byte screenId;
    public byte previousScreenId;
    public UIManager R;
    public DebugLogger S;
    private static boolean g;
    public static boolean T;
    public static byte U;
    public static byte V;
    private static byte[][] h;
    private static Timer i;
    private static TimerTask j;
    private static boolean k;
    public static boolean isGameStarted;
    public static boolean isVipUnlocked;
    private byte billingDialogState;
    private static byte[] packagePurchaseCounts;
    private SmsSender smsSender = null;
    private byte selectedBillingPackageId;
    private byte currentSmsSuccessCount;
    private byte requiredSmsCount;
    private String[] billingServiceCodes = new String[]{"01", "02", "03", "04", "05"};
    private byte[][] billingMetadata = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
    private String[][] billingDescriptions = new String[][]{{"Kích hoạt", "Bạn muốn khám phá bí mật của vương quốc sủng vật, dẫn dắt thú yêu chiến đấu, tiến hóa, ấp trứng? Chỉ cần 1 tin nhắn 15000đ để kích hoạt trò chơi, chỉ nhắn tin 1 lần cho tất cả các lượt chơi. Bạn có muốn nhắn tin không?"}, {"Tất trúng cầu", "Chỉ cần nhắn 1 tin nhắn 10000đ, bạn sẽ sở hữu 1 tất trúng cầu, tỷ lệ 100% bắt được sủng vật? Bạn có muốn nhắn tin không?"}, {"Mua sắm kim tiền", "Kiếm tiền vất vả, vật phẩm đắt đỏ? Chỉ cần nhắn 1 tin nhắn 10000đ bạn sẽ đạt được 10000 kim tiền. Bạn có muốn nhắn tin không?"}, {"Mua đẳng cấp", "Thăng cấp chậm chạp, kẻ địch lại quá mạnh? Chỉ cần 1 tin nhắn 10000đ, tất cả sủng vật trong ba lô của bạn đều được thăng lên 5 cấp. Bạn có muốn nhắn tin không?"}, {"Mua sắm huy hiệu", "Kiếm huy hiệu khó khăn? Chỉ cần 1 tin nhắn 10000đ, bạn sẽ đạt được 10 huy hiệu. Bạn có muốn nhắn tin không?"}};
    private BillingCanvas billingCanvas;

    public abstract void b();

    public abstract void b(Graphics g);

    public abstract boolean d();

    public abstract void f();

    public abstract void setScreenMode(byte mode);

    public final void startTimer() {
        if (g) {
            return;
        }
        if (i == null || j == null) {
            i = new Timer();
            j = new CanvasRepaintTimerTask();
        }
        i.schedule(j, 10L, 200L);
        g = true;
    }

    protected static void t() {
        if (j != null) {
            j.cancel();
            j = null;
        }
        if (i != null) {
            i.cancel();
            i = null;
            System.gc();
        }
        g = false;
        k = true;
    }

    public static void resumeGame() {
        k = false;
    }

    public static boolean isPaused() {
        return k;
    }

    public static void setScreenSize(short width, short height) {
        screenWidth = width;
        screenHeight = height;
    }

    public static short getScreenWidth() {
        return screenWidth;
    }

    public static short getScreenHeight() {
        return screenHeight;
    }

    public static short getHalfWidth() {
        return (short)(screenWidth / 2);
    }

    public static short getHalfHeight() {
        return (short)(screenHeight / 2);
    }

    /**
     * Resets the game frame delay. Defaulted to 33ms (~30 FPS, 2x speed) by bytecode patch.
     * Dynamic speed (1x, 2x, 3x, 4x) is configured in speed.conf and managed by GameSpeedConfig.
     */
    public static void resetFrameDelay() {
        frameDelay = 33;
    }

    public static int getFrameDelay() {
        return frameDelay;
    }

    public static void setFrameDelay(int delay) {
        frameDelay = delay;
    }

    public static void setKeyDelay(int delay) {
        keyDelay = delay;
    }

    public static int getKeyDelay() {
        return keyDelay;
    }

    public static Font getSmallFont() {
        if (d == null) {
            d = Font.getFont(0, 0, 8);
        }
        return smallFont;
    }

    public static Font getMediumFont() {
        if (e == null) {
            e = Font.getFont(0, 0, 16);
        }
        return mediumFont;
    }

    public static int getFontCharWidth() {
        if (d == null) {
            return 18;
        }
        return d.stringWidth("Sủng");
    }

    public static int getFontHeight() {
        return d.getHeight();
    }

    public static String f(int n2) {
        if (n2 == 0) {
            return "";
        }
        return GameDatabase.d[n2];
    }

    public static String a(int n2, int[] intArray) {
        if (n2 == 0) {
            return "";
        }
        int n3 = 0;
        String string = "";
        int n4 = BaseScreen.f(n2).indexOf("%s", 0);
        if (n4 == -1) {
            return BaseScreen.f(n2);
        }
        int n5 = 0;
        while (n4 != -1) {
            string = string + BaseScreen.f(n2).substring(n5, n4) + intArray[n3];
            ++n3;
            n5 = n4 + 2;
            n4 = BaseScreen.f(n2).indexOf("%s", n5);
        }
        return string + BaseScreen.f(n2).substring(n5);
    }

    public static String a(String text, int[] intArray) {
        if (text.equals("")) {
            return "";
        }
        int n2 = 0;
        String string2 = "";
        int n3 = text.indexOf("%s", 0);
        if (n3 == -1) {
            return text;
        }
        int n4 = 0;
        while (n3 != -1) {
            string2 = string2 + text.substring(n4, n3) + intArray[n2];
            ++n2;
            n4 = n3 + 2;
            n3 = text.indexOf("%s", n4);
        }
        return string2 + text.substring(n4);
    }

    public static String a(int n2, String[] strArray) {
        if (n2 == 0) {
            return "";
        }
        int n3 = 0;
        String string = "";
        int n4 = BaseScreen.f(n2).indexOf("%s", 0);
        if (n4 == -1) {
            return BaseScreen.f(n2);
        }
        int n5 = 0;
        while (n4 != -1) {
            string = string + BaseScreen.f(n2).substring(n5, n4) + strArray[n3];
            ++n3;
            n5 = n4 + 2;
            n4 = BaseScreen.f(n2).indexOf("%s", n5);
        }
        return string + BaseScreen.f(n2).substring(n5);
    }

    public static void a(Graphics g, Image img, String text, int n2, int n3, int n4, int n5) {
        for (int i = 0; i < text.length(); ++i) {
            int n6 = text.charAt(i);
            if (Character.isDigit((char)n6)) {
                n6 = (char)(n6 - 48);
            } else {
                switch (n6) {
                    case 45: {
                        n6 = 10;
                        break;
                    }
                    case 43: {
                        n6 = 10;
                    }
                }
            }
            g.drawRegion(img, n6 * n4, 0, n4, n5, 0, n2 - ((text.length() - 1 - (i << 1)) * n4 >> 1), n3, 20);
        }
    }

    public static boolean H() {
        return U != -1;
    }

    public void l() {
    }

    public void m() {
    }

    public static boolean I() {
        if (U == -1) {
            return true;
        }
        return h[U][0] == 1;
    }

    public static boolean J() {
        if (U == -1) {
            return true;
        }
        return h[U][0] == 2;
    }

    public static boolean b(int n2, int n3) {
        if (U == -1) {
            return false;
        }
        if (n3 != h[U][2]) {
            return true;
        }
        if (h[U][1] == -1) {
            return true;
        }
        return h[U][1] == n2;
    }

    public static void c(int n2, int n3) {
        if (U == -1) {
            return;
        }
        if (n3 == -1) {
            BaseScreen.h[BaseScreen.U][2] = 0;
        }
        BaseScreen.h[BaseScreen.U][n2] = (byte)n3;
    }

    public static byte K() {
        if (U == -1) {
            return -1;
        }
        return h[U][1];
    }

    public final void onBillingResult(boolean success) {
        if (this.billingDialogState == 4) {
            if (flag) {
                BaseScreen an2 = this;
                an2.currentSmsSuccessCount = (byte)(an2.currentSmsSuccessCount + 1);
                byte by = an2.selectedBillingPackageId;
                packagePurchaseCounts[by] = (byte)(packagePurchaseCounts[by] + 1);
                System.out.println(" curNum = " + an2.currentSmsSuccessCount + " tolNum = " + an2.requiredSmsCount);
                if (an2.currentSmsSuccessCount >= an2.requiredSmsCount) {
                    switch (an2.selectedBillingPackageId) {
                        case 0: {
                            X = true;
                            game.Player.getInstance().addGold(2000);
                            game.Player.getInstance().c(1, 5, (byte)0);
                            game.Player.getInstance().c(4, 5, (byte)0);
                            game.Player.getInstance().c(11, 2, (byte)0);
                            game.Player.getInstance().addArenaPoints(5);
                            game.OverworldScreen.getInstance().b[game.WorldManager.a((int)9, (int)0)][5] = 3;
                            game.OverworldScreen.getInstance().a[5].a((byte)3);
                            break;
                        }
                        case 1: {
                            game.Player.getInstance().c(0, 1, (byte)0);
                            break;
                        }
                        case 2: {
                            game.Player.getInstance().addGold(10000);
                            break;
                        }
                        case 3: {
                            game.WorldManager.levelUpStatus = 0;
                            if (game.WorldManager.levelUpPetIndices == null) {
                                game.WorldManager.levelUpPetIndices = new Vector();
                            }
                            if (game.WorldManager.eligibleLevelUpPets == null) {
                                game.WorldManager.eligibleLevelUpPets = new Vector();
                            }
                            game.WorldManager.levelUpPetIndices.removeAllElements();
                            game.WorldManager.eligibleLevelUpPets.removeAllElements();
                            for (int i = 0; i < game.Player.getInstance().partyPetCount; ++i) {
                                if (game.Player.getInstance().petParty[i].getLevel() == 50) {
                                    game.Player.getInstance().petParty[i].J();
                                    continue;
                                }
                                game.Player.getInstance().petParty[i].x();
                                if (game.Player.getInstance().petParty[i].getLevel() + 5 >= 50) {
                                    game.Player.getInstance().petParty[i].h(50 - game.Player.getInstance().petParty[i].getLevel());
                                } else {
                                    game.Player.getInstance().petParty[i].h(5);
                                }
                                game.Player.getInstance().petParty[i].I();
                                if (game.Player.getInstance().petParty[i].E() >= 5 || game.Player.getInstance().petParty[i].E() >= game.Player.getInstance().petParty[i].getLevel() / 10 + 1) continue;
                                game.WorldManager.eligibleLevelUpPets.addElement(game.Player.getInstance().petParty[i]);
                                game.WorldManager.levelUpPetIndices.addElement("" + i);
                            }
                            if (game.WorldManager.eligibleLevelUpPets.size() <= 0) {
                                game.WorldManager.levelUpStatus = (byte)2;
                                break;
                            }
                            game.WorldManager.levelUpStatus = 1;
                            break;
                        }
                        case 4: {
                            game.Player.getInstance().addArenaPoints(10);
                        }
                    }
                }
                an2.setBillingDialogState((byte)2);
                return;
            }
            this.d((byte)3);
        }
    }

    private boolean a() {
        if (this.smsSender == null) {
            try {
                this.smsSender = new SmsSender(this);
                this.smsSender.a("sms://");
            }
            catch (ClassNotFoundException classNotFoundException) {
                return false;
            }
        }
        switch (this.selectedBillingPackageId) {
            case 0: {
                this.a((int)this.selectedBillingPackageId);
                break;
            }
            case 1: {
                this.a((int)this.selectedBillingPackageId);
                break;
            }
            case 2: {
                this.a((int)this.selectedBillingPackageId);
                break;
            }
            case 3: {
                this.a((int)this.selectedBillingPackageId);
                break;
            }
            case 4: {
                this.a((int)this.selectedBillingPackageId);
            }
        }
        return true;
    }

    public final boolean setBillingPackage(byte packageId) {
        this.selectedBillingPackageId = val;
        switch (val) {
            case 0: {
                this.requiredSmsCount = 1;
                break;
            }
            case 1: {
                this.requiredSmsCount = 1;
                break;
            }
            case 2: {
                this.requiredSmsCount = 1;
                break;
            }
            case 3: {
                this.requiredSmsCount = 1;
                break;
            }
            case 4: {
                this.requiredSmsCount = 1;
            }
        }
        this.currentSmsSuccessCount = 0;
        return true;
    }

    public final void setBillingDialogState(byte state) {
        while (true) {
            if (val != 5 && val != 0) {
                this.S.showSmsTipDialog();
            }
            switch (val) {
                case 1: {
                    System.out.println(" " + BaseScreen.a(513, new int[]{this.requiredSmsCount, this.currentSmsSuccessCount}));
                    this.S.d(BaseScreen.a(513, new int[]{this.requiredSmsCount, this.currentSmsSuccessCount}));
                    break;
                }
                case 4: {
                    System.out.println(" " + BaseScreen.f(514));
                    this.S.d(BaseScreen.f(514));
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    System.out.println(" " + BaseScreen.f(516));
                    this.S.d(BaseScreen.f(516));
                    break;
                }
                case 5: {
                    T = false;
                    this.S.closeSmsTipDialog();
                }
            }
            this.billingDialogState = val;
            if (val != 5) break;
            val = 0;
        }
    }

    public final int getBillingPackageId() {
        return this.selectedBillingPackageId;
    }

    public final boolean isPaymentComplete() {
        return this.currentSmsSuccessCount >= this.requiredSmsCount;
    }

    public final byte getBillingDialogState() {
        return this.billingDialogState;
    }

    public final byte getRequiredSmsCount() {
        return this.requiredSmsCount;
    }

    public final void confirmBillingPurchase(int option) {
        T = true;
        if (n2 == 1) {
            this.d((byte)4);
            this.b(true);
            return;
        }
        if (n2 == 2) {
            this.d((byte)5);
        }
    }

    public final void handleBillingDialogAction(int action) {
        switch (this.billingDialogState) {
            case 1: {
                this.g(n2);
                return;
            }
            case 3: {
                if (n2 != 1 && n2 != 2) break;
                this.d((byte)5);
            }
        }
    }

    private void a(int n2) {
        SmsConfig.a(GameMIDLet.a);
        SmsConfig.a(n2);
        this.billingCanvas = new BillingCanvas(GameMIDLet.a, game.GameCanvas.a, "", "", this.billingServiceCodes[n2], this.billingMetadata[n2][0], this.billingDescriptions[n2][0], SmsConfig.c[n2], "");
        Display.getDisplay(GameMIDLet.a).setCurrent(this.billingCanvas);
        this.billingCanvas.a(this);
    }

    public final void a(boolean flag) {
        this.b(flag);
    }

    static {
        g = false;
        T = false;
        U = (byte)-1;
        V = 0;
        h = new byte[7][3];
        k = false;
        X = true;
        m = new byte[]{0, 0, 0, 0, 0};
    }
}

