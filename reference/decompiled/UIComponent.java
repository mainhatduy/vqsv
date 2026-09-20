/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;

/**
 * UIComponent - Interface for interactive UI widgets supporting key handling and rendering.
 */
public interface UIComponent {
    public void a(boolean var1);

    public void a(Graphics g, boolean var2, boolean var3, UIComponent var4, int[] intArray);

    public void a(boolean var1, boolean var2, UIComponent var3, int[] intArray);

    public int a();

    public int b();

    public void a(int id, UIComponent var2);

    public int c();

    public void b(int id, UIComponent var2);

    public int d();

    public void c(int id, UIComponent var2);

    public int e();

    public void d(int id, UIComponent var2);

    public NumericInputWidget f();

    public UIComponent[] g();

    public UIButton h();

    public void a(UIButton var1);

    public int i();

    public int j();

    public void a(UIComponent var1);

    public void k();
}

