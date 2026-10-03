import java.lang.reflect.Field;
import java.util.Vector;
import game.*;

/** Exercise the new bridge against actual game classes, without starting a Canvas or touching RMS. */
public final class RuntimeRefactorCheck {
    private static void set(Object target, String name, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }

    public static void main(String[] args) throws Exception {
        org.recompile.mobile.PlatformFont.setScreenSize(240, 320);
        UIManager manager = UIManager.getInstance();
        UILayoutView view = new UILayoutView(null);
        MenuWidget root = new MenuWidget();
        ItemListWidget title = new ItemListWidget();
        ItemListWidget content = new ItemListWidget();
        set(title, "componentId", 5);
        set(content, "componentId", 8);
        set(root, "children", new UIComponent[]{title, content});
        set(view, "root", root);
        manager.activeView = view;
        Field pathsField = UIManager.class.getDeclaredField("openPaths");
        pathsField.setAccessible(true);
        Vector paths = (Vector)pathsField.get(manager);
        paths.addElement("/data/ui/help.ui");
        title.getStyle().text = "Trợ giúp";
        content.getStyle().text = "Original help";
        if (GameSpeedConfig.isOptionsMenuOpen()) throw new AssertionError("Wrong title");
        GameSpeedConfig.updateOptionsMenu();
        if (!"Original help".equals(content.getStyle().text)) throw new AssertionError("Help overwritten");
        title.getStyle().text = "Tùy chọn";
        if (!GameSpeedConfig.isOptionsMenuOpen()) throw new AssertionError("Options undetected");
        for (int speed = 1; speed <= 4; speed++) {
            GameSpeedConfig.setMultiplier(speed);
            if (BaseScreen.frameDelayMs != GameSpeedConfig.calculateDelay(speed))
                throw new AssertionError("Frame delay not applied");
            if (!GameSpeedConfig.getMenuDisplayText().equals(content.getStyle().text))
                throw new AssertionError("Options text not applied");
            GameSpeedConfig.loadConfig();
            if (GameSpeedConfig.speedMultiplier != speed) throw new AssertionError("Config round trip");
        }
        paths.addElement("/data/ui/other.ui");
        if (GameSpeedConfig.isOptionsMenuOpen()) throw new AssertionError("Covered options detected");
        String previousText = content.getStyle().text;
        GameSpeedConfig.speedMultiplier = 1;
        GameSpeedConfig.updateOptionsMenu();
        if (!previousText.equals(content.getStyle().text)) throw new AssertionError("Covered view changed");
        if (game.GameMIDLet.getInstance() != game.GameMIDLet.a) throw new AssertionError("MIDlet ABI alias");
        System.out.println("Runtime bridge, title/stack guards, frame delay and configuration: OK");
    }
}
