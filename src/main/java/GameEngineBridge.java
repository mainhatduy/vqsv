import game.BaseScreen;
import game.UIComponent;
import game.UILayoutView;
import game.UIManager;
import game.UIStyle;

/** Access to the source-built engine used by desktop speed controls. */
final class GameEngineBridge {
    private GameEngineBridge() {}

    static boolean isTopViewWithTitle(String path, int titleComponentId, String title) {
        UIManager uiManager = getGameUIManager();
        return uiManager != null && uiManager.isTopUI(path)
            && title.equals(getComponentText(getActiveUIView(uiManager), titleComponentId));
    }

    static void setTopViewText(String path, int titleComponentId, String title,
                               int contentComponentId, String text) {
        UIManager uiManager = getGameUIManager();
        if (uiManager != null && uiManager.isTopUI(path)) {
            UILayoutView activeView = getActiveUIView(uiManager);
            if (title.equals(getComponentText(activeView, titleComponentId))) {
                setComponentText(activeView, contentComponentId, text);
            }
        }
    }

    /**
     * Lấy bộ quản lý giao diện singleton UIManager (lớp gốc: ab.class, hàm ab.a()).
     */
    private static UIManager getGameUIManager() {
        try {
            return UIManager.getInstance();
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * Lấy view giao diện đang hoạt động activeView (lớp gốc: ao.class, trường ab.a).
     */
    private static UILayoutView getActiveUIView(UIManager uiManager) {
        if (uiManager == null) return null;
        return uiManager.activeView;
    }

    /**
     * Lấy UIComponent theo ID (lớp gốc: w.class, hàm ao.a(int id)).
     */
    private static UIComponent getUIComponent(UILayoutView activeView, int componentId) {
        if (activeView == null) return null;
        try {
            return activeView.getComponent(componentId);
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * Lấy style chứa text và thuộc tính vẽ của UIComponent (lớp gốc: k.class, hàm w.h()).
     */
    private static UIStyle getComponentStyle(UIComponent component) {
        if (component == null) return null;
        try {
            return component.getStyle();
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * Lấy chuỗi văn bản của một thành phần UI.
     */
    private static String getComponentText(UILayoutView activeView, int componentId) {
        UIComponent component = getUIComponent(activeView, componentId);
        UIStyle componentStyle = getComponentStyle(component);
        return (componentStyle != null && componentStyle.text != null) ? componentStyle.text : "";
    }

    /**
     * Gán chuỗi văn bản cho một thành phần UI nếu nội dung có thay đổi.
     */
    private static boolean setComponentText(UILayoutView activeView, int componentId, String newText) {
        UIComponent component = getUIComponent(activeView, componentId);
        UIStyle componentStyle = getComponentStyle(component);
        if (componentStyle != null) {
            if (!newText.equals(componentStyle.text)) {
                componentStyle.text = newText;
                return true;
            }
        }
        return false;
    }

    /**
     * Áp dụng frame delay trực tiếp vào engine được biên dịch từ source.
     */
    static void applyFrameDelayToBaseScreen(int frameDelayMs) {
        BaseScreen.frameDelayMs = frameDelayMs;
    }
}
