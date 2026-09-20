/*
 * Decompiled with CFR 0.152.
 */
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 * TextRenderer - Font metrics, multiline text formatting, dialog text box renderer, and RMS backup helpers.
 */
public final class TextRenderer {
    private UIComponent[] a;
    private int b;
    private MenuWidget c;
    private int[] d;
    private int[] e;
    private int[] f;
    private TextPainter g;
    private ScriptEventListener h;

    public TextRenderer(ScriptEventListener i2) {
        this.a = new UIComponent[200];
        this.b = 0;
        this.d = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        this.e = null;
        this.f = null;
        this.h = i2;
        this.d();
    }

    private void d() {
        this.c = new MenuWidget();
        this.c.a(0);
        this.c.c(-1);
        this.e = EngineUtils.b(100);
        this.f();
    }

    public final MenuWidget a() {
        return this.c;
    }

    public final void a(TextPainter y2) {
        this.g = y2;
    }

    public final void a(String text, int n2) {
        this.d();
        byte[] byteArray = new byte[20000];
        EngineUtils.b(byteArray, (String)text);
        text = new int[]{0};
        EngineUtils.d(byteArray, (int[])text);
        EngineUtils.d(byteArray, (int[])text);
        short s2 = EngineUtils.c(byteArray, (int[])text);
        this.c.b(s2);
        s2 = EngineUtils.d(byteArray, (int[])text);
        this.c.a(s2);
        s2 = EngineUtils.d(byteArray, (int[])text);
        this.c.a(s2, this.c);
        s2 = EngineUtils.d(byteArray, (int[])text);
        this.c.b(s2, this.c);
        s2 = EngineUtils.d(byteArray, (int[])text);
        this.c.c(s2, this.c);
        s2 = EngineUtils.d(byteArray, (int[])text);
        this.c.d(s2, this.c);
        this.a[this.b] = this.c;
        this.b = 1;
        this.a(byteArray, (int[])text, this.c, n2, false);
        text = this;
        this.e = EngineUtils.b(50);
        super.a(((TextRenderer)text).c, -1);
    }

    private void a(byte[] byteArray, int[] intArray, MenuWidget al2, int n2, boolean flag5) {
        int n3;
        int n4;
        int n5 = EngineUtils.c(byteArray, intArray);
        if (n5 > 0) {
            byte[][] byteArray2 = new byte[n5][4];
            for (n4 = 0; n4 < n5; ++n4) {
                byteArray2[n4][0] = EngineUtils.c(byteArray, intArray);
                byteArray2[n4][1] = EngineUtils.c(byteArray, intArray);
                byteArray2[n4][2] = EngineUtils.c(byteArray, intArray);
                byteArray2[n4][3] = EngineUtils.c(byteArray, intArray);
            }
            al2.a(byteArray2);
        }
        int n6 = EngineUtils.c(byteArray, intArray);
        for (n4 = 0; n4 < n6; ++n4) {
            int n7;
            int n8;
            int n9;
            n5 = EngineUtils.c(byteArray, intArray);
            NumericInputWidget z2 = new NumericInputWidget(n5);
            new NumericInputWidget(n5).g = EngineUtils.c(byteArray, intArray) != 0;
            z2.d = EngineUtils.d(byteArray, intArray);
            z2.a = EngineUtils.d(byteArray, intArray);
            z2.h = EngineUtils.c(byteArray, intArray);
            z2.i = EngineUtils.c(byteArray, intArray);
            for (n9 = 0; n9 < z2.a; ++n9) {
                n8 = EngineUtils.d(byteArray, intArray);
                z2.b[n9] = n8;
                n7 = EngineUtils.d(byteArray, intArray);
                byte[] byArray3 = new byte[n7];
                for (int i = 0; i < n7; ++i) {
                    byArray3[i] = EngineUtils.c(byteArray, intArray);
                }
                z2.j.addElement(EngineUtils.randomInt(byArray3));
            }
            z2.c = new int[z2.d + this.d.length][][];
            for (n9 = 0; n9 < z2.c.length; ++n9) {
                z2.c[n9] = new int[0][];
            }
            n9 = EngineUtils.d(byteArray, intArray);
            for (n8 = 0; n8 < n9; ++n8) {
                n7 = EngineUtils.d(byteArray, intArray);
                int n10 = EngineUtils.d(byteArray, intArray);
                int[][] nArrayArray = new int[n10][];
                for (n3 = 0; n3 < n10; ++n3) {
                    nArrayArray[n3] = new int[5];
                    nArrayArray[n3][0] = EngineUtils.d(byteArray, intArray);
                    nArrayArray[n3][1] = EngineUtils.d(byteArray, intArray);
                    nArrayArray[n3][2] = EngineUtils.d(byteArray, intArray);
                    nArrayArray[n3][3] = EngineUtils.d(byteArray, intArray);
                    nArrayArray[n3][4] = EngineUtils.d(byteArray, intArray);
                }
                z2.c[n7] = nArrayArray;
            }
            if (n5 == 0) {
                al2.a = z2;
                continue;
            }
            al2.b = z2;
        }
        n4 = EngineUtils.d(byteArray, intArray);
        for (n5 = 0; n5 < n4; ++n5) {
            short s2;
            int n11;
            UIComponent w2;
            int n12 = EngineUtils.c(byteArray, intArray);
            if (n12 == 0) {
                w2 = new MenuWidget();
                ((MenuWidget)w2).b(n12);
                ((MenuWidget)w2).a(EngineUtils.d(byteArray, intArray));
                ((MenuWidget)w2).a(EngineUtils.d(byteArray, intArray), this.c);
                ((MenuWidget)w2).b(EngineUtils.d(byteArray, intArray), this.c);
                ((MenuWidget)w2).c(EngineUtils.d(byteArray, intArray), this.c);
                ((MenuWidget)w2).d(EngineUtils.d(byteArray, intArray), this.c);
                ((MenuWidget)w2).c(al2.a());
                if (al2.a != null) {
                    for (int i3 = 0; i3 < al2.a.b.length; ++i3) {
                        if (al2.a.b[i3] != ((MenuWidget)w2).a()) continue;
                        ((MenuWidget)w2).a(al2.a);
                        break;
                    }
                }
                if (al2.b != null) {
                    for (int i4 = 0; i4 < al2.b.b.length; ++i4) {
                        if (al2.b.b[i4] != ((MenuWidget)w2).a()) continue;
                        ((MenuWidget)w2).a(al2.b);
                        break;
                    }
                }
                this.a[this.b] = w2;
                ++this.b;
                al2.g()[n5] = w2;
                this.a(byteArray, intArray, (MenuWidget)al2.g()[n5], n2, flag5);
                continue;
            }
            if (n12 == 1) {
                w2 = new ItemListWidget();
                ((ItemListWidget)w2).b(n12);
                ((ItemListWidget)w2).a(EngineUtils.d(byteArray, intArray));
                ((ItemListWidget)w2).a(EngineUtils.d(byteArray, intArray), this.c);
                ((ItemListWidget)w2).b(EngineUtils.d(byteArray, intArray), this.c);
                ((ItemListWidget)w2).c(EngineUtils.d(byteArray, intArray), this.c);
                ((ItemListWidget)w2).d(EngineUtils.d(byteArray, intArray), this.c);
                ((ItemListWidget)w2).l();
                ((ItemListWidget)w2).c = this.g;
                int n13 = EngineUtils.d(byteArray, intArray);
                byte[] byArray4 = new byte[n13];
                for (n11 = 0; n11 < n13; ++n11) {
                    byArray4[n11] = EngineUtils.c(byteArray, intArray);
                }
                ((ItemListWidget)w2).h().a = EngineUtils.randomInt(byArray4);
                ((ItemListWidget)w2).h().b = EngineUtils.c(byteArray, intArray);
                ((ItemListWidget)w2).h().c = EngineUtils.c(byteArray, intArray);
                ((ItemListWidget)w2).h().d = EngineUtils.c(byteArray, intArray) != 0;
                ((ItemListWidget)w2).h().e = EngineUtils.e(byteArray, intArray);
                ((ItemListWidget)w2).h().f = EngineUtils.e(byteArray, intArray);
                ((ItemListWidget)w2).h().g = EngineUtils.e(byteArray, intArray);
                n11 = EngineUtils.d(byteArray, intArray);
                s2 = EngineUtils.c(byteArray, intArray);
                if (n11 < 0) {
                    ((ItemListWidget)w2).h().i = null;
                } else {
                    ((ItemListWidget)w2).h().i = new SpriteWidget();
                    ((ItemListWidget)w2).h().i.a = s2;
                    ((ItemListWidget)w2).h().i.a(n11);
                }
                ((ItemListWidget)w2).h().j = EngineUtils.e(byteArray, intArray);
                ((ItemListWidget)w2).h().k = EngineUtils.e(byteArray, intArray);
                ((ItemListWidget)w2).h().l = EngineUtils.e(byteArray, intArray);
                n3 = EngineUtils.d(byteArray, intArray);
                n6 = EngineUtils.c(byteArray, intArray);
                if (n3 < 0) {
                    ((ItemListWidget)w2).h().m = null;
                } else {
                    ((ItemListWidget)w2).h().m = new SpriteWidget();
                    ((ItemListWidget)w2).h().m.a(n3);
                    ((ItemListWidget)w2).h().m.a = n6;
                }
                ((ItemListWidget)w2).h().h = EngineUtils.c(byteArray, intArray);
                if (((ItemListWidget)w2).h().i != null) {
                    ((ItemListWidget)w2).h().i.a(n2, flag5, ((ItemListWidget)w2).h().h);
                }
                if (((ItemListWidget)w2).h().m != null) {
                    ((ItemListWidget)w2).h().m.a(n2, flag5, ((ItemListWidget)w2).h().h);
                }
                ((ItemListWidget)w2).c(al2.a());
                if (al2.a != null) {
                    for (n12 = 0; n12 < al2.a.b.length; ++n12) {
                        if (al2.a.b[n12] != ((ItemListWidget)w2).a()) continue;
                        ((ItemListWidget)w2).a(al2.a);
                        break;
                    }
                }
                if (al2.b != null) {
                    for (n12 = 0; n12 < al2.b.b.length; ++n12) {
                        if (al2.b.b[n12] != ((ItemListWidget)w2).a()) continue;
                        ((ItemListWidget)w2).a(al2.b);
                        break;
                    }
                }
                this.a[this.b] = w2;
                ++this.b;
                al2.g()[n5] = w2;
                ((ItemListWidget)w2).a = EngineUtils.c(byteArray, intArray);
                ((ItemListWidget)w2).b = EngineUtils.c(byteArray, intArray);
                continue;
            }
            if (n12 != 2) continue;
            w2 = new MessageBox();
            ((MessageBox)w2).q(EngineUtils.d(byteArray, intArray));
            ((MessageBox)w2).a(EngineUtils.d(byteArray, intArray), this.c);
            ((MessageBox)w2).b(EngineUtils.d(byteArray, intArray), this.c);
            ((MessageBox)w2).a((int)EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).b(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).c(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).d(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).e(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).f(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).g(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).h(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).k(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).l(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).m(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).n(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).o(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).p(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).i(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).j(EngineUtils.c(byteArray, intArray));
            ((MessageBox)w2).a = EngineUtils.e(byteArray, intArray);
            short s3 = EngineUtils.d(byteArray, intArray);
            int n14 = EngineUtils.c(byteArray, intArray);
            if (s3 < 0) {
                ((MessageBox)w2).b = null;
            } else {
                ((MessageBox)w2).b = new SpriteWidget();
                ((MessageBox)w2).b.a(s3);
                ((MessageBox)w2).b.a = n14;
                ((MessageBox)w2).b.a(n2, flag5, (byte)n14);
            }
            s3 = EngineUtils.d(byteArray, intArray);
            n14 = EngineUtils.c(byteArray, intArray);
            if (s3 < 0) {
                ((MessageBox)w2).c = null;
            } else {
                ((MessageBox)w2).c = new SpriteWidget();
                ((MessageBox)w2).c.a(s3);
                ((MessageBox)w2).c.a = n14;
                ((MessageBox)w2).c.a(n2, flag5, (byte)n14);
            }
            ((MessageBox)w2).r(EngineUtils.d(byteArray, intArray));
            byte by = EngineUtils.c(byteArray, intArray);
            n11 = by;
            if (by == 0) {
                ((MessageBox)w2).e = ((MessageBox)w2).n();
            } else if (n11 == 1) {
                n12 = ((MessageBox)w2).m();
                n6 = ((MessageBox)w2).l();
                UIHotspot[] aArray = new UIHotspot[n6 * n12];
                for (n14 = 0; n14 != n6 * n12; ++n14) {
                    aArray[n14] = new UIHotspot();
                }
                ((MessageBox)w2).e = aArray;
                s2 = EngineUtils.d(byteArray, intArray);
                for (n3 = 0; n3 < s2; ++n3) {
                    n6 = EngineUtils.d(byteArray, intArray);
                    n12 = EngineUtils.d(byteArray, intArray);
                    byte by2 = EngineUtils.c(byteArray, intArray);
                    n14 = EngineUtils.d(byteArray, intArray);
                    n11 = EngineUtils.d(byteArray, intArray);
                    short s4 = EngineUtils.d(byteArray, intArray);
                    short s5 = EngineUtils.d(byteArray, intArray);
                    ((MessageBox)w2).e[n6] = new UIHotspot(n12, by2, n14, n11, s4, s5);
                }
            }
            ((MessageBox)w2).s(al2.a());
            this.a[this.b] = w2;
            ++this.b;
            al2.g()[n5] = w2;
        }
    }

    private Rectangle a(UIComponent w2) {
        if (w2.i() != 0) {
            return new Rectangle(w2.b(), w2.c(), w2.d(), w2.e());
        }
        MenuWidget al2 = (MenuWidget)w2;
        if (al2.g() == null || al2.g()[0] == null) {
            return new Rectangle(w2.b(), w2.c(), w2.d(), w2.e());
        }
        int n2 = this.a((UIComponent)al2.g()[0]).a;
        int n3 = this.a((UIComponent)al2.g()[0]).a + this.a((UIComponent)al2.g()[0]).c;
        int n4 = this.a((UIComponent)al2.g()[0]).b;
        int n5 = this.a((UIComponent)al2.g()[0]).b + this.a((UIComponent)al2.g()[0]).d;
        for (int i = 0; i < al2.g().length && al2.g()[i2] != null; ++i) {
            Rectangle ak2 = this.a(al2.g()[i]);
            if (n2 > ak2.a) {
                n2 = ak2.a;
            }
            if (n3 < ak2.a + ak2.c) {
                n3 = ak2.a + ak2.c;
            }
            if (n4 > ak2.b) {
                n4 = ak2.b;
            }
            if (n5 >= ak2.b + ak2.d) continue;
            n5 = ak2.b + ak2.d;
        }
        return new Rectangle(n2, n4, n3 - n2, n5 - n4);
    }

    /*
     * Unable to fully structure code
     */
    public final void a(Graphics var1_1) {
        var3_2 = this.c;
        var2_3 = var1_1;
        var1_1 = this;
        var6_4 = var3_2;
        var5_6 = var2_3;
        var4_8 = var1_1;
        if (var6_4.a == null && var6_4.b == null) ** GOTO lbl-1000
        if (var6_4.a == null && var6_4.b != null) {
            v0 = var6_4.b;
        } else if (var6_4.a != null && var6_4.b == null) {
            v0 = var6_4.a;
        } else lbl-1000:
        // 2 sources

        {
            v0 = var6_4 = null;
        }
        if (v0 != null && var6_4.d < var6_4.a) {
            v1 = var7_10 = var4_8;
            var4_8 = var6_4;
            var6_4 = v1;
            var8_11 = 0;
            var9_12 = 0;
            var10_13 = 0;
            var11_14 = 0;
            if (var4_8.b[0] != -1) {
                var12_15 = super.a(var7_10.a(var4_8.b[0]));
                var8_11 = var12_15.a;
                var9_12 = var12_15.a + var12_15.c;
                var10_13 = var12_15.b;
                var11_14 = var12_15.b + var12_15.d;
                for (var13_16 = 1; var13_16 != var4_8.d; ++var13_16) {
                    var12_15 = super.a(var7_10.a(var4_8.b[var13_16]));
                    if (var8_11 > var12_15.a) {
                        var8_11 = var12_15.a;
                    }
                    if (var9_12 < var12_15.a + var12_15.c) {
                        var9_12 = var12_15.a + var12_15.c;
                    }
                    if (var10_13 > var12_15.b) {
                        var10_13 = var12_15.b;
                    }
                    if (var11_14 >= var12_15.b + var12_15.d) continue;
                    var11_14 = var12_15.b + var12_15.d;
                }
            }
            new Rectangle(var8_11, var10_13, var9_12 - var8_11, var11_14 - var10_13);
            var4_8 = new Rectangle();
            var6_4 = new Rectangle();
            var5_6.setColor(255, 255, 255);
            var5_6.fillRect(var4_8.a, var4_8.b, var4_8.c, var4_8.d);
            var5_6.setColor(245, 222, 179);
            var5_6.drawRect(var4_8.a, var4_8.b, var4_8.c, var4_8.d);
            var5_6.setColor(95, 158, 160);
            var5_6.fillRect(var6_4.a, var6_4.b, var6_4.c, var6_4.d);
        }
        for (var4_9 = 0; var4_9 < var3_2.g().length && var3_2.g()[var4_9] != null; ++var4_9) {
            if (var3_2.g()[var4_9].f() != null) {
                var5_7 = false;
                var6_5 = EngineUtils.a(var1_1.f);
                if (var6_5 > 0 && var1_1.f[var6_5 - 1] == var3_2.a()) {
                    var5_7 = true;
                }
                var3_2.g()[var4_9].f().a(var2_3, var3_2.g()[var4_9].a(), var5_7, var1_1.f, true, var1_1.c);
                continue;
            }
            var3_2.g()[var4_9].a(var2_3, false, true, var1_1.c, var1_1.f);
        }
    }

    public final void b() {
        MenuWidget al2 = this.c;
        TextRenderer ao2 = this;
        for (int i = 0; i < al2.g().length && al2.g()[i2] != null; ++i) {
            if (al2.g()[i].f() != null) {
                al2.g()[i].f().a(al2.g()[i].a(), ao2.f, true, ao2.c);
                continue;
            }
            al2.g()[i].a(false, true, ao2.c, ao2.f);
        }
    }

    public final UIComponent a(int n2) {
        return EngineUtils.a(this.c, n2);
    }

    public final boolean b(int n2) {
        boolean bl;
        UIComponent w2;
        int n3 = 0;
        int[] intArray = this.e;
        UIComponent w3 = this.c;
        TextRenderer ao2 = this;
        while (true) {
            int n4;
            if ((n4 = EngineUtils.randomInt(intArray)) == 0) {
                w2 = w3;
                break;
            }
            if (n3 == n4 - 1) {
                w2 = w3.g()[intArray[n3]];
                break;
            }
            UIComponent w4 = w3.g()[intArray[n3]];
            ++n3;
            w3 = w4;
        }
        MenuWidget al2 = w2;
        if (w2.i() == 2) {
            w3 = (MessageBox)((Object)al2);
            int n5 = n2;
            TextRenderer ao3 = this;
            boolean bl2 = false;
            switch (n5) {
                case 0: {
                    bl2 = ((MessageBox)w3).a((byte)0);
                    int[] intArray2 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 0});
                    break;
                }
                case 1: {
                    bl2 = ((MessageBox)w3).a((byte)1);
                    int[] nArray3 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 1});
                    break;
                }
                case 2: {
                    bl2 = ((MessageBox)w3).a((byte)2);
                    int[] nArray4 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 2});
                    break;
                }
                case 3: {
                    bl2 = ((MessageBox)w3).a((byte)3);
                    int[] nArray5 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 3});
                    break;
                }
                case 5: {
                    int[] nArray6 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 4});
                    bl2 = true;
                    break;
                }
                case 7: {
                    bl2 = ao3.e();
                    if (bl2) {
                        ((MessageBox)w3).d = false;
                        int[] nArray7 = new int[]{-1, -1, -1, -1};
                        ao3.h.a(new int[]{-1, -1, 7});
                        break;
                    }
                    int[] nArray8 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 5});
                }
            }
            bl = bl2;
        } else {
            w3 = al2;
            int n6 = n2;
            TextRenderer ao4 = this;
            boolean bl3 = false;
            n3 = 0;
            Object object = ((MenuWidget)w3).l() != null ? ((MenuWidget)w3).l() : (Object)new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
            int[] nArray9 = new int[3];
            nArray9[1] = ((MenuWidget)w3).b != null ? ((MenuWidget)w3).b.f : -1;
            nArray9[0] = ((MenuWidget)w3).a != null ? ((MenuWidget)w3).a.f : -1;
            for (int i = 0; i < ((byte[][])object).length; ++i) {
                if (object[i][0] != n6) continue;
                n3 = 1;
                bl3 = false;
                switch (object[i][1]) {
                    case 0: {
                        nArray9[2] = 0;
                        if (((MenuWidget)w3).a != null) {
                            if (object[i][3] != -1 && ((MenuWidget)w3).a.f % (object[i][3] + 1) == 0) {
                                ((MenuWidget)w3).a.a(object[i][3], ao4.c);
                            } else {
                                ((MenuWidget)w3).a.b(object[i][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[0] = ((MenuWidget)w3).a.f;
                            int[] nArray10 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray11 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 1: {
                        nArray9[2] = 1;
                        if (((MenuWidget)w3).a != null) {
                            if (object[i][3] != -1 && (((MenuWidget)w3).a.f + 1) % (object[i][3] + 1) == 0) {
                                ((MenuWidget)w3).a.b(object[i][3], ao4.c);
                            } else {
                                ((MenuWidget)w3).a.a(object[i][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[0] = ((MenuWidget)w3).a.f;
                            int[] nArray12 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray13 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 2: {
                        nArray9[2] = 2;
                        if (((MenuWidget)w3).b != null) {
                            if (object[i][3] != -1 && ((MenuWidget)w3).b.f % (object[i][3] + 1) == 0) {
                                ((MenuWidget)w3).b.a(object[i][3], ao4.c);
                            } else {
                                ((MenuWidget)w3).b.b(object[i][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[1] = ((MenuWidget)w3).b.f;
                            int[] nArray14 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray15 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 3: {
                        nArray9[2] = 3;
                        if (((MenuWidget)w3).b != null) {
                            if (object[i][3] != -1 && (((MenuWidget)w3).b.f + 1) % (object[i][3] + 1) == 0) {
                                ((MenuWidget)w3).b.b(object[i][3], ao4.c);
                            } else {
                                ((MenuWidget)w3).b.a(object[i][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[1] = ((MenuWidget)w3).b.f;
                            int[] nArray16 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray17 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 4: {
                        if (((MenuWidget)w3).b != null) {
                            bl3 = ((MenuWidget)w3).b.f >= EngineUtils.a(((MenuWidget)w3).b.b) ? ao4.a(w3, ((MenuWidget)w3).b.b[((MenuWidget)w3).b.f - ((MenuWidget)w3).b.e]) : ao4.a(w3, ((MenuWidget)w3).b.b[((MenuWidget)w3).b.f]);
                        }
                        if (!bl3 && ((MenuWidget)w3).a != null) {
                            bl3 = ((MenuWidget)w3).a.f >= EngineUtils.a(((MenuWidget)w3).a.b) ? ao4.a(w3, ((MenuWidget)w3).a.b[((MenuWidget)w3).a.f - ((MenuWidget)w3).a.e]) : ao4.a(w3, ((MenuWidget)w3).a.b[((MenuWidget)w3).a.f]);
                        }
                        if (bl3) {
                            nArray9[2] = 6;
                            int[] nArray18 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        nArray9[2] = 4;
                        int[] nArray19 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 5: {
                        bl3 = ao4.e();
                        if (bl3) {
                            nArray9[2] = 7;
                            int[] nArray20 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        nArray9[2] = 5;
                        int[] nArray21 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                    }
                }
                if (bl3) break;
            }
            if (n3 == 0) {
                switch (n6) {
                    case 14: 
                    case 15: 
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: 
                    case 22: 
                    case 23: {
                        nArray9[2] = n6 - 6;
                        int[] nArray22 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                    }
                }
            }
            bl = bl3;
        }
        return bl;
    }

    private boolean e() {
        boolean bl = false;
        int n2 = -1;
        for (int i2 = EngineUtils.a(this.f) - 2; i2 >= 0; --i2) {
            UIComponent w2 = EngineUtils.a(this.c, this.f[i2]);
            if (((MenuWidget)w2).a == null && ((MenuWidget)w2).b == null) continue;
            bl = true;
            n2 = w2.a();
            break;
        }
        if (bl) {
            this.e = this.c(n2);
            if (this.e == null) {
                this.e = EngineUtils.b(50);
            }
            this.f();
        }
        return bl;
    }

    private boolean a(UIComponent w2, int n2) {
        if (w2.i() == 1) {
            return false;
        }
        int n3 = this.a(w2, n2, true);
        if (n3 == -1) {
            return false;
        }
        this.e = this.c(n3);
        if (this.e == null) {
            this.e = EngineUtils.b(50);
        }
        this.f();
        return true;
    }

    private void f() {
        this.f = EngineUtils.b(50);
        UIComponent w2 = this.c;
        int n2 = 0;
        ++n2;
        this.f[0] = w2.a();
        for (int i = 0; i < this.e.length && this.e[i2] != -1; ++i) {
            w2 = w2.g()[this.e[i]];
            this.f[n2++] = w2.a();
        }
    }

    private int a(UIComponent w2, int n2, boolean flag3) {
        if (w2.i() == 2 && n2 == -1) {
            if (flag3) {
                ((MessageBox)w2).d = true;
            }
            return w2.a();
        }
        if ((((MenuWidget)w2).a != null || ((MenuWidget)w2).b != null) && n2 == -1) {
            return w2.a();
        }
        for (int i = 0; i < w2.g().length && w2.g()[i2] != null; ++i) {
            int n3;
            if (w2.g()[i].i() == 1 || n2 != -1 && w2.g()[i].a() != n2 || (n3 = this.a(w2.g()[i], -1, flag3)) == -1) continue;
            return n3;
        }
        return -1;
    }

    private int[] c(int n2) {
        int[] intArray = EngineUtils.b(50);
        UIComponent w2 = EngineUtils.a(this.c, n2);
        while (w2.j() != -1) {
            UIComponent w3 = EngineUtils.a(this.c, w2.j());
            for (int i = 0; i < w3.g().length && w3.g()[i2] != null; ++i) {
                if (w3.g()[i].a() != w2.a()) continue;
                EngineUtils.randomRange(intArray, i);
                break;
            }
            w2 = EngineUtils.a(this.c, w2.j());
        }
        return intArray;
    }

    public final void c() {
        this.c.k();
        this.d = null;
        this.e = null;
        this.f = null;
        this.h = null;
        this.a = null;
    }

    public TextRenderer() {
    }

    private static RecordStore a(String text, String string2, String string3, String charSequence, int n2) {
        RecordStore recordStore = null;
        try {
            if (string3 == null || string3.length() == 0) {
                string3 = "00";
            }
            if (charSequence != null) {
                ((String)charSequence).length();
            }
            charSequence = new StringBuffer();
            ((StringBuffer)charSequence).append("dcn").append(text).append(string2).append(string3).append(n2);
            recordStore = RecordStore.openRecordStore(((StringBuffer)charSequence).toString(), true, 1, true);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return recordStore;
    }

    public static void a(CarrierHelper object, String object2, String object3, String object4, String text, int n2) {
        if ((object2 = TextRenderer.a((String)object2, (String)object3, (String)object4, text, n2)) != null) {
            try {
                try {
                    object3 = new ByteArrayOutputStream();
                    object4 = new DataOutputStream((OutputStream)object3);
                    ((DataOutputStream)object4).writeInt(((CarrierHelper)object).i());
                    if (((CarrierHelper)object).i() < n2) {
                        ((DataOutputStream)object4).writeInt(((CarrierHelper)object).d());
                        ((DataOutputStream)object4).writeUTF(((CarrierHelper)object).c());
                        ((DataOutputStream)object4).writeUTF(((CarrierHelper)object).b());
                        ((DataOutputStream)object4).writeUTF(((CarrierHelper)object).g());
                        ((DataOutputStream)object4).writeInt(((CarrierHelper)object).a());
                        ((DataOutputStream)object4).writeUTF(((CarrierHelper)object).f());
                        ((DataOutputStream)object4).writeUTF(((CarrierHelper)object).e());
                        ((DataOutputStream)object4).writeBoolean(((CarrierHelper)object).h());
                        ((DataOutputStream)object4).writeInt(((CarrierHelper)object).j());
                        ((DataOutputStream)object4).writeLong(((CarrierHelper)object).k());
                    }
                    object = ((ByteArrayOutputStream)object3).toByteArray();
                    if (((RecordStore)object2).getNumRecords() == 0) {
                        ((RecordStore)object2).addRecord((byte[])object, 0, ((Object)object).length);
                    } else {
                        ((RecordStore)object2).setRecord(1, (byte[])object, 0, ((Object)object).length);
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    try {
                        ((RecordStore)object2).closeRecordStore();
                        return;
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                        recordStoreNotOpenException.printStackTrace();
                        return;
                    }
                    catch (RecordStoreException recordStoreException) {
                        recordStoreException.printStackTrace();
                        return;
                    }
                }
            }
            catch (Throwable throwable) {
                try {
                    ((RecordStore)object2).closeRecordStore();
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    recordStoreNotOpenException.printStackTrace();
                }
                catch (RecordStoreException recordStoreException) {
                    recordStoreException.printStackTrace();
                }
                throw throwable;
            }
            try {
                ((RecordStore)object2).closeRecordStore();
                return;
            }
            catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                recordStoreNotOpenException.printStackTrace();
                return;
            }
            catch (RecordStoreException recordStoreException) {
                recordStoreException.printStackTrace();
            }
        }
    }
}

