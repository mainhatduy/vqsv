/*
 * Decompiled with CFR 0.152.
 */
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * NumericInputWidget - Numeric quantity picker and amount selector widget.
 */
public final class NumericInputWidget {
    public int a;
    public int[] b;
    public int[][][] c;
    public int d;
    private Rectangle[] k;
    public int e;
    public int f;
    public boolean g;
    public int h;
    public int i;
    private int l;
    private int m;
    private Vector n;
    public Vector j;

    public NumericInputWidget(int n2) {
        this.h = 0;
        this.i = 0;
        this.l = -1;
        this.m = -1;
        this.n = new Vector();
        this.j = new Vector();
        this.a = 0;
        this.b = null;
        this.b = EngineUtils.b(50);
        this.d = 0;
        this.k = new Rectangle[20];
        this.e = 0;
        this.f = 0;
        this.g = true;
        this.l = -1;
    }

    public final void a(int n2, UIComponent w2) {
        if (this.a <= 1) {
            this.f = 0;
            this.e = 0;
            return;
        }
        if (this.g) {
            if (this.h == 0) {
                this.f += n2;
                if (this.f >= this.a) {
                    this.f %= this.a;
                    if (this.f >= this.e + this.d || this.f < this.e) {
                        this.e = this.f;
                    }
                    this.a(w2);
                    return;
                }
                if (this.f >= this.e + this.d) {
                    this.e += n2;
                    if (this.e + this.d >= this.a) {
                        this.e = this.a - this.d;
                    }
                    this.a(w2);
                    return;
                }
            } else if (this.h == 1) {
                this.f += n2;
                if (this.f >= this.a) {
                    this.f %= this.a;
                }
                this.e = this.f - this.i < 0 ? this.a + (this.f - this.i) : this.f - this.i;
                this.a(w2);
                return;
            }
        } else {
            this.f += n2;
            if (this.f >= this.a) {
                this.f = this.a - 1;
                if (this.a >= this.d) {
                    this.e = this.a - this.d;
                }
                this.a(w2);
                return;
            }
            if (this.f >= this.e + this.d) {
                this.e += n2;
                this.a(w2);
            }
        }
    }

    public final void b(int n2, UIComponent w2) {
        if (this.a <= 1) {
            this.f = 0;
            this.e = 0;
            return;
        }
        if (this.g) {
            if (this.h == 0) {
                this.f -= n2;
                if (this.f < 0) {
                    this.f = this.a + this.f % this.a;
                    if (this.f >= this.e + this.d || this.f < this.e) {
                        this.e = this.a - this.d - (this.a - this.f - 1);
                    }
                    this.a(w2);
                    return;
                }
                if (this.f < this.e) {
                    this.e = this.f;
                    this.a(w2);
                    return;
                }
            } else if (this.h == 1) {
                this.f -= n2;
                if (this.f < 0) {
                    this.f = this.a + this.f % this.a;
                }
                this.e = this.f - this.i < 0 ? this.a + (this.f - this.i) : this.f - this.i;
                this.a(w2);
                return;
            }
        } else {
            this.f -= n2;
            if (this.f < 0) {
                this.f = 0;
                this.e = 0;
                this.a(w2);
                return;
            }
            if (this.f < this.e) {
                this.e -= n2;
                this.a(w2);
            }
        }
    }

    private void a(UIComponent w2) {
        if (this.l == 1) {
            if (this.m == 1) {
                for (int i = 0; i < this.d; ++i) {
                    int n2 = (null).length > ((String[])this.n.elementAt((this.e + i) % this.a)).length ? ((String[])this.n.elementAt((this.e + i) % this.a)).length : (null).length;
                    for (int i3 = 0; i3 < n2; ++i3) {
                        EngineUtils.a((UIComponent)w2, (int)null[i3]).h().a = ((String[])this.n.elementAt((this.e + i) % this.a))[i3];
                    }
                }
                return;
            }
            if (this.m == 2) {
                for (int i4 = 0; i4 < this.d; ++i4) {
                    int n3 = (null).length > ((UIButton[])this.n.elementAt((this.e + i4) % this.a)).length ? ((UIButton[])this.n.elementAt((this.e + i4) % this.a)).length : (null).length;
                    for (int i5 = 0; i5 < n3; ++i5) {
                        EngineUtils.a(w2, (int)null[i5]).a(((UIButton[])this.n.elementAt((this.e + i4) % this.a))[i5]);
                    }
                }
            }
        }
    }

    public final void a(Graphics g, int n2, boolean flag3, int[] intArray, boolean flag5, UIComponent w2) {
        int n3;
        if (this.k != null) {
            this.k = new Rectangle[20];
            for (n3 = 0; n3 < this.d; ++n3) {
                Rectangle ak2;
                UIComponent w3 = EngineUtils.a(w2, this.b[n3]);
                this.k[n3] = ak2 = new Rectangle(w3.b(), w3.c(), w3.d(), w3.e());
            }
        }
        n3 = -1;
        for (int i = 0; i < this.b.length && this.b[i2] != -1; ++i) {
            if (this.b[i] != n2) continue;
            n3 = i;
            break;
        }
        int[] intArray2 = EngineUtils.b(50);
        if (this.l == 1) {
            for (int i3 = 0; i3 < this.d; ++i3) {
                intArray2[i3] = i3;
            }
        } else if (this.l == -1) {
            for (int i4 = 0; i4 < this.d; ++i4) {
                intArray2[i4] = (this.e + i4) % this.b.length;
            }
        }
        for (int i5 = 0; i5 < intArray2.length && intArray2[i5] != -1; ++i5) {
            if (n3 != intArray2[i5]) continue;
            UIComponent w4 = EngineUtils.a(w2, n2);
            int n4 = w4.b() - this.k[i5].a;
            int n5 = w4.c() - this.k[i5].b;
            int n6 = w4.d();
            int n7 = w4.e();
            EngineUtils.a(w4, -n4, -n5, w2);
            w4.c(this.k[i5].c, w2);
            w4.d(this.k[i5].d, w2);
            if (flag3 && this.a > 0) {
                if (this.l == 1) {
                    if (this.f == (this.e + intArray2[i5]) % this.a) {
                        w4.a(g, true, flag5, w2, intArray);
                    } else {
                        w4.a(g, false, flag5, w2, intArray);
                    }
                } else if (this.f == intArray2[i5]) {
                    w4.a(g, true, flag5, w2, intArray);
                } else {
                    w4.a(g, false, flag5, w2, intArray);
                }
            } else {
                w4.a(g, false, flag5, w2, intArray);
            }
            EngineUtils.a(w4, n4, n5, w2);
            w4.c(n6, w2);
            w4.d(n7, w2);
        }
    }

    public final void a(int n2, int[] intArray, boolean flag3, UIComponent w2) {
        int n3;
        if (this.k != null) {
            this.k = new Rectangle[20];
            for (n3 = 0; n3 < this.d; ++n3) {
                Rectangle ak2;
                UIComponent w3 = EngineUtils.a(w2, this.b[n3]);
                this.k[n3] = ak2 = new Rectangle(w3.b(), w3.c(), w3.d(), w3.e());
            }
        }
        n3 = -1;
        for (int i = 0; i < this.b.length && this.b[i2] != -1; ++i) {
            if (this.b[i] != n2) continue;
            n3 = i;
            break;
        }
        int[] intArray2 = EngineUtils.b(50);
        if (this.l == 1) {
            for (int i3 = 0; i3 < this.d; ++i3) {
                intArray2[i3] = i3;
            }
        } else if (this.l == -1) {
            for (int i4 = 0; i4 < this.d; ++i4) {
                intArray2[i4] = (this.e + i4) % this.b.length;
            }
        }
        for (int i5 = 0; i5 < intArray2.length && intArray2[i5] != -1; ++i5) {
            if (n3 != intArray2[i5]) continue;
            UIComponent w4 = EngineUtils.a(w2, n2);
            int n4 = w4.b() - this.k[i5].a;
            int n5 = w4.c() - this.k[i5].b;
            int n6 = w4.d();
            int n7 = w4.e();
            EngineUtils.a(w4, -n4, -n5, w2);
            w4.c(this.k[i5].c, w2);
            w4.d(this.k[i5].d, w2);
            w4.a(flag3, flag3, w2, intArray);
            EngineUtils.a(w4, n4, n5, w2);
            w4.c(n6, w2);
            w4.d(n7, w2);
        }
    }

    public final void a() {
        if (this.j != null) {
            this.j = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.n != null) {
            this.n = null;
        }
        if (this.k != null) {
            this.k = null;
        }
    }

    public final void a(int n2) {
        if (n2 != 1 && n2 != -1) {
            this.l = -1;
            return;
        }
        this.l = n2;
    }

    public NumericInputWidget() {
    }

    public static int a(String text, Font font) {
        int n2 = 0;
        int n3 = 0;
        while (n3 < text.length()) {
            char c2 = text.charAt(n3);
            n2 += font.charWidth(c2);
            ++n3;
        }
        return n2;
    }

    public static int[] a(Graphics g, String text, Font font, int n2, int n3, int n4, int n5) {
        int n6 = 0;
        int n7 = 0;
        while (true) {
            int n8;
            int n9;
            String string2;
            block6: {
                Font font2 = font;
                string2 = text;
                int n10 = 0;
                int n11 = 0;
                while (n11 < string2.length()) {
                    char c2 = string2.charAt(n11);
                    if (c2 == '\n') {
                        n9 = n11 + 1;
                        break block6;
                    }
                    if ((n10 += font2.charWidth(c2)) > n2) {
                        n9 = n11;
                        break block6;
                    }
                    ++n11;
                }
                n9 = n8 = 0;
            }
            if (n9 == 0) break;
            string2 = text.charAt(n8 - 1) == '\n' ? text.substring(0, n8 - 1) : text.substring(0, n8);
            if (n6 >= n4 && n5 < n3) {
                g.drawString(string2, 5, n5, 0);
                n5 += font.getHeight();
            }
            if (n5 >= n3) {
                ++n7;
            }
            ++n6;
            text = text.substring(n8, text.length());
        }
        g.drawString(text, 5, n5, 0);
        return new int[]{n5, n7};
    }
}

