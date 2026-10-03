package game;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;

public class ResourceStream extends DataInputStream {
   private String resourcePath;
   private static Vector openStreams = new Vector();

   private ResourceStream(InputStream var1, String var2) {
      super(var1);
      this.resourcePath = var2;
   }

   public static InputStream openResource(String var0) {
      InputStream var1;
      if ((var1 = ResourceStream.class.getResourceAsStream(var0)) != null) {
         var1 = new ResourceStream(var1, var0);
         openStreams.addElement(var1);
         if (openStreams.size() > 10) {
            System.out.println("current size: " + openStreams.size());

            for (int var6 = 0; var6 < openStreams.size(); var6++) {
               ResourceStream var2 = (ResourceStream)openStreams.elementAt(var6);

               try {
                  if (var2.available() == 0) {
                     System.out.println("auto close1: " + var2.resourcePath);
                     var2.close();
                     var6--;
                  }
               } catch (Exception var4) {
               }
            }

            System.out.println("new size: " + openStreams.size());
            if (openStreams.size() > 10) {
               ResourceStream var7 = (ResourceStream)openStreams.elementAt(0);

               try {
                  System.out.println("auto close2: " + var7.resourcePath);
                  var7.close();
               } catch (Exception var3) {
               }
            }
         }
      }

      return var1;
   }

   public void close() {
      openStreams.removeElement(this);

      try {
         super.close();
      } catch (Exception var1) {
         System.out.println("close error");
      }
   }
}
