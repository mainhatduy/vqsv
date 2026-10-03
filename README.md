# Vương Quốc Sủng Vật — build hoàn toàn từ Java

Project phục hồi từ JAR Liệt Hỏa 240×320 để chạy bằng FreeJ2ME-Plus.
Toàn bộ 68 class của game đã được dựng lại thành Java hợp lệ; cùng 3 helper
phát triển, **71 file trong `src/main/java/` được biên dịch khi build**.
Đây là source phục hồi từ bytecode, không phải source gốc của nhà phát triển.

## Build và chạy

```bash
python3 project.py build
python3 project.py run
```

`run` tự build rồi mở emulator. Cũng có thể nhấp đúp `Run Game.command` hoặc dùng
các task `VQSV: Build` / `VQSV: Run` trong VS Code. Đóng phiên game cũ trước khi chạy lại.
Cần Python 3, JDK hỗ trợ `javac --release 8` và `../emulator/freej2me_plus.jar`.
JAR đầu ra là `build/vuong-quoc-sung-vat-dev.jar`.

Build chỉ lấy class vừa compile và tài nguyên trong `src/main/resources/`.
Classpath chỉ có API emulator; **không đọc hoặc đóng gói class từ `original/game.jar`**.
Build từ chối mọi file `.class` trong resources để tránh đưa binary patch trở lại.
Xóa asset khỏi resources sẽ xóa asset đó khỏi JAR đầu ra.

## Sửa code ở đâu?

| Đường dẫn | Nội dung |
| --- | --- |
| `src/main/java/game/GameMIDLet.java`, `GameCanvas.java` | Khởi động, vòng lặp, input |
| `src/main/java/game/Pet.java`, `BattleScreen.java` | Thú, chỉ số, chiến đấu |
| `src/main/java/game/WorldManager.java`, `OverworldScreen.java`, `ScriptEngine.java` | Map, save/load, event, menu và tương tác |
| `src/main/java/game/AnimationCache.java`, `SpriteRenderer.java` | Sprite và animation |
| `src/main/java/game/UIManager.java`, `UILayoutView.java` | UI và layout nhị phân |
| `src/main/java/GameSpeedConfig.java`, `GameEngineBridge.java` | Tốc độ, hotkey desktop và cầu nối UI |
| `src/main/java/game/billing/`, `lavax/` | Billing và API messaging đã phục hồi |
| `src/main/resources/` | Toàn bộ asset và manifest của game |
| `reference/decompiled/` | Bản CFR lịch sử để đối chiếu; không được compile |
| `original/`, `reference/runtime-patches/` | Bytecode nền để đối chiếu; không tham gia build |
| `runtime/` | Cấu hình và save riêng của bản phát triển |

Ví dụ: sửa `src/main/java/game/Pet.java` rồi build sẽ thay logic thú trong game.
Engine và UI hiện dùng package `game`, billing dùng `game.billing`. Không cần
thay `game.b` bằng binary patch nữa. Một class mới vẫn cần được caller gọi hoặc
đăng ký vào state/input/render để tính năng xuất hiện.

Đọc [quy trình phục hồi và kiểm chứng](docs/SOURCE-BUILD.md),
[bản đồ source](docs/SOURCE-MAP.md) và [cẩm nang mở rộng game](docs/EXTENDING-GAME.md).
Cẩm nang còn giữ chữ ký gốc ở một số phần khảo sát định dạng; dùng
`tools/source-names.tsv` để đối chiếu với API Java hiện tại.

## Kiểm tra

```bash
python3 -m unittest discover -s tests -v
python3 tools/verify_source.py
```

Unit test kiểm tra build không cần JAR gốc, toàn bộ class/asset được đóng gói,
UI và tốc độ. Bài đối chiếu riêng dùng JAR gốc đã patch làm oracle: chỉ số thú,
sát thương, sprite/UI/map, RMS, luồng 6.000 tick và save/load. Nó còn khởi động
MIDlet thật và kiểm tra tốc độ 1–4. Báo cáo và ảnh nằm ở `build/source-verification/`.
Phân biệt những trường hợp chạy thành công với exception vốn đã có ở bản nền;
đây chưa phải kiểm thử mọi nhiệm vụ hay mọi nhánh gameplay.

Source còn nhiều tên local tạm và member chưa xác minh. Layout/asset `.mid` vẫn
là nhị phân, không phải JSON có thể sửa trực tiếp. `tools/recover_assets.py` xuất
PNG/JSON để đọc; chưa có packer nhập lại mọi định dạng.

Build dành cho FreeJ2ME trên desktop, dùng Java 8 và các helper AWT. Chưa có bước
preverify/toolchain CLDC cho máy Nokia thật. Save giữ tên RMS và thứ tự dữ liệu
của bản nền; project dùng thư mục `runtime/` riêng.

## Nguồn và công cụ tham khảo

- [Bản game](https://java.waptai.com/game/download-game-pokemon-vuong-quoc-sung-vat-mien-phi/), [JAR đã dùng](https://up.waptai.com/download/tro-choi/vqsv-240x320.jar); hash trong `original/SHA256.txt`.
- [Vineflower](https://github.com/Vineflower/vineflower) 1.12.0 dùng cho lần phục hồi source.
- [CFR](https://www.benf.org/other/cfr/) 0.152 dùng cho reference lịch sử.
- [FreeJ2ME-Plus](https://github.com/TASEmulators/freej2me-plus).

`python3 project.py decompile` xuất CFR vào thư mục mới `build/decompiled/`.
`tools/rebuild_reference.py` tái tạo reference có tên dễ đọc; xem
[quy trình đặt tên](docs/REFACTORING.md). Các lệnh này không thay source đang build.
