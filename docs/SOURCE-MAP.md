# Đọc mã game từ đâu?

Các mô tả dưới đây dựa trên mã dịch ngược, không phải tên module gốc.

1. **`src/main/java/game/GameMIDLet.java`**: entry point J2ME. Constructor lấy
   `Display`, tạo Canvas qua `game.e.a(this)` rồi hiển thị. Đây là class đang
   được build từ Java, còn các class khác được giữ từ JAR gốc.
2. **`reference/decompiled/game/e.java`**: kế thừa `Canvas`, triển khai `Runnable`.
   Có vòng lặp cập nhật/vẽ, `keyPressed`, `keyReleased` và sự kiện con trỏ.
3. **`reference/decompiled/game/i.java`**: được Canvas tạo qua `game.i.a()`;
   Canvas chuyển cập nhật, vẽ và đầu vào sang đối tượng này. Điểm bắt đầu để
   lần theo điều phối trạng thái game.
4. **`src/main/resources/data/ui/`**: tên file gợi ý màn hình như `bag.ui`,
   `world.ui`, `petstate.ui`, `skill.ui`. Đây có thể là định dạng nhị phân riêng,
   không nên coi là XML hay văn bản chỉ dựa vào tên.
5. **`src/main/resources/data/`**: tài nguyên ảnh, chữ, script và dữ liệu game.
   Nhiều file mang đuôi `.mid` nhưng chưa xác định định dạng; không mặc định mọi
   file `.mid` là nhạc MIDI.

## Vì sao không đưa cả 68 file vào phần build?

Mã đã bị obfuscate. Một số class có tên rất ngắn, có tham chiếu từ package
`game` tới class ở default package mà Java source thông thường không cho phép,
hoặc các hàm CFR chưa khôi phục được cấu trúc điều khiển/kiểu dữ liệu.
Chỉ đổi tên file hoặc thêm `import` không giải quyết toàn bộ vấn đề này.

CFR ghi cảnh báo cho: `ao`, `b`, `q`, `a.h`, `game.b`, `game.c`, `game.d`,
`game.j`, `game.k`. Chi tiết nằm trong `reference/decompiled/summary.txt`.
Các class không có cảnh báo cũng chưa được xác nhận có thể biên dịch lại.

Nên sửa từng class hoặc thêm class mới, build và kiểm tra từng thay đổi. Nếu
muốn tái dựng toàn bộ source có tên rõ ràng, cần thêm giai đoạn khôi phục kiểu,
package và tên gọi nhất quán trong bytecode/source.
