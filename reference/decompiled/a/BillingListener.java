/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.NetworkConnection;

/**
 * BillingListener - Interface for network billing transaction callbacks.
 */
public interface BillingListener {
    public int a(NetworkConnection conn, int code);
}

