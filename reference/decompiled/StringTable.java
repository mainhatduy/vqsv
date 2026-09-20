/*
 * Decompiled with CFR 0.152.
 */
import game.NpcEntity;
import game.Player;
import game.WorldManager;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 * StringTable - Game string indexer and localization text lookup table.
 */
public final class StringTable {
    private MapEngine a;
    private Vector b;
    private Vector c = new Vector();
    private Vector d;
    private ParticleEffect e;

    public StringTable() {
        this.b = new Vector();
        this.d = new Vector();
    }

    public final void a(MapEngine j2) {
        this.a = j2;
    }

    public final void a(BaseEntity n2) {
        switch (n2.s) {
            case 0: {
                this.b.addElement(n2);
                return;
            }
            case 1: {
                this.c.addElement(n2);
                return;
            }
            case 2: {
                this.d.addElement(n2);
            }
        }
    }

    public final void b(BaseEntity n2) {
        switch (n2.s) {
            case 0: {
                this.b.removeElement(n2);
                return;
            }
            case 1: {
                this.c.removeElement(n2);
                return;
            }
            case 2: {
                this.d.removeElement(n2);
            }
        }
    }

    public final void c(BaseEntity n2) {
        this.b(n2);
        this.d.addElement(n2);
    }

    public final void a(ParticleEffect ai2) {
        this.e = ai2;
    }

    public final void a() {
        this.a = null;
        this.e = null;
        this.c.removeAllElements();
        this.b.removeAllElements();
        this.d.removeAllElements();
    }

    public final void b() {
        int n2;
        this.e.d();
        this.a.a(this.e.i, this.e.j);
        this.a.c();
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            ((WorldEntity)this.b.elementAt(n2)).a();
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            for (int i = 0; i < this.c.size() - n2 - 1; ++i) {
                BaseEntity n3 = (BaseEntity)this.c.elementAt(i);
                BaseEntity n4 = (BaseEntity)this.c.elementAt(i + 1);
                if (n3.j <= n4.j) continue;
                this.c.setElementAt(n4, i);
                this.c.setElementAt(n3, i + 1);
            }
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            WorldEntity f2 = (WorldEntity)this.c.elementAt(n2);
            f2.a();
            if (!(f2 instanceof UIHotspot)) continue;
            if (((UIHotspot)f2).G != null && ((UIHotspot)f2).G.i()) {
                ((UIHotspot)f2).G.a();
                continue;
            }
            if (((UIHotspot)f2).H == null || !((UIHotspot)f2).H.i()) continue;
            ((UIHotspot)f2).H.a();
        }
        for (n2 = 0; n2 < this.d.size(); ++n2) {
            ((WorldEntity)this.d.elementAt(n2)).a();
        }
    }

    public final void a(Graphics g) {
        int n2;
        int n3;
        this.a.a(g, 1, 1);
        this.a.a(g, 2, 1);
        for (n3 = 0; n3 < this.d.size(); ++n3) {
            if (!((WorldEntity)this.d.elementAt(n3)).k()) continue;
            ((WorldEntity)this.d.elementAt(n3)).a(g, this.a.a, this.a.b);
        }
        n3 = 0;
        try {
            if (UIButton.a().c.P[2] == 2) {
                n3 = 1;
                for (n2 = 0; n2 < this.c.size(); ++n2) {
                    n3 = 2;
                    n3 = 2;
                    if (((WorldEntity)this.c.elementAt(n2)).k()) {
                        if ((WorldEntity)this.c.elementAt(n2) instanceof FormattedTextBuffer) continue;
                        n3 = 3;
                        ((WorldEntity)this.c.elementAt(n2)).a(g, this.a.a, this.a.b);
                    }
                    if ((WorldEntity)this.c.elementAt(n2) instanceof UIHotspot && ((UIHotspot)this.c.elementAt((int)n2)).v == 14) {
                        n3 = 4;
                        ((UIHotspot)this.c.elementAt(n2)).b(g, this.a.a, this.a.b);
                    }
                    n3 = 501;
                    if (!((WorldEntity)this.c.elementAt(n2) instanceof UIHotspot)) continue;
                    if (((UIHotspot)this.c.elementAt((int)n2)).G != null && ((UIHotspot)this.c.elementAt((int)n2)).G.j()) {
                        n3 = 5;
                        ((UIHotspot)this.c.elementAt((int)n2)).G.a(g, this.a.a, this.a.b);
                        continue;
                    }
                    if (((UIHotspot)this.c.elementAt((int)n2)).H == null || !((UIHotspot)this.c.elementAt((int)n2)).H.j()) continue;
                    n3 = 6;
                    ((UIHotspot)this.c.elementAt((int)n2)).H.a(g, this.a.a, this.a.b);
                }
                n3 = 7;
                n2 = MapEngine.getInstance().b(g.o().i, g.o().j);
                n3 = 8;
                if (n2 != 1 && UIButton.a().c.b != null && UIButton.a().c.j()) {
                    n3 = 9;
                    UIButton.a().c.b.a(g, this.a.a, this.a.b);
                }
                n3 = 10;
                UIButton.a().c.a(g, this.a.a, this.a.b);
            } else {
                for (n2 = 0; n2 < this.c.size(); ++n2) {
                    n3 = 110;
                    n3 = 11;
                    if (((WorldEntity)this.c.elementAt(n2)).k()) {
                        n3 = 12;
                        if (((WorldEntity)this.c.elementAt((int)n2)).b != null && ((WorldEntity)this.c.elementAt(n2)).j()) {
                            n3 = 13;
                            ((WorldEntity)this.c.elementAt((int)n2)).b.a(g, this.a.a, this.a.b);
                        }
                        n3 = 14;
                        ((WorldEntity)this.c.elementAt(n2)).a(g, this.a.a, this.a.b);
                    }
                    if ((WorldEntity)this.c.elementAt(n2) instanceof UIHotspot && ((UIHotspot)this.c.elementAt((int)n2)).v == 14) {
                        n3 = 15;
                        ((UIHotspot)this.c.elementAt(n2)).b(g, this.a.a, this.a.b);
                    }
                    n3 = 16;
                    if (!((WorldEntity)this.c.elementAt(n2) instanceof UIHotspot) || !((UIHotspot)this.c.elementAt(n2)).k()) continue;
                    n3 = 17;
                    if (((UIHotspot)this.c.elementAt((int)n2)).G != null && ((UIHotspot)this.c.elementAt((int)n2)).G.j()) {
                        n3 = 18;
                        ((UIHotspot)this.c.elementAt((int)n2)).G.a(g, this.a.a, this.a.b);
                        continue;
                    }
                    if (((UIHotspot)this.c.elementAt((int)n2)).H == null || !((UIHotspot)this.c.elementAt((int)n2)).H.j()) continue;
                    n3 = 19;
                    ((UIHotspot)this.c.elementAt((int)n2)).H.a(g, this.a.a, this.a.b);
                }
            }
        }
        catch (Exception exception) {
            DebugLogger.a(exception, "" + n3);
        }
        this.a.a(g, 3, 1);
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            WorldEntity f2 = (WorldEntity)this.b.elementAt(n2);
            if (!f2.k()) continue;
            f2.a(g, this.a.a, this.a.b);
        }
    }

    public final void b(Graphics g) {
        int n2;
        UIButton.a();
        UIButton.a(g, 0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
        this.a.a(g, 1, 1);
        this.a.a(g, 2, 1);
        for (n2 = 0; n2 < this.d.size(); ++n2) {
            if (!((WorldEntity)this.d.elementAt(n2)).k() || !(this.d.elementAt(n2) instanceof UIHotspot) || ((UIHotspot)this.d.elementAt((int)n2)).v != 0) continue;
            ((WorldEntity)this.d.elementAt(n2)).a(g, this.a.a, this.a.b);
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            if (!((WorldEntity)this.c.elementAt(n2)).k() || !(this.c.elementAt(n2) instanceof UIHotspot) || ((UIHotspot)this.c.elementAt((int)n2)).v != 0) continue;
            ((WorldEntity)this.c.elementAt(n2)).a(g, this.a.a, this.a.b);
        }
        this.a.a(g, 3, 1);
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            if (!((WorldEntity)this.b.elementAt(n2)).k() || !(this.b.elementAt(n2) instanceof UIHotspot) || ((UIHotspot)this.b.elementAt((int)n2)).v != 0) continue;
            ((WorldEntity)this.b.elementAt(n2)).a(g, this.a.a, this.a.b);
        }
        for (n2 = 0; n2 < BaseScreen.getScreenWidth() / GameDatabase.f.getWidth(); ++n2) {
            for (int i = 0; i < BaseScreen.getScreenHeight() / GameDatabase.f.getHeight(); ++i) {
                g.drawImage(GameDatabase.f, n2 * GameDatabase.f.getWidth(), i * GameDatabase.f.getHeight(), 20);
            }
        }
    }
}

