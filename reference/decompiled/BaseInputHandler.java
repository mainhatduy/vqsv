/*
 * Decompiled with CFR 0.152.
 */
/**
 * BaseInputHandler - Low-level key press/release bitmask manager and key event dispatcher.
 */
public abstract class BaseInputHandler {
    protected boolean Y;
    private BaseInputHandler a;
    private int keyHeldMask;
    private int keyPressedMask;
    private int keyReleasedMask;
    private int e;
    private int f;
    private int g;
    private int pointerX = -1;
    private int pointerY = -1;

    public void onKeyPressed(int keyCode) {
        int n3 = BaseInputHandler.getKeyBitmask(keyCode);
        this.keyPressedMask |= n3;
        this.keyHeldMask |= n3;
        if (this.a != null) {
            this.a.onKeyPressed(keyCode);
        }
    }

    public void onKeyReleased(int keyCode) {
        int n3 = BaseInputHandler.getKeyBitmask(keyCode);
        this.keyReleasedMask |= n3;
        this.keyHeldMask &= ~n3;
        if (this.a != null) {
            this.a.onKeyReleased(keyCode);
        }
    }

    public void c(boolean flag) {
        this.resetInputState();
        this.Y = flag;
    }

    public void resetInputState() {
        this.keyHeldMask = 0;
        this.keyPressedMask = 0;
        this.keyReleasedMask = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        if (this.a != null) {
            this.a.resetInputState();
        }
    }

    public void onPointerEvent(int x, int y) {
        this.pointerX = x;
        this.pointerY = y;
        if (this.a != null) {
            this.a.onPointerEvent(x, y);
        }
    }

    private static int getKeyBitmask(int keyCode) {
        switch (keyCode) {
            case 48: {
                return 1;
            }
            case 49: {
                return 2;
            }
            case 50: {
                return 4;
            }
            case 51: {
                return 8;
            }
            case 52: {
                return 16;
            }
            case 53: {
                return 32;
            }
            case 54: {
                return 64;
            }
            case 55: {
                return 128;
            }
            case 56: {
                return 256;
            }
            case 57: {
                return 512;
            }
            case 42: {
                return 1024;
            }
            case 35: {
                return 2048;
            }
            case -1: /* KEY_UP */ {
                return 4096 /* MASK_UP */;
            }
            case -2: /* KEY_DOWN */ {
                return 8192 /* MASK_DOWN */;
            }
            case -3: /* KEY_LEFT */ {
                return 16384 /* MASK_LEFT */;
            }
            case -4: /* KEY_RIGHT */ {
                return 32768 /* MASK_RIGHT */;
            }
            case -5: /* KEY_FIRE / OK */ {
                return 65536 /* MASK_FIRE */;
            }
            case -21: /* KEY_SOFT_LEFT (Moto) */ 
            case -6: /* KEY_SOFT_LEFT */ {
                return 131072 /* MASK_SOFT_LEFT */;
            }
            case -22: /* KEY_SOFT_RIGHT (Moto) */ 
            case -7: /* KEY_SOFT_RIGHT */ {
                return 262144 /* MASK_SOFT_RIGHT */;
            }
        }
        return 0;
    }

    public boolean isKeyPressed(int keyCodeMask) {
        return (this.f & n2) != 0;
    }

    public boolean isLeftSoftAreaTouched() {
        if (this.pointerX < 40 || this.pointerX > 85 || this.pointerY < 228 || this.pointerY > 248) {
            return false;
        }
        this.pointerX = -1;
        this.pointerY = -1;
        return true;
    }

    public boolean isRightSoftAreaTouched() {
        return (this.g & 0xF154) != 0;
    }

    public boolean isKeyHeld(int keyCodeMask) {
        return (this.e & n2) != 0;
    }

    protected void S() {
        this.e = this.keyHeldMask;
        this.f = this.keyPressedMask;
        this.g = this.keyReleasedMask;
        this.keyPressedMask = 0;
        this.keyReleasedMask = 0;
    }

    protected void a(BaseInputHandler ap2) {
        if (this.a != null) {
            this.a.c(false);
            this.a = null;
        }
        if (ap2 != null) {
            ap2.c(true);
            this.a = ap2;
        }
    }
}

