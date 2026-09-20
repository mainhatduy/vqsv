/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.TransactionRecord;
import a.SecurityHelper;
import a.PropertyRef;

/**
 * NetworkConfig - Telecom carrier endpoints, APN configuration, and gateway URLs.
 */
public final class NetworkConfig {
    public TransactionRecord a;
    public SecurityHelper b;
    public PropertyRef[] c;

    public NetworkConfig(TransactionRecord f2, SecurityHelper h2) {
        this.a = f2;
        this.b = h2;
        this.c = new PropertyRef[f2.f];
    }

    public final String toString() {
        return "";
    }
}

