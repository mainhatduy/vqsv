/*
 * Decompiled with CFR 0.152.
 */
import a.BillingListener;
import a.NetworkConnection;

/**
 * BillingCallback - Transaction callback bridge connecting payment UI with network client.
 */
public final class BillingCallback
implements ScreenView {
    private int a;
    private BillingCanvas b;

    public BillingCallback(BillingCanvas q2, int n2) {
        this.b = q2;
        this.a = n2;
    }

    public final int a(Renderable c2, int n2) {
        return this.b.a(this.a, c2, n2);
    }
}

