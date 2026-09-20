/*
 * Decompiled with CFR 0.152.
 */
import game.WorldManager;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * MapEngine - Tile map data loader, layer compositor, and viewport clipping manager.
 */
public final class MapEngine {
    private static MapEngine e;
    private static final int[] f;
    private int g = -1;
    private int h = -1;
    private short i = (short)-1;
    private short j = (short)-1;
    private short k = (short)-1;
    private short l = (short)-1;
    private int m = -1;
    private byte n = (byte)-1;
    private byte o = (byte)-1;
    private Image[] p = null;
    public int a;
    public int b;
    private int q;
    private int r;
    public int c;
    public int d;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private byte y;
    private short[][] z;
    private short[][][] A;
    private byte[] B = null;
    private static Image C;
    private static Graphics D;
    private boolean E = true;

    public static MapEngine getInstance() {
        if (e == null) {
            e = new MapEngine();
        }
        return e;
    }

    public MapEngine() {
        this.h = BaseScreen.getScreenHeight();
        this.g = BaseScreen.getScreenWidth();
    }

    public final void b() {
        int n2;
        if (this.p != null) {
            for (n2 = 0; n2 < this.p.length; ++n2) {
                this.p[n2] = null;
            }
            this.p = null;
        }
        for (n2 = 0; n2 < GameDatabase.mapTable[this.n].length; ++n2) {
            ImageCache.c(GameDatabase.mapTable[this.n][n2]);
        }
        C = null;
        D = null;
        MapEngine j2 = this;
        this.A = null;
        j2.B = null;
        j2.z = null;
    }

    public final void a(int n2) {
        this.m = n2;
        MapEngine j2 = this;
        try {
            j2.getClass();
            InputStream inputStream = ResourceStream.a("/data/map/map_" + j2.m + ".mid");
            inputStream = new DataInputStream(inputStream);
            byte by = ((DataInputStream)inputStream).readByte();
            j2.o = j2.n;
            j2.n = ((DataInputStream)inputStream).readByte();
            j2.d();
            j2.i = by == 1 ? (short)((DataInputStream)inputStream).readByte() : ((DataInputStream)inputStream).readShort();
            j2.j = by == 1 ? (short)((DataInputStream)inputStream).readByte() : ((DataInputStream)inputStream).readShort();
            j2.l = j2.k = (short)((DataInputStream)inputStream).readByte();
            j2.c = j2.i * j2.k;
            j2.d = j2.j * j2.l;
            j2.y = ((DataInputStream)inputStream).readByte();
            j2.B = new byte[j2.y];
            j2.A = new short[j2.y][][];
            for (int i = 0; i < j2.y; ++i) {
                int n3;
                int n4;
                byte by2 = ((DataInputStream)inputStream).readByte();
                j2.B[i] = ((DataInputStream)inputStream).readByte();
                short s2 = ((DataInputStream)inputStream).readShort();
                if (j2.B[by2] == 0 || j2.B[by2] == 1) {
                    j2.A[by2] = new short[j2.i][j2.j];
                    for (n4 = 0; n4 < j2.i; ++n4) {
                        for (n3 = 0; n3 < j2.j; ++n3) {
                            j2.A[by2][n4][n3] = -1;
                        }
                    }
                } else {
                    j2.A[by2] = new short[s2][4];
                }
                for (n4 = 0; n4 < s2; ++n4) {
                    short s3;
                    if (by == 1) {
                        n3 = ((DataInputStream)inputStream).readByte();
                        s3 = ((DataInputStream)inputStream).readByte();
                    } else {
                        n3 = ((DataInputStream)inputStream).readShort();
                        s3 = ((DataInputStream)inputStream).readShort();
                    }
                    short s4 = ((DataInputStream)inputStream).readShort();
                    if (j2.B[i] == 1) {
                        j2.A[i][n3][s3] = s4;
                        continue;
                    }
                    if (j2.B[by2] == 0) {
                        j2.A[i][n3][s3] = (short)(s4 & 0xFFF);
                        continue;
                    }
                    j2.A[i][n4][1] = n3;
                    j2.A[i][n4][2] = s3;
                    j2.A[i][n4][0] = (short)(s4 & 0xFFF);
                    j2.A[i][n4][3] = (short)((s4 & 0x7000) >> 12);
                }
            }
            ((FilterInputStream)inputStream).close();
        }
        catch (Exception exception) {
            Object var2_4 = null;
            exception.printStackTrace();
        }
        if (C == null) {
            C = Image.createImage(this.g, this.h);
            D = C.getGraphics();
        }
        this.E = true;
    }

    private void d() {
        int n2;
        if (this.p != null) {
            for (n2 = 0; n2 < this.p.length; ++n2) {
                for (int i = 0; i < GameDatabase.mapTable[this.n].length; ++i) {
                    if (GameDatabase.mapTable[this.o][n2] != GameDatabase.mapTable[this.n][i]) continue;
                    ImageCache.releaseImage(GameDatabase.mapTable[this.o][n2]);
                    this.p[n2] = null;
                    break;
                }
                if (this.p[n2] == null) continue;
                ImageCache.c(GameDatabase.mapTable[this.o][n2]);
                this.p[n2] = null;
            }
            this.p = null;
        }
        this.p = new Image[GameDatabase.mapTable[this.n].length];
        for (n2 = 0; n2 < this.p.length; ++n2) {
            this.p[n2] = ImageCache.getImage(GameDatabase.mapTable[this.n][n2]);
        }
        try {
            "".getClass();
            InputStream inputStream = ResourceStream.a("/data/mod/mod_" + this.n + ".mid");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n3 = dataInputStream.readShort();
            this.z = new short[n3][5];
            for (int i3 = 0; i3 < n3; ++i3) {
                this.z[i3][0] = dataInputStream.readByte();
                this.z[i3][1] = dataInputStream.readShort();
                this.z[i3][2] = dataInputStream.readShort();
                this.z[i3][3] = dataInputStream.readShort();
                this.z[i3][4] = dataInputStream.readShort();
            }
            dataInputStream.close();
            inputStream.close();
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public final void c() {
        if (this.k == 0) {
            return;
        }
        this.t = this.b / this.l;
        this.s = this.a / this.k;
        this.v = (this.b + this.h) / this.l;
        if ((this.b + this.h) % this.l != 0) {
            ++this.v;
        }
        if (this.v > this.j) {
            this.v = this.j;
        }
        this.u = (this.a + this.g) / this.k;
        if ((this.a + this.g) % this.k != 0) {
            ++this.u;
        }
        if (this.u > this.i) {
            this.u = this.i;
        }
        this.x = this.h / this.l + 1;
        this.w = this.g / this.k + 1;
        if (this.t + this.x >= this.j) {
            this.x = this.j - 1 - this.t;
        }
        if (this.w + this.s >= this.i) {
            this.w = this.i - 1 - this.s;
        }
    }

    private void a(Graphics g, int n2, int n3, int n4, int n5, int n6) {
        game.WorldManager.getInstance();
        game.WorldManager.a(g, n3, n4, n5, n6);
        switch (this.B[n2]) {
            case 0: {
                this.b(g, n2, n3, n4, n5, n6);
                return;
            }
            case 1: {
                this.c(g, n2, n3, n4, n5, n6);
            }
        }
    }

    public final void a(Graphics g, int n2, int n3) {
        switch (this.B[n2]) {
            case 0: 
            case 1: {
                n3 = n2;
                Graphics graphics = g;
                g = this;
                if (((MapEngine)g).E) {
                    int n4 = ((MapEngine)g).a / ((MapEngine)g).k < 0 ? 0 : ((MapEngine)g).a / ((MapEngine)g).k;
                    int n5 = ((MapEngine)g).b / ((MapEngine)g).l < 0 ? 0 : ((MapEngine)g).b / ((MapEngine)g).l;
                    int n6 = (((MapEngine)g).a + ((MapEngine)g).g) / ((MapEngine)g).k + 1 > ((MapEngine)g).i ? ((MapEngine)g).i : (((MapEngine)g).a + ((MapEngine)g).g) / ((MapEngine)g).k + 1;
                    int n7 = (((MapEngine)g).b + ((MapEngine)g).h) / ((MapEngine)g).l + 1 > ((MapEngine)g).j ? ((MapEngine)g).j : (((MapEngine)g).b + ((MapEngine)g).h) / ((MapEngine)g).l + 1;
                    game.WorldManager.getInstance();
                    game.WorldManager.a(D, 0, 0, ((MapEngine)g).g, ((MapEngine)g).h);
                    switch (((MapEngine)g).B[n3]) {
                        case 0: {
                            super.b(D, n3, n4, n5, n6, n7);
                            break;
                        }
                        case 1: {
                            super.c(D, n3, n4, n5, n6, n7);
                        }
                    }
                    ((MapEngine)g).E = false;
                } else if (((MapEngine)g).q != ((MapEngine)g).a || ((MapEngine)g).r != ((MapEngine)g).b) {
                    int n8 = 0;
                    int n9 = 0;
                    if (((MapEngine)g).a > ((MapEngine)g).q) {
                        n8 = ((MapEngine)g).q - ((MapEngine)g).a;
                    } else if (((MapEngine)g).a < ((MapEngine)g).q) {
                        n8 = ((MapEngine)g).q - ((MapEngine)g).a;
                    }
                    if (((MapEngine)g).b > ((MapEngine)g).r) {
                        n9 = ((MapEngine)g).r - ((MapEngine)g).b;
                    } else if (((MapEngine)g).b < ((MapEngine)g).r) {
                        n9 = ((MapEngine)g).r - ((MapEngine)g).b;
                    }
                    D.copyArea(0, 0, ((MapEngine)g).g, ((MapEngine)g).h, n8, n9, 20);
                    if (((MapEngine)g).a > ((MapEngine)g).q) {
                        int n10 = (((MapEngine)g).q + ((MapEngine)g).g) / ((MapEngine)g).k;
                        super.a(D, n3, n10, ((MapEngine)g).t, ((MapEngine)g).u, ((MapEngine)g).v);
                    } else if (((MapEngine)g).a < ((MapEngine)g).q) {
                        n8 = ((MapEngine)g).q / ((MapEngine)g).k + 1;
                        super.a(D, n3, ((MapEngine)g).s, ((MapEngine)g).t, n8, ((MapEngine)g).v);
                    }
                    if (((MapEngine)g).b > ((MapEngine)g).r) {
                        int n11 = (((MapEngine)g).r + ((MapEngine)g).h) / ((MapEngine)g).l;
                        super.a(D, n3, ((MapEngine)g).s, n11, ((MapEngine)g).u, ((MapEngine)g).v);
                    } else if (((MapEngine)g).b < ((MapEngine)g).r) {
                        n8 = ((MapEngine)g).r / ((MapEngine)g).l + 1;
                        super.a(D, n3, ((MapEngine)g).s, ((MapEngine)g).t, ((MapEngine)g).u, n8);
                    }
                }
                graphics.drawImage(C, 0, 0, 20);
                ((MapEngine)g).q = ((MapEngine)g).a;
                ((MapEngine)g).r = ((MapEngine)g).b;
                return;
            }
            case 2: 
            case 3: 
            case 4: {
                n3 = n2;
                Graphics graphics = g;
                g = this;
                for (int i = 0; i < ((MapEngine)g).A[n3].length; ++i) {
                    if (((MapEngine)g).A[n3][i][2] < 0 || !EngineUtils.a(((MapEngine)g).A[n3][i][1], ((MapEngine)g).A[n3][i][2], ((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][3], ((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][4], ((MapEngine)g).s, ((MapEngine)g).t, ((MapEngine)g).w + 1, ((MapEngine)g).x + 1, ((MapEngine)g).A[n3][i][3])) continue;
                    graphics.drawRegion(((MapEngine)g).p[((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][0]], ((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][1], ((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][2], ((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][3], ((MapEngine)g).z[((MapEngine)g).A[n3][i][0]][4], f[((MapEngine)g).A[n3][i][3]], (((MapEngine)g).A[n3][i][1] - ((MapEngine)g).s) * ((MapEngine)g).k - ((MapEngine)g).a % ((MapEngine)g).k, (((MapEngine)g).A[n3][i][2] - ((MapEngine)g).t) * ((MapEngine)g).l - ((MapEngine)g).b % ((MapEngine)g).l, 20);
                }
                break;
            }
        }
    }

    private void b(Graphics g, int n2, int n3, int n4, int n5, int n6) {
        while (n3 < n5) {
            for (int i = n4; i < n6; ++i) {
                short s2 = this.A[n2][n3][i];
                if (s2 == -1) continue;
                g.drawRegion(this.p[0], this.z[s2][1], this.z[s2][2], this.z[s2][3], this.z[s2][4], 0, n3 * this.k - this.a, i * this.l - this.b, 20);
            }
            ++n3;
        }
    }

    private void c(Graphics g, int n2, int n3, int n4, int n5, int n6) {
        while (n3 < n5) {
            for (int i = n4; i < n6; ++i) {
                short s2 = this.A[n2][n3][i];
                if (s2 == -1) continue;
                short s3 = (short)(s2 & 0xFFF);
                s2 = (short)f[(s2 & 0x7000) >> 12];
                g.drawRegion(this.p[this.z[s3][0]], this.z[s3][1], this.z[s3][2], this.z[s3][3], this.z[s3][4], s2, n3 * this.k - this.a, i * this.l - this.b, 20);
            }
            ++n3;
        }
    }

    public final void a(int n2, int n3) {
        this.a = n2 - this.g / 2;
        this.b = n3 - this.h / 2;
        if (this.a + this.g >= this.i * this.k) {
            this.a = this.i * this.k - this.g;
        }
        if (this.a <= 0) {
            this.a = 0;
        }
        if (this.b + this.h >= this.j * this.l) {
            this.b = this.j * this.l - this.h;
        }
        if (this.b <= 0) {
            this.b = 0;
        }
    }

    public final byte b(int n2, int n3) {
        if (this.A == null || this.A[0] == null) {
            return -1;
        }
        int n4 = n2 / this.k;
        int n5 = n3 / this.l;
        if (this.c(n2, n3)) {
            return 1;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n4 > this.i) {
            n4 = this.i;
        }
        if (n5 > this.j) {
            n5 = this.j;
        }
        return (byte)this.A[0][n4][n5];
    }

    public final boolean c(int n2, int n3) {
        return n2 <= 0 || n2 >= this.c || n3 <= 0 || n3 >= this.d;
    }

    static {
        int[] nArray = new int[]{0, 270, 180, 90, 8192 /* MASK_DOWN */, 8462, 8372, 8282};
        f = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    }
}

