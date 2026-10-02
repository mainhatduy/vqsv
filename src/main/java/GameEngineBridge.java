import java.lang.reflect.Field;

/** Compatibility boundary for the original obfuscated JAR. */
final class GameEngineBridge {
    private GameEngineBridge() {}

    static boolean isTopViewWithTitle(String path, int titleComponentId, String title) {
        ab uiManager = getGameUIManager();
        return uiManager != null && uiManager.b(path)
            && title.equals(getComponentText(getActiveUIView(uiManager), titleComponentId));
    }

    static void setTopViewText(String path, int titleComponentId, String title,
                               int contentComponentId, String text) {
        ab uiManager = getGameUIManager();
        if (uiManager != null && uiManager.b(path)) {
            ao activeView = getActiveUIView(uiManager);
            if (title.equals(getComponentText(activeView, titleComponentId))) {
                setComponentText(activeView, contentComponentId, text);
            }
        }
    }

    /**
     * Lấy bộ quản lý giao diện singleton UIManager (lớp gốc: ab.class, hàm ab.a()).
     */
    private static ab getGameUIManager() {
        try {
            return ab.a();
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * Lấy view giao diện đang hoạt động activeView (lớp gốc: ao.class, trường ab.a).
     */
    private static ao getActiveUIView(ab uiManager) {
        if (uiManager == null) return null;
        return uiManager.a;
    }

    /**
     * Lấy UIComponent theo ID (lớp gốc: w.class, hàm ao.a(int id)).
     */
    private static w getUIComponent(ao activeView, int componentId) {
        if (activeView == null) return null;
        try {
            return activeView.a(componentId);
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * Lấy style chứa text và thuộc tính vẽ của UIComponent (lớp gốc: k.class, hàm w.h()).
     */
    private static k getComponentStyle(w component) {
        if (component == null) return null;
        try {
            return component.h();
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * Lấy chuỗi văn bản của một thành phần UI.
     */
    private static String getComponentText(ao activeView, int componentId) {
        w component = getUIComponent(activeView, componentId);
        k componentStyle = getComponentStyle(component);
        return (componentStyle != null && componentStyle.a != null) ? componentStyle.a : "";
    }

    /**
     * Gán chuỗi văn bản cho một thành phần UI nếu nội dung có thay đổi.
     */
    private static boolean setComponentText(ao activeView, int componentId, String newText) {
        w component = getUIComponent(activeView, componentId);
        k componentStyle = getComponentStyle(component);
        if (componentStyle != null) {
            if (!newText.equals(componentStyle.a)) {
                componentStyle.a = newText;
                return true;
            }
        }
        return false;
    }

    /**
     * Áp dụng giá trị frame delay vào biến static c của BaseScreen (lớp gốc: an.class).
     * Ghi chú: Dùng reflection vì khi build qua javac, file original/game.jar chứa field c là private.
     */
    static void applyFrameDelayToBaseScreen(int frameDelayMs) {
        try {
            Field frameDelayField = an.class.getDeclaredField("c");
            frameDelayField.setAccessible(true);
            frameDelayField.setInt(null, frameDelayMs);
        } catch (Throwable frameDelayError) {
            System.err.println("[VQSV Speed] Lỗi áp dụng frameDelay vào BaseScreen (an.c): " + frameDelayError.getMessage());
        }
    }
}
