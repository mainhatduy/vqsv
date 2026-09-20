/*
 * Decompiled with CFR 0.152.
 */
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 * UIManager - UI dialog window manager, layout loader, and focus controller.
 */
public final class UIManager {
    private static UIManager c;
    private Hashtable d = new Hashtable();
    private Vector e = new Vector();
    private Vector f = new Vector();
    public TextRenderer a;
    public TextPainter b = new TextPainter();

    private UIManager() {
    }

    public static UIManager getInstance() {
        if (c == null) {
            c = new UIManager();
        }
        return c;
    }

    public final void b() {
        if (c != null) {
            UIManager ab2 = c;
            Enumeration enumeration2 = ab2.d.elements();
            while (enumeration2.hasMoreElements()) {
                ((TextRenderer)enumeration2.nextElement()).c();
            }
            ab2.d.clear();
            ab2.e.removeAllElements();
        }
        this.a = null;
    }

    public final void a(Graphics g) {
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); ++i) {
                ((TextRenderer)this.e.elementAt(i)).a(g);
            }
        }
        g.setClip(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
    }

    public final void c() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public final void a(String text, int n2, ScriptEventListener i2) {
        TextRenderer ao2 = (TextRenderer)this.d.get(text);
        if (ao2 != null) {
            if (!text.equals("/data/ui/dialog.ui")) {
                this.d.remove(text);
            }
            this.e.removeElement(ao2);
            if (!text.equals("/data/ui/dialog.ui")) {
                ao2.c();
                ao2 = null;
            }
            if (text.equals("/data/ui/dialog.ui")) {
                this.e.addElement(ao2);
                this.a = ao2;
                this.f.addElement(text);
            }
        }
        if (ao2 == null) {
            ao2 = new TextRenderer(i2);
            ao2.a(this.b);
            ao2.a(text, n2);
            this.d.put(text, ao2);
            this.e.addElement(ao2);
            this.a = ao2;
            this.f.addElement(text);
        }
    }

    public final void a(String text) {
        TextRenderer ao2 = (TextRenderer)this.d.get(text);
        if (ao2 != null) {
            if (this.a.equals(ao2)) {
                this.a = null;
            }
            if (!text.equals("/data/ui/dialog.ui")) {
                this.d.remove(text);
            }
            this.e.removeElement(ao2);
            this.f.removeElement(text);
            if (!text.equals("/data/ui/dialog.ui")) {
                ao2.c();
            }
        }
        if (this.d.size() > 0 && this.e.size() > 0) {
            this.a = (TextRenderer)this.e.lastElement();
        }
    }

    public final boolean b(String text) {
        return this.f.size() > 0 && this.f.lastElement().equals(text);
    }

    public final boolean c(String text) {
        return this.f.size() > 0 && this.f.contains(text);
    }

    public final TextRenderer d(String text) {
        return (TextRenderer)this.d.get(text);
    }

    public static boolean a(TextRenderer ao2, int n2) {
        return ((ItemListWidget)ao2.a((int)1)).h().m.a().b(n2);
    }

    public final boolean d() {
        return ((ItemListWidget)this.a.a((int)1)).h().m.a().e() && ((ItemListWidget)this.a.a((int)1)).h().m.a().f();
    }
}

