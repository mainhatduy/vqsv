---
name: java-deobfuscation-refactor
description: Recover meaningful class, field, method and parameter names in obfuscated or decompiled Java, synchronizing overloads and inheritance while preserving runtime ABI. Use for recovered JARs and readable reference trees; includes a configurable, verified legacy-bytecode remapper.
---

# Java Deobfuscation Refactor

Đổi tên dựa trên hành vi đã xác minh, giữ lời gọi và kế thừa nhất quán. Không coi
một tên ngắn hoặc một tên do lần refactor trước đặt là bằng chứng về ý nghĩa.

## Chọn cách refactor theo nguồn đang có

- **Source Java hợp lệ và đang build:** dùng rename theo symbol của IDE/compiler
  hoặc AST có type resolution. Sửa cả declaration/caller/override; chạy build và
  test hành vi bị ảnh hưởng. Không cần qua JAR hoặc dịch ngược.
- **Reference dịch ngược không build được:** dùng bytecode gốc làm nguồn chân lý.
  Lập mapping theo owner/name/descriptor rồi tái tạo reference. Script đi kèm
  hỗ trợ JAR legacy với các giới hạn ở dưới.
- **Source mới chồng lên binary cũ:** đổi tên private/internal khi đã kiểm tra
  caller. Public API mà binary ngoài vẫn gọi phải giữ tên/chữ ký hoặc có bridge
  tương thích. Tách tên readable khỏi tên runtime trong tài liệu và ví dụ.

Xác định thư mục thực sự được compile, dependency JAR, override/resource, thứ tự
đóng gói và entrypoint trước khi sửa. Giữ thay đổi đang có của người dùng.
Reference được đổi tên không tự thay class gốc đang chạy.

## Xây mapping có bằng chứng

Đọc declaration, thân hàm và nơi sử dụng; đối chiếu bytecode khi source có dấu
hiệu đổi tên sai, thiếu biến hoặc trùng chữ ký:

```bash
javap -classpath original.jar -p -s -c package.ObfuscatedClass
```

Dùng **owner + tên gốc + descriptor JVM** để xác định member. Descriptor bao gồm
kiểu trả về; `a()V`, `a(I)I` và field `a:I` là các symbol khác nhau. Giải quyết
class khai báo thật của field kế thừa; tên của một hợp đồng override/interface
phải đồng bộ, còn private/static method trùng tên không mặc nhiên là override.

Đặt tên theo ý nghĩa quan sát được, không theo vị trí chữ cái. Kiểm tra cả đơn vị,
miền giá trị và caller: kích thước đã nhân tile là pixel; boolean có thể chọn định
dạng dữ liệu thay vì bật animation loop. Giữ tên chưa xác minh hoặc đánh dấu
trung tính; không bịa ý nghĩa để đạt mục tiêu đổi tên toàn bộ. Ghi suy luận chưa
chắc ở notes của mapping hoặc tài liệu đi kèm.

Tham số/local cần phân tích scope và data flow. Bytecode obfuscate có thể tái sử
dụng một slot cho nhiều loại giá trị; debug name của tham số không đủ để gán nghĩa
cho mọi lần sử dụng sau đó. Không thay `.a(`, identifier hay file name bằng regex
trên toàn cây source; regex chỉ thích hợp cho tìm kiếm hoặc format không có symbol.

## Dùng script tái tạo reference

Đọc [mapping và giới hạn backend](references/mapping-and-verification.md) trước
khi tạo JSON hoặc chọn dependency. Script không phụ thuộc vào layout của một
repo, không tải thư viện và không sửa input/source/runtime.

```bash
python3 /path/to/java-deobfuscation-refactor/scripts/refactor_reference.py \
  --input /project/original.jar \
  --mapping /project/readable-names.json \
  --output /project/build/readable-review \
  --asm-classpath /tools/asm-3-compatible.jar \
  --cfr /tools/cfr.jar \
  --source-classpath /tools/application-api.jar
```

Cần Python 3, JDK có `javac --release 8`, CFR executable JAR và classpath ASM có
`ClassAdapter`/`MethodAdapter` (API 3.x). Backend chỉ hỗ trợ class version 45–52
với tập metadata hạn chế; script dừng khi gặp generic signatures, annotations,
inner/outer-class metadata, static/default interface method, method handles/
invokedynamic hoặc cấu trúc mới hơn.
Với input như vậy, dùng AST/type resolution hoặc backend ASM hiện đại có remapper
đầy đủ; không xóa metadata để vượt qua kiểm tra.

Output phải là thư mục mới. Script validate symbol, propagate override, remap,
đổi ngược rồi so canonical class bytes bỏ debug metadata và so resource byte
chính xác. Sau đó mới xuất source, effective mapping và báo cáo. Đọc `names.tsv`,
`decompiled/summary.txt`, diff các hệ thống bị ảnh hưởng trước khi đưa kết quả vào
reference của repo. JAR trung gian chỉ dùng để đọc/dịch ngược: chuỗi reflection,
manifest, service registrations và tên resource vẫn giữ nguyên.

## Kiểm chứng và bàn giao

Round-trip xác nhận phép biến đổi có thể đảo và nội dung được giữ; không chứng
minh nghĩa tên mới đúng, Java dịch ngược compile được hay app đổi tên chạy được.
Kiểm tra fixture cho overload, virtual/interface dispatch, field kế thừa và
private method trùng tên; kiểm tra string/asset không đổi. Với runtime source
được sửa, build theo pipeline thật và test hành vi tại ranh giới tích hợp.

Bàn giao phạm vi đã đổi tên, vị trí mapping, cách tái tạo, check đã chạy và tên
còn chưa xác minh/bị buộc giữ vì ABI. Phân biệt rõ kiểm chứng reference, build,
test tích hợp và thử app qua UI; chỉ ghi nhận bước thực sự đã chạy.
