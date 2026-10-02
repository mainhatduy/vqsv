# Mapping và kiểm chứng reference Java

## JSON đầu vào

Class dùng JVM internal name (`package/ClassName`, default package không có `/`).
Descriptor dùng tên runtime gốc, kể cả khi class đó cũng được đổi tên. Mảng member
luôn có 4 phần tử; `parameters` dùng danh sách tên đối số cuối cùng.

```json
{
  "classes": {
    "sample/a": "sample/CounterBase",
    "sample/b": "sample/CounterChild",
    "sample/i": "sample/CounterListener"
  },
  "fields": [
    ["sample/a", "a", "I", "count"]
  ],
  "methods": [
    ["sample/i", "a", "(I)I", "addCount"],
    ["sample/a", "a", "(Ljava/lang/String;)I", "textLength"],
    ["sample/a", "b", "(I)I", "privateCalculation"]
  ],
  "parameters": [
    ["sample/a", "a", "(I)I", ["amount"]]
  ],
  "notes": [
    "count được xác minh bằng getter, constructor và các phép cộng trong caller."
  ]
}
```

Mapping class/member có thể chỉ bao phủ một phần JAR; tên chưa có mapping giữ
nguyên. Metadata bổ sung như `notes` không tham gia remap. Owner phải là class
**khai báo** member; không khai báo lại field của superclass bằng tên subclass.
Tất cả class ứng dụng tham gia đổi tên/kế thừa phải nằm trong input JAR. Source
classpath giúp CFR giải quyết kiểu; nó không bổ sung hierarchy cho remapper.

`I` = int, `B` = byte, `S` = short, `Z` = boolean, `J` = long, `D` = double;
`Lsample/a;` là reference, `[I` là mảng int. Method descriptor `(I)I` có một
đối số int và trả int. Không đổi `<init>`/`<clinit>`; chỉ đặt tên tham số constructor
nếu đã xác minh. Tên mới dùng identifier Java ASCII; tránh keyword.

Có thể cung cấp cả tên parent và override nếu chúng thống nhất. Khi chỉ mapping
một thành viên của hợp đồng virtual/interface, tool lan truyền tên qua những
implementation/override trong JAR. Private/static và package-private ở package
khác không bị coi là override. Effective mapping đã lan truyền nằm trong output
`names.tsv`; dùng nó để truy ngược tên gốc và đánh giá diff.

## CLI và output

```bash
python3 scripts/refactor_reference.py --help
```

`--input`, `--mapping`, `--output`, `--asm-classpath`, `--cfr` là bắt buộc.
`--source-classpath` là tùy chọn cho CFR; `--expected-sha256` buộc hash input phải
khớp giá trị đã biết. Nếu nhiều JAR, classpath dùng separator của hệ điều hành
(`:` trên macOS/Linux, `;` trên Windows); quote toàn bộ classpath.

Script xử lý ở thư mục tạm, không đè thư mục output đã có. Các file được bàn giao:

- `decompiled/`: Java readable, header phân biệt reference và runtime; CFR giữ
  cảnh báo/hàm chưa khôi phục hoàn chỉnh trong `summary.txt` khi có.
- `names.tsv`: tên có hiệu lực sau khi đồng bộ override.
- `mapping.json`: mapping đã dùng.
- `verification.json`: hash input, số class/member, kết quả round-trip và trạng
  thái runtime chưa kiểm chứng.
- `readable-reference.jar`: artifact trung gian cho decompiler, không để deploy.

Không copy cây reference vào source đang compile để thay runtime. Java package
không import kiểu default package theo cách thông thường; binary gốc có thể có
liên kết mà source tái dựng chưa diễn đạt hợp lệ. Nếu cần thay class runtime,
khôi phục Java hợp lệ, giữ ABI hoặc migrate mọi caller, rồi build/test riêng.

## Giới hạn backend đi kèm

`ReadableReference.java` dùng visitor ASM legacy có sẵn ở nhiều toolchain cũ.
Chỉ dùng classpath thực sự có API `ClassAdapter`, `MethodAdapter`, `ClassReader`,
`ClassWriter`, `Type`, `Opcodes`; một `asm.jar` hiện đại thường không có adapter
cũ này. Không gắn tên file hoặc đường dẫn emulator của một dự án vào skill.

Preflight nhận class version 45–52 với constant-pool tags cơ bản và các attribute
`Code`, `ConstantValue`, `Exceptions`, `SourceFile`, `SourceDebugExtension`,
`LineNumberTable`, `LocalVariableTable`, `StackMap`, `StackMapTable`, `Synthetic`,
`Deprecated`. Attribute/constant mới hoặc chưa xử lý bị từ chối, gồm generic
`Signature`, annotations, `InnerClasses`/`EnclosingMethod`, `MethodParameters`,
method handles/types, invokedynamic, dynamic constants, static/default interface
methods, records và modules.
Số version phù hợp chưa đủ: nhiều class Java 8 cũng dùng feature chưa hỗ trợ.

Backend bảo toàn string literal; không đổi chuỗi `Class.forName`, tên resource,
manifest MIDlet/Main-Class, service file, native linkage hoặc serialized type
identity. Đây là lựa chọn cho reference, không phải bộ shrink/obfuscate/deploy.
Generic signatures và metadata cần remapper đầy đủ nếu triển khai backend mới.
Không bỏ qua kiểm tra chỉ vì tên class trông đơn giản.

## Ý nghĩa của round-trip

Mapping được đảo bằng tên thực tế sau khi lan truyền override. Class gốc và class
đổi ngược được đọc/ghi lại bằng ASM, bỏ debug metadata rồi so byte. Phải giữ
chữ ký/access, constants, instructions, exception table và metadata được hỗ trợ.
Non-class entries so byte chính xác; số entry và đường dẫn phải khớp. Hash input
được kiểm tra lại trước khi xuất để tránh xử lý input thay đổi giữa chừng.

Điều này **không** phát hiện một tên đọc hiểu sai (`speed` nhưng thực tế là ID),
reflection string chưa đổi trong JAR readable, hoặc mọi lỗi resolver có thể tự
đảo lại. Cần test thực thi trên fixture độc lập và đọc call sites. Debug parameter
names có scope hữu hạn; không phải thuật toán deobfuscate mọi local variable.

Chạy bài test bundled bằng dependency có sẵn:

```bash
python3 scripts/test_refactor.py \
  --asm-classpath /tools/asm-3-compatible.jar \
  --cfr /tools/cfr.jar
```

Test tạo JAR fixture ở thư mục tạm, dùng CLI thật, chạy trước/sau để kiểm tra
behavior overload, inherited field, override/interface và private homonym;
đồng thời xác nhận literal/resource giữ nguyên, generic metadata bị từ chối và
input không đổi. Không thực thi networking, billing hay ứng dụng thật.
