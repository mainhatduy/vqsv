/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.BillingListener;
import a.NetworkConnection;
import a.NetworkConfig;
import a.ProtocolEncoder;
import a.SecurityHelper;
import a.SessionData;
import java.util.Random;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 * BillingClient - Network client managing billing sessions and protocol requests.
 */
public final class BillingClient
implements BillingListener {
    private static final Object a;
    private int b;
    private static BillingClient[] c;
    private static long d;
    private static Class e;

    static {
        Runtime.getRuntime();
        a = new Integer(1);
    }

    private BillingClient(int n2) {
        this.b = n2;
    }

    public static void a(ProtocolEncoder g2) {
        int n2;
        String[] strArray = new String[23];
        String[] stringArray2 = strArray;
        strArray[0] = "pcall";
        stringArray2[1] = "print";
        stringArray2[2] = "select";
        stringArray2[3] = "type";
        stringArray2[4] = "tostring";
        stringArray2[5] = "tonumber";
        stringArray2[6] = "error";
        stringArray2[7] = "unpack";
        stringArray2[8] = "next";
        stringArray2[9] = "setfenv";
        stringArray2[10] = "getfenv";
        stringArray2[11] = "rawequal";
        stringArray2[12] = "_s";
        stringArray2[13] = "_ss";
        stringArray2[14] = "_t";
        stringArray2[15] = "_a";
        stringArray2[16] = "rp";
        stringArray2[17] = "_cn";
        stringArray2[18] = "_r";
        stringArray2[19] = "_o";
        stringArray2[20] = "_c";
        stringArray2[21] = "_sp";
        stringArray2[22] = "nc";
        if (c == null) {
            c = new BillingClient[23];
            n2 = 0;
            while (n2 < 23) {
                a.BillingClient.c[n2] = new BillingClient(n2);
                ++n2;
            }
        }
        n2 = 0;
        while (n2 < 23) {
            g2.a.a.a((Object)stringArray2[n2], (Object)c[n2]);
            ++n2;
        }
        SecurityHelper h2 = g2.a.a;
        Class<?> clazz = e;
        if (clazz == null) {
            try {
                clazz = e = Class.forName("javax.microedition.io.Connector");
            }
            catch (ClassNotFoundException classNotFoundException) {
                throw new NoClassDefFoundError(classNotFoundException.getMessage());
            }
        }
        h2.a((Object)"_co", (Object)clazz);
    }

    public final int a(NetworkConnection c2, int n2) {
        switch (this.b) {
            case 0: {
                return c2.a.g.b(c2.a, n2 - 1);
            }
            case 1: {
                ProtocolEncoder g2 = c2.a.g;
                ProtocolEncoder g3 = null;
                Object object = ProtocolEncoder.b(g2.a.a, "tostring");
                StringBuffer stringBuffer = new StringBuffer();
                int n3 = 0;
                while (n3 < n2) {
                    Object object2 = c2.a(n3);
                    Object object3 = object;
                    g3 = g2;
                    int n4 = g3.a.f;
                    g3.a.a(n4 + 1 + 3);
                    Object[] objectArray = g3.a.e;
                    g3.a.e[n4] = object3;
                    objectArray[n4 + 1] = object2;
                    objectArray[n4 + 2] = null;
                    objectArray[n4 + 3] = null;
                    int n5 = g3.a(g3.a, 3);
                    object2 = null;
                    if (n5 > 0) {
                        object2 = g3.a.e[n4];
                    }
                    g3.a.a(n4);
                    object3 = object2;
                    stringBuffer.append(object3);
                    if (n3 < n2) {
                        stringBuffer.append("\t");
                    }
                    ++n3;
                }
                g2.b.println(stringBuffer.toString());
                return 0;
            }
            case 2: {
                return 0;
            }
            case 3: {
                Object object;
                a.BillingClient.a(n2 > 0, "Not enough arguments");
                Object object4 = object = c2.a(0);
                c2.a(object4 == null ? "nil" : (object4 instanceof String ? "string" : (object4 instanceof Integer ? "number" : (object4 instanceof Boolean ? "boolean" : (object4 instanceof BillingListener || object4 instanceof NetworkConfig ? "function" : (object4 instanceof SecurityHelper ? "table" : (object4 instanceof SessionData ? "thread" : "userdata")))))));
                return 1;
            }
            case 4: {
                a.BillingClient.a(n2 > 0, "Not enough arguments");
                ProtocolEncoder cfr_ignored_0 = c2.a.g;
                Object object = c2.a(0);
                String string = object == null ? "nil" : (object instanceof String ? (String)object : (object instanceof Boolean ? (object == Boolean.TRUE ? "true" : "false") : (object instanceof BillingListener || object instanceof NetworkConfig ? "function 0x" + System.identityHashCode(object) : (object instanceof SecurityHelper ? "table 0x" + System.identityHashCode(object) : object.toString()))));
                c2.a(string);
                return 1;
            }
            case 5: {
                a.BillingClient.a(n2 > 0, "Not enough arguments");
                Object object = c2.a(0);
                if (n2 == 1) {
                    c2.a(a.BillingClient.b(object));
                    return 1;
                }
                Object object5 = (String)object;
                Integer n6 = a.BillingClient.b(c2.a(1));
                a.BillingClient.a(n6 != null, "Argument 2 must be a number");
                int n7 = n6;
                object5 = a.BillingClient.a((String)object5, n7);
                c2.a(object5);
                return 1;
            }
            case 6: {
                return 0;
            }
            case 7: {
                return a.BillingClient.b(c2, n2);
            }
            case 8: {
                Object object;
                a.BillingClient.a(n2 > 0, "Not enough arguments");
                SecurityHelper h2 = (SecurityHelper)c2.a(0);
                Object object6 = null;
                if (n2 >= 2) {
                    object6 = c2.a(1);
                }
                if ((object = h2.b(object6)) == null) {
                    c2.c(1);
                    c2.a(0, null);
                    return 1;
                }
                Object object7 = h2.a(object);
                c2.c(2);
                c2.a(0, object);
                c2.a(1, object7);
                return 2;
            }
            case 9: {
                NetworkConfig d2;
                a.BillingClient.a(n2 >= 2, "Not enough arguments");
                SecurityHelper h3 = (SecurityHelper)c2.a(1);
                a.BillingClient.a(h3 != null, "expected a table");
                Object object = c2.a(0);
                if (object instanceof NetworkConfig) {
                    d2 = (NetworkConfig)object;
                } else {
                    a.BillingClient.a((object = a.BillingClient.b(object)) != null, "expected a function or a number");
                    int n8 = (Integer)object;
                    if (n8 == 0) {
                        c2.a.a = h3;
                        return 0;
                    }
                    NetworkConnection c3 = c2.a.c(n8);
                    if (!c3.e()) {
                        a.BillingClient.a("No closure found at this level: " + n8);
                    }
                    d2 = c3.b;
                }
                d2.b = h3;
                c2.c(1);
                return 1;
            }
            case 10: {
                SecurityHelper h4;
                Object object = a;
                if (n2 > 0) {
                    object = c2.a(0);
                }
                if (object == null || object instanceof BillingListener) {
                    h4 = c2.a.a;
                } else if (object instanceof NetworkConfig) {
                    h4 = ((NetworkConfig)object).b;
                } else {
                    Integer n9 = a.BillingClient.b(object);
                    a.BillingClient.a(n9 != null, "Expected number");
                    int n10 = n9;
                    a.BillingClient.a(n10 >= 0, "level must be non-negative");
                    object = c2.a.c(n10);
                    h4 = ((NetworkConnection)object).e() ? ((NetworkConnection)object).b.b : ((NetworkConnection)object).a.a;
                }
                SecurityHelper h5 = h4;
                c2.a(h5);
                return 1;
            }
            case 11: {
                a.BillingClient.a(n2 >= 2, "Not enough arguments");
                Object object = c2.a(0);
                Object object8 = c2.a(1);
                c2.a(ProtocolEncoder.c(object, object8) ? Boolean.TRUE : Boolean.FALSE);
                return 1;
            }
            case 12: {
                if (n2 == 3) {
                    try {
                        d = -10L;
                        MessageConnection messageConnection = (MessageConnection)c2.a(0);
                        TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
                        textMessage.setAddress((String)c2.a(1));
                        textMessage.setPayloadText((String)c2.a(2));
                        lavax.wireless.messaging.MessageConnection.send(textMessage);
                        messageConnection.close();
                        c2.a(textMessage);
                        return 1;
                    }
                    catch (Exception exception) {
                        c2.a(null);
                        return 1;
                    }
                }
                return 0;
            }
            case 13: {
                if (n2 == 1) {
                    try {
                        MessageConnection messageConnection = (MessageConnection)Connector.open((String)c2.a(0));
                        c2.a(messageConnection);
                        return 1;
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                        c2.a(null);
                        return 1;
                    }
                }
                return 0;
            }
            case 14: {
                c2.a(new Integer((int)(System.currentTimeMillis() % Integer.MAX_VALUE)));
                return 1;
            }
            case 15: {
                Object object;
                if (n2 == 1 && (object = c2.a(0)) instanceof TextMessage) {
                    c2.a(((TextMessage)object).getAddress());
                    return 1;
                }
                return 0;
            }
            case 16: {
                c2.a(a.BillingClient.a((String)c2.a(0), (String)c2.a(1), (String)c2.a(2)));
                return 1;
            }
            case 17: {
                c2.a(c2.a(0).getClass());
                return 1;
            }
            case 18: {
                n2 = (Integer)c2.a(0);
                int n11 = (Integer)c2.a(1);
                c2.a(new Integer(new Random().nextInt() % (n11 - n2 + 1) + n2));
                return 1;
            }
            case 19: {
                c2.a(new Integer(((String)c2.a(0)).charAt(0)));
                return 1;
            }
            case 20: {
                StringBuffer stringBuffer = new StringBuffer();
                int n12 = 0;
                while (n12 < n2) {
                    stringBuffer.append((char)((Integer)c2.a(n12)).intValue());
                    ++n12;
                }
                c2.a(stringBuffer.toString());
                return 1;
            }
            case 21: {
                String string = System.getProperty((String)c2.a(0));
                if (string != null) {
                    string = string.toLowerCase();
                }
                c2.a(string);
                return 1;
            }
            case 22: {
                c2.a(new Integer((int)(d + 10L)));
                return 1;
            }
        }
        return 0;
    }

    private static int b(NetworkConnection c2, int n2) {
        a.BillingClient.a(n2 > 0, "Not enough arguments");
        SecurityHelper h2 = (SecurityHelper)c2.a(0);
        Object object = null;
        Object object2 = null;
        if (n2 >= 2) {
            object = c2.a(1);
        }
        if (n2 >= 3) {
            object2 = c2.a(2);
        }
        n2 = object != null ? (Integer)object : 1;
        int n3 = object2 != null ? ((Integer)object2).intValue() : h2.a();
        if ((n3 = n3 + 1 - n2) <= 0) {
            c2.c(0);
            return 0;
        }
        c2.c(n3);
        int n4 = 0;
        while (n4 < n3) {
            c2.a(n4, h2.a(ProtocolEncoder.a(n2 + n4)));
            ++n4;
        }
        return n3;
    }

    public static void a(boolean flag, String text) {
        if (!flag) {
            a.BillingClient.a(text);
        }
    }

    public static void a(String text) {
        throw new RuntimeException(text);
    }

    private static Integer a(String text, int n2) {
        if (n2 < 2 || n2 > 36) {
            throw new RuntimeException("base out of range");
        }
        try {
            if (n2 == 10) {
                return Integer.valueOf(text);
            }
            return ProtocolEncoder.a(Integer.parseInt(text, n2));
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    public static String a(Object object) {
        if (object instanceof String) {
            return (String)object;
        }
        if (object instanceof Integer) {
            return ((Integer)object).toString();
        }
        return object.toString();
    }

    public static Integer b(Object object) {
        if (object instanceof Integer) {
            return (Integer)object;
        }
        if (object instanceof String) {
            return a.BillingClient.a((String)object, 10);
        }
        return null;
    }

    private static String a(String text, String string2, String string3) {
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = text.indexOf(string2);
        int n3 = 0;
        int n4 = string2.length();
        while (n2 != -1) {
            stringBuffer.append(text.substring(n3, n2)).append(string3);
            n3 = n2 + n4;
            n2 = text.indexOf(string2, n3);
        }
        stringBuffer.append(text.substring(n3, text.length()));
        return stringBuffer.toString();
    }
}

