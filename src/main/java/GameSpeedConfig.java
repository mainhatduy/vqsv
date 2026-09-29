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
 * Hỗ trợ các mức tốc độ x1, x2, x3, x4 qua file cấu hình speed.conf, phím tắt và menu UI in-game.
 */
public final class GameSpeedConfig {

    // =========================================================================
    // 1. CẤU HÌNH THỜI GIAN VÀ TỐC ĐỘ (FRAME RATE & SPEED CONSTANTS)
    // =========================================================================
    public static final int BASE_FRAME_DELAY_MS = 66; // Frame delay gốc của game (66ms ~15 FPS)
    public static final int MIN_SPEED_MULTIPLIER = 1; // Mức tốc độ tối thiểu (1x)
    public static final int MAX_SPEED_MULTIPLIER = 4; // Mức tốc độ thông thường tối đa (4x)
    public static final int ABSOLUTE_MAX_MULTIPLIER = 10;
    public static final int DEFAULT_SPEED_MULTIPLIER = 2; // Tốc độ mặc định (2x ~30 FPS)
    public static final int DEFAULT_FRAME_DELAY_MS = 33;  // Frame delay mặc định (33ms)

    // Giữ hằng số BASE_FRAME_DELAY để tương thích ngược nếu có mã nguồn ngoài gọi tới
    public static final int BASE_FRAME_DELAY = BASE_FRAME_DELAY_MS;

    // =========================================================================
    // 2. BIẾN TRẠNG THÁI HIỆN TẠI (RUNTIME STATE VARIABLES)
    // =========================================================================
    public static int speedMultiplier = DEFAULT_SPEED_MULTIPLIER; // Hệ số nhân tốc độ hiện tại (1, 2, 3, 4)
    public static int currentFrameDelayMs = DEFAULT_FRAME_DELAY_MS; // Độ trễ khung hình hiện tại tính bằng mili-giây
    private static boolean isInitialized = false;

    // Các biến tương thích ngược (aliases)
    public static int currentMultiplier = DEFAULT_SPEED_MULTIPLIER;
    public static int currentDelay = DEFAULT_FRAME_DELAY_MS;

    // =========================================================================
    // 3. ĐỊNH NGHĨA FILE CẤU HÌNH & GIAO DIỆN GAME (CONFIG & UI CONSTANTS)
    // =========================================================================
    private static final String CONFIG_FILE_NAME = "speed.conf";

    // File binary UI help.ui được game engine dùng chung cho Trợ Giúp, Giới Thiệu và Tùy Chọn
    private static final String UI_PATH_HELP_SCREEN = "/data/ui/help.ui";

    // Tiêu đề của màn hình Tùy Chọn
    private static final String UI_TITLE_OPTIONS_MENU = "Tùy chọn";

    // ID của các UIComponent trong help.ui (theo định dạng binary của game):
    private static final int UI_COMPONENT_ID_TITLE = 5;        // Component 5: Tiêu đề giao diện ("Tùy chọn")
    private static final int UI_COMPONENT_ID_CONTENT_AREA = 8; // Component 8: Vùng văn bản lớn hiển thị thông tin tốc độ

    // Mã phím J2ME Keypad
    private static final int J2ME_KEY_STAR = 42;  // Phím '*' (phím 'E' trên bàn phím máy tính)
    private static final int J2ME_KEY_POUND = 35; // Phím '#' (phím 'R' trên bàn phím máy tính)

    /**
     * Khởi tạo hệ thống quản lý tốc độ: nạp cấu hình, kích hoạt phím tắt và bộ theo dõi giao diện.
     */
    public static synchronized void init() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        loadConfig();
        applySpeed();
        registerGlobalHotkeys();
        startOptionsMenuWatcher();
    }

    /**
     * Tính toán frame delay (ms) từ hệ số nhân tốc độ (1x, 2x, 3x, 4x,...).
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
            return Math.max(5, BASE_FRAME_DELAY_MS / multiplier);
        }
    }

    /**
     * Tìm vị trí file cấu hình speed.conf (ưu tiên runtime/, sau đó đến root).
     */
    private static File findConfigFile(boolean forWriting) {
        File[] candidateFiles = new File[] {
            new File(CONFIG_FILE_NAME),
            new File("../" + CONFIG_FILE_NAME),
            new File("runtime/" + CONFIG_FILE_NAME)
        };

        for (File candidate : candidateFiles) {
            if (candidate.exists() && candidate.isFile()) {
                return candidate;
            }
        }

        if (forWriting) {
            return new File(CONFIG_FILE_NAME);
        }
        return candidateFiles[0];
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

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "UTF-8"))) {
            Properties properties = new Properties();
            properties.load(fileReader);

            String delayPropertyStr = properties.getProperty("frame_delay");
            String speedPropertyStr = properties.getProperty("speed");

            if (delayPropertyStr != null && !delayPropertyStr.trim().isEmpty()) {
                try {
                    int parsedDelay = Integer.parseInt(delayPropertyStr.trim());
                    if (parsedDelay >= 5 && parsedDelay <= 200) {
                        currentFrameDelayMs = parsedDelay;
                        speedMultiplier = Math.max(1, Math.round((float) BASE_FRAME_DELAY_MS / parsedDelay));
                        syncLegacyVariables();
                        return;
                    }
                } catch (NumberFormatException ignored) {}
            }

            if (speedPropertyStr != null && !speedPropertyStr.trim().isEmpty()) {
                try {
                    int parsedSpeed = Integer.parseInt(speedPropertyStr.trim());
                    if (parsedSpeed >= MIN_SPEED_MULTIPLIER && parsedSpeed <= ABSOLUTE_MAX_MULTIPLIER) {
                        speedMultiplier = parsedSpeed;
                        currentFrameDelayMs = calculateDelay(parsedSpeed);
                        syncLegacyVariables();
                        return;
                    }
                } catch (NumberFormatException ignored) {}
            }
        } catch (Exception e) {
            System.err.println("[VQSV Speed] Không thể đọc file speed.conf: " + e.getMessage());
        }

        speedMultiplier = DEFAULT_SPEED_MULTIPLIER;
        currentFrameDelayMs = calculateDelay(DEFAULT_SPEED_MULTIPLIER);
        syncLegacyVariables();
    }

    /**
     * Ghi cấu hình hiện tại vào các vị trí file speed.conf mục tiêu.
     */
    public static void saveConfig() {
        File[] targetConfigFiles = new File[] {
            new File(CONFIG_FILE_NAME),
            new File("../" + CONFIG_FILE_NAME),
            new File("runtime/" + CONFIG_FILE_NAME)
        };

        String configContent = "# ==============================================================================\n"
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
                + "speed=" + speedMultiplier + "\n"
                + "frame_delay=" + currentFrameDelayMs + "\n";

        for (File targetFile : targetConfigFiles) {
            try {
                File parentDirectory = targetFile.getParentFile();
                if (parentDirectory != null && !parentDirectory.exists()) {
                    continue;
                }
                try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"))) {
                    fileWriter.write(configContent);
                }
            } catch (Exception ignored) {}
        }
    }

    /**
     * Đồng bộ các biến tương thích ngược (legacy aliases) để đảm bảo không bị đứt gãy.
     */
    private static void syncLegacyVariables() {
        currentMultiplier = speedMultiplier;
        currentDelay = currentFrameDelayMs;
    }

    /**
     * Áp dụng frameDelay vào biến tĩnh c của BaseScreen (lớp gốc an.class).
     */
    public static void applySpeed() {
        syncLegacyVariables();
        applyFrameDelayToBaseScreen(currentFrameDelayMs);

        int approxFps = Math.round(1000f / currentFrameDelayMs);
        System.out.println("--------------------------------------------------");
        System.out.println("[VQSV Speed] Tốc độ game hiện tại: " + speedMultiplier + "x");
        System.out.println("             Frame delay: " + currentFrameDelayMs + " ms (~" + approxFps + " FPS)");
        System.out.println("             (Phím F1-F4 / '+', '-' / '*', '#' để đổi)");
        System.out.println("--------------------------------------------------");

        updateOptionsMenu();
    }

    /**
     * Đặt trực tiếp hệ số nhân tốc độ (1x, 2x, 3x, 4x,...).
     */
    public static void setMultiplier(int multiplier) {
        if (multiplier < MIN_SPEED_MULTIPLIER) multiplier = MIN_SPEED_MULTIPLIER;
        if (multiplier > ABSOLUTE_MAX_MULTIPLIER) multiplier = ABSOLUTE_MAX_MULTIPLIER;

        speedMultiplier = multiplier;
        currentFrameDelayMs = calculateDelay(multiplier);
        applySpeed();
        saveConfig();
    }

    /**
     * Chuyển sang mức tốc độ tiếp theo theo vòng lặp (1 -> 2 -> 3 -> 4 -> 1).
     */
    public static void cycleNext() {
        int nextMultiplier = speedMultiplier + 1;
        if (nextMultiplier > MAX_SPEED_MULTIPLIER) {
            nextMultiplier = MIN_SPEED_MULTIPLIER;
        }
        setMultiplier(nextMultiplier);
    }

    /**
     * Chuyển sang mức tốc độ liền trước theo vòng lặp (4 -> 3 -> 2 -> 1 -> 4).
     */
    public static void cyclePrev() {
        int prevMultiplier = speedMultiplier - 1;
        if (prevMultiplier < MIN_SPEED_MULTIPLIER) {
            prevMultiplier = MAX_SPEED_MULTIPLIER;
        }
        setMultiplier(prevMultiplier);
    }

    /**
     * Xử lý phím keypad J2ME (* hoặc #).
     */
    public static boolean handleJ2meKey(int keyCode) {
        if (keyCode == J2ME_KEY_STAR) {
            cycleNext();
            return true;
        } else if (keyCode == J2ME_KEY_POUND) {
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
                public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                    if (keyEvent.getID() != KeyEvent.KEY_PRESSED) {
                        return false;
                    }

                    int pressedKeyCode = keyEvent.getKeyCode();

                    // Nếu menu Tùy Chọn đang mở, bắt các phím điều hướng và phím số để chỉnh tốc độ
                    if (isOptionsMenuOpen()) {
                        switch (pressedKeyCode) {
                            case KeyEvent.VK_UP:
                            case KeyEvent.VK_W:
                            case KeyEvent.VK_PAGE_UP:
                                cycleNext();
                                return true;
                            case KeyEvent.VK_DOWN:
                            case KeyEvent.VK_S:
                            case KeyEvent.VK_PAGE_DOWN:
                                cyclePrev();
                                return true;
                            case KeyEvent.VK_1:
                            case KeyEvent.VK_NUMPAD1:
                                setMultiplier(1);
                                return true;
                            case KeyEvent.VK_2:
                            case KeyEvent.VK_NUMPAD2:
                                setMultiplier(2);
                                return true;
                            case KeyEvent.VK_3:
                            case KeyEvent.VK_NUMPAD3:
                                setMultiplier(3);
                                return true;
                            case KeyEvent.VK_4:
                            case KeyEvent.VK_NUMPAD4:
                                setMultiplier(4);
                                return true;
                        }
                    }

                    // Phím tắt toàn cục hoạt động mọi lúc trong game
                    switch (pressedKeyCode) {
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

    /**
     * Tạo chuỗi text hiển thị cho mục Tốc độ trong menu Tùy Chọn UI.
     * Game engine hỗ trợ '#n' cho ngắt dòng.
     * Bố cục:
     * - Dòng 1-2: Tốc độ hiện tại và thanh chọn mức.
     * - Dòng 3-5: Dòng trống để tránh đè lên nhãn 'Âm lượng' và 3 cột sóng âm.
     * - Dòng 6-7: Hướng dẫn phím Lên/Xuống và Trái/Phải.
     */
    public static String getMenuDisplayText() {
        StringBuilder menuTextBuilder = new StringBuilder();
        menuTextBuilder.append("Tốc độ: ").append(speedMultiplier).append("x#n");
        menuTextBuilder.append("  ");
        for (int speedOption = 1; speedOption <= MAX_SPEED_MULTIPLIER; speedOption++) {
            if (speedOption == speedMultiplier) {
                menuTextBuilder.append("[").append(speedOption).append("x]");
            } else {
                menuTextBuilder.append(" ").append(speedOption).append("x ");
            }
            if (speedOption < MAX_SPEED_MULTIPLIER) {
                menuTextBuilder.append("  ");
            }
        }
        menuTextBuilder.append("#n#n#n#n");
        menuTextBuilder.append("Lên/Xuống: Đổi tốc độ#n");
        menuTextBuilder.append("Trái/Phải: Âm lượng");
        return menuTextBuilder.toString();
    }

    /**
     * Kiểm tra xem màn hình Tùy Chọn (/data/ui/help.ui) có đang hiển thị hay không.
     */
    public static boolean isOptionsMenuOpen() {
        try {
            ab uiManager = getGameUIManager();
            if (uiManager != null && uiManager.b(UI_PATH_HELP_SCREEN)) {
                ao activeView = getActiveUIView(uiManager);
                String currentTitle = getComponentText(activeView, UI_COMPONENT_ID_TITLE);
                return UI_TITLE_OPTIONS_MENU.equals(currentTitle);
            }
        } catch (Throwable ignored) {}
        return false;
    }

    /**
     * Cập nhật nội dung hiển thị tốc độ trên Component 8 của menu Tùy Chọn.
     */
    public static void updateOptionsMenu() {
        try {
            ab uiManager = getGameUIManager();
            if (uiManager != null && uiManager.b(UI_PATH_HELP_SCREEN)) {
                ao activeView = getActiveUIView(uiManager);
                String currentTitle = getComponentText(activeView, UI_COMPONENT_ID_TITLE);
                if (UI_TITLE_OPTIONS_MENU.equals(currentTitle)) {
                    String expectedText = getMenuDisplayText();
                    setComponentText(activeView, UI_COMPONENT_ID_CONTENT_AREA, expectedText);
                }
            }
        } catch (Throwable ignored) {}
    }

    /**
     * Khởi chạy thread chạy nền để tự động đồng bộ UI khi người chơi vừa mở menu Tùy Chọn.
     */
    private static void startOptionsMenuWatcher() {
        Thread watcherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(30);
                        updateOptionsMenu();
                    } catch (Throwable ignored) {}
                }
            }
        }, "VQSV-OptionsMenu-Watcher");
        watcherThread.setDaemon(true);
        watcherThread.start();
    }

    // =========================================================================
    // 5. CÁC HÀM BỌC (WRAPPERS) TƯƠNG TÁC VỚI ENGINE GỐC ĐÃ BỊ OBFUSCATE
    // =========================================================================

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
     * Lấy hotspot chứa text và thuộc tính vẽ của UIComponent (lớp gốc: k.class, hàm w.h()).
     */
    private static k getUIHotspot(w component) {
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
        k hotspot = getUIHotspot(component);
        return (hotspot != null && hotspot.a != null) ? hotspot.a : "";
    }

    /**
     * Gán chuỗi văn bản cho một thành phần UI nếu nội dung có thay đổi.
     */
    private static boolean setComponentText(ao activeView, int componentId, String newText) {
        w component = getUIComponent(activeView, componentId);
        k hotspot = getUIHotspot(component);
        if (hotspot != null) {
            if (!newText.equals(hotspot.a)) {
                hotspot.a = newText;
                return true;
            }
        }
        return false;
    }

    /**
     * Áp dụng giá trị frame delay vào biến static c của BaseScreen (lớp gốc: an.class).
     * Ghi chú: Dùng reflection vì khi build qua javac, file original/game.jar chứa field c là private.
     */
    private static void applyFrameDelayToBaseScreen(int frameDelayMs) {
        try {
            Field frameDelayField = an.class.getDeclaredField("c");
            frameDelayField.setAccessible(true);
            frameDelayField.setInt(null, frameDelayMs);
        } catch (Throwable t) {
            System.err.println("[VQSV Speed] Lỗi áp dụng frameDelay vào BaseScreen (an.c): " + t.getMessage());
        }
    }
}
