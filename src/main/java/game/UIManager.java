package game;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class UIManager {
   private static UIManager instance;
   private Hashtable viewsByPath = new Hashtable();
   private Vector viewStack = new Vector();
   private Vector openPaths = new Vector();
   public UILayoutView activeView;
   public TextPainter textPainter = new TextPainter();

   private UIManager() {
   }

   public static UIManager getInstance() {
      if (instance == null) {
         instance = new UIManager();
      }

      return instance;
   }

   public final void releaseAll() {
      if (instance != null) {
         UIManager var1 = instance;
         Enumeration var2 = instance.viewsByPath.elements();

         while (var2.hasMoreElements()) {
            ((UILayoutView)var2.nextElement()).release();
         }

         var1.viewsByPath.clear();
         var1.viewStack.removeAllElements();
      }

      this.activeView = null;
   }

   public final void render(Graphics var1) {
      if (this.viewStack != null) {
         for (int var2 = 0; var2 < this.viewStack.size(); var2++) {
            ((UILayoutView)this.viewStack.elementAt(var2)).render(var1);
         }
      }

      var1.setClip(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
   }

   public final void update() {
      if (this.activeView != null) {
         this.activeView.update();
      }
   }

   public final void openUI(String var1, int var2, ScriptEventListener var3) {
      UILayoutView var4;
      if ((var4 = (UILayoutView)this.viewsByPath.get(var1)) != null) {
         if (!var1.equals("/data/ui/dialog.ui")) {
            this.viewsByPath.remove(var1);
         }

         this.viewStack.removeElement(var4);
         if (!var1.equals("/data/ui/dialog.ui")) {
            var4.release();
            var4 = null;
         }

         if (var1.equals("/data/ui/dialog.ui")) {
            this.viewStack.addElement(var4);
            this.activeView = var4;
            this.openPaths.addElement(var1);
         }
      }

      if (var4 == null) {
         (var4 = new UILayoutView(var3)).setTextPainter(this.textPainter);
         var4.loadLayout(var1, var2);
         this.viewsByPath.put(var1, var4);
         this.viewStack.addElement(var4);
         this.activeView = var4;
         this.openPaths.addElement(var1);
      }
   }

   public final void closeUI(String var1) {
      UILayoutView var2;
      if ((var2 = (UILayoutView)this.viewsByPath.get(var1)) != null) {
         if (this.activeView.equals(var2)) {
            this.activeView = null;
         }

         if (!var1.equals("/data/ui/dialog.ui")) {
            this.viewsByPath.remove(var1);
         }

         this.viewStack.removeElement(var2);
         this.openPaths.removeElement(var1);
         if (!var1.equals("/data/ui/dialog.ui")) {
            var2.release();
         }
      }

      if (this.viewsByPath.size() > 0 && this.viewStack.size() > 0) {
         this.activeView = (UILayoutView)this.viewStack.lastElement();
      }
   }

   public final boolean isTopUI(String var1) {
      return this.openPaths.size() > 0 && this.openPaths.lastElement().equals(var1);
   }

   public final boolean isUIOpen(String var1) {
      return this.openPaths.size() > 0 && this.openPaths.contains(var1);
   }

   public final UILayoutView getView(String var1) {
      return (UILayoutView)this.viewsByPath.get(var1);
   }

   public static boolean isDialogAnimationStep(UILayoutView var0, int var1) {
      return ((ItemListWidget)var0.getComponent(1)).getStyle().m.a().isAnimationStep(var1);
   }

   public final boolean isDialogAnimationFinished() {
      return ((ItemListWidget)this.activeView.getComponent(1)).getStyle().m.a().isLastAnimationStep()
         && ((ItemListWidget)this.activeView.getComponent(1)).getStyle().m.a().isFrameTimerExpired();
   }
}
