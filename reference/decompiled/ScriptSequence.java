/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;
import java.util.Vector;

/**
 * ScriptSequence - Ordered sequence of script commands executed by the game engine.
 */
public final class ScriptSequence {
    private ScriptCommand[] a;
    private byte b;
    private Vector c;
    private byte d;
    private byte e;
    private int f;

    public final void a(DataInputStream dataInputStream, byte val2, int n2, String[] strArray) {
        this.b = val2;
        this.f = n2;
        val2 = (byte)dataInputStream.readShort();
        this.a = new ScriptCommand[val2];
        this.c = new Vector();
        for (n2 = 0; n2 < val2; ++n2) {
            this.a[n2] = new ScriptCommand();
            this.a[n2].a(dataInputStream, strArray);
            this.c.addElement(this.a[n2]);
        }
        this.e = 0;
    }

    public final byte a() {
        return this.e;
    }

    public final void a(byte val) {
        this.e = val;
    }

    public final byte b() {
        return this.b;
    }

    public final void b(byte val) {
        this.d = val;
    }

    public final ScriptCommand c() {
        if (this.d >= this.c.size()) {
            return null;
        }
        return (ScriptCommand)this.c.elementAt(this.d);
    }

    public final ScriptCommand d() {
        return (ScriptCommand)this.c.firstElement();
    }

    public final void e() {
        this.c();
        this.d = (byte)(this.d + 1);
        if (this.d >= this.c.size()) {
            this.d = 0;
        }
    }

    public final int[] f() {
        if (this.f == -1) {
            return null;
        }
        int[] intArray = new int[2];
        int[] intArray2 = intArray;
        intArray[0] = this.f >> 8 & 0xFF;
        intArray2[1] = this.f & 0xFF;
        return intArray2;
    }
}

