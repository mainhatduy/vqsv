/*
 * Decompiled with CFR 0.152.
 */
import game.WorldManager;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

/**
 * GameDatabase - Global database tables for pet stats, growth curves, item definitions, and skill costs.
 */
public final class GameDatabase {
    public static short[][] spriteTable;
    public static short[][] mapTable;
    public static short[][][] gameDatabase;
    public static String[] d;
    public static int[][] e;
    public static Image f;

    public static void a() {
        Object object = "/data/script/sprite.mid";
        try {
            object = EngineUtils.a((String)object);
            a = EngineUtils.a((InputStream)object);
            ((InputStream)object).close();
        }
        catch (Exception exception) {}
        object = "/data/mod/modInfo.mid";
        try {
            "".getClass();
            object = ResourceStream.a((String)object);
            DataInputStream dataInputStream = new DataInputStream((InputStream)object);
            int n2 = dataInputStream.readByte();
            b = new short[n2][];
            for (int i = 0; i < n2; ++i) {
                int n3 = dataInputStream.readByte();
                GameDatabase.mapTable[i] = new short[n3];
                for (int i3 = 0; i3 < n3; ++i3) {
                    short s2;
                    GameDatabase.mapTable[i][i3] = s2 = dataInputStream.readShort();
                }
            }
            dataInputStream.close();
            ((InputStream)object).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        GameDatabase.gameDatabase("/data/script/chs.mid");
        GameDatabase.spriteTable("/data/script/npcDialog.mid");
        GameDatabase.mapTable();
        GameDatabase.mapTable("/data/script/db.mid");
        f = ae.loadImage("/data/tex/", "bk");
    }

    private static void a(String text) {
        try {
            text = EngineUtils.a((String)text);
            String[][] strArray = EngineUtils.c((InputStream)text);
            k.N = new String[strArray.length];
            for (int i = 0; i < strArray.length; ++i) {
                System.arraycopy(strArray[i], 0, k.N, i, strArray[i].length);
            }
            ((InputStream)text).close();
            return;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }

    public static short a(byte val, short sVal2, byte val3) {
        return c[val][sVal2][val3];
    }

    private static void b(String text) {
        try {
            text = EngineUtils.a((String)text);
            c = new short[9][][];
            for (int i = 0; i < 9; ++i) {
                GameDatabase.gameDatabase[i] = EngineUtils.a((InputStream)text);
            }
            ((InputStream)text).close();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    private static void c(String text) {
        try {
            text = EngineUtils.a((String)text);
            String[][] strArray = EngineUtils.c((InputStream)text);
            d = new String[strArray.length];
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArray.length; ++i) {
                stringBuffer.delete(0, stringBuffer.length());
                for (int i3 = 0; i3 < strArray[i].length; ++i3) {
                    stringBuffer.append(strArray[i][i3]);
                }
                GameDatabase.d[i] = stringBuffer.toString();
            }
            ((InputStream)text).close();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    private static void b() {
        e = new int[4][];
        for (int i = 0; i < 4; ++i) {
            GameDatabase.e[i] = EngineUtils.a(ae.loadImage("/data/tex/", "tex_" + i));
        }
    }
}

