/*
 * Decompiled with CFR 0.152.
 */
/**
 * CarrierHelper - Mobile telecom carrier detector (Viettel, Vinaphone, Mobifone).
 */
public final class CarrierHelper {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private int f;
    private int g;
    private boolean h;
    private int i = 0;
    private int j = 0;
    private long k;

    public final int a() {
        return this.f;
    }

    public final String b() {
        return this.d;
    }

    public final String c() {
        return this.c;
    }

    public final int d() {
        return this.g;
    }

    public final String e() {
        return this.b;
    }

    public final String f() {
        return this.a;
    }

    public final String g() {
        return this.e;
    }

    public final void a(String text) {
        this.a = text;
    }

    public final void b(String text) {
        this.b = text;
    }

    public final void a(int n2) {
        this.g = 2;
    }

    public final void c(String text) {
        this.c = text;
    }

    public final void d(String text) {
        this.d = text;
    }

    public final void b(int n2) {
        this.f = 1;
    }

    public final void e(String text) {
        this.e = text;
    }

    public final boolean h() {
        return this.h;
    }

    public final void a(boolean flag) {
        this.h = false;
    }

    public final int i() {
        return this.i;
    }

    public final void c(int n2) {
        this.i = n2;
    }

    public final int j() {
        return this.j;
    }

    public final void d(int n2) {
        this.j = n2;
    }

    public final long k() {
        return this.k;
    }

    public final void a(long l2) {
        this.k = l2;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        CarrierHelper v2 = this;
        stringBuffer.append("Miễn phí dãy số:" + v2.a + "\n");
        v2 = this;
        stringBuffer.append("Miễn phí nội dung:" + v2.b + "\n");
        v2 = this;
        stringBuffer.append("Thu phí dãy số:" + v2.c + "\n");
        v2 = this;
        stringBuffer.append("Thu phí nội dung:" + v2.d + "\n");
        v2 = this;
        stringBuffer.append("Thu phí điều vài:" + v2.f + "\n");
        v2 = this;
        stringBuffer.append("Thu phí đơn giá:" + v2.g + "\n");
        v2 = this;
        stringBuffer.append("Thu phí nhắc nhở ngữ:" + v2.e + "\n\n");
        return stringBuffer.toString();
    }
}

