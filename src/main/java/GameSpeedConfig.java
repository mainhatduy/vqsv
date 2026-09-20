import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * GameSpeedConfig - Quản lý cấu hình tốc độ game và frame rate.
 * Hỗ trợ các mức tốc độ x1, x2, x3, x4 qua file cấu hình speed.conf và phím tắt nóng.
 */
public final class GameSpeedConfig {
    public static final int BASE_FRAME_DELAY = 66; // Frame delay gốc (15 FPS)

    public static int currentMultiplier = 2; // Mặc định 2x (~30 FPS)
    public static int currentDelay = 33;     // Frame delay tính bằng mili-giây
    private static boolean initialized = false;

    /**
     * Khởi tạo hệ thống quản lý tốc độ: nạp cấu hình và kích hoạt phím tắt.
     */
    public static synchronized void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        loadConfig();
        applySpeed();
        registerGlobalHotkeys();
    }

    /**
     * Tính toán frame delay từ hệ số nhân (1x, 2x, 3x, 4x,...).
     */
    public static int calculateDelay(int multiplier) {
        if (multiplier <= 1) {
            return 66; // 1x: 66ms (~15 FPS)
        } else if (multiplier == 2) {
            return 33; // 2x: 33ms (~30 FPS)
        } else if (multiplier == 3) {
            return 22; // 3x: 22ms (~45 FPS)
        } else if (multiplier == 4) {
            return 16; // 4x: 16ms (~60 FPS)
        } else {
            return Math.max(5, BASE_FRAME_DELAY / multiplier);
        }
    }

    /**
     * Tìm vị trí file cấu hình speed.conf (ưu tiên runtime/, sau đó đến root).
     */
    private static File findConfigFile(boolean forWriting) {
        File[] candidates = new File[] {
            new File("speed.conf"),
            new File("../speed.conf"),
            new File("runtime/speed.conf")
        };

        for (File f : candidates) {
            if (f.exists() && f.isFile()) {
                return f;
            }
        }

        if (forWriting) {
            return new File("speed.conf");
        }
        return candidates[0];
    }

    /**
     * Đọc cấu hình từ file speed.conf.
     */
    public static void loadConfig() {
        File configFile = findConfigFile(false);
        if (!configFile.exists()) {
            saveConfig(); // Tạo file mặc định nếu chưa tồn tại
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "UTF-8"))) {
            Properties props = new Properties();
            props.load(reader);

            String delayStr = props.getProperty("frame_delay");
            String speedStr = props.getProperty("speed");

            if (delayStr != null && !delayStr.trim().isEmpty()) {
                try {
                    int delay = Integer.parseInt(delayStr.trim());
                    if (delay >= 5 && delay <= 200) {
                        currentDelay = delay;
                        currentMultiplier = Math.max(1, Math.round((float) BASE_FRAME_DELAY / delay));
                        return;
                    }
                } catch (NumberFormatException ignored) {}
            }

            if (speedStr != null && !speedStr.trim().isEmpty()) {
                try {
                    int speed = Integer.parseInt(speedStr.trim());
                    if (speed >= 1 && speed <= 10) {
                        currentMultiplier = speed;
                        currentDelay = calculateDelay(speed);
                        return;
                    }
                } catch (NumberFormatException ignored) {}
            }
        } catch (Exception e) {
            System.err.println("[VQSV Speed] Không thể đọc file speed.conf: " + e.getMessage());
        }

        currentMultiplier = 2;
        currentDelay = calculateDelay(2);
    }

    /**
     * Ghi cấu hình hiện tại vào file speed.conf.
     */
    public static void saveConfig() {
        File[] targetFiles = new File[] {
            new File("speed.conf"),
            new File("../speed.conf"),
            new File("runtime/speed.conf")
        };

        String content = "# ==============================================================================\n"
                + "# VƯƠNG QUỐC SỦNG VẬT - CẤU HÌNH TỐC ĐỘ GAME & FRAME RATE\n"
                + "# ==============================================================================\n"
                + "# 1. Các mức tốc độ nhân (Speed Multipliers):\n"
                + "#    speed=1   -> Tốc độ gốc (1x): 66ms delay (~15 FPS)\n"
                + "#    speed=2   -> Tốc độ 2x:       33ms delay (~30 FPS) [Mặc định]\n"
                + "#    speed=3   -> Tốc độ 3x:       22ms delay (~45 FPS)\n"
                + "#    speed=4   -> Tốc độ 4x:       16ms delay (~60 FPS)\n"
                + "#\n"
                + "# 2. Phím tắt trong game (In-Game Hotkeys):\n"
                + "#    - Bàn phím PC:\n"
                + "#        F1: 1x    F2: 2x    F3: 3x    F4: 4x\n"
                + "#        Phím '+' hoặc ']': Tăng tốc độ\n"
                + "#        Phím '-' hoặc '[': Giảm tốc độ\n"
                + "#    - Phím điện thoại (Keypad):\n"
                + "#        Phím '*' (phím E trên PC): Chuyển vòng lặp (1x -> 2x -> 3x -> 4x)\n"
                + "#        Phím '#' (phím R trên PC): Chuyển ngược lại\n"
                + "# ==============================================================================\n\n"
                + "speed=" + currentMultiplier + "\n"
                + "frame_delay=" + currentDelay + "\n";

        for (File f : targetFiles) {
            try {
                File parent = f.getParentFile();
                if (parent != null && !parent.exists()) {
                    continue;
                }
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"))) {
                    writer.write(content);
                }
            } catch (Exception ignored) {}
        }
    }

    /**
     * Áp dụng giá trị currentDelay vào biến static c của BaseScreen (an.class).
     */
    public static void applySpeed() {
        try {
            Field f = an.class.getDeclaredField("c");
            f.setAccessible(true);
            f.setInt(null, currentDelay);
        } catch (Throwable t) {
            System.err.println("[VQSV Speed] Lỗi áp dụng frameDelay: " + t.getMessage());
        }

        int approxFps = Math.round(1000f / currentDelay);
        System.out.println("--------------------------------------------------");
        System.out.println("[VQSV Speed] Tốc độ game hiện tại: " + currentMultiplier + "x");
        System.out.println("             Frame delay: " + currentDelay + " ms (~" + approxFps + " FPS)");
        System.out.println("             (Phím F1-F4 / '+', '-' / '*', '#' để đổi)");
        System.out.println("--------------------------------------------------");
    }

    /**
     * Đặt trực tiếp hệ số nhân tốc độ (1x, 2x, 3x, 4x,...).
     */
    public static void setMultiplier(int multiplier) {
        if (multiplier < 1) multiplier = 1;
        if (multiplier > 10) multiplier = 10;
        currentMultiplier = multiplier;
        currentDelay = calculateDelay(multiplier);
        applySpeed();
        saveConfig();
    }

    /**
     * Chuyển sang mức tốc độ tiếp theo (1 -> 2 -> 3 -> 4 -> 1).
     */
    public static void cycleNext() {
        int next = currentMultiplier + 1;
        if (next > 4) {
            next = 1;
        }
        setMultiplier(next);
    }

    /**
     * Chuyển sang mức tốc độ liền trước (4 -> 3 -> 2 -> 1 -> 4).
     */
    public static void cyclePrev() {
        int prev = currentMultiplier - 1;
        if (prev < 1) {
            prev = 4;
        }
        setMultiplier(prev);
    }

    /**
     * Xử lý phím keypad J2ME (* hoặc #).
     */
    public static boolean handleJ2meKey(int keyCode) {
        if (keyCode == 42) { // Phím '*' (phím 'E' trên PC)
            cycleNext();
            return true;
        } else if (keyCode == 35) { // Phím '#' (phím 'R' trên PC)
            cyclePrev();
            return true;
        }
        return false;
    }

    /**
     * Đăng ký lắng nghe phím nóng toàn cục trên cửa sổ AWT/Swing của FreeJ2ME.
     */
    private static void registerGlobalHotkeys() {
        try {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                    if (e.getID() != KeyEvent.KEY_PRESSED) {
                        return false;
                    }

                    int code = e.getKeyCode();
                    switch (code) {
                        case KeyEvent.VK_F1:
                            setMultiplier(1);
                            return true;
                        case KeyEvent.VK_F2:
                            setMultiplier(2);
                            return true;
                        case KeyEvent.VK_F3:
                            setMultiplier(3);
                            return true;
                        case KeyEvent.VK_F4:
                            setMultiplier(4);
                            return true;
                        case KeyEvent.VK_EQUALS: // Phím '=' hoặc '+'
                        case KeyEvent.VK_ADD:
                        case KeyEvent.VK_CLOSE_BRACKET: // ']'
                        case KeyEvent.VK_PAGE_UP:
                            cycleNext();
                            return true;
                        case KeyEvent.VK_MINUS: // Phím '-'
                        case KeyEvent.VK_SUBTRACT:
                        case KeyEvent.VK_OPEN_BRACKET: // '['
                        case KeyEvent.VK_PAGE_DOWN:
                            cyclePrev();
                            return true;
                        default:
                            return false;
                    }
                }
            });
            System.out.println("[VQSV Speed] Đã kích hoạt phím nóng F1-F4 và '+', '-' để chỉnh tốc độ.");
        } catch (Throwable t) {
            System.out.println("[VQSV Speed] Không thể đăng ký AWT KeyEventDispatcher (chế độ không có giao diện AWT).");
        }
    }
}
