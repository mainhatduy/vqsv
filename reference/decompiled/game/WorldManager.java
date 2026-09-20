/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.NpcEntity;
import game.OverworldScreen;
import game.BattleScreen;
import game.Player;
import game.ScriptEngine;
import game.GameStateController;
import game.TileMapRenderer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * WorldManager - Map/scene navigator, room transitions, actor spawning, and script event executor.
 */
public final class WorldManager
extends BaseScreen {
    private static WorldManager instance;
    public TileMapRenderer tileMapRenderer;
    private ParticleEffect aa;
    public StringTable stringTable;
    public Player player;
    public NpcEntity[] npcList;
    public Vector e;
    private int ab;
    public int sceneId;
    public int roomId;
    private int ac;
    public short playerSpawnX;
    public short playerSpawnY;
    public int j;
    public String mapName;
    public static int[] l;
    public static Image m;
    private static Image ad;
    private static Image ae;
    public static SkillEffect n;
    private static SaveStorage[] af;
    private static byte[][][] ag;
    private static short[][][] ah;
    private static boolean[][] ai;
    private static String[] aj;
    private static short[][] ak;
    private static Vector al;
    private static Vector am;
    private static Vector an;
    private static Vector ao;
    public WorldEntity o;
    private WorldEntity ap;
    public WorldEntity p;
    public static int q;
    public static byte r;
    public static byte s;
    public static byte t;
    public static short u;
    public static short v;
    public static byte w;
    public static boolean x;
    public static int y;
    public static int z;
    public static int A;
    public static int B;
    public static int C;
    private static byte[] aq;
    private int[] ar;
    private int[] as;
    private short[] at;
    private static String[] au;
    public static boolean D;
    public static Vector E;
    public static Vector F;
    public static byte G;
    public static Vector H;
    public static byte I;
    public static boolean J;
    protected static boolean K;
    public static byte[] L;
    private Image[] av;
    public OverworldScreen M;
    public static String[] N;
    private byte[][] aw;
    private short[][] ax;
    private int ay;
    private String az;
    private byte aA;
    private byte aB;
    private byte aC;
    private byte aD;
    private int aE;
    private int aF;
    private int aG;
    private int aH;
    private boolean aI;
    private WorldEntity aJ;
    private WorldEntity[] aK;
    private int aL;
    private int[] aM;
    private int[] aN;
    private int[] aO;
    private int[][] aP;
    private int[] aQ;
    public static byte O;
    private boolean aR;
    private boolean[] aS;
    private byte[] aT;
    private byte aU;
    private byte aV;
    private boolean aW;

    public static WorldManager getInstance() {
        if (instance == null) {
            instance = new WorldManager();
        }
        return instance;
    }

    public WorldManager() {
        game.TileMapRenderer.getInstance();
        this.e = new Vector();
        this.sceneId = 0;
        this.roomId = 0;
        this.ac = 0;
        this.playerSpawnX = (short)128;
        this.playerSpawnY = (short)256;
        this.j = -1;
        this.mapName = "Gỗ thô";
        this.o = null;
        this.ap = null;
        this.p = null;
        this.ar = new int[]{21, 35, 50, 0, 45};
        this.as = new int[]{9, 0, 20, 3, 9, 1, 17, 1, 9, 2, 9, 4, 9, 6, 86, 5, 9, 6, 58, 6, 9, 5, 21, 2, 9, 4, 3, 0};
        this.at = new short[]{1, 5, 0, 616, 3, 6, 0, 617, 4, 0, 0, 618, 5, 2, 0, 619, 6, 0, 1, 620};
        this.aw = null;
        this.ax = null;
        this.ay = 0;
        this.az = "Ngoại trừ tiến hóa, sủng vật còn có thể dị hoá, dị hoá sau sủng vật đem càng cụ tính công kích. Mặt khác từng chủ thành liên minh huấn luyện sư cũng sẽ cung cấp tiến hóa cùng dị hoá phục vụ, ngươi có thể thường đi xem.";
        this.aI = false;
        this.aJ = null;
        this.aK = null;
        this.aL = 8;
        this.aM = new int[]{2, 1, 73, 158, 3, 3, 216, 165, 4, 5, 161, 338, 5, 3, 111, 385, 5, 5, 112, 124, 6, 1, 140, 100, 7, 2, 48, 58};
        this.aN = new int[]{1, 5, 265, 113, 3, 6, 281, 192, 4, 0, 24, 144, 5, 2, 88, 175, 6, 0, 55, 190};
        this.aO = new int[]{16735795, 5708544, 5693667, 28273, 7796622, 1924393, 16774529, 7760896, 3291479, 10268671, 2038828, 13341951, 4443391, 0xFFFFFF, 1862959, 13886935};
        this.aP = new int[][]{{0, 0, 1, 0, 386, 5, 5, 5, 0, 1, 1, 387, 5, 5, 0, 5, 1, 2, 388, 5, 5, 0, 10, 1, 3, 389, 5, 5, 5, 10, 1, 4, 390, 5, 5, 10, 10, 1, 5, 391, 5, 5, 10, 15, 1, 6, 392, 5, 5, 10, 20, -1, -1, 518, 5, 5}, {0, 0, -1, -1, 517, 5, 5, 0, 5, 2, 0, 393, 5, 5, 0, 10, 2, 1, 394, 5, 5, 0, 15, 2, 2, 395, 5, 5, 5, 10, 2, 3, 396, 5, 5, 5, 5, 2, 4, 397, 5, 5, 5, 15, 2, 5, 398, 5, 5, 5, 20, 2, 6, 399, 5, 5, 5, 25, 2, 7, 400, 5, 5}, {15, 0, -1, -1, 518, 5, 5, 15, 5, 3, 0, 401, 5, 5, 15, 10, 3, 1, 402, 5, 5, 10, 10, 3, 2, 403, 5, 5, 10, 15, 3, 3, 404, 5, 5, 5, 15, 3, 4, 405, 5, 5, 0, 15, 3, 5, 406, 5, 5, 15, 15, 3, 6, 407, 5, 5, 15, 20, 3, 7, 408, 5, 5}, {0, 15, 4, 0, 409, 5, 5, 5, 15, 4, 1, 410, 5, 5, 10, 15, 4, 5, 414, 5, 5, 10, 20, 4, 6, 415, 5, 5, 15, 20, 4, 7, 416, 5, 5, 20, 20, 4, 8, 417, 5, 5, 15, 15, 4, 9, 418, 5, 5, 20, 15, 4, 10, 419, 5, 5, 15, 10, 4, 11, 420, 5, 5, 15, 5, 4, 12, 421, 5, 5, 0, 10, 4, 2, 411, 5, 5, 5, 10, 4, 3, 412, 5, 5, 10, 10, 4, 4, 413, 5, 5, 15, 0, -1, -1, 524, 5, 5}, {10, 5, 5, 0, 422, 5, 5, 5, 5, 5, 1, 423, 5, 5, 0, 5, 5, 2, 424, 5, 5, 5, 0, 5, 3, 425, 5, 5, 15, 5, 5, 4, 426, 5, 5, 20, 5, 5, 5, 427, 5, 5, 18, 0, 5, 6, 428, 5, 5, 10, 10, -1, -1, 522, 5, 5}, {0, 5, 6, 0, 429, 5, 5, 0, 0, 6, 1, 430, 5, 5}, {5, 15, 7, 0, 431, 5, 5, 5, 10, 7, 1, 432, 5, 5, 5, 5, 7, 2, 433, 5, 5, 0, 5, 7, 3, 434, 5, 5, 0, 0, 7, 4, 435, 5, 5, 0, 10, 7, 5, 436, 5, 5, 0, 15, 7, 6, 437, 5, 5, 10, 5, 7, 7, 438, 5, 5, 10, 0, 7, 8, 439, 5, 5, 15, 0, 7, 9, 440, 5, 5, 15, 5, 7, 10, 441, 5, 5, 10, 10, 7, 11, 442, 5, 5, 10, 15, 7, 12, 443, 5, 5}, {5, 10, 8, 0, 444, 5, 5, 5, 15, 8, 1, 445, 5, 5, 0, 15, 8, 2, 446, 5, 5, 0, 10, 8, 3, 447, 5, 5, 0, 5, 8, 4, 448, 5, 5, 5, 5, 8, 5, 449, 5, 5, 5, 0, 8, 6, 450, 5, 5}};
        this.aQ = new int[]{3, 5, 2, 6, 4, 5, 5, 5, 5, 3, 1, 2, 4, 4, 2, 4};
        this.aR = false;
        this.aS = new boolean[]{false, false, false, false, false, false, false};
        this.aT = new byte[]{10, 15, 20, 30, 40, 50, 100};
        this.aW = false;
        this.tileMapRenderer = MapEngine.getInstance();
        this.aa = ParticleEffect.getInstance();
        this.stringTable = new StringTable();
    }

    public final boolean c() {
        return ak[l[this.sceneId] + this.roomId][2] != -1;
    }

    private void T() {
        int n2;
        Object object;
        if (ak == null) {
            try {
                object = EngineUtils.a("/data/script/petArea.mid");
                ak = EngineUtils.a((InputStream)object);
                ((InputStream)object).close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        if (!this.c()) {
            return;
        }
        object = new int[ak[l[this.sceneId] + this.roomId].length - 5];
        for (n2 = 0; n2 < ((Object)object).length; ++n2) {
            object[n2] = ak[l[this.sceneId] + this.roomId][n2 + 5];
        }
        block9: for (n2 = 0; n2 < ((Object)object).length / 4; ++n2) {
            int[] intArray = new int[4];
            switch (object[(n2 << 2) + 1]) {
                case 0: {
                    System.arraycopy(object, n2 << 2, intArray, 0, intArray.length);
                    al.addElement(intArray);
                    continue block9;
                }
                case 1: {
                    System.arraycopy(object, n2 << 2, intArray, 0, intArray.length);
                    am.addElement(intArray);
                    continue block9;
                }
                case 2: {
                    System.arraycopy(object, n2 << 2, intArray, 0, intArray.length);
                    an.addElement(intArray);
                    continue block9;
                }
                case 4: {
                    System.arraycopy(object, n2 << 2, intArray, 0, intArray.length);
                    ao.addElement(intArray);
                }
            }
        }
    }

    private void U() {
        int n2;
        if (this.aw == null) {
            try {
                InputStream inputStream = EngineUtils.a("/data/script/petRide.mid");
                this.aw = EngineUtils.b(inputStream);
                inputStream.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        this.player.Q = this.aw[l[this.sceneId] + this.roomId];
        if (this.player.t >= 0 && !this.player.g(this.player.t)) {
            this.player.s();
        }
        this.aA = this.player.Q[4];
        this.aB = (byte)-1;
        for (n2 = 0; n2 < this.aM.length / 4; n2 = (int)((byte)(n2 + 1))) {
            if (this.sceneId != this.aM[n2 << 2] || this.roomId != this.aM[(n2 << 2) + 1]) continue;
            this.aB = (byte)n2;
            break;
        }
        this.aC = (byte)-1;
        for (n2 = 0; n2 < this.aN.length / 4; n2 = (int)((byte)(n2 + 1))) {
            if (this.sceneId != this.aN[n2 << 2] || this.roomId != this.aN[(n2 << 2) + 1]) continue;
            this.aC = (byte)n2;
            return;
        }
    }

    private void V() {
        m = null;
        try {
            if (this.ax == null) {
                InputStream inputStream = EngineUtils.a("/data/script/backPic.mid");
                this.ax = EngineUtils.randomInt(inputStream);
                inputStream.close();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        for (int i = 0; i < this.ax.length; ++i) {
            if (this.ax[i][0] == this.sceneId && this.ax[i][1] == this.roomId) {
                if (this.ax[i][2] == 0) {
                    m = ae.loadImage("/data/img/", "img_" + this.ax[i][3]);
                    break;
                }
                if (this.ax[i][2] != 1) break;
                an.e(this.ax[i][3] << 16 | this.ax[i][4] << 8 | this.ax[i][5]);
                break;
            }
            an.e(2996676);
        }
        if (ad == null) {
            ad = ae.loadImage("/data/tex/", "gold");
        }
        if (ae == null) {
            ae = ae.loadImage("/data/img/", "img_10023");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final boolean d() {
        int n2;
        this.s();
        if (af == null) {
            af = new SaveStorage[10];
        }
        for (n2 = 0; n2 < af.length; ++n2) {
            if (af[n2] != null) continue;
            game.WorldManager.af[n2] = new SaveStorage(au[n2]);
        }
        this.eventManager = game.OverworldScreen.getInstance();
        this.eventManager.a(this);
        this.player = game.Player.getInstance();
        game.WorldManager.L[1] = -1;
        game.WorldManager.L[0] = -1;
        if (ag == null) {
            ag = new byte[127][][];
            ah = new short[127][][];
            ai = new boolean[127][2];
        }
        if (!this.player.y) {
            if (W) {
                this.Y();
                this.aa();
            }
            game.WorldManager.ac();
        }
        Object object = "/data/event/";
        int n3 = this.roomId;
        n2 = this.sceneId;
        Object object2 = this;
        Object object3 = (String)object + "scene_" + n2 + ".mid";
        try {
            int n4;
            int n5;
            int n6;
            "".getClass();
            object3 = ResourceStream.a((String)object3);
            object = new DataInputStream((InputStream)object3);
            int n7 = ((DataInputStream)object).readShort();
            short[] shortArray = new short[n7];
            for (n6 = 0; n6 < n7; ++n6) {
                shortArray[n6] = ((DataInputStream)object).readShort();
            }
            n6 = 0;
            for (n7 = 0; n7 < n3; n6 += shortArray[n7], ++n7) {
            }
            ((DataInputStream)object).skipBytes(n6);
            n7 = ((DataInputStream)object).readShort();
            String[] strArray = null;
            if (n7 > 0) {
                strArray = new String[n7];
                for (int i = 0; i < n7; ++i) {
                    n6 = ((DataInputStream)object).readShort();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (n5 = 0; n5 < n6; ++n5) {
                        stringBuffer.append((char)(((FilterInputStream)object).read() << 8 | ((FilterInputStream)object).read() & 0xFF));
                    }
                    strArray[i] = stringBuffer.toString();
                }
            }
            int n8 = ((DataInputStream)object).readByte();
            StringBuffer stringBuffer = new StringBuffer();
            for (n4 = 0; n4 < n8; ++n4) {
                stringBuffer.append((char)(((FilterInputStream)object).read() << 8 | ((FilterInputStream)object).read() & 0xFF));
            }
            ((WorldManager)object2).ab = ((DataInputStream)object).readShort();
            aj = null;
            ((DataInputStream)object).readShort();
            n5 = ((DataInputStream)object).readShort();
            Object object4 = new boolean[2];
            int n9 = ((WorldManager)object2).g;
            int n10 = ((WorldManager)object2).f;
            if (ag[l[n10] + n9] == null) {
                n9 = ((WorldManager)object2).g;
                n10 = ((WorldManager)object2).f;
                game.WorldManager.ag[game.WorldManager.l[n10] + n9] = new byte[n5][3];
                object4[0] = true;
            }
            if (((WorldManager)object2).f == 9) {
                n9 = ((WorldManager)object2).g;
                n10 = ((WorldManager)object2).f;
                game.WorldManager.ah[game.WorldManager.l[n10] + n9] = null;
            }
            if (ah[l[n10 = ((WorldManager)object2).f] + (n9 = ((WorldManager)object2).g)] == null) {
                n9 = ((WorldManager)object2).g;
                n10 = ((WorldManager)object2).f;
                game.WorldManager.ah[game.WorldManager.l[n10] + n9] = new short[n5][2];
                object4[1] = true;
            }
            if (n5 > 0) {
                int n11;
                ((WorldManager)object2).d = new NpcEntity[n5];
                n8 = -1;
                block16: for (n11 = 0; n11 < n5; ++n11) {
                    try {
                        ((WorldManager)object2).d[n11] = new NpcEntity();
                        short[] shortArray2 = new short[((DataInputStream)object).readShort()];
                        short[] sArray3 = shortArray2;
                        shortArray2[0] = ((DataInputStream)object).readByte();
                        sArray3[1] = ((DataInputStream)object).readShort();
                        n8 = sArray3[1];
                        sArray3[2] = ((DataInputStream)object).readShort();
                        sArray3[3] = ((DataInputStream)object).readShort();
                        sArray3[4] = ((DataInputStream)object).readShort();
                        sArray3[5] = ((DataInputStream)object).readByte();
                        sArray3[6] = ((DataInputStream)object).readByte();
                        switch (sArray3[0]) {
                            case 1: {
                                sArray3[7] = ((DataInputStream)object).readByte();
                                sArray3[8] = ((DataInputStream)object).readShort();
                                sArray3[9] = ((DataInputStream)object).readShort();
                                sArray3[10] = ((DataInputStream)object).readShort();
                                if (object4[0] != false && sArray3[6] == 3) {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ag[game.WorldManager.l[n10] + n9][n11][0] = (byte)sArray3[2];
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ag[game.WorldManager.l[n10] + n9][n11][1] = (byte)sArray3[5];
                                }
                                if (sArray3[6] == 3) {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    sArray3[2] = ag[l[n10] + n9][n11][0];
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    sArray3[5] = ag[l[n10] + n9][n11][1];
                                }
                                ((WorldManager)object2).d[n11].a(sArray3, n11);
                                continue block16;
                            }
                            case 2: {
                                sArray3[7] = ((DataInputStream)object).readShort();
                                if (sArray3[7] == 1) {
                                    sArray3[8] = ((DataInputStream)object).readByte();
                                    sArray3[9] = ((DataInputStream)object).readByte();
                                    sArray3[10] = ((DataInputStream)object).readByte();
                                    sArray3[11] = ((DataInputStream)object).readByte();
                                    sArray3[12] = ((DataInputStream)object).readByte();
                                }
                                ((WorldManager)object2).d[n11].a(sArray3, n11);
                                continue block16;
                            }
                            case 0: {
                                sArray3[7] = ((DataInputStream)object).readByte();
                                sArray3[8] = ((DataInputStream)object).readByte();
                                sArray3[9] = ((DataInputStream)object).readByte();
                                sArray3[10] = ((DataInputStream)object).readByte();
                                sArray3[11] = ((DataInputStream)object).readShort();
                                sArray3[12] = ((DataInputStream)object).readShort();
                                if (object4[0] != false) {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ag[game.WorldManager.l[n10] + n9][n11][0] = (byte)sArray3[2];
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ag[game.WorldManager.l[n10] + n9][n11][1] = (byte)sArray3[5];
                                } else {
                                    if (sArray3[6] != 7 && sArray3[6] != 6) {
                                        n9 = ((WorldManager)object2).g;
                                        n10 = ((WorldManager)object2).f;
                                        sArray3[2] = ag[l[n10] + n9][n11][0];
                                    }
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    sArray3[5] = ag[l[n10] + n9][n11][1];
                                }
                                if (object4[1] != false) {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ah[game.WorldManager.l[n10] + n9][n11][0] = sArray3[3];
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ah[game.WorldManager.l[n10] + n9][n11][1] = sArray3[4];
                                } else {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    sArray3[3] = ah[l[n10] + n9][n11][0];
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    sArray3[4] = ah[l[n10] + n9][n11][1];
                                }
                                if (sArray3[1] == 324) continue block16;
                                ((WorldManager)object2).d[n11].a(sArray3, n11);
                                if (object4[0] == false) {
                                    if (sArray3[6] != 1) break;
                                    if (sArray3[6] != 7 && sArray3[6] != 6) {
                                        n9 = ((WorldManager)object2).g;
                                        n10 = ((WorldManager)object2).f;
                                        sArray3[2] = ag[l[n10] + n9][n11][0];
                                    }
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    n9 = ag[l[n10] + n9][n11][2];
                                    ((WorldManager)object2).d[n11].n = (byte)n9;
                                    ((WorldManager)object2).d[n11].d((byte)sArray3[2]);
                                    continue block16;
                                }
                                n9 = ((WorldManager)object2).g;
                                n10 = ((WorldManager)object2).f;
                                game.WorldManager.ag[game.WorldManager.l[n10] + n9][n11][2] = ((WorldManager)object2).d[n11].n;
                                continue block16;
                            }
                            case 3: {
                                sArray3[7] = ((DataInputStream)object).readByte();
                                sArray3[8] = ((DataInputStream)object).readByte();
                                sArray3[9] = ((DataInputStream)object).readByte();
                                sArray3[10] = ((DataInputStream)object).readShort();
                                sArray3[11] = ((DataInputStream)object).readShort();
                                if (object4[0] != false) {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    game.WorldManager.ag[game.WorldManager.l[n10] + n9][n11][0] = (byte)sArray3[2];
                                } else {
                                    n9 = ((WorldManager)object2).g;
                                    n10 = ((WorldManager)object2).f;
                                    sArray3[2] = ag[l[n10] + n9][n11][0];
                                }
                                ((WorldManager)object2).d[n11].a(sArray3, n11);
                            }
                        }
                        continue;
                    }
                    catch (Exception exception) {
                        System.out.println(" k = " + n11 + " e = " + exception + " actorId = " + n8);
                    }
                }
                short s2 = ((DataInputStream)object).readShort();
                n11 = s2;
                aj = new String[s2];
                for (n4 = 0; n4 < n11; ++n4) {
                    object4 = new StringBuffer();
                    n8 = ((DataInputStream)object).readByte();
                    for (n5 = 0; n5 < n8; ++n5) {
                        ((StringBuffer)object4).append((char)(((DataInputStream)object).readByte() << 8 | ((DataInputStream)object).readByte() & 0xFF));
                    }
                    game.WorldManager.aj[n4] = ((StringBuffer)object4).toString();
                }
            }
            ((WorldManager)object2).ay = 1;
            short s3 = ((DataInputStream)object).readShort();
            ((WorldManager)object2).ay = 2;
            if (s3 > 0) {
                ((WorldManager)object2).M.a((DataInputStream)object, ((WorldManager)object2).f, ((WorldManager)object2).g, s3, strArray);
            }
            ((FilterInputStream)object).close();
            ((InputStream)object3).close();
        }
        catch (Exception exception) {
            System.out.println(" initRoom = " + exception + " bug = " + ((WorldManager)object2).ay);
        }
        object2 = this;
        if (((WorldManager)object2).p == null) {
            ((WorldManager)object2).p = new WorldEntity();
            ((WorldManager)object2).p.a(259, false);
        }
        this.T();
        this.U();
        this.V();
        object2 = this;
        this.mapName = game.WorldManager.f(384 + l[((WorldManager)object2).f] + ((WorldManager)object2).g);
        ((WorldManager)object2).a.a(((WorldManager)object2).ab);
        ((WorldManager)object2).a.a(0, 0);
        ((WorldManager)object2).b.a(((WorldManager)object2).a);
        this.ak();
        if (x) {
            game.OverworldScreen.f = false;
            object2 = this;
            if (!((WorldManager)object2).c.y) {
                if (W) {
                    ((WorldManager)object2).d(((WorldManager)object2).c);
                } else {
                    object3 = new short[]{((WorldManager)object2).h, ((WorldManager)object2).i, w, 4, 4, 8, 40, 100, 0};
                    ((WorldManager)object2).c.a((short[])object3);
                }
            } else if (((WorldManager)object2).j >= 0) {
                int n12 = ((WorldManager)object2).d[((WorldManager)object2).j].i - ((WorldManager)object2).d[((WorldManager)object2).j].i % ((WorldManager)object2).c.c[2];
                int n13 = ((WorldManager)object2).d[((WorldManager)object2).j].j - ((WorldManager)object2).d[((WorldManager)object2).j].j % ((WorldManager)object2).c.c[2];
                ((WorldManager)object2).c.b(n12, n13);
                ((WorldManager)object2).c.b.b(n12, n13);
                ((WorldManager)object2).c.b((byte)0, ((WorldManager)object2).d[((WorldManager)object2).j].C);
                if (((WorldManager)object2).d[((WorldManager)object2).j].a.a == 222) {
                    ((WorldManager)object2).c.a(24);
                } else {
                    ((WorldManager)object2).c.a(32);
                }
            } else {
                object3 = new short[]{((WorldManager)object2).h, ((WorldManager)object2).i, w, 4, 4, 8, 40, 100, 0};
                ((WorldManager)object2).c.a((short[])object3);
            }
            ((WorldManager)object2).b.a(((WorldManager)object2).c);
            ((WorldManager)object2).c.B();
            ((WorldManager)object2).c.c();
            ((WorldManager)object2).a((WorldEntity)((WorldManager)object2).c);
            object2 = this;
            ((WorldManager)object2).aa.getAnimationData(((WorldManager)object2).c, true);
            ((WorldManager)object2).b.a(((WorldManager)object2).aa);
            ((WorldManager)object2).b.b();
        } else {
            if (!this.player.y) {
                object2 = new short[]{this.playerSpawnX, this.playerSpawnY, w, 4, 4, 8, 40, 100, 0};
                this.player.a((short[])object2);
                this.player.I();
            }
            if (y == -1) {
                this.aa.getAnimationData(z, A, true);
                this.stringTable.a(this.aa);
                this.stringTable.b();
            } else {
                this.aa.getAnimationData(this.npcList[y], true);
                this.stringTable.a(this.aa);
                this.stringTable.b();
            }
            x = true;
        }
        this.W();
        if (this.sceneId == 3 && this.roomId == 7) {
            if (this.player.x > 0) {
                this.player.x = 0;
                this.player.c(0);
            }
            if (this.av == null) {
                this.av = new Image[4];
                for (int i3 = 0; i3 < this.av.length; ++i3) {
                    this.av[i3] = ae.loadImage("/data/tex/", "down" + i3);
                }
            }
            this.player.s();
            this.player.h(0);
        }
        if (this.sceneId == 5 && this.roomId == 6 || this.sceneId == 4 && (this.roomId == 3 || this.roomId == 4)) {
            if (this.player.B[0][0] == 2) {
                ScreenView.getInstance().a(this.aa.i, this.aa.j - this.ar[this.player.t + 1], game.WorldManager.w(), game.WorldManager.x(), 110, 110);
            } else {
                ScreenView.getInstance().a(this.aa.i, this.aa.j - this.ar[this.player.t + 1], game.WorldManager.w(), game.WorldManager.x(), 50, 50);
            }
        } else {
            ScreenView.getInstance().c();
        }
        this.S = game.ScriptEngine.getInstance();
        this.S.a(this);
        this.R = UIManager.getInstance();
        this.eventManager.i();
        this.eventManager.b();
        J = true;
        this.a((byte)0);
        game.WorldManager.t();
        return true;
    }

    private void W() {
        for (int i = 0; i < this.npcList.length; ++i) {
            this.npcList[i].f();
            this.stringTable.a(this.npcList[i]);
        }
    }

    public final void a(int n2, int n3, WorldEntity f2) {
        if (this.p.i()) {
            return;
        }
        this.p.a.a((byte)13, (byte)-1);
        this.p.s = 0;
        this.p.c();
        this.stringTable.a(this.p);
        this.p.b(n2, n3);
        WorldEntity f3 = f2;
        this.p.p = f3;
    }

    public final void e() {
        if (this.p != null) {
            this.p.d();
            this.stringTable.b(this.p);
        }
    }

    public final void a(int n2) {
        if (this.o == null) {
            this.o = new WorldEntity();
            this.o.a(n2, false);
            this.o.s = 1;
        }
    }

    public final void a(WorldEntity f2) {
        if (this.o == null) {
            return;
        }
        this.ap = f2;
        WorldEntity f3 = f2;
        this.o.p = f3;
        this.o.c(f2.a.g());
        this.o.c();
        this.stringTable.a(this.o);
    }

    public final void g() {
        if (this.o != null) {
            this.stringTable.b(this.o);
            this.o = null;
        }
    }

    private boolean a(Player g2) {
        try {
            int n2;
            int n3;
            int n4;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (this.sceneId == 9) {
                dataOutputStream.writeShort(this.S.m[(this.roomId << 2) + 2]);
                dataOutputStream.writeShort(this.S.m[(this.roomId << 2) + 3]);
                dataOutputStream.writeByte(2);
            } else if (this.sceneId == 3 && this.roomId == 7) {
                dataOutputStream.writeShort(240);
                dataOutputStream.writeShort(40);
                dataOutputStream.writeByte(0);
            } else {
                dataOutputStream.writeShort(g2.i);
                dataOutputStream.writeShort(g2.j);
                dataOutputStream.writeByte(g2.n);
            }
            for (n4 = 0; n4 < g2.B.length; ++n4) {
                for (n3 = 0; n3 < g2.B[n4].length; ++n3) {
                    dataOutputStream.writeByte(g2.B[n4][n3]);
                }
            }
            for (n4 = 0; n4 < g2.P.length; ++n4) {
                dataOutputStream.writeByte(g2.P[n4]);
            }
            for (n4 = 0; n4 < g2.C.length; ++n4) {
                for (n3 = 0; n3 < g2.C[n4].length; ++n3) {
                    dataOutputStream.writeByte(g2.C[n4][n3]);
                }
            }
            for (n4 = 0; n4 < g2.E.length; ++n4) {
                dataOutputStream.writeByte(g2.E[n4]);
            }
            for (n4 = 0; n4 < g2.D.length; ++n4) {
                for (n3 = 0; n3 < g2.D[n4].length; ++n3) {
                    dataOutputStream.writeByte(this.player.D[n4][n3]);
                }
            }
            dataOutputStream.writeByte(this.player.H);
            dataOutputStream.writeByte(this.player.G);
            dataOutputStream.writeByte(this.player.F);
            dataOutputStream.writeByte(this.player.I);
            for (n4 = 0; n4 < this.player.R.length; ++n4) {
                dataOutputStream.writeByte(this.player.R[n4]);
            }
            if (!this.j()) {
                return false;
            }
            if (!this.ah()) {
                return false;
            }
            if (!this.af()) {
                return false;
            }
            dataOutputStream.writeInt(g2.L.size());
            for (n4 = 0; n4 < g2.L.size(); ++n4) {
                int[] intArray = (int[])g2.L.elementAt(n4);
                dataOutputStream.writeInt(intArray.length);
                for (n2 = 0; n2 < intArray.length; ++n2) {
                    dataOutputStream.writeInt(intArray[n2]);
                }
            }
            dataOutputStream.writeInt(g2.M.size());
            for (n4 = 0; n4 < g2.M.size(); ++n4) {
                int[] intArray = (int[])g2.M.elementAt(n4);
                dataOutputStream.writeInt(intArray.length);
                for (n2 = 0; n2 < intArray.length; ++n2) {
                    dataOutputStream.writeInt(intArray[n2]);
                }
            }
            dataOutputStream.writeInt(g2.N.size());
            for (n4 = 0; n4 < g2.N.size(); ++n4) {
                int[] intArray = (int[])g2.N.elementAt(n4);
                dataOutputStream.writeInt(intArray.length);
                for (n2 = 0; n2 < intArray.length; ++n2) {
                    dataOutputStream.writeInt(intArray[n2]);
                }
            }
            for (n4 = 0; n4 < g2.T.length; ++n4) {
                dataOutputStream.writeBoolean(g2.T[n4]);
            }
            if (!this.ad()) {
                return false;
            }
            if (F == null) {
                F = new Vector();
            }
            dataOutputStream.writeByte(F.size());
            for (n4 = 0; n4 < F.size(); ++n4) {
                String string = (String)F.elementAt(n4);
                dataOutputStream.writeByte(EngineUtils.d(string));
            }
            for (n4 = 0; n4 < this.aS.length; ++n4) {
                dataOutputStream.writeBoolean(this.aS[n4]);
            }
            if (this.o == null) {
                dataOutputStream.writeByte(-1);
            } else {
                dataOutputStream.writeByte(this.o.a.a);
            }
            dataOutputStream.write(this.player.u);
            dataOutputStream.writeInt(q);
            dataOutputStream.writeBoolean(K);
            long l2 = game.GameStateController.getInstance().d + game.GameStateController.getInstance().e - game.GameStateController.getInstance().f;
            dataOutputStream.writeLong(l2);
            dataOutputStream.writeByte(this.player.t);
            af[0].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    private boolean b(Player g2) {
        try {
            int n2;
            int n3;
            int[] intArray;
            int n4;
            int n5;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[0].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.playerSpawnX = dataInputStream.readShort();
            this.playerSpawnY = dataInputStream.readShort();
            short s2 = dataInputStream.readByte();
            for (n5 = 0; n5 < g2.B.length; ++n5) {
                for (n4 = 0; n4 < g2.B[n5].length; ++n4) {
                    g2.B[n5][n4] = dataInputStream.readByte();
                }
            }
            for (n5 = 0; n5 < g2.P.length; ++n5) {
                g2.P[n5] = dataInputStream.readByte();
            }
            for (n5 = 0; n5 < g2.C.length; ++n5) {
                for (n4 = 0; n4 < g2.C[n5].length; ++n4) {
                    g2.C[n5][n4] = dataInputStream.readByte();
                }
            }
            for (n5 = 0; n5 < g2.E.length; ++n5) {
                g2.E[n5] = dataInputStream.readByte();
            }
            for (n5 = 0; n5 < g2.D.length; ++n5) {
                for (n4 = 0; n4 < g2.D[n5].length; ++n4) {
                    this.player.D[n5][n4] = dataInputStream.readByte();
                }
            }
            this.player.H = dataInputStream.readByte();
            this.player.G = dataInputStream.readByte();
            this.player.F = dataInputStream.readByte();
            this.player.I = dataInputStream.readByte();
            for (n5 = 0; n5 < this.player.R.length; ++n5) {
                this.player.R[n5] = dataInputStream.readByte();
            }
            this.aj();
            this.ai();
            this.ag();
            n5 = dataInputStream.readInt();
            g2.L.removeAllElements();
            for (n4 = 0; n4 < n5; ++n4) {
                intArray = new int[dataInputStream.readInt()];
                for (n3 = 0; n3 < intArray.length; ++n3) {
                    intArray[n3] = dataInputStream.readInt();
                }
                g2.L.addElement(intArray);
            }
            n5 = dataInputStream.readInt();
            g2.M.removeAllElements();
            for (n4 = 0; n4 < n5; ++n4) {
                intArray = new int[dataInputStream.readInt()];
                for (n3 = 0; n3 < intArray.length; ++n3) {
                    intArray[n3] = dataInputStream.readInt();
                }
                g2.M.addElement(intArray);
            }
            n5 = dataInputStream.readInt();
            g2.N.removeAllElements();
            for (n4 = 0; n4 < n5; ++n4) {
                intArray = new int[dataInputStream.readInt()];
                for (n3 = 0; n3 < intArray.length; ++n3) {
                    intArray[n3] = dataInputStream.readInt();
                }
                g2.N.addElement(intArray);
            }
            for (n4 = 0; n4 < g2.T.length; ++n4) {
                g2.T[n4] = dataInputStream.readBoolean();
            }
            this.ae();
            if (E == null) {
                E = new Vector();
            }
            E.removeAllElements();
            byte by = dataInputStream.readByte();
            n5 = by;
            int[] intArray2 = new int[by];
            for (n2 = 0; n2 < n5; ++n2) {
                intArray2[n2] = dataInputStream.readByte();
                if (this.player.z[intArray2[n2]] == null) continue;
                this.player.z[intArray2[n2]].w();
                E.addElement(this.player.z[intArray2[n2]]);
            }
            for (n2 = 0; n2 < this.aS.length; ++n2) {
                this.aS[n2] = dataInputStream.readBoolean();
            }
            byte by2 = dataInputStream.readByte();
            n2 = by2;
            if (by2 != -1) {
                this.a(n2);
            }
            this.player.u = dataInputStream.readByte();
            q = dataInputStream.readInt();
            K = dataInputStream.readBoolean();
            game.GameStateController.getInstance().d += dataInputStream.readLong();
            g2.t = dataInputStream.readByte();
            g2.a(new short[]{this.playerSpawnX, this.playerSpawnY, s2, 4, 4, 8, 40, 100, 0});
            byteArrayInputStream.close();
            dataInputStream.close();
        }
        catch (Exception exception) {
            System.out.println(" ex = " + exception);
            return false;
        }
        return true;
    }

    private boolean X() {
        try {
            int n2;
            int n3;
            int n4;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.sceneId);
            dataOutputStream.writeInt(this.roomId);
            for (n4 = 0; n4 < ag.length; ++n4) {
                if (ag[n4] == null) {
                    dataOutputStream.writeShort(-1);
                    continue;
                }
                dataOutputStream.writeShort(ag[n4].length);
                for (n3 = 0; n3 < ag[n4].length; ++n3) {
                    if (ag[n4][n3] == null) {
                        dataOutputStream.writeByte(-1);
                        continue;
                    }
                    dataOutputStream.writeByte(ag[n4][n3].length);
                    for (n2 = 0; n2 < ag[n4][n3].length; ++n2) {
                        dataOutputStream.writeByte(ag[n4][n3][n2]);
                    }
                }
            }
            for (n4 = 0; n4 < ah.length; ++n4) {
                if (ah[n4] == null) {
                    dataOutputStream.writeShort(-1);
                    continue;
                }
                dataOutputStream.writeShort(ah[n4].length);
                for (n3 = 0; n3 < ah[n4].length; ++n3) {
                    if (ah[n4][n3] == null) {
                        dataOutputStream.writeByte(-1);
                        continue;
                    }
                    dataOutputStream.writeByte(ah[n4][n3].length);
                    for (n2 = 0; n2 < ah[n4][n3].length; ++n2) {
                        dataOutputStream.writeShort(ah[n4][n3][n2]);
                    }
                }
            }
            af[1].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean Y() {
        try {
            int n2;
            int n3;
            int n4;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[1].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.sceneId = dataInputStream.readInt();
            this.roomId = dataInputStream.readInt();
            for (n4 = 0; n4 < ag.length; ++n4) {
                n3 = dataInputStream.readShort();
                if (n3 == -1) {
                    game.WorldManager.ag[n4] = null;
                    continue;
                }
                game.WorldManager.ag[n4] = new byte[n3][];
                for (n2 = 0; n2 < ag[n4].length; ++n2) {
                    byte by = dataInputStream.readByte();
                    n3 = by;
                    if (by == -1) {
                        game.WorldManager.ag[n4][n2] = null;
                        continue;
                    }
                    game.WorldManager.ag[n4][n2] = new byte[n3];
                    for (n3 = 0; n3 < ag[n4][n2].length; ++n3) {
                        game.WorldManager.ag[n4][n2][n3] = dataInputStream.readByte();
                    }
                }
            }
            for (n4 = 0; n4 < ah.length; ++n4) {
                n3 = dataInputStream.readShort();
                if (n3 == -1) {
                    game.WorldManager.ah[n4] = null;
                    continue;
                }
                game.WorldManager.ah[n4] = new short[n3][];
                for (n2 = 0; n2 < ah[n4].length; ++n2) {
                    byte by = dataInputStream.readByte();
                    n3 = by;
                    if (by == -1) {
                        game.WorldManager.ah[n4][n2] = null;
                        continue;
                    }
                    game.WorldManager.ah[n4][n2] = new short[n3];
                    for (n3 = 0; n3 < ah[n4][n2].length; ++n3) {
                        game.WorldManager.ah[n4][n2][n3] = dataInputStream.readShort();
                    }
                }
            }
            byteArrayInputStream.close();
            dataInputStream.close();
        }
        catch (IOException iOException) {
            System.out.println(" sceneId ex = " + iOException);
            return false;
        }
        return true;
    }

    private boolean Z() {
        try {
            int n2;
            int n3;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (n3 = 0; n3 < this.eventManager.b.length; ++n3) {
                if (this.eventManager.b[n3] == null) {
                    dataOutputStream.writeByte(-1);
                    continue;
                }
                dataOutputStream.writeByte(this.eventManager.b[n3].length);
                for (n2 = 0; n2 < this.eventManager.b[n3].length; ++n2) {
                    dataOutputStream.writeByte(this.eventManager.b[n3][n2]);
                }
            }
            dataOutputStream.writeByte(game.OverworldScreen.t);
            dataOutputStream.writeByte(game.OverworldScreen.u);
            for (n3 = 0; n3 < game.OverworldScreen.u; ++n3) {
                dataOutputStream.writeShort(game.OverworldScreen.s[n3][0]);
                dataOutputStream.writeShort(game.OverworldScreen.s[n3][1]);
            }
            int[] intArray = this.eventManager.k();
            if (intArray == null) {
                dataOutputStream.writeByte(-1);
            } else {
                dataOutputStream.writeByte(intArray.length);
                for (n2 = 0; n2 < intArray.length; ++n2) {
                    dataOutputStream.writeInt(intArray[n2]);
                }
                dataOutputStream.writeByte(this.eventManager.p);
            }
            af[2].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean aa() {
        try {
            int n2;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[2].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            for (n2 = 0; n2 < this.eventManager.b.length; ++n2) {
                int n3 = dataInputStream.readByte();
                if (n3 == -1) {
                    this.eventManager.b[n2] = null;
                    continue;
                }
                this.eventManager.b[n2] = new byte[n3];
                for (n3 = 0; n3 < this.eventManager.b[n2].length; ++n3) {
                    this.eventManager.b[n2][n3] = dataInputStream.readByte();
                }
            }
            game.OverworldScreen.t = dataInputStream.readByte();
            game.OverworldScreen.u = dataInputStream.readByte();
            for (n2 = 0; n2 < game.OverworldScreen.u; ++n2) {
                game.OverworldScreen.s[n2][0] = dataInputStream.readShort();
                game.OverworldScreen.s[n2][1] = dataInputStream.readShort();
            }
            byte by = dataInputStream.readByte();
            n2 = by;
            if (by != -1) {
                int n4;
                int[] intArray = new int[n2];
                int[] intArray2 = this.eventManager.j();
                for (n4 = 0; n4 < n2; ++n4) {
                    intArray[n4] = dataInputStream.readInt();
                }
                this.eventManager.a(intArray);
                n4 = 0;
                if (intArray2[0] > intArray[0] || intArray2[1] > intArray[1] || intArray2[2] > intArray[2] || intArray2[3] - intArray[3] >= 20) {
                    n4 = 1;
                }
                this.eventManager.p = dataInputStream.readByte();
                if (n4 != 0) {
                    this.eventManager.p = 0;
                }
            }
            x = true;
            byteArrayInputStream.close();
            dataInputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    public static boolean h() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            W = true;
            dataOutputStream.writeBoolean(W);
            dataOutputStream.writeBoolean(x);
            dataOutputStream.writeBoolean(D);
            dataOutputStream.writeByte(G);
            af[3].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    public static boolean i() {
        if (af[3] == null) {
            game.WorldManager.af[3] = new SaveStorage(au[3]);
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[3].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            W = dataInputStream.readBoolean();
            x = dataInputStream.readBoolean();
            D = dataInputStream.readBoolean();
            G = dataInputStream.readByte();
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            W = false;
            return false;
        }
    }

    private static boolean ab() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeBoolean(X);
            af[4].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private static boolean ac() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[4].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            X = true;
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return true;
        }
    }

    private boolean ad() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.player.E());
            dataOutputStream.writeInt(this.player.G());
            af[6].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean ae() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[6].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.player.F();
            this.player.H();
            this.player.s(dataInputStream.readInt());
            this.player.u(dataInputStream.readInt());
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    private boolean af() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.player.K.size());
            for (int i = 0; i < this.player.K.size(); ++i) {
                int[] intArray = (int[])this.player.K.elementAt(i);
                dataOutputStream.writeInt(intArray.length);
                for (int i3 = 0; i3 < intArray.length; ++i3) {
                    dataOutputStream.writeInt(intArray[i3]);
                }
            }
            af[9].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean ag() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[9].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            int n2 = dataInputStream.readInt();
            this.player.K.removeAllElements();
            for (int i = 0; i < n2; ++i) {
                int[] intArray = new int[dataInputStream.readInt()];
                for (int i3 = 0; i3 < intArray.length; ++i3) {
                    intArray[i3] = dataInputStream.readInt();
                }
                this.player.K.addElement(intArray);
            }
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    private boolean ah() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.player.J.size());
            for (int i = 0; i < this.player.J.size(); ++i) {
                int[] intArray = (int[])this.player.J.elementAt(i);
                dataOutputStream.writeInt(intArray.length);
                for (int i3 = 0; i3 < intArray.length; ++i3) {
                    dataOutputStream.writeInt(intArray[i3]);
                }
            }
            af[8].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean ai() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[8].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            int n2 = dataInputStream.readInt();
            this.player.J.removeAllElements();
            for (int i = 0; i < n2; ++i) {
                int[] intArray = new int[dataInputStream.readInt()];
                for (int i3 = 0; i3 < intArray.length; ++i3) {
                    intArray[i3] = dataInputStream.readInt();
                }
                this.player.J.addElement(intArray);
            }
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    public final boolean j() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(this.player.A);
            for (int i = 0; i < this.player.A; ++i) {
                int[] intArray = this.player.z[i].P();
                dataOutputStream.writeInt(intArray.length);
                for (int i3 = 0; i3 < intArray.length; ++i3) {
                    dataOutputStream.writeInt(intArray[i3]);
                }
            }
            af[7].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean aj() {
        try {
            int n2;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(af[7].a());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            byte by = dataInputStream.readByte();
            for (n2 = 0; n2 < this.player.A; ++n2) {
                this.player.z[n2] = null;
            }
            this.player.A = 0;
            for (n2 = 0; n2 < by; ++n2) {
                int[] intArray = new int[dataInputStream.readInt()];
                for (int i = 0; i < intArray.length; ++i) {
                    intArray[i] = dataInputStream.readInt();
                }
                this.player.a(intArray);
            }
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    public final boolean k() {
        if (!this.c(this.player)) {
            return false;
        }
        if (!this.X()) {
            return false;
        }
        if (!this.Z()) {
            return false;
        }
        if (!game.WorldManager.h()) {
            return false;
        }
        return game.WorldManager.ab();
    }

    public final boolean n() {
        if (!game.WorldManager.ab()) {
            return false;
        }
        if (!this.ad()) {
            return false;
        }
        return this.af();
    }

    public static void o() {
        ag = null;
        ah = null;
        for (int i = 0; i < 10; ++i) {
            if (i == 4 || af[i] == null) continue;
            af[i].b();
            game.WorldManager.af[i] = null;
        }
        af = null;
    }

    public final boolean p() {
        this.eventManager.a(this);
        this.S.a(this);
        game.OverworldScreen.f = false;
        J = true;
        this.a((byte)0);
        this.R.a("/data/ui/battle.ui");
        return true;
    }

    private void ak() {
        for (int i = 0; i < this.npcList.length; ++i) {
            if (this.npcList[i].t != 0 || this.npcList[i].v != 14) continue;
            NpcEntity a2 = this.npcList[i];
            this.npcList[i].A = 0;
            while (true) {
                int n2 = 16 * (a2.A + 1);
                byte by = a2.a.g();
                NpcEntity a3 = a2;
                byte by2 = 0;
                switch (by) {
                    case 2: {
                        by2 = MapEngine.getInstance().b(a3.i, a3.j - n2);
                        break;
                    }
                    case 0: {
                        by2 = MapEngine.getInstance().b(a3.i, a3.j + n2);
                        break;
                    }
                    case 3: {
                        by2 = MapEngine.getInstance().b(a3.i - n2, a3.j);
                        break;
                    }
                    case 1: {
                        by2 = MapEngine.getInstance().b(a3.i + n2, a3.j);
                    }
                }
                if (!(by2 == 0)) break;
                ++a2.A;
            }
            a2.B = a2.A;
            a2.A = 0;
        }
    }

    public final void f() {
        this.stringTable.getInstance();
        this.tileMapRenderer.b();
        if (this.npcList != null) {
            for (int i = 0; i < this.npcList.length; ++i) {
                NpcEntity a2 = this.npcList[i];
                a2.e();
                if (a2.b != null) {
                    a2.b.a.a();
                    a2.b = null;
                }
                if (a2.I != -1 && a2.a.a != 0 && a2.a.a != 8) {
                    a2.a.b();
                    a2.a = null;
                }
                if (a2.G != null) {
                    a2.G.a.b();
                    a2.G = null;
                }
                if (a2.H != null) {
                    a2.H.a.b();
                    a2.H = null;
                }
                a2.I = (short)-1;
                this.npcList[i] = null;
            }
            this.npcList = null;
        }
        m = null;
        ad = null;
        ak = null;
        this.aw = null;
        this.ax = null;
        this.av = null;
        this.p.a.b();
        this.p = null;
        this.e.removeAllElements();
        al.removeAllElements();
        an.removeAllElements();
        ao.removeAllElements();
        am.removeAllElements();
        if (H != null) {
            H.removeAllElements();
            H = null;
        }
        this.R.b();
        this.eventManager.f();
        u = (short)-1;
    }

    public final void a(byte val) {
        this.Q = this.P;
        switch (val) {
            case 0: {
                BaseScreen.c(1, -1);
                BaseScreen.c(0, 0);
                BaseScreen.U = (byte)-1;
                BaseScreen.V = 0;
                if (!game.OverworldScreen.f) {
                    if (J) {
                        this.S.c();
                    } else {
                        this.S.d();
                    }
                }
                this.player.b((byte)0, this.player.n);
                break;
            }
            case 1: {
                this.S.j = 1;
                this.S.C();
                break;
            }
            case 2: {
                if (u != -1 && this.npcList[u] != null && this.npcList[game.WorldManager.u].a.a == 24) {
                    this.S.a(4, (byte)0);
                    break;
                }
                if (u == -1 || this.npcList[u] == null || this.npcList[game.WorldManager.u].a.a != 20) break;
                this.S.a(3, (byte)2);
                break;
            }
            case 32: {
                this.S.j = (byte)3;
                this.S.a(3, (byte)2);
                break;
            }
            case 26: {
                this.S.j = (byte)2;
                this.S.a(4, (byte)0);
                break;
            }
            case 3: {
                this.S.L();
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                this.S.ad();
                break;
            }
            case 6: {
                this.S.k();
                break;
            }
            case 7: {
                this.S.c = 0;
                this.S.W();
                break;
            }
            case 8: {
                this.S.Y();
                break;
            }
            case 9: {
                this.S.N();
                break;
            }
            case 10: {
                this.S.R();
                break;
            }
            case 22: {
                this.S.H();
                this.S.a("Có lưu dữ liệu không?");
                break;
            }
            case 11: {
                this.S.P();
                break;
            }
            case 12: {
                this.S.T();
                break;
            }
            case 13: {
                this.S.m();
                break;
            }
            case 14: {
                this.S.az();
                break;
            }
            case 16: {
                this.S.A();
                break;
            }
            case 15: {
                this.S.y();
                break;
            }
            case 17: {
                this.S.l = false;
            }
            case 18: 
            case 19: {
                this.S.c = 0;
                this.S.W();
                break;
            }
            case 20: {
                this.S.u();
                break;
            }
            case 21: {
                this.S.w();
                break;
            }
            case 23: {
                if (this.Q == 7) {
                    this.S.a("", this.az, -1);
                    break;
                }
                if (this.npcList == null) break;
                if (this.npcList[game.WorldManager.u].a.a == 68) {
                    this.S.a(aj[this.npcList[game.WorldManager.u].y], "Muốn lên thuyền đi đâu?", 1);
                    break;
                }
                if (this.npcList[game.WorldManager.u].x < 0) {
                    this.S.a(aj[this.npcList[game.WorldManager.u].y], N[0], 1);
                    break;
                }
                this.S.a(aj[this.npcList[game.WorldManager.u].y], N[this.npcList[game.WorldManager.u].x], 1);
                break;
            }
            case 27: {
                this.S.aQ();
                break;
            }
            case 31: {
                this.S.f = 0;
                Object object = this;
                int n2 = 0;
                ((WorldManager)object).al();
                if (((WorldManager)object).aV >= ((WorldManager)object).aU) {
                    n2 = 1;
                }
                this.aW = n2;
                if (this.aW) {
                    if (this.aU == this.aT.length - 1) {
                        this.S.a(aj[this.npcList[game.WorldManager.u].y], game.WorldManager.f(613), 1);
                        break;
                    }
                    if (this.aU == this.aT.length - 2) {
                        this.S.a(aj[this.npcList[game.WorldManager.u].y], game.WorldManager.f(612), 1);
                        break;
                    }
                    object = new int[]{this.aT[this.aU], this.aT[this.aU + 1]};
                    this.S.a(aj[this.npcList[game.WorldManager.u].y], game.WorldManager.a(611, (int[])object), 1);
                    break;
                }
                if (this.aU < this.aT.length) {
                    byte by2 = this.aT[this.aU];
                    n2 = BaseScreen.f(614).indexOf("%s");
                    this.S.a(aj[this.npcList[game.WorldManager.u].y], n2 == -1 ? BaseScreen.f(614) : BaseScreen.f(614).substring(0, n2) + by2 + BaseScreen.f(614).substring(n2 + 2), 1);
                    break;
                }
                this.S.a(aj[this.npcList[game.WorldManager.u].y], game.WorldManager.f(615), 1);
                break;
            }
            case 24: {
                this.S.h();
                break;
            }
            case 29: {
                ScreenView.getInstance().c(0, 2);
                break;
            }
            case 30: {
                this.S.aO();
                break;
            }
            case 100: {
                this.S.aG();
                break;
            }
            case 101: {
                this.S.aH();
                break;
            }
            case 102: {
                this.S.aJ();
                break;
            }
            case 104: {
                this.S.aI();
                break;
            }
            case 25: {
                this.S.ar();
                break;
            }
            case 28: {
                byte by3;
                for (by3 = 0; by3 < this.at.length / 4 && (this.at[by3 << 2] != this.sceneId || this.at[(by3 << 2) + 1] != this.roomId); by3 = (byte)((byte)(by3 + 1))) {
                }
                this.S.a(by3, (int)this.at[(by3 << 2) + 2], (int)this.at[(by3 << 2) + 3]);
            }
        }
        this.S.g = true;
        this.P = val;
        this.resetInputState();
    }

    /*
     * Unable to fully structure code
     */
    public final void b() {
        if (!this.Y) {
            return;
        }
        this.S();
        switch (this.P) {
            case 0: {
                var1_1 = this;
                if (!var1_1.M.h() && var1_1.c.h() < 5 && !var1_1.S.j() && var1_1.S.G()) {
                    if (var1_1.l(4100)) {
                        var1_1.c.b((byte)1, (byte)2);
                    } else if (var1_1.l(8448)) {
                        var1_1.c.b((byte)1, (byte)0);
                    } else if (var1_1.l(16400)) {
                        var1_1.c.b((byte)1, (byte)3);
                    } else if (var1_1.l(32832)) {
                        var1_1.c.b((byte)1, (byte)1);
                    }
                    if (var1_1.k(65568)) {
                        if (game.WorldManager.u != -1) {
                            var1_1.c.b((byte)0, var1_1.c.n);
                            if (game.OverworldScreen.g) {
                                game.OverworldScreen.h = true;
                                game.OverworldScreen.g = false;
                            } else {
                                if (var1_1.d[game.WorldManager.u].a.a <= 85) {
                                    var1_1.d[game.WorldManager.u].m = var1_1.d[game.WorldManager.u].n;
                                    switch (var1_1.c.n) {
                                        case 0: {
                                            var1_1.d[game.WorldManager.u].n = (byte)2;
                                            break;
                                        }
                                        case 2: {
                                            var1_1.d[game.WorldManager.u].n = 0;
                                            break;
                                        }
                                        case 3: {
                                            var1_1.d[game.WorldManager.u].n = 1;
                                            break;
                                        }
                                        case 1: {
                                            var1_1.d[game.WorldManager.u].n = (byte)3;
                                        }
                                    }
                                    var1_1.d[game.WorldManager.u].d((byte)0);
                                }
                                if (var1_1.d[game.WorldManager.u].a.a == 17) {
                                    var1_1.S.d = 0;
                                    var1_1.a((byte)27);
                                } else {
                                    var1_1.a((byte)23);
                                }
                            }
                            game.WorldManager.getInstance().e();
                        } else if ((NpcEntity)var1_1.c.p != null && ((NpcEntity)var1_1.c.p).t == 3) {
                            var1_1.c.v();
                        } else {
                            var1_1.c.w();
                        }
                    }
                    if (var1_1.R()) {
                        var1_1.c.b((byte)0, var1_1.c.n);
                    }
                    if (var1_1.k(262144 /* MASK_SOFT_RIGHT */)) {
                        var1_1.m();
                        var1_1.S.b = 0;
                        var1_1.a((byte)6);
                    } else if (var1_1.k(131072 /* MASK_SOFT_LEFT */)) {
                        var1_1.S.b = 0;
                        var1_1.a((byte)13);
                    } else if (var1_1.k(1)) {
                        var2_5 = var1_1;
                        var3_8 = true;
                        for (var4_13 = 0; var4_13 < game.WorldManager.aq.length; ++var4_13) {
                            if (game.WorldManager.aq[var4_13] != var2_5.f) continue;
                            var3_8 = false;
                            break;
                        }
                        if (var3_8) {
                            for (var4_13 = 0; var4_13 < var2_5.aP[var2_5.aA].length / 7; var4_13 = (byte)((byte)(var4_13 + 1))) {
                                if (var2_5.aP[var2_5.aA][var4_13 * 7 + 2] != var2_5.f || var2_5.aP[var2_5.aA][var4_13 * 7 + 3] != var2_5.g) continue;
                                var2_5.aD = var4_13;
                                break;
                            }
                            var2_5.aG = (game.WorldManager.w() >> 1) - (var2_5.aP[var2_5.aA][var2_5.aD * 7] << 4) - 40;
                            var2_5.aH = (game.WorldManager.x() >> 1) - (var2_5.aP[var2_5.aA][var2_5.aD * 7 + 1] << 3) - 20;
                            var2_5.aI = true;
                            if (var2_5.aJ == null) {
                                var2_5.aJ = new WorldEntity();
                                var2_5.aJ.a(0, false);
                                var2_5.aJ.a((byte)3, (byte)-1, false);
                                var2_5.aJ.c();
                            }
                            if (var2_5.aK == null) {
                                var2_5.aK = new WorldEntity[4];
                                for (var4_13 = 0; var4_13 < var2_5.aK.length; var4_13 = (byte)(var4_13 + 1)) {
                                    var2_5.aK[var4_13] = new WorldEntity();
                                    var2_5.aK[var4_13].a(223, false);
                                    if (var4_13 <= 1) {
                                        var2_5.aK[var4_13].b(game.WorldManager.w() >> 1, 20 + var4_13 * (game.WorldManager.x() - 20));
                                    } else {
                                        var2_5.aK[var4_13].b(10 + var4_13 % 2 * (game.WorldManager.w() - 20), game.WorldManager.x() >> 1);
                                    }
                                    var2_5.aK[var4_13].a(var4_13, (byte)-1, false);
                                    super.c((int)var4_13);
                                }
                            }
                            game.BattleScreen.getInstance().battleBgImage = Image.createImage(BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                            var4_14 = game.BattleScreen.getInstance().battleBgImage.getGraphics();
                            var2_5.c.b((byte)0, var2_5.c.n);
                            var2_5.b.b(var4_14);
                            var2_5.a((byte)4);
                        } else {
                            var2_5.S.b("Khu này không có bản đồ");
                        }
                    } else if (var1_1.k(2)) {
                        var1_1.S.b = 0;
                        var1_1.a((byte)10);
                    } else if (var1_1.k(8)) {
                        var1_1.S.b = 1;
                        var1_1.a((byte)10);
                    } else if (var1_1.k(512)) {
                        if (var1_1.f == 3 && var1_1.g == 7) break;
                        if (var1_1.c.t >= 0 && var1_1.M.k) {
                            if (var1_1.c.r()) {
                                var1_1.c.s();
                            }
                        } else if (var1_1.M.k) {
                            var1_1.a((byte)5);
                        }
                    }
                }
                var1_1.c.q();
                for (var2_6 = 0; var2_6 < var1_1.d.length; ++var2_6) {
                    if (var1_1.d[var2_6] == null) continue;
                    var1_1.d[var2_6].o();
                }
                if (var1_1.o != null && var1_1.o.i()) {
                    var1_1.o.a(var1_1.ap.a, var1_1.o.a);
                }
                var1_1.c.o = var1_1.c.n;
                var1_1.b.b();
                var2_7 = var1_1;
                if (!var2_7.c.D()) ** GOTO lbl195
                var3_9 = var2_7;
                var4_15 = MapEngine.getInstance().b(game.Player.getInstance().posX, game.Player.getInstance().posY);
                var5_19 = null;
                game.WorldManager.O = var4_15;
                switch (var4_15) {
                    case 0: {
                        if (game.WorldManager.al.size() <= 0) {
                            v0 = false;
                            break;
                        }
                        var5_19 = (int[])game.WorldManager.al.elementAt(EngineUtils.a(game.WorldManager.al.size()));
                        ** GOTO lbl167
                    }
                    case 1: {
                        if (game.WorldManager.am.size() <= 0) {
                            v0 = false;
                            break;
                        }
                        var5_19 = (int[])game.WorldManager.am.elementAt(EngineUtils.a(game.WorldManager.am.size()));
                        ** GOTO lbl167
                    }
                    case 2: {
                        if (game.WorldManager.an.size() <= 0) {
                            v0 = false;
                            break;
                        }
                        var5_19 = (int[])game.WorldManager.an.elementAt(EngineUtils.a(game.WorldManager.an.size()));
                        ** GOTO lbl167
                    }
                    case 4: {
                        if (game.WorldManager.ao.size() <= 0) {
                            v0 = false;
                            break;
                        }
                        var5_19 = (int[])game.WorldManager.ao.elementAt(EngineUtils.a(game.WorldManager.ao.size()));
                        ** GOTO lbl167
                    }
                    case 3: {
                        v0 = false;
                        break;
                    }
lbl167:
                    // 5 sources

                    default: {
                        var4_16 = var5_19;
                        var5_20 = -1;
                        if (var4_16[2] != -1) {
                            var5_20 = EngineUtils.b(var4_16[2], var4_16[3]);
                        }
                        if (!var3_9.c()) {
                            v0 = false;
                            break;
                        }
                        var6_21 = EngineUtils.b(game.WorldManager.ak[game.WorldManager.l[var3_9.f] + var3_9.g][3], (int)game.WorldManager.ak[game.WorldManager.l[var3_9.f] + var3_9.g][4]);
                        var3_9.c.a((byte)GameDatabase.gameDatabase[0][var4_16[0]][1], var4_16[0], (byte)1);
                        game.BattleScreen.getInstance().a(new int[][]{{var4_16[0], var6_21, var5_20}});
                        v0 = true;
                    }
                }
                if (!v0) {
                    var2_7.c.v = var2_7.c.C();
                } else {
                    game.OverworldScreen.j = var2_7.f != 3 || var2_7.g != 7;
                    var3_9 = var2_7;
                    v1 = game.BattleScreen.getInstance();
                    game.BattleScreen.getInstance().getClass();
                    v1.a = 0;
                    game.BattleScreen.getInstance().battleMode = 0;
                    game.BattleScreen.getInstance().battleBgImage = Image.createImage(BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    var4_17 = game.BattleScreen.getInstance().battleBgImage.getGraphics();
                    var3_9.b.b(var4_17);
                    var3_9.c.b((byte)0, var3_9.c.n);
                    var3_9.c.v = var3_9.c.C();
                    game.GameStateController.getInstance().a((byte)12);
                }
lbl195:
                // 3 sources

                if (game.WorldManager.G == 1 && game.WorldManager.X) {
                    var1_1.a((byte)25);
                }
                if (!var1_1.M.h() && !var1_1.S.G() && !game.WorldManager.K && game.WorldManager.L[0] != -1 && var1_1.k(32)) {
                    game.WorldManager.U = (byte)4;
                    game.WorldManager.K = true;
                    var1_1.S.c = 0;
                    var1_1.a((byte)7);
                    var1_1.S.F();
                }
                if (!var1_1.S.j() && game.WorldManager.r == 0 && var1_1.r()) {
                    var1_1.S.b("Có thể tiến hành sản xuất trứng sủng vật");
                    game.WorldManager.r = 1;
                }
                var1_1.M.c();
                var1_1.S.e();
                var2_7 = var1_1;
                for (var3_10 = 0; var3_10 < var2_7.c.V.size(); ++var3_10) {
                    v2 = (int[])var2_7.c.V.elementAt(var3_10);
                    var4_18 = v2;
                    v2[3] = v2[3] + 5;
                    if (var4_18[3] <= 30) continue;
                    var2_7.c.V.removeElementAt(var3_10);
                    --var3_10;
                }
                if (var1_1.S.j()) break;
                var1_1.M.b();
                var1_1.l();
                break;
            }
            case 1: {
                this.S.D();
                break;
            }
            case 2: {
                if (game.WorldManager.u != -1 && this.npcList[game.WorldManager.u] != null && this.npcList[game.WorldManager.u].a.a == 24 || this.eventManager.c == 0) {
                    this.S.a((byte)4, (byte)0);
                    break;
                }
                if ((game.WorldManager.u == -1 || this.npcList[game.WorldManager.u] == null || this.npcList[game.WorldManager.u].a.a != 20) && this.eventManager.c != 1) break;
                this.S.a((byte)3, (byte)2);
                break;
            }
            case 32: {
                this.S.a((byte)3, (byte)2);
                break;
            }
            case 26: {
                this.S.a((byte)4, (byte)0);
                break;
            }
            case 3: {
                this.S.M();
                break;
            }
            case 4: {
                if (this.aI) {
                    if (this.aE == this.aG && this.aF == this.aH) {
                        this.aI = false;
                    }
                    if ((var1_2 = EngineUtils.a(this.aE, this.aF, this.aG, this.aH)) < this.aL) {
                        this.aE = this.aG;
                        this.aF = this.aH;
                    } else {
                        this.aE += (this.aG - this.aE) * this.aL / var1_2;
                        this.aF += (this.aH - this.aF) * this.aL / var1_2;
                    }
                }
                if (!this.aI) {
                    if (this.l(16400)) {
                        if (this.aE < 0) {
                            this.aE += this.aL;
                        }
                    } else if (this.l(32832)) {
                        if (this.aE + (this.aQ[this.aA << 1] << 4) * 5 > game.WorldManager.w()) {
                            this.aE -= this.aL;
                        }
                    } else if (this.l(4100)) {
                        if (this.aF < 0) {
                            this.aF += this.aL;
                        }
                    } else if (this.l(8448)) {
                        if (this.aF + (this.aQ[(this.aA << 1) + 1] << 3) * 5 > game.WorldManager.x() - 30) {
                            this.aF -= this.aL;
                        }
                    } else if (this.k(262145)) {
                        game.BattleScreen.getInstance().battleBgImage = null;
                        this.a((byte)0);
                    }
                }
                this.aJ.a();
                for (var1_2 = 0; var1_2 < this.aK.length; ++var1_2) {
                    this.c(var1_2);
                    this.aK[var1_2].a();
                }
                break;
            }
            case 5: {
                this.S.ae();
                break;
            }
            case 6: {
                this.S.l();
                break;
            }
            case 7: {
                this.S.X();
                this.l();
                break;
            }
            case 8: {
                this.S.ac();
                break;
            }
            case 9: {
                this.S.O();
                break;
            }
            case 10: {
                this.S.S();
                break;
            }
            case 22: {
                this.S.K();
                break;
            }
            case 11: {
                this.S.Q();
                break;
            }
            case 12: {
                this.S.U();
                break;
            }
            case 13: {
                this.S.n();
                break;
            }
            case 14: {
                this.S.aA();
                break;
            }
            case 16: {
                this.S.B();
                break;
            }
            case 15: {
                this.S.z();
                break;
            }
            case 17: {
                this.S.Z();
                break;
            }
            case 18: {
                this.S.aa();
                break;
            }
            case 19: {
                this.S.ab();
                break;
            }
            case 20: {
                this.S.v();
                break;
            }
            case 21: {
                this.S.x();
                break;
            }
            case 23: {
                var1_3 = this;
                if (!var1_3.S.c(game.WorldManager.t, game.WorldManager.s) || !var1_3.k(196640)) ** GOTO lbl358
                if (EngineUtils.b >= EngineUtils.b()) ** GOTO lbl340
                EngineUtils.c();
                var1_3.S.b(EngineUtils.b);
                ** GOTO lbl358
lbl340:
                // 1 sources

                var1_3.S.aC();
                if (var1_3.Q == 7) ** GOTO lbl357
                if (var1_3.d[game.WorldManager.u].a.a <= 85) {
                    var1_3.d[game.WorldManager.u].n = var3_11 = var1_3.d[game.WorldManager.u].m;
                }
                var1_3.d[game.WorldManager.u].d((byte)0);
                var1_3.c.b((byte)0, var1_3.c.n);
                if (var1_3.d[game.WorldManager.u].a.a == 24 || var1_3.d[game.WorldManager.u].a.a == 20) {
                    var1_3.a((byte)1);
                } else if (var1_3.d[game.WorldManager.u].a.a == 25) {
                    var1_3.a((byte)16);
                } else if (var1_3.d[game.WorldManager.u].a.a == 68) {
                    var1_3.a((byte)28);
                } else {
                    if (game.WorldManager.u != -1) {
                        game.WorldManager.getInstance().a(game.WorldManager.getInstance().d[game.WorldManager.u].i, game.WorldManager.getInstance().d[game.WorldManager.u].j - 40, game.WorldManager.getInstance().d[game.WorldManager.u]);
                    }
lbl357:
                    // 4 sources

                    var1_3.a((byte)0);
                }
lbl358:
                // 6 sources

                var1_3.b.b();
                break;
            }
            case 31: {
                var1_4 = this;
                if (var1_4.S.c(game.WorldManager.t, game.WorldManager.s) && !var1_4.S.j() && var1_4.k(196640)) {
                    if (EngineUtils.b < EngineUtils.b()) {
                        EngineUtils.c();
                        var1_4.S.b(EngineUtils.b);
                    } else {
                        var1_4.S.aC();
                        if (var1_4.d[game.WorldManager.u].a.a <= 85) {
                            var1_4.d[game.WorldManager.u].n = var3_12 = var1_4.d[game.WorldManager.u].m;
                        }
                        var1_4.d[game.WorldManager.u].d((byte)0);
                        var1_4.c.b((byte)0, var1_4.c.n);
                        var1_4.S.f = 1;
                        if (var1_4.aW) {
                            var1_4.aS[var1_4.aU] = true;
                            if (var1_4.aU < var1_4.aT.length - 1) {
                                var1_4.c.u(1);
                                var1_4.S.b("Đạt được 1 huy hiệu");
                            } else if (var1_4.c.c((byte)7, (byte)0) == 0) {
                                var1_4.S.b("Đạt được hoàng kim huy hiệu");
                                var1_4.c.a((byte)7, (byte)0, (byte)2);
                                game.OverworldScreen.t = (byte)(game.OverworldScreen.r.length / 2);
                            }
                        }
                    }
                }
                var1_4.S.f();
                if (var1_4.S.f == 1 && var1_4.S.ax()) {
                    var1_4.S.d = 0;
                    var1_4.a((byte)27);
                }
                var1_4.b.b();
                break;
            }
            case 27: {
                this.S.aR();
                break;
            }
            case 24: {
                this.S.i();
                break;
            }
            case 28: {
                this.S.aN();
                break;
            }
            case 29: {
                ScreenView.getInstance().b();
                if (!ScreenView.getInstance().b) break;
                ScreenView.getInstance().a = -1;
                game.GameStateController.getInstance().a((byte)23);
                break;
            }
            case 30: {
                this.S.aP();
                break;
            }
            case 100: 
            case 101: 
            case 102: 
            case 104: {
                this.S.aM();
                break;
            }
            case 25: {
                this.S.as();
            }
        }
        if (this.P == 0 && !this.eventManager.h() && game.WorldManager.I == 0 && game.WorldManager.H != null && game.WorldManager.H.size() > 0) {
            if (this.ac >= game.WorldManager.H.size()) {
                game.WorldManager.H.removeAllElements();
                this.ac = 0;
                game.WorldManager.I = 1;
            } else if (this.S.ax()) {
                var1_1 = (int[])game.WorldManager.H.elementAt(this.ac);
                var2_7 = "Tiến hóa";
                if (GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)var1_1[0], (byte)19)][2] == 3) {
                    var2_7 = "Dị hoá";
                }
                if (!game.WorldManager.K && game.WorldManager.L[0] != -1) {
                    if (this.ac == game.WorldManager.H.size() - 1) {
                        this.S.E();
                        this.S.a("Nhấn #2" + game.WorldManager.f((int)var1_1[1]) + "#0 đạt tới có thể" + (String)var2_7 + " điều kiện", "Nhấn nút 5 để tiếp tục");
                    } else {
                        this.S.b("#2" + game.WorldManager.f((int)var1_1[1]) + "#0 có thể" + (String)var2_7);
                    }
                } else {
                    this.S.b("#2" + game.WorldManager.f((int)var1_1[1]) + "#0 có thể" + (String)var2_7);
                }
                ++this.ac;
            }
        }
        this.R.c();
        if (game.WorldManager.n != null) {
            game.WorldManager.n.e();
        }
    }

    public static void a(Graphics g, int n2, int n3, int n4, int n5) {
        g.setColor(game.WorldManager.C());
        int n6 = (n2 << 4) - MapEngine.getInstance().cameraX;
        int n7 = (n3 << 4) - MapEngine.getInstance().cameraY;
        g.fillRect(n6, n7, n4 - n2 << 4, n5 - n3 << 4);
    }

    private void c(int n2) {
        switch (n2) {
            case 0: {
                if (this.aF >= 0) {
                    this.aK[n2].d();
                    return;
                }
                this.aK[n2].c();
                return;
            }
            case 1: {
                if (this.aF + (this.aQ[(this.aA << 1) + 1] << 3) * 5 <= game.WorldManager.x()) {
                    this.aK[n2].d();
                    return;
                }
                this.aK[n2].c();
                return;
            }
            case 2: {
                if (this.aE >= 0) {
                    this.aK[n2].d();
                    return;
                }
                this.aK[n2].c();
                return;
            }
            case 3: {
                if (this.aE + (this.aQ[this.aA << 1] << 4) * 5 <= game.WorldManager.w()) {
                    this.aK[n2].d();
                    return;
                }
                this.aK[n2].c();
            }
        }
    }

    private void a(Graphics g) {
        try {
            if (this.P == 4) {
                int n2;
                int n3;
                Graphics graphics2 = g;
                WorldManager k2 = this;
                graphics2.drawImage(game.BattleScreen.getInstance().battleBgImage, 0, 0, 20);
                for (n3 = 0; n3 < k2.aP[k2.aA].length / 7; ++n3) {
                    if (k2.f == k2.aP[k2.aA][n3 * 7 + 2] && k2.g == k2.aP[k2.aA][n3 * 7 + 3]) {
                        graphics2.setColor(188, 122, 255);
                    } else {
                        graphics2.setColor(k2.aO[k2.aA << 1]);
                    }
                    graphics2.fillRoundRect(k2.aE + (k2.aP[k2.aA][n3 * 7] << 4), k2.aF + (k2.aP[k2.aA][n3 * 7 + 1] << 3), k2.aP[k2.aA][n3 * 7 + 5] << 4, k2.aP[k2.aA][n3 * 7 + 6] << 3, 12, 12);
                    graphics2.setColor(0);
                    graphics2.drawRoundRect(k2.aE + (k2.aP[k2.aA][n3 * 7] << 4), k2.aF + (k2.aP[k2.aA][n3 * 7 + 1] << 3), k2.aP[k2.aA][n3 * 7 + 5] << 4, k2.aP[k2.aA][n3 * 7 + 6] << 3, 12, 12);
                    if (n3 == k2.aD) {
                        k2.aJ.b(k2.aE + (k2.aP[k2.aA][k2.aD * 7] << 4) + 16 * k2.aP[k2.aA][k2.aD * 7 + 5] / 2, k2.aF + (k2.aP[k2.aA][k2.aD * 7 + 1] << 3) + 8 * k2.aP[k2.aA][k2.aD * 7 + 6] / 2 + 20);
                        k2.aJ.a(graphics2, 0, 0);
                    }
                    TextPainter cfr_ignored_0 = k2.R.b;
                    EngineUtils.a(graphics2, game.WorldManager.f(k2.aP[k2.aA][n3 * 7 + 4]), k2.aO[(k2.aA << 1) + 1], k2.aE + (k2.aP[k2.aA][n3 * 7] << 4) + 16 * k2.aP[k2.aA][n3 * 7 + 5] / 2, k2.aF + (k2.aP[k2.aA][n3 * 7 + 1] << 3) + 8 * k2.aP[k2.aA][n3 * 7 + 6] / 2 - TextLayoutHelper.a / 2, k2.R.b, -1);
                }
                graphics2.setColor(65280);
                for (int i = 0; i < k2.e.size(); ++i) {
                    n3 = (((NpcEntity)k2.e.elementAt((int)i)).i * k2.aP[k2.aA][k2.aD * 7 + 5] << 4) / MapEngine.getInstance().mapTileWidth + (k2.aP[k2.aA][k2.aD * 7] << 4) + k2.aE;
                    n2 = (((NpcEntity)k2.e.elementAt((int)i)).j * k2.aP[k2.aA][k2.aD * 7 + 6] << 3) / MapEngine.getInstance().mapTileHeight + (k2.aP[k2.aA][k2.aD * 7 + 1] << 3) + k2.aF;
                    if (((NpcEntity)k2.e.elementAt(i)).h() == 0 || ((NpcEntity)k2.e.elementAt(i)).h() == 1) {
                        graphics2.fillRect(n3, n2 - 2, 9, 3);
                        continue;
                    }
                    graphics2.fillRect(n3, n2 - 5, 3, 9);
                }
                if (k2.aB != -1) {
                    n3 = (k2.aM[(k2.aB << 2) + 2] * k2.aP[k2.aA][k2.aD * 7 + 5] << 4) / MapEngine.getInstance().mapTileWidth + (k2.aP[k2.aA][k2.aD * 7] << 4) + k2.aE;
                    n2 = (k2.aM[(k2.aB << 2) + 3] * k2.aP[k2.aA][k2.aD * 7 + 6] << 3) / MapEngine.getInstance().mapTileHeight + (k2.aP[k2.aA][k2.aD * 7 + 1] << 3) + k2.aF;
                    graphics2.setColor(0xFF0000);
                    graphics2.fillRect(n3, n2, 6, 6);
                }
                if (k2.aC != -1) {
                    n3 = (k2.aN[(k2.aC << 2) + 2] * k2.aP[k2.aA][k2.aD * 7 + 5] << 4) / MapEngine.getInstance().mapTileWidth + (k2.aP[k2.aA][k2.aD * 7] << 4) + k2.aE;
                    n2 = (k2.aN[(k2.aC << 2) + 3] * k2.aP[k2.aA][k2.aD * 7 + 6] << 3) / MapEngine.getInstance().mapTileHeight + (k2.aP[k2.aA][k2.aD * 7 + 1] << 3) + k2.aF;
                    graphics2.setColor(2758133);
                    graphics2.fillRect(n3, n2, 6, 6);
                }
                graphics2.setColor(1862801);
                graphics2.fillRect(0, game.WorldManager.x() - 30, game.WorldManager.w(), 30);
                graphics2.setColor(65280);
                graphics2.fillRect(25, game.WorldManager.x() - 22, 16, 16);
                graphics2.drawString("Cửa ra vào", 45, game.WorldManager.x() - 25, 20);
                graphics2.setColor(2758133);
                graphics2.fillRect(90, game.WorldManager.x() - 22, 16, 16);
                graphics2.drawString("Bến tàu", 115, game.WorldManager.x() - 25, 20);
                graphics2.setColor(0xFF0000);
                graphics2.fillRect(155, game.WorldManager.x() - 22, 16, 16);
                graphics2.drawString("Đạo quán vào cửa", 175, game.WorldManager.x() - 25, 20);
                return;
            }
            if (this.P == 0 || this.P == 23 || this.S.g) {
                this.stringTable.a(g);
                ScreenView.getInstance().c(g);
                if (this.S.g) {
                    this.S.g = false;
                }
            }
            if (ScreenView.getInstance().d != -1) {
                if (this.aa.p instanceof Player) {
                    ScreenView.getInstance().b(this.aa.i - MapEngine.getInstance().cameraX, this.aa.j - MapEngine.getInstance().cameraY - this.ar[this.player.t + 1]);
                } else {
                    ScreenView.getInstance().b(this.aa.i - MapEngine.getInstance().cameraX, this.aa.j - MapEngine.getInstance().cameraY - 20);
                }
                ScreenView.getInstance().b(g);
            }
            game.OverworldScreen.a(g);
            this.R.a(g);
            if (n != null) {
                n.a(g);
            }
            Graphics graphics3 = g;
            WorldManager k3 = this;
            for (int i3 = 0; i3 < k3.c.V.size(); ++i3) {
                int[] intArray = (int[])k3.c.V.elementAt(i3);
                EngineUtils.a(graphics3, "+" + intArray[0], 16704699, intArray[1] + 12 - k3.a.a, intArray[2] - intArray[3] - k3.a.b, k3.R.b, 2);
                graphics3.drawImage(ad, intArray[1] - k3.a.a - 6, intArray[2] - intArray[3] - k3.a.b, 20);
            }
            if (!this.S.j() && !game.WorldManager.H()) {
                this.eventManager.b(g);
            }
            if (this.sceneId == 3 && this.roomId == 7 && this.P == 0) {
                if (this.eventManager.n > 0) {
                    if (this.av != null) {
                        g.drawImage(this.av[this.eventManager.n - 1], game.WorldManager.w() >> 1, game.WorldManager.x() >> 1, 3);
                        return;
                    }
                } else if (game.GameStateController.getInstance().b != 0L) {
                    g.setColor(896);
                    g.setFont(game.WorldManager.E());
                    g.drawString(game.WorldManager.a(game.GameStateController.getInstance().b - game.GameStateController.getInstance().a)[0], 10, 40, 20);
                }
            }
            return;
        }
        catch (Exception exception) {
            h.a(exception, "res = " + this.P);
            return;
        }
    }

    public final void q() {
    }

    public static int a(int n2, int n3) {
        return l[n2] + n3;
    }

    public final void a(int n2, int n3, byte val3, boolean flag4) {
        int n4 = this.sceneId;
        int n5 = this.roomId;
        if (ag[l[n4] + n5][n2] != null) {
            n5 = this.roomId;
            n4 = this.sceneId;
            game.WorldManager.ai[game.WorldManager.l[n4] + n5][0] = flag4;
            n5 = this.roomId;
            n4 = this.sceneId;
            game.WorldManager.ag[game.WorldManager.l[n4] + n5][n2][n3] = val3;
        }
    }

    public final void a(int n2, int n3, int n4) {
        int n5 = this.sceneId;
        int n6 = this.roomId;
        if (ah[l[n5] + n6][n2] != null) {
            n6 = this.roomId;
            n5 = this.sceneId;
            game.WorldManager.ah[game.WorldManager.l[n5] + n6][n2][n3] = (short)n4;
        }
    }

    public final boolean b(int n2) {
        for (int i = 0; i < this.as.length / 4; ++i) {
            if (this.as[i << 2] != this.sceneId || this.as[(i << 2) + 1] != this.roomId || n2 != this.as[(i << 2) + 2] || this.player.B[this.as[(i << 2) + 3]][0] != 2) continue;
            return true;
        }
        return false;
    }

    public final boolean r() {
        return this.player.I == 0 && q >= 10 || this.player.I > 0 && q >= 30;
    }

    private void al() {
        int n2;
        this.aU = (byte)this.aS.length;
        for (n2 = 0; n2 < this.aS.length; ++n2) {
            if (this.aS[n2]) continue;
            this.aU = (byte)n2;
            break;
        }
        n2 = 0;
        for (int i2 = this.aT.length - 1; i2 >= 0; --i2) {
            if (this.player.F < this.aT[i2]) continue;
            this.aV = (byte)i2;
            n2 = 1;
            break;
        }
        if (n2 == 0) {
            this.aV = (byte)-1;
        }
    }

    public final void l() {
        switch (U) {
            case 1: {
                if (V == 0) {
                    game.WorldManager.c(0, 1);
                    if (X) {
                        game.WorldManager.c(1, 1);
                    } else {
                        game.WorldManager.c(1, 0);
                    }
                    V = (byte)(V + 1);
                    this.a((byte)6);
                    return;
                }
                if (V == 1) {
                    V = (byte)(V + 1);
                    this.S.c("Hãy lựa chọn #2Sủng vật");
                    return;
                }
                if (V == 3) {
                    game.WorldManager.c(1, 0);
                    String string = game.WorldManager.f(GameDatabase.gameDatabase[0][this.player.z[BaseScreen.K()].q()][0]);
                    V = (byte)(V + 1);
                    this.S.c("Hãy lựa chọn #2" + string);
                    return;
                }
                if (V == 4) {
                    if (!this.S.ay() || !BaseScreen.b(this.S.b, 0)) break;
                    V = (byte)(V + 1);
                    this.S.c("Hãy nhấn #2nút 5");
                    return;
                }
                if (V == 6) {
                    V = (byte)(V + 1);
                    this.S.c("Hãy lựa chọn #2Vật phẩm trang sức");
                    return;
                }
                if (V == 8) {
                    game.WorldManager.c(1, 0);
                    V = (byte)(V + 1);
                    this.S.c("Nhấn #2nút 5#1 trang thượng vật phẩm trang sức");
                    return;
                }
                if (V != 10) break;
                this.S.c("Nhấn #2nút mềm phải#0 để quay lại");
                game.WorldManager.c(1, -1);
                game.WorldManager.c(0, 2);
                V = (byte)(V + 1);
                return;
            }
            case 3: {
                if (V == 0) {
                    V = (byte)(V + 1);
                    game.WorldManager.c(1, 0);
                    game.WorldManager.c(0, 1);
                    this.a((byte)1);
                    return;
                }
                if (V == 1) {
                    if (!game.WorldManager.b(this.S.b, 0)) break;
                    V = (byte)(V + 1);
                    this.S.c("Hãy nhấn vào mục #2Mua sắm");
                    return;
                }
                if (V == 3) {
                    V = (byte)(V + 1);
                    this.S.c("Trước tiên hãy mua #2Hồng sắc ốc biển#1");
                    return;
                }
                if (V == 4) {
                    if (!this.S.ay()) break;
                    game.WorldManager.c(1, 1);
                    V = (byte)(V + 1);
                    return;
                }
                if (V == 5) {
                    if (!game.WorldManager.b(this.S.b, 0)) break;
                    V = (byte)(V + 1);
                    this.S.c("Nhấn #2nút 5#1 mua sắm");
                    return;
                }
                if (V != 7) break;
                this.S.c("Hãy nhấn #2nút mềm phải#1 để quay lại");
                game.WorldManager.c(1, -1);
                game.WorldManager.c(0, 2);
                V = (byte)(V + 1);
                return;
            }
            case 4: {
                if (V == 0) {
                    game.WorldManager.c(0, 1);
                    for (int i = 0; i < this.player.A; ++i) {
                        if (this.player.z[i].s() != L[0] || this.player.z[i].q() != L[1]) continue;
                        game.WorldManager.c(1, i);
                        break;
                    }
                    V = (byte)(V + 1);
                    String string = game.WorldManager.f(GameDatabase.spriteTable((byte)0, (short)this.player.z[BaseScreen.K()].q(), (byte)0));
                    this.S.c("Hãy lựa chọn #2" + string + "#0 tiến hành tiến hóa");
                    return;
                }
                if (V == 1) {
                    if (!game.WorldManager.b(this.S.b, 0) || !this.S.ay()) break;
                    V = (byte)(V + 1);
                    this.S.c("Hãy nhấn #2nút 5#0 để tiếp tục");
                    return;
                }
                if (V == 3) {
                    if (!game.WorldManager.b(this.S.c, 0)) break;
                    V = (byte)(V + 1);
                    this.S.c("Nhấn #2nút 5#0 để vào mục Tiến hóa");
                    return;
                }
                if (V != 5) break;
                V = (byte)(V + 1);
                this.S.c("Nhấn #2nút mềm trái#0 để Tiến hóa");
                return;
            }
            case 6: {
                if (V == 0) {
                    V = (byte)(V + 1);
                    game.WorldManager.c(0, 1);
                    game.WorldManager.c(1, 2);
                    this.a((byte)6);
                    return;
                }
                if (V == 1) {
                    V = (byte)(V + 1);
                    this.S.c("Hãy lựa chọn #2Ba lô#0");
                    return;
                }
                if (V == 2) {
                    if (!this.S.ay() || !game.WorldManager.b(this.S.b, 0)) break;
                    this.S.c("Nhấn #2nút mềm trái#0 vào Tuyển hạng");
                    V = (byte)(V + 1);
                    return;
                }
                if (V == 4) {
                    V = (byte)(V + 1);
                    this.S.c("Hãy sử dụng #2Gia tốc dược#0");
                    return;
                }
                if (V == 5) {
                    if (!this.S.ay() || !game.WorldManager.b(this.S.h, 0)) break;
                    V = (byte)(V + 1);
                    this.S.c("Nhấn #2nút mềm trái#0 sử dụng");
                    return;
                }
                if (V == 7) {
                    V = (byte)(V + 1);
                    game.WorldManager.c(0, 3);
                    game.WorldManager.c(2, 1);
                    game.WorldManager.c(1, 3);
                    this.S.c("Hãy lựa chọn #2Đặc thù đạo cụ#0 ấp trứng trứng sủng vật");
                    return;
                }
                if (V == 9) {
                    if (!this.S.ay() || !game.WorldManager.b(this.S.h, 0)) break;
                    game.WorldManager.c(0, 1);
                    this.S.c("Nhấn #2nút mềm trái#0 để Ấp trứng");
                    V = (byte)(V + 1);
                    return;
                }
                if (V != 11) break;
                V = (byte)(V + 1);
                game.WorldManager.c(0, 2);
                game.WorldManager.c(1, -1);
                this.S.c("Nhấn #2nút mềm phải#0 để quay lại");
            }
        }
    }

    public final void m() {
        switch (U) {
            case 1: {
                if (V == 2 || V == 7) {
                    V = (byte)(V + 1);
                    return;
                }
                if (V == 5) {
                    game.WorldManager.c(1, 2);
                    V = (byte)(V + 1);
                    return;
                }
                if (V != 9) break;
                V = (byte)(V + 1);
                int n2 = this.roomId;
                int n3 = this.sceneId;
                this.eventManager.b[game.WorldManager.l[n3] + n2][this.eventManager.g()] = 3;
                if (this.eventManager.a == null) break;
                this.eventManager.a[this.eventManager.g()].a((byte)3);
                return;
            }
            case 3: {
                if (V != 6 && V != 2) break;
                V = (byte)(V + 1);
                return;
            }
            case 4: {
                if (V == 2) {
                    game.WorldManager.c(1, 5);
                    V = (byte)(V + 1);
                    return;
                }
                if (V == 4) {
                    V = (byte)(V + 1);
                    return;
                }
                if (V != 6) break;
                this.S.c("Nhấn #2nút mềm phải#0 để quay lại");
                game.WorldManager.c(1, -1);
                game.WorldManager.c(0, 2);
                V = (byte)(V + 1);
                return;
            }
            case 6: {
                if (V == 3) {
                    for (int i = 0; i < this.player.J.size() + this.player.K.size(); ++i) {
                        if (i < this.player.K.size()) continue;
                        if (this.player.J.size() <= 0) break;
                        if (((int[])this.player.J.elementAt(i - this.player.K.size()))[0] != 14) continue;
                        game.WorldManager.c(1, i);
                        break;
                    }
                    V = (byte)(V + 1);
                    return;
                }
                if (V == 6 || V == 10) {
                    V = (byte)(V + 1);
                    return;
                }
                if (V != 8 || !game.WorldManager.b(this.S.b, 1)) break;
                this.S.c("Hãy lựa chọn #2Trứng sủng vật#0 để ấp trứng");
                game.WorldManager.c(2, 0);
                game.WorldManager.c(1, 0);
                V = (byte)(V + 1);
            }
        }
    }

    protected static String[] a(long l2) {
        String[] strArray = new String[2];
        String[] stringArray2 = strArray;
        stringArray2[0] = "0'00\"000";
        strArray[1] = "0'00\"000";
        long l3 = l2 % 1000L;
        long l4 = (l2 /= 1000L) % 60L;
        long l5 = (l2 /= 60L) % 60L;
        long l6 = l2 / 60L;
        String string = l3 < 10L ? "00" + l3 : (l3 < 100L ? "0" + l3 : "" + l3);
        String string2 = l4 < 10L ? "0" + l4 + "\"" : l4 + "\"";
        String string3 = l2 + "'";
        stringArray2[0] = string3 + string2 + string;
        String string4 = l6 + "'";
        string3 = l5 + "\"";
        string2 = l4 < 10L ? "0" + l4 : "" + l4;
        stringArray2[1] = string4 + string3 + string2;
        return stringArray2;
    }

    static {
        l = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
        m = null;
        ad = null;
        ae = null;
        n = null;
        af = new SaveStorage[10];
        ag = null;
        ah = null;
        ai = null;
        al = new Vector();
        am = new Vector();
        an = new Vector();
        ao = new Vector();
        q = 0;
        r = 0;
        u = (short)-1;
        v = (short)-1;
        w = 0;
        x = false;
        y = -1;
        z = 0;
        A = 0;
        B = game.WorldManager.w();
        C = game.WorldManager.x();
        aq = new byte[]{9, 10, 11};
        au = new String[]{"PK6_RMS_ACTOR", "PK6_RMS_WORLD", "PK6_RMS_EVENT", "PK6_RMS_RMS", "PK6_RMS_SMS", "PK6_RMS_CNTSMS", "PK6_RMS_GOLD", "PK6_RMS_POKPET", "PK6_RMS_CONITEM", "PK6_RMS_PETBALL"};
        D = false;
        E = null;
        H = null;
        I = 0;
        J = false;
        K = false;
        L = new byte[2];
        O = (byte)-1;
    }

    public final void b(Graphics g) {
        try {
            if (this.P == 4) {
                int n2;
                int[] intArray = this.aP[this.aA];
                int n3 = intArray.length;
                int n4 = this.aD * 7;
                g.drawImage(game.BattleScreen.getInstance().battleBgImage, 0, 0, 20);
                int n5 = 0;
                while (n5 < n3) {
                    if (this.sceneId == intArray[n5 + 2] && this.roomId == intArray[n5 + 3]) {
                        g.setColor(188, 122, 255);
                    } else {
                        g.setColor(this.aO[this.aA << 1]);
                    }
                    g.fillRoundRect(this.aE + (intArray[n5] << 4), this.aF + (intArray[n5 + 1] << 3), intArray[n5 + 5] << 4, intArray[n5 + 6] << 3, 12, 12);
                    g.setColor(0);
                    g.drawRoundRect(this.aE + (intArray[n5] << 4), this.aF + (intArray[n5 + 1] << 3), intArray[n5 + 5] << 4, intArray[n5 + 6] << 3, 12, 12);
                    if (n5 == n4) {
                        this.aJ.b(this.aE + (intArray[n4] << 4) + 16 * intArray[n4 + 5] / 2, this.aF + (intArray[n4 + 1] << 3) + 8 * intArray[n4 + 6] / 2 + 20);
                        this.aJ.a(g, 0, 0);
                    }
                    String[] strArray = TextLayoutHelper.a(game.WorldManager.f(intArray[n5 + 4]), intArray[n5 + 5] << 4);
                    n2 = strArray.length;
                    int n6 = 0;
                    while (n6 < n2) {
                        EngineUtils.a(g, strArray[n6], this.aO[(this.aA << 1) + 1], this.aE + (intArray[n5] << 4) + 16 * intArray[n5 + 5] / 2, this.aF + (intArray[n5 + 1] << 3) + 8 * intArray[n5 + 6] / 2 + (n6 - n2 / 2) * (TextLayoutHelper.a + 1), this.R.b, -1);
                        ++n6;
                    }
                    n5 += 7;
                }
                g.setColor(65280);
                n2 = 0;
                while (n2 < this.e.size()) {
                    NpcEntity a2 = (NpcEntity)this.e.elementAt(n2);
                    n5 = (a2.i * intArray[n4 + 5] << 4) / MapEngine.getInstance().mapTileWidth + (intArray[n4] << 4) + this.aE;
                    int n7 = (a2.j * intArray[n4 + 6] << 3) / MapEngine.getInstance().mapTileHeight + (intArray[n4 + 1] << 3) + this.aF;
                    if (a2.h() == 0 || a2.h() == 1) {
                        g.fillRect(n5, n7 - 2, 9, 3);
                    } else {
                        g.fillRect(n5, n7 - 5, 3, 9);
                    }
                    ++n2;
                }
                if (this.aB != -1) {
                    n5 = (this.aM[(this.aB << 2) + 2] * intArray[n4 + 5] << 4) / MapEngine.getInstance().mapTileWidth + (intArray[n4] << 4) + this.aE;
                    int n8 = (this.aM[(this.aB << 2) + 3] * intArray[n4 + 6] << 3) / MapEngine.getInstance().mapTileHeight + (intArray[n4 + 1] << 3) + this.aF;
                    g.setColor(0xFF0000);
                    g.fillRect(n5, n8, 6, 6);
                }
                if (this.aC != -1) {
                    n5 = (this.aN[(this.aC << 2) + 2] * intArray[n4 + 5] << 4) / MapEngine.getInstance().mapTileWidth + (intArray[n4] << 4) + this.aE;
                    int n9 = (this.aN[(this.aC << 2) + 3] * intArray[n4 + 6] << 3) / MapEngine.getInstance().mapTileHeight + (intArray[n4 + 1] << 3) + this.aF;
                    g.setColor(2758133);
                    g.fillRect(n5, n9, 6, 6);
                }
                g.setColor(1862801);
                g.fillRect(0, BaseScreen.getScreenHeight() - 30, BaseScreen.getScreenWidth(), 30);
                g.setColor(65280);
                g.fillRect(25, BaseScreen.getScreenHeight() - 22, 16, 16);
                TextLayoutHelper.a(g, "Cửa ra vào", 45, BaseScreen.getScreenHeight() - 18);
                g.setColor(2758133);
                g.fillRect(90, BaseScreen.getScreenHeight() - 22, 16, 16);
                TextLayoutHelper.a(g, "Bến tàu", 115, BaseScreen.getScreenHeight() - 18);
                g.setColor(0xFF0000);
                g.fillRect(155, BaseScreen.getScreenHeight() - 22, 16, 16);
                TextLayoutHelper.a(g, "Cửa đạo quán", 175, BaseScreen.getScreenHeight() - 18);
                return;
            }
            this.a(g);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private boolean c(Player g2) {
        game.WorldManager.af[0].a = true;
        if (this.a(g2)) {
            game.WorldManager.af[0].a = false;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeInt(g2.O.size());
                int n2 = 0;
                while (n2 < g2.O.size()) {
                    int[] intArray = (int[])g2.O.elementAt(n2);
                    dataOutputStream.writeInt(intArray.length);
                    int n3 = 0;
                    while (n3 < intArray.length) {
                        dataOutputStream.writeInt(intArray[n3]);
                        ++n3;
                    }
                    ++n2;
                }
                dataOutputStream.flush();
                af[0].a(byteArrayOutputStream.toByteArray());
                return true;
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
                return false;
            }
        }
        game.WorldManager.af[0].a = false;
        return false;
    }

    private boolean d(Player g2) {
        if (this.b(g2)) {
            if (game.WorldManager.af[0].b != null) {
                try {
                    InputStream inputStream = new ByteArrayInputStream(game.WorldManager.af[0].b);
                    inputStream = new DataInputStream(inputStream);
                    int n2 = ((DataInputStream)inputStream).readInt();
                    if (n2 > 0) {
                        g2.O.removeAllElements();
                        int n3 = 0;
                        while (n3 < n2) {
                            int[] intArray = new int[((DataInputStream)inputStream).readInt()];
                            int n4 = 0;
                            while (n4 < intArray.length) {
                                intArray[n4] = ((DataInputStream)inputStream).readInt();
                                ++n4;
                            }
                            g2.O.addElement(intArray);
                            ++n3;
                        }
                    }
                }
                catch (Exception exception) {
                    Exception exception2 = exception;
                    exception.printStackTrace();
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

