# Vương Quốc Sủng Vật — project để đọc và sửa game

Project được tạo từ bản JAR Liệt Hỏa 240×320 đang chạy bằng FreeJ2ME-Plus.
Đây là mã dịch ngược từ bytecode, không phải source gốc của nhà phát triển.

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
| `src/main/java/` | Mã Java được biên dịch khi build; có thể thêm class mới |
| `src/main/resources/` | 744 file tài nguyên đã tách, giữ đường dẫn như trong JAR |
| `reference/decompiled/` | 68 file Java dịch ngược để đọc, chưa được sửa hết lỗi |
| `reference/decompiled/summary.txt` | Cảnh báo của công cụ dịch ngược |
| `original/game.jar` | Bản game làm nền để build; không sửa trực tiếp |
| `original/MANIFEST.MF` | Bản trích manifest để tham khảo |
| `build/vuong-quoc-sung-vat-dev.jar` | File game sau khi build |
| `runtime/` | Cấu hình, log và dữ liệu lưu của bản phát triển |
| `docs/SOURCE-MAP.md` | Gợi ý các file cần đọc và giới hạn của việc dịch ngược |

## Thử thay đổi đầu tiên

Mở `src/main/java/game/GameMIDLet.java`, tìm `startApp()` và đổi dòng:

```java
System.out.println("[VQSV Dev] Running the editable GameMIDLet.");
```

thành thông báo của bạn. Chạy `python3 project.py run`; thông báo mới xuất hiện
trong Terminal chứng minh mã bạn sửa đã được đưa vào game. Đây là thông báo log,
không phải chữ hiển thị trên màn hình game.

Bạn có thể thêm class trong `src/main/java/game/` rồi gọi từ `GameMIDLet`.
Nếu muốn sửa một class có sẵn, chép file tương ứng từ `reference/decompiled/`
sang đúng package dưới `src/main/java/`, sửa lỗi Java và build lại. Giữ tên class,
chữ ký các hàm và các trường đang được các class gốc sử dụng.

## Build hoạt động thế nào?

Build biên dịch **chỉ** Java trong `src/main/java/`, lấy JAR gốc và API của
FreeJ2ME làm classpath. Sau đó đóng gói JAR mới với class vừa biên dịch và tài
nguyên trong `src/main/resources/`. Các class chưa sửa được giữ từ JAR gốc.
Không đóng gói emulator vào game.

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
  `reference/decompiled/` là source có thể build ngay.
- Project dùng save riêng trong `runtime/`, không nhập tiến trình Nokia hoặc save
  ở thư mục cha. Lần chạy đầu sao chép cấu hình phím hiện tại, gồm hàng số 0–9.
- Kiểm tra hiện tại xác nhận build thành công và entry point Java đã sửa được
  thực thi khi giả lập khởi động; chưa kiểm tra toàn bộ gameplay.

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
