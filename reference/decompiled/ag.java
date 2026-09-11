/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public final class ag
implements Runnable {
    private x a = null;
    private String b;
    private Thread c = null;

    public ag() {
        Class.forName("javax.wireless.messaging.Message");
        this.a = null;
        if (this.c == null) {
            this.c = new Thread(this);
            this.c.start();
        }
    }

    public ag(x x2) {
        Class.forName("javax.wireless.messaging.Message");
        this.a = x2;
        if (this.c == null) {
            this.c = new Thread(this);
            this.c.start();
        }
    }

    public final synchronized void a(String string) {
        this.b = string;
    }

    public final void run() {
        while (true) {
            ag ag2 = this;
            synchronized (ag2) {
                try {
                    this.wait();
                }
                catch (Exception exception) {}
                int n2 = ((String)null).length();
                Object object = n2 >= 3 && ((String)null).substring(0, 3) == "+86" ? ((String)null).substring(3) : (n2 > 0 && ((String)null).substring(0, 1) == "+" ? ((String)null).substring(1) : null);
                MessageConnection messageConnection = null;
                try {
                    try {
                        object = this.b + (String)object;
                        try {
                            messageConnection = (MessageConnection)Connector.open((String)object);
                            TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
                            textMessage.setAddress((String)object);
                            textMessage.setPayloadText(null);
                            messageConnection.send(textMessage);
                            if (messageConnection != null) {
                                messageConnection.close();
                            }
                            if (this.a != null) {
                                this.a.b(true);
                            }
                        }
                        catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        finally {
                            if (messageConnection != null) {
                                messageConnection.close();
                            }
                            if (this.a != null) {
                                this.a.b(false);
                            }
                        }
                    }
                    catch (IOException iOException) {
                        System.out.println(iOException.getMessage());
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                catch (Throwable throwable) {
                    object = throwable;
                    throw throwable;
                }
            }
        }
    }
}

