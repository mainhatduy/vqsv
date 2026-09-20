/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.BillingClient;
import a.NetworkConnection;
import a.NetworkConfig;
import a.ProtocolEncoder;
import a.SecurityHelper;
import a.PropertyRef;
import java.util.Vector;

/**
 * SessionData - Runtime session context storage for billing requests.
 */
public final class SessionData {
    public SecurityHelper a;
    public SessionData b;
    public String c = "";
    public Vector d;
    public Object[] e;
    public int f;
    private NetworkConnection[] h;
    private int i;
    public ProtocolEncoder g;

    public SessionData() {
    }

    public SessionData(ProtocolEncoder g2, SecurityHelper h2) {
        this.g = g2;
        this.a = h2;
        this.e = new Object[10];
        this.h = new NetworkConnection[10];
        this.d = new Vector();
    }

    public final NetworkConnection a(NetworkConfig d2, int n2, int n3, int n4, boolean flag5, boolean flag6) {
        this.d(this.i + 1);
        NetworkConnection c2 = this.b();
        this.b().d = n2;
        c2.e = n3;
        c2.f = n4;
        c2.g = flag5;
        c2.h = flag6;
        c2.b = d2;
        return c2;
    }

    public final void a() {
        if (this.c()) {
            throw new RuntimeException("Stack underflow");
        }
        this.d(this.i - 1);
    }

    private void d(int n2) {
        if (n2 > this.i) {
            int n3;
            SessionData i2 = this;
            if (n2 > 100) {
                throw new RuntimeException("Stack overflow");
            }
            int n4 = n3 = i2.h.length;
            while (n4 <= n2) {
                n4 <<= 1;
            }
            if (n4 > n3) {
                NetworkConnection[] cArray = new NetworkConnection[n4];
                System.arraycopy(i2.h, 0, cArray, 0, n3);
                i2.h = cArray;
            }
        } else {
            this.a(n2, this.i - 1);
        }
        this.i = n2;
    }

    private void a(int n2, int n3) {
        while (n2 <= n3) {
            if (this.h[n2] != null) {
                this.h[n2].b = null;
            }
            ++n2;
        }
    }

    public final void a(int n2) {
        if (this.f < n2) {
            int n3;
            SessionData i2 = this;
            if (n2 > 1000) {
                throw new RuntimeException("Stack overflow");
            }
            int n4 = n3 = i2.e.length;
            while (n4 <= n2) {
                n4 <<= 1;
            }
            if (n4 > n3) {
                Object[] objectArray = new Object[n4];
                System.arraycopy(i2.e, 0, objectArray, 0, n3);
                i2.e = objectArray;
            }
        } else {
            this.b(n2, this.f - 1);
        }
        this.f = n2;
    }

    public final void a(int n2, int n3, int n4) {
        if (n4 > 0 && n2 != n3) {
            System.arraycopy(this.e, n2, this.e, n3, n4);
        }
    }

    private void b(int n2, int n3) {
        while (n2 <= n3) {
            this.e[n2] = null;
            ++n2;
        }
    }

    public final void b(int n2) {
        int n3 = this.d.size();
        while (--n3 >= 0) {
            PropertyRef j2 = (PropertyRef)this.d.elementAt(n3);
            if (j2.b < n2) {
                return;
            }
            j2.c = this.e[j2.b];
            j2.a = null;
            this.d.removeElementAt(n3);
        }
    }

    public final NetworkConnection b() {
        if (this.c()) {
            return null;
        }
        NetworkConnection c2 = this.h[this.i - 1];
        if (c2 == null) {
            this.h[this.i - 1] = c2 = new NetworkConnection(this);
        }
        return c2;
    }

    public final NetworkConnection c(int n2) {
        a.BillingClient.a(n2 >= 0, "Level must be non-negative");
        n2 = this.i - n2 - 1;
        a.BillingClient.a(n2 >= 0, "Level too high");
        return this.h[n2];
    }

    public final boolean c() {
        return this.i == 0;
    }
}

