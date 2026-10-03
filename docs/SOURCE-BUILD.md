# Phục hồi và build toàn bộ source Java

## Trạng thái hiện tại

68 class trong JAR gốc đã có source tương ứng, cùng 3 helper phát triển: tổng
71 file Java được compile. Các class engine/UI từ default package được đưa vào
`game`; các class thanh toán `a.*` chuyển thành `game.billing.*`; API messaging
`lavax.*` giữ package. `game.GameMIDLet` vẫn là entrypoint của manifest.

`project.py build` xóa thư mục class cũ, compile tất cả Java với classpath chỉ có
emulator, rồi đóng gói class mới và resources. Không có bước merge JAR gốc,
remap runtime, decompile hoặc tải tool trong build. Mọi `.class` trong resources
đều bị từ chối. Có thể build khi `original/game.jar` không tồn tại.

## Nguồn phục hồi

Oracle là `original/game.jar` cùng 4 patch đã có trước lần chuyển đổi:
`an.class`, `game/h.class`, `game/i.class`, `game/k.class`. Các patch được giữ
trong `reference/runtime-patches/` chỉ để kiểm chứng. Hành vi tốc độ, shortcut,
thanh toán và các thay đổi trong những patch này đã được đưa vào Java.

`tools/recover_source.py` tạo staging mới, không ghi đè source đang duy trì:

```bash
python3 tools/recover_source.py \
  --vineflower build/toolchain/vineflower-1.12.0.jar \
  --output build/source-recovery-review
```

Cần cung cấp Vineflower 1.12.0 riêng; công cụ không tự tải hoặc đóng gói nó.
Remapper dùng ASM có trong emulator và registry `tools/reference-names.json`.
Owner/name/descriptor phân biệt overload, kể cả các chữ ký JVM chỉ khác kiểu trả
về mà Java không cho phép. Các xung đột còn lại dùng tên trung tính có descriptor.
Mapping hiệu lực của source được giữ tại `tools/source-names.tsv`.

Trước khi dịch ngược, tool đổi tên ngược và đối chiếu bytecode chuẩn hóa cùng
asset; lần phục hồi đã khớp 813 entry. Đối chiếu này chỉ xác nhận remap, không
chứng minh Java dịch ngược đúng. Vineflower bật verify merges/pre-post merges,
không simplify stack, không suy luận boolean từ int và xuất bytecode mapping.
Không thêm tên tham số vào bytecode vì slot gốc thường bị dùng lại cho biến tạm.

Java xuất ra vẫn cần sửa thủ công dựa trên bytecode: kiểu biến tạm, scope,
nhánh control flow, checked exception, quyền truy cập callback Canvas, vòng lặp
bị suy luận thành byte/short và hash-table của billing. Source hiện tại đã bao
gồm các sửa này; **chạy lại recovery không tái tạo toàn bộ bản đã sửa**.
Không chép staging đè lên `src/main/java/` khi đang phát triển game.

Tên class/member mới là tên phục hồi, không phải tên source gốc. Một số member
và nhiều local còn tên ngắn vì chưa đủ bằng chứng về ý nghĩa. Các nhánh lỗi đã có
ở bytecode gốc được giữ; không dùng stub hoặc giá trị mặc định để che lỗi dịch ngược.

## Kiểm chứng

```bash
python3 -m unittest discover -s tests -v
python3 tools/verify_source.py
```

Unit test đổi đường dẫn JAR gốc sang file không tồn tại rồi build, kiểm tra đủ
68 class phục hồi, manifest, mọi asset và không có class binary cũ. Test UI/tốc
độ kiểm tra menu, frame delay 1–4 và cấu hình. Test remapper kiểm tra overload,
kế thừa và dispatch bằng fixture riêng.

`verify_source.py` build source, tạo oracle riêng và chạy các JVM độc lập với
thư mục save riêng. Probe dùng owner/name/descriptor để gọi đúng API ở cả hai bản:

- Các tổ hợp 100 loài, cấp, phẩm chất, biến thể và pet save round-trip.
- Cặp hệ/loài, 70 chiêu và RNG cố định; so sát thương, trạng thái và số lần rút RNG.
- Frame sprite/transform, cây widget/layout và render, map/layer/camera/collision.
- RMS đọc/ghi, hash table và numeric equality của billing.
- 6.000 tick từ game mới qua mở đầu, nhiều phòng, trận đấu và quay lại world;
  so trạng thái và pixel render tại các mốc, sau đó so dữ liệu save và reload đội thú.
- Khởi động MIDlet thật qua emulator, Canvas thread và nối cấu hình tốc độ 1–4.

Báo cáo `build/source-verification/report.json` ghi riêng số trường hợp thành
công và exception giống bản nền. Hash pixel khớp chỉ cho những frame đã lấy mẫu.
Fixture lỗi giống bản nền không được tính là gameplay thành công. Bài chạy tự
động tăng tốc thời gian hội thoại và dùng chuỗi input cố định; chưa kiểm chứng
mọi quest, mọi tình huống battle, SMS mạng thật hoặc save Nokia nhập từ bên ngoài.

Lần kiểm chứng ngày 2026-10-03: 3 unit test qua; 31.483 trường hợp thành công
và 195 trường hợp exception khớp oracle; 98 mẫu flow/save/reload khớp, gồm 94
mẫu trạng thái/render. MIDlet thật khởi động không có uncaught exception và
frame delay ở tốc độ 1–4 lần lượt là 66/33/22/16 ms. Cả 745 asset giữ nguyên bytes.

## Sửa và chạy tiếp

Sửa trực tiếp `src/main/java/`, rồi chạy `python3 project.py build` hoặc `run`.
Không sửa `reference/decompiled/` để đổi game. Khi thêm asset, giữ đường dẫn mà
loader yêu cầu. Khi đổi cấu trúc save, sửa cả writer/reader và xác định migration;
việc đổi package Java hiện tại giữ nguyên tên RMS, asset và thứ tự dữ liệu.

Build dùng `javac --release 8` cho FreeJ2ME desktop. JDK mới có thể cảnh báo Java
8 đã cũ và source còn raw collections. Emulator là dependency API/runtime bên
ngoài, không được nhúng vào JAR game. Để chạy trên J2ME thật vẫn cần giải quyết
helper AWT, API/toolchain CLDC và preverify.

## Sửa lỗi hội thoại khi thiếu dữ liệu chia trang

Overworld có thể đi vào nhánh xác nhận của lệnh 4/84 trong trạng thái chờ (5)
khi `EngineUtils.a` chưa có dữ liệu chia trang. Bản source hiện kiểm tra cả trạng
thái lệnh lẫn dữ liệu phân trang; nếu thiếu, nó dựng lại hội thoại từ script và
chờ lượt xác nhận tiếp theo. Không bỏ nội dung hay tự kết thúc lệnh.

`DialogueRecoveryCheck` tái hiện NPE trước sửa, rồi kiểm tra cả hội thoại thường,
hội thoại có tham số, từng trang và thời điểm chuyển sang lệnh kế tiếp.
Kiểm tra này chạy trong unit test với RMS riêng. Nó bổ sung cho bài đối chiếu
baseline; không khẳng định đã tái hiện toàn bộ thao tác dẫn tới lỗi trên máy người dùng.
