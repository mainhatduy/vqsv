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
    public static boolean W;
    public static boolean X;
    private byte l;
    private static byte[] m;
    private SmsSender n = null;
    private byte o;
    private byte p;
    private byte q;
    private String[] r = new String[]{"01", "02", "03", "04", "05"};
    private byte[][] s = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
    private String[][] t = new String[][]{{"Kích hoạt", "Bạn muốn khám phá bí mật của vương quốc sủng vật, dẫn dắt thú yêu chiến đấu, tiến hóa, ấp trứng? Chỉ cần 1 tin nhắn 15000đ để kích hoạt trò chơi, chỉ nhắn tin 1 lần cho tất cả các lượt chơi. Bạn có muốn nhắn tin không?"}, {"Tất trúng cầu", "Chỉ cần nhắn 1 tin nhắn 10000đ, bạn sẽ sở hữu 1 tất trúng cầu, tỷ lệ 100% bắt được sủng vật? Bạn có muốn nhắn tin không?"}, {"Mua sắm kim tiền", "Kiếm tiền vất vả, vật phẩm đắt đỏ? Chỉ cần nhắn 1 tin nhắn 10000đ bạn sẽ đạt được 10000 kim tiền. Bạn có muốn nhắn tin không?"}, {"Mua đẳng cấp", "Thăng cấp chậm chạp, kẻ địch lại quá mạnh? Chỉ cần 1 tin nhắn 10000đ, tất cả sủng vật trong ba lô của bạn đều được thăng lên 5 cấp. Bạn có muốn nhắn tin không?"}, {"Mua sắm huy hiệu", "Kiếm huy hiệu khó khăn? Chỉ cần 1 tin nhắn 10000đ, bạn sẽ đạt được 10 huy hiệu. Bạn có muốn nhắn tin không?"}};
    private BillingCanvas u;

    public abstract void b();

    public abstract void b(Graphics g);

    public abstract boolean d();

    public abstract void f();

    public abstract void a(byte val);

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

    public static void resetFrameDelay() {
        frameDelay = 66;
    }

    public static int getFrameDelay() {
        return frameDelay;
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

    public final void b(boolean flag) {
        if (this.l == 4) {
            if (flag) {
                BaseScreen an2 = this;
                an2.p = (byte)(an2.p + 1);
                byte by = an2.o;
                m[by] = (byte)(m[by] + 1);
                System.out.println(" curNum = " + an2.p + " tolNum = " + an2.q);
                if (an2.p >= an2.q) {
                    switch (an2.o) {
                        case 0: {
                            X = true;
                            game.Player.getInstance().s(2000);
                            game.Player.getInstance().c(1, 5, (byte)0);
                            game.Player.getInstance().c(4, 5, (byte)0);
                            game.Player.getInstance().c(11, 2, (byte)0);
                            game.Player.getInstance().u(5);
                            game.OverworldScreen.getInstance().b[game.WorldManager.a((int)9, (int)0)][5] = 3;
                            game.OverworldScreen.getInstance().a[5].a((byte)3);
                            break;
                        }
                        case 1: {
                            game.Player.getInstance().c(0, 1, (byte)0);
                            break;
                        }
                        case 2: {
                            game.Player.getInstance().s(10000);
                            break;
                        }
                        case 3: {
                            game.WorldManager.G = 0;
                            if (game.WorldManager.F == null) {
                                game.WorldManager.F = new Vector();
                            }
                            if (game.WorldManager.E == null) {
                                game.WorldManager.E = new Vector();
                            }
                            game.WorldManager.F.removeAllElements();
                            game.WorldManager.E.removeAllElements();
                            for (int i = 0; i < game.Player.getInstance().A; ++i) {
                                if (game.Player.getInstance().z[i].s() == 50) {
                                    game.Player.getInstance().z[i].J();
                                    continue;
                                }
                                game.Player.getInstance().z[i].x();
                                if (game.Player.getInstance().z[i].s() + 5 >= 50) {
                                    game.Player.getInstance().z[i].h(50 - game.Player.getInstance().z[i].s());
                                } else {
                                    game.Player.getInstance().z[i].h(5);
                                }
                                game.Player.getInstance().z[i].I();
                                if (game.Player.getInstance().z[i].E() >= 5 || game.Player.getInstance().z[i].E() >= game.Player.getInstance().z[i].s() / 10 + 1) continue;
                                game.WorldManager.E.addElement(game.Player.getInstance().z[i]);
                                game.WorldManager.F.addElement("" + i);
                            }
                            if (game.WorldManager.E.size() <= 0) {
                                game.WorldManager.G = (byte)2;
                                break;
                            }
                            game.WorldManager.G = 1;
                            break;
                        }
                        case 4: {
                            game.Player.getInstance().u(10);
                        }
                    }
                }
                an2.d((byte)2);
                return;
            }
            this.d((byte)3);
        }
    }

    private boolean a() {
        if (this.n == null) {
            try {
                this.n = new SmsSender(this);
                this.n.a("sms://");
            }
            catch (ClassNotFoundException classNotFoundException) {
                return false;
            }
        }
        switch (this.o) {
            case 0: {
                this.a((int)this.o);
                break;
            }
            case 1: {
                this.a((int)this.o);
                break;
            }
            case 2: {
                this.a((int)this.o);
                break;
            }
            case 3: {
                this.a((int)this.o);
                break;
            }
            case 4: {
                this.a((int)this.o);
            }
        }
        return true;
    }

    public final boolean c(byte val) {
        this.o = val;
        switch (val) {
            case 0: {
                this.q = 1;
                break;
            }
            case 1: {
                this.q = 1;
                break;
            }
            case 2: {
                this.q = 1;
                break;
            }
            case 3: {
                this.q = 1;
                break;
            }
            case 4: {
                this.q = 1;
            }
        }
        this.p = 0;
        return true;
    }

    public final void d(byte val) {
        while (true) {
            if (val != 5 && val != 0) {
                this.S.aK();
            }
            switch (val) {
                case 1: {
                    System.out.println(" " + BaseScreen.a(513, new int[]{this.q, this.p}));
                    this.S.d(BaseScreen.a(513, new int[]{this.q, this.p}));
                    break;
                }
                case 4: {
                    System.out.println(" " + BaseScreen.f(514));
                    this.S.d(BaseScreen.f(514));
                    break;
                }
                case 2: {
                    if (this.M()) {
                        if (this.o == 0) {
                            this.S.d(BaseScreen.f(515) + BaseScreen.f(633));
                            break;
                        }
                        this.S.d(BaseScreen.f(515));
                        break;
                    }
                    this.S.d(BaseScreen.f(516));
                    System.out.println(" " + BaseScreen.f(516));
                    break;
                }
                case 3: {
                    System.out.println(" " + BaseScreen.f(516));
                    this.S.d(BaseScreen.f(516));
                    break;
                }
                case 5: {
                    T = false;
                    this.S.aL();
                }
            }
            this.l = val;
            if (val != 5) break;
            val = 0;
        }
    }

    public final int L() {
        return this.o;
    }

    public final boolean M() {
        return this.p >= this.q;
    }

    public final byte N() {
        return this.l;
    }

    public final byte O() {
        return this.q;
    }

    public final void g(int n2) {
        T = true;
        if (n2 == 1) {
            this.d((byte)4);
            if (!this.a()) {
                this.d((byte)3);
                return;
            }
        } else if (n2 == 2) {
            this.d((byte)5);
        }
    }

    public final void h(int n2) {
        switch (this.l) {
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
        this.u = new BillingCanvas(GameMIDLet.a, game.GameCanvas.a, "", "", this.r[n2], this.s[n2][0], this.t[n2][0], SmsConfig.c[n2], "");
        Display.getDisplay(GameMIDLet.a).setCurrent(this.u);
        this.u.a(this);
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

