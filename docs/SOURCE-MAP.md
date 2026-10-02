# Bản đồ source và tên class runtime

Đọc [Cẩm nang mở rộng game](EXTENDING-GAME.md) để có quy trình thêm Pokémon,
UI, tính năng hệ thống, vật phẩm, kỹ năng, NPC, nhiệm vụ và bản đồ.
Trang này giúp tìm code và đối chiếu tên; các tên thân thiện là tên đặt lại sau
dịch ngược, không phải API được nhà phát triển gốc công bố.

## 1. Mã nào được đóng gói?

| Nguồn | Vai trò |
| --- | --- |
| [GameMIDLet.java](../src/main/java/game/GameMIDLet.java) | Entry point đang biên dịch; gọi `game.e.a(this)`, đặt Canvas, gọi `GameSpeedConfig.init()` qua reflection |
| [GameSpeedConfig.java](../src/main/java/GameSpeedConfig.java) | Source đang biên dịch; cấu hình tốc độ, AWT hotkey, cập nhật vùng chữ menu |
| [src/main/resources](../src/main/resources/) | Tài nguyên và bytecode override `an.class`, `game/h.class`, `game/k.class` |
| [original/game.jar](../original/game.jar) | Class/tài nguyên nền; tên binary thực tế phần lớn còn bị rút gọn |
| [reference/decompiled](../reference/decompiled/) | 68 file tham khảo đã đổi tên; compiler của project không đọc thư mục này |

[project.py](../project.py) chỉ compile Java trong `src/main/java`, dùng JAR gốc
và API emulator làm classpath. Khi trùng entry, class vừa compile thắng resources,
resources thắng JAR gốc. Build không chạy lại
[script patch](../tools/patch_sms_and_shortcuts.py). Sửa reference không sửa bản chạy.

## 2. Tìm theo hệ thống

Tên runtime không có dấu chấm nằm ở default package. Đường dẫn bên trái là file
hiện tồn tại; ví dụ `game.b` bên phải là class trong JAR, không phải tên file
Java cần tìm trong reference.

| File reference | Runtime | Đọc để hiểu |
| --- | --- | --- |
| [GameCanvas](../reference/decompiled/game/GameCanvas.java) | `game.e` | Vòng lặp update/repaint/sleep, bàn phím/con trỏ |
| [GameStateController](../reference/decompiled/game/GameStateController.java) | `game.i` | State cấp game, chuyển màn hình, âm thanh |
| [TitleScreen](../reference/decompiled/game/TitleScreen.java) | `game.f` | Menu đầu game, bắt đầu/tải game |
| [WorldManager](../reference/decompiled/game/WorldManager.java) | `game.k` | Scene/room, actor, encounter, điều phối và save/load |
| [OverworldScreen](../reference/decompiled/game/OverworldScreen.java) | `game.c` | Gameplay trên map, điều kiện và interpreter event/quest |
| [BattleScreen](../reference/decompiled/game/BattleScreen.java) | `game.d` | Trận đấu, lượt, bắt thú và kết quả |
| [ScriptEngine](../reference/decompiled/game/ScriptEngine.java) | `game.h` | Menu, hội thoại, tương tác, shop; không phải nơi duy nhất thực thi event |
| [Player](../reference/decompiled/game/Player.java) | `game.g` | Đội thú, túi, tiền, huy chương, thu thập, cưỡi thú |
| [Pet](../reference/decompiled/game/Pet.java) | `game.b` | Init/chỉ số, học chiêu, tiến hóa, dữ liệu instance, tính sát thương |
| [NpcEntity](../reference/decompiled/game/NpcEntity.java) | `game.a` | Actor/NPC trên map |
| [TileMapRenderer](../reference/decompiled/game/TileMapRenderer.java) | `game.j` | Render tile/layer |
| [MapEngine](../reference/decompiled/MapEngine.java) | `j` | Nạp map/tileset, dữ liệu ô và camera |
| [GameDatabase](../reference/decompiled/GameDatabase.java) | `aq` | Database, bảng sprite, text và ảnh nền |
| [EngineUtils](../reference/decompiled/EngineUtils.java) | `ae` | Đọc bảng nhị phân, ảnh, chuỗi, RNG, hàm hỗ trợ |
| [ResourceStream](../reference/decompiled/ResourceStream.java) | `aj` | Mở resource và theo dõi stream |
| [ImageCache](../reference/decompiled/ImageCache.java) | `am` | Cache ảnh theo image ID |
| [AnimationCache](../reference/decompiled/AnimationCache.java) | `aa` | Nạp metadata và tạo frame thú 86–185 |
| [SpriteData](../reference/decompiled/SpriteData.java) | `o` | Các mảng module/frame/animation/vùng |
| [SpriteRenderer](../reference/decompiled/SpriteRenderer.java) | `d` | Nạp sprite, tiến animation, transform và vẽ |
| [BaseEntity](../reference/decompiled/BaseEntity.java) | `n` | Vị trí, chỉ số và trạng thái entity |
| [WorldEntity](../reference/decompiled/WorldEntity.java) | `f` | Hướng/di chuyển và sprite của entity |
| [BaseInputHandler](../reference/decompiled/BaseInputHandler.java) | `ap` | Keycode và mask input |
| [BaseScreen](../reference/decompiled/BaseScreen.java) | `an` | Cơ sở màn hình, delay, kích thước và trạng thái dùng chung |
| [UIManager](../reference/decompiled/UIManager.java) | `ab` | Mở/đóng/cache/stack UI |
| [TextRenderer](../reference/decompiled/TextRenderer.java) | `ao` | Parser layout UI, component lookup, điều hướng và vẽ |
| [UIComponent](../reference/decompiled/UIComponent.java) | `w` | Interface của widget |
| [MenuWidget](../reference/decompiled/MenuWidget.java) | `al` | Container, type 0 của layout |
| [ItemListWidget](../reference/decompiled/ItemListWidget.java) | `af` | Widget type 1, chữ/icon |
| [MessageBox](../reference/decompiled/MessageBox.java) | `ac` | Widget type 2, lưới/hộp |
| [UIButton](../reference/decompiled/UIButton.java) | `k` | Thuộc tính chữ/nền/icon của widget |
| [NumericInputWidget](../reference/decompiled/NumericInputWidget.java) | `z` | Cấu hình chọn/điều hướng |
| [SpriteWidget](../reference/decompiled/SpriteWidget.java) | `m` | Sprite trong UI |
| [ScriptCommand](../reference/decompiled/ScriptCommand.java) | `ad` | Opcode, tham số số và chuỗi |
| [ScriptSequence](../reference/decompiled/ScriptSequence.java) | `p` | Chuỗi lệnh, program counter, trạng thái thực thi |
| [ScriptEventListener](../reference/decompiled/ScriptEventListener.java) | `i` | Callback `a(int[])` |
| [SkillEffect](../reference/decompiled/SkillEffect.java) | `ah` | Hiệu ứng chiêu thức |
| [ParticleEffect](../reference/decompiled/ParticleEffect.java) | `ai` | Hiệu ứng hạt |
| [SaveStorage](../reference/decompiled/SaveStorage.java) | `ar` | RecordStore/RMS |

Bảng đổi tên đầy đủ của 68 class nằm trong `CLASS_RENAME_MAP` ở
[rename_classes_and_files.py](../tools/rename_classes_and_files.py).
Đọc mapping không cần chạy script; script có chức năng sửa file reference.

## 3. Các chữ ký cần tra bằng tên gốc

| Ý định | Chữ ký/trường runtime |
| --- | --- |
| Tạo thú | `new game.b()`; `a(int,int,short,byte,short,byte)` |
| ID/cấp/dữ liệu lưu của thú | `game.b.q()`, `s()`, `P()` |
| Đọc định nghĩa loài | `aq.c[0][petId]` |
| Ánh xạ sprite | `aq.a[spriteId]` |
| Nạp renderer | `d.a(int,boolean)` |
| Singleton UI | `ab.a()` |
| Mở/đóng UI | `ab.a(String,int,i)` / `ab.a(String)` |
| UI trên cùng/có trong stack | `ab.b(String)` / `ab.c(String)` |
| Active view / component | `ab.a` / `ao.a(int)` |
| Chữ của widget phù hợp | `w.h().a` qua đối tượng runtime `k` |
| Đọc/chuyển state cấp game | `game.i.e()` / `game.i.a(byte)` |
| Frame delay | `an.c`; quyền truy cập của bản patch khác JAR gốc |

```bash
javap -classpath original/game.jar -p game.b aq ab ao
javap -classpath original/game.jar -p -c aa
```

`GameDatabase.spriteTable`, `Pet.initPet`, `UIManager.openUI` là tên trong
reference; không viết chúng như API runtime nếu chưa thay toàn bộ liên kết tương
ứng. Một số phép đổi tên còn sai nghĩa: `Pet.growthRate` giữ sprite ID; tham số
`loop` trong renderer chọn định dạng bước animation. Chữ ký cùng tên bị overload
phải đối chiếu cả kiểu tham số và kiểu trả về.

## 4. Tài nguyên và công cụ

| Đường dẫn dưới `src/main/resources/data` | Loader/điểm tham khảo |
| --- | --- |
| `script/db.mid`, `script/chs.mid`, `script/sprite.mid` | GameDatabase, EngineUtils |
| `img/`, `tex/`, `spr/` | ImageCache, EngineUtils, AnimationCache, SpriteRenderer |
| `ui/*.ui` | TextRenderer + UIManager; binary, không phải XML |
| `event/scene_*.mid` | WorldManager, ScriptCommand, ScriptSequence, OverworldScreen |
| `map/`, `mod/` | MapEngine, TileMapRenderer |
| `script/petArea.mid`, `script/petRide.mid` | WorldManager |
| `script/mTask.mid`, `script/bTask.mid`, `script/bqTask.mid` | OverworldScreen |

[recover_assets.py](../tools/recover_assets.py) giải mã nhiều asset thành PNG/JSON,
nhưng chưa có writer nhập lại. [ReferenceProbe.java](../tools/ReferenceProbe.java)
dùng bytecode gốc làm đối chiếu; nó không phải bộ test đầy đủ của bản mod.
Không mặc định file `.mid` nào cũng là âm thanh hoặc dùng chung một schema.

## 5. Giới hạn dịch ngược

CFR ghi lỗi control flow/kiểu tại chín class, đối chiếu với
[summary.txt](../reference/decompiled/summary.txt):

| Runtime | Tên file hiện tại |
| --- | --- |
| `ao` | TextRenderer |
| `b` | ScreenView |
| `q` | BillingCanvas |
| `a.h` | SecurityHelper |
| `game.b` | Pet |
| `game.c` | OverworldScreen |
| `game.d` | BattleScreen |
| `game.j` | TileMapRenderer |
| `game.k` | WorldManager |

Các class không có cảnh báo cũng chưa được xác nhận biên dịch lại được. Bản
reference đã qua đổi tên có thêm chỗ khai báo/call site không nhất quán. Muốn sửa
class gốc cần giữ binary name, superclass/interface, trường và chữ ký được bên
ngoài dùng; khôi phục Java hợp lệ và giải quyết vấn đề package. Tái dựng cả source
thành kiến trúc mới là công việc riêng, không phải chỉ đổi tên file hoặc thêm import.
