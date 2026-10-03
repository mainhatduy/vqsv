# Đặt tên rõ nghĩa cho code dịch ngược

Toàn bộ source runtime hiện nằm trong `src/main/java/` và đã compile được;
engine/UI dùng `game.*`, billing dùng `game.billing.*`. Sửa tên trong source
phải đồng bộ declaration/caller/override bằng công cụ hiểu symbol và chạy build.
[GameEngineBridge](../src/main/java/GameEngineBridge.java) gọi trực tiếp API UI
và `BaseScreen.frameDelayMs`. `GameMIDLet.instance` là singleton hiện tại;
alias `a` vẫn được giữ cho các helper cũ.

Các mục dưới mô tả **quy trình tái tạo reference CFR lịch sử**. Registry và lệnh
`--apply` chỉ thay `reference/decompiled/`, không đổi source runtime. Mapping
cho lần phục hồi source nằm ở `tools/source-names.tsv`; nó giúp probe đối chiếu
bytecode cũ, không phải bộ đổi tên tự động cho Java đang phát triển.
Xem [SOURCE-BUILD](SOURCE-BUILD.md) về phục hồi source.

## Tên reference đã sửa

| Runtime | Tên reference hiện tại | Tên cũ gây nhầm |
| --- | --- | --- |
| `ao` | `UILayoutView` | `TextRenderer`: còn đọc layout và xử lý focus/input |
| `k` | `UIStyle` | `UIButton`: là style dùng chung của widget |
| `z` | `UISelectionConfig` | `NumericInputWidget`: là cấu hình chọn/điều hướng |
| `c` | `BillingResultListener` | `Renderable`: callback nhận boolean của billing |
| `x` | `SmsResultListener` | `TimerCallback`: callback kết quả gửi SMS |
| `game.b.C:S` | `Pet.spriteId` | `growthRate`: giữ trường 17 trong database |
| `d.a(IZ)Z` | `loadSprite(spriteId, hasExtendedAnimationSteps)` | `loop`: chọn định dạng bước 2/4 số |
| `j.c:I`, `j.d:I` | `mapPixelWidth`, `mapPixelHeight` | `mapTileWidth`, `mapTileHeight`: đã nhân với kích thước tile |
| `ab.b(String)` | `isTopUI` | `isUILoaded`: kiểm tra path trên cùng |
| `ap.R()Z` | `hasNavigationKeyRelease` | Không phải kiểm tra pointer hoặc riêng softkey phải; mask là `0xF154` |

Các nhóm đã đặt tên gồm database, sprite/cache, UI/component, input, entity,
Pet, map, player/collection/tiền, script command/sequence và state controller.
Tham số của các hàm đã đối chiếu được đặt tên qua local-variable debug metadata.
Slot tham số có thể được compiler gốc dùng lại làm biến tạm; CFR vẫn có thể
hiện `n`, `s`, `var...` hoặc suy luận kiểu sai. Tên chưa xác minh được giữ nguyên
để tránh gán sai nghĩa. Không tuyên bố đã khôi phục tên source gốc.

## Mapping và tái tạo

[reference-names.json](../tools/reference-names.json) là nguồn mapping hiện tại.
Mỗi member gồm **owner, tên gốc, descriptor JVM, tên mới**. Ví dụ:

```json
["aq", "a", "[[S", "spriteTable"]
["d", "a", "(IZ)Z", "loadSprite"]
["d", "a", "()V", "releaseSprite"]
```

`d.a` là nhiều hàm khác nhau. Không thay toàn cục `.a(`; phải phân biệt cả kiểu
tham số và kiểu trả về, class khai báo và override. Tool Java dùng ASM có sẵn
trong emulator để đổi symbol trong bytecode, giải quyết member kế thừa và lan
truyền tên của các method override/interface. String literal (asset, RMS,
reflection, nội dung) giữ nguyên.

```bash
# Chạy ở root repo; cần JDK, Python 3, emulator và CFR đã có trong dự án.
python3 tools/rebuild_reference.py
# Đọc thử kết quả trong build/readable-reference/ trước khi thay reference.
python3 tools/rebuild_reference.py --apply
```

Tool kiểm tra hash JAR gốc, member tồn tại, descriptor, xung đột tên và đủ 68 class.
Trước khi xuất Java, tool đổi ngược mapping (kể cả override), rồi so sánh toàn bộ
entry: class được chuẩn hóa bằng ASM, bỏ debug metadata; tài nguyên so byte chính
xác. Bytecode, chữ ký, hằng số và exception table phải khớp. Kết quả mapping có
hiệu lực nằm trong [names.tsv](../reference/decompiled/names.tsv).

JAR trung gian **chỉ phục vụ dịch ngược**: manifest và chuỗi reflection vẫn dùng
tên runtime cũ. Không chạy hoặc phát hành JAR này. `project.py build` chỉ compile source hiện
tại và đóng gói resources; reference và JAR gốc không tham gia build. Lỗi CFR
còn ghi trong [summary.txt](../reference/decompiled/summary.txt), thuộc reference
lịch sử; source runtime đã được phục hồi và sửa riêng.

Các script `refactor_*` và `rename_classes_and_files.py` được giữ làm lịch sử,
đã chặn chạy trực tiếp vì regex cũ làm hỏng overload, khai báo và call site.
Không dùng chúng để bổ sung tên mới. Tài liệu skill trong `.agents/` còn mô tả
build overlay của lần khảo sát trước; dùng `project.py`, SOURCE-BUILD và
SOURCE-MAP hiện tại để xác định pipeline và API đang chạy.

## Bổ sung tên mới

1. Tra `javap -classpath original/game.jar -p -s -c <runtime-class>`; đọc thân
   hàm và nơi gọi để xác minh ý nghĩa, không dựa vào tên tạm của CFR.
2. Thêm entry cụ thể vào JSON. Đặt tên chung cho cùng một hợp đồng override;
   không đặt tên riêng cho subclass nếu làm lệch ý nghĩa của interface.
3. Chạy tool ở chế độ xuất riêng, kiểm tra phần Java và `names.tsv` bị ảnh hưởng.
4. Chạy kiểm tra rồi dùng `--apply`. Sửa bản tham khảo trực tiếp sẽ bị ghi đè ở
   lần tái tạo sau.

```bash
python3 -m unittest discover -s tests -v
python3 project.py build
```

Test đặt tên thực thi fixture trước/sau để kiểm tra overload, interface dispatch,
field kế thừa, private method trùng tên, string/asset không đổi và descriptor sai
bị từ chối. Test runtime chạy bridge trên class game thật trong thư mục tạm:
phân biệt Trợ giúp/Tùy chọn, UI bị che, cập nhật chữ, delay 1–4x và đọc lại cấu hình.
Đây là kiểm tra tích hợp không mở Canvas; chưa xác nhận gameplay, phím AWT,
chiến đấu hoặc save/RMS qua giao diện.
