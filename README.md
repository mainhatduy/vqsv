# Vương Quốc Sủng Vật — project để đọc và sửa game

Project được tạo từ bản JAR Liệt Hỏa 240×320 đang chạy bằng FreeJ2ME-Plus.
Đây là mã dịch ngược từ bytecode, không phải source gốc của nhà phát triển.

Muốn thêm Pokémon, UI, vật phẩm, kỹ năng, NPC, nhiệm vụ, bản đồ hoặc tính năng
hệ thống? Đọc [Cẩm nang mở rộng game](docs/EXTENDING-GAME.md), có ví dụ Điện Miêu,
cấu trúc frame/database, cách nối code và checklist kiểm tra. Dùng
[Bản đồ source](docs/SOURCE-MAP.md) để đối chiếu tên dễ đọc với class gốc trong JAR.

## Chạy nhanh

Mở riêng thư mục `vuong-quoc-sung-vat` bằng **File → Open Folder** trong IDE.
Trong Terminal tại thư mục này:

```bash
python3 project.py build
python3 project.py run
```

`run` tự build trước khi mở game. Bạn cũng có thể nhấp đúp `Run Game.command`
trong Finder. Đóng cửa sổ game cũ trước khi chạy lại.

Project dùng JDK và Python 3 đã có trên Mac, cùng `../emulator/freej2me_plus.jar`.
Trong VS Code, có sẵn task `VQSV: Build` và `VQSV: Run`.

## Đọc và sửa ở đâu?

| Đường dẫn | Mục đích |
| --- | --- |
| `src/main/java/game/GameMIDLet.java` | Điểm khởi động đã biên dịch và chạy thử được; bắt đầu sửa ở đây |
| `src/main/java/GameSpeedConfig.java` | Cấu hình tốc độ, hotkey desktop và nội dung menu Tùy chọn đang được build |
| `src/main/java/` | Mã Java được biên dịch khi build; có thể thêm class mới |
| `src/main/resources/` | Tài nguyên cùng các class patch `an.class`, `game/h.class`, `game/k.class`; giữ đường dẫn trong JAR |
| `reference/decompiled/` | 68 file Java dịch ngược để đọc, chưa được sửa hết lỗi |
| `reference/decompiled/summary.txt` | Cảnh báo của công cụ dịch ngược |
| `original/game.jar` | Bản game làm nền để build; không sửa trực tiếp |
| `original/MANIFEST.MF` | Bản trích manifest để tham khảo |
| `build/vuong-quoc-sung-vat-dev.jar` | File game sau khi build |
| `runtime/` | Cấu hình, log và dữ liệu lưu của bản phát triển |
| `docs/SOURCE-MAP.md` | File cần đọc, tên class runtime, chữ ký API và giới hạn dịch ngược |
| `docs/EXTENDING-GAME.md` | Quy trình mở rộng nội dung, UI và hệ thống; ví dụ, định dạng và kiểm tra |

## Thử thay đổi đầu tiên

Mở `src/main/java/game/GameMIDLet.java`, tìm `startApp()` và đổi dòng:

```java
System.out.println("[VQSV Dev] Running the editable GameMIDLet.");
```

thành thông báo của bạn. Chạy `python3 project.py run`; thông báo mới xuất hiện
trong Terminal chứng minh mã bạn sửa đã được đưa vào game. Đây là thông báo log,
không phải chữ hiển thị trên màn hình game.

Bạn có thể thêm class trong `src/main/java/` rồi nối lời gọi từ điểm tích hợp phù
hợp. Helper cần gọi các kiểu ở default package có thể đặt ở default package;
`GameMIDLet` hiện gọi `GameSpeedConfig` bằng reflection. Class mới không tự được
khởi tạo hay nhận sự kiện chỉ vì đã nằm trong JAR.

Nếu muốn sửa class gốc, trước hết đối chiếu tên binary và chữ ký bằng `javap`.
Ví dụ file tham khảo `Pet.java` tương ứng với `game.b`, không phải `game.Pet`.
Chép nguyên file đã đổi tên sang source chưa thay thế được class runtime: còn
phải khôi phục Java hợp lệ, giữ liên kết binary và giải quyết các tham chiếu
package. Xem [kiến trúc và build](docs/EXTENDING-GAME.md#architecture).

## Build hoạt động thế nào?

Build biên dịch **chỉ** Java trong `src/main/java/`, lấy JAR gốc và API của
FreeJ2ME làm classpath. Sau đó đóng gói JAR mới với class vừa biên dịch và tài
nguyên trong `src/main/resources/`. Khi trùng đường dẫn, thứ tự ưu tiên là
**class vừa biên dịch → file trong resources → entry trong JAR gốc**.
Không đóng gói emulator vào game. Build không chạy lại script tạo bytecode patch;
classpath biên dịch vẫn là JAR gốc, không phải các class patch trong resources.

Sửa file dưới `reference/decompiled/` không tác động đến bản chạy. Xóa một tài
nguyên khỏi `src/main/resources/` cũng không xóa bản tương ứng trong JAR gốc;
quy trình hiện tại chỉ thêm/thay thế tài nguyên.

Đây là build dành cho **FreeJ2ME trên Mac**: dùng `javac --release 8`.
JDK 26 có thể hiện cảnh báo Java 8 đã cũ; build vẫn thành công.
Chưa có bước preverify hoặc toolchain CLDC để cài bản sửa lên Nokia thật.

## Giới hạn và dữ liệu lưu

- Tên biến/class đã bị rút gọn và chú thích gốc không còn. Không thể khôi phục
  chính xác toàn bộ source ban đầu chỉ từ JAR.
- CFR báo các hàm chưa tái dựng hoàn chỉnh trong 9 class. Không coi toàn bộ
  `reference/decompiled/` là source có thể build ngay. Việc đổi tên trong bản tham
  khảo được tạo lại bằng mapping owner/descriptor; các tên chưa xác minh còn giữ
  tên gốc. Kiểm tra bytecode khi khôi phục source runtime.
- Project dùng save riêng trong `runtime/`, không nhập tiến trình Nokia hoặc save
  ở thư mục cha. Lần chạy đầu sao chép cấu hình phím hiện tại, gồm hàng số 0–9.
- Kiểm tra hiện tại xác nhận build thành công và entry point Java đã sửa được
  thực thi khi giả lập khởi động; chưa kiểm tra toàn bộ gameplay.

## Xem tài nguyên và định dạng

`tools/recover_assets.py` xuất nhiều ảnh/bảng/sprite/map/event thành PNG và JSON
để đọc. Đây là công cụ giải mã, chưa có bước pack/import ngược và chưa đọc layout
UI. File `.mid` có thể là PNG, bảng nhị phân, event hoặc âm thanh tùy loader.
Xem [cách xuất asset](docs/EXTENDING-GAME.md#inspect-assets) và
[định dạng, công cụ còn thiếu](docs/EXTENDING-GAME.md#formats) trước khi sửa.

## Nguồn và công cụ

- Bản game: https://java.waptai.com/game/download-game-pokemon-vuong-quoc-sung-vat-mien-phi/
- File đã dùng: https://up.waptai.com/download/tro-choi/vqsv-240x320.jar
- SHA-256 được lưu trong `original/SHA256.txt`; build kiểm tra JAR gốc trước khi chạy.
- Công cụ dịch ngược CFR 0.152: https://www.benf.org/other/cfr/
- CFR source và giấy phép MIT: https://github.com/leibnitz27/cfr
- FreeJ2ME-Plus: https://github.com/TASEmulators/freej2me-plus

Để tạo lại bản dịch ngược mà không đè lên bản tham khảo:

```bash
python3 project.py decompile
```

Kết quả nằm ở `build/decompiled/`. Lệnh sẽ dừng nếu thư mục đó đã tồn tại.

Để tái tạo reference với tên dễ đọc, xem [quy trình refactor](docs/REFACTORING.md):

```bash
python3 tools/rebuild_reference.py          # xuất riêng vào build/readable-reference/
python3 tools/rebuild_reference.py --apply  # tái tạo reference/decompiled/
```

Hai lệnh kiểm tra đổi tên ngược trước khi xuất, không sửa JAR gốc hoặc bản game chạy.
