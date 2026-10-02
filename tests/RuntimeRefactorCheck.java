import java.lang.reflect.Field;
import java.util.Vector;

/** Exercise the new bridge against actual game classes, without starting a Canvas or touching RMS. */
public final class RuntimeRefactorCheck {
    private static void set(Object target, String name, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }

    public static void main(String[] args) throws Exception {
        org.recompile.mobile.PlatformFont.setScreenSize(240, 320);
        ab manager = ab.a();
        ao view = new ao(null);
        al root = new al();
        af title = new af();
        af content = new af();
        set(title, "d", 5);
        set(content, "d", 8);
        set(root, "n", new w[]{title, content});
        set(view, "c", root);
        manager.a = view;
        Field pathsField = ab.class.getDeclaredField("f");
        pathsField.setAccessible(true);
        Vector paths = (Vector)pathsField.get(manager);
        paths.addElement("/data/ui/help.ui");
        title.h().a = "Trợ giúp";
        content.h().a = "Original help";
        if (GameSpeedConfig.isOptionsMenuOpen()) throw new AssertionError("Wrong title");
        GameSpeedConfig.updateOptionsMenu();
        if (!"Original help".equals(content.h().a)) throw new AssertionError("Help overwritten");
        title.h().a = "Tùy chọn";
        if (!GameSpeedConfig.isOptionsMenuOpen()) throw new AssertionError("Options undetected");
        for (int speed = 1; speed <= 4; speed++) {
            GameSpeedConfig.setMultiplier(speed);
            Field delay = an.class.getDeclaredField("c");
            delay.setAccessible(true);
            if (delay.getInt(null) != GameSpeedConfig.calculateDelay(speed))
                throw new AssertionError("Frame delay not applied");
            if (!GameSpeedConfig.getMenuDisplayText().equals(content.h().a))
                throw new AssertionError("Options text not applied");
            GameSpeedConfig.loadConfig();
            if (GameSpeedConfig.speedMultiplier != speed) throw new AssertionError("Config round trip");
        }
        paths.addElement("/data/ui/other.ui");
        if (GameSpeedConfig.isOptionsMenuOpen()) throw new AssertionError("Covered options detected");
        String previousText = content.h().a;
        GameSpeedConfig.speedMultiplier = 1;
        GameSpeedConfig.updateOptionsMenu();
        if (!previousText.equals(content.h().a)) throw new AssertionError("Covered view changed");
        if (game.GameMIDLet.getInstance() != game.GameMIDLet.a) throw new AssertionError("MIDlet ABI alias");
        System.out.println("Runtime bridge, title/stack guards, frame delay and configuration: OK");
    }
}
