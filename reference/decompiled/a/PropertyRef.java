/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.SessionData;

/**
 * PropertyRef - Dynamic property reference holder with reflection-like accessors.
 */
public final class PropertyRef {
    public SessionData a;
    public int b;
    public Object c;

    public final Object getValue() {
        if (this.a == null) {
            return this.c;
        }
        return this.a.e[this.b];
    }

    public final void setValue(Object value) {
        if (this.a == null) {
            this.c = value;
            return;
        }
        this.a.e[this.b] = value;
    }
}

