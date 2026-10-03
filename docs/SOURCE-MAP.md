# Bản đồ source và tên class runtime

Đọc [Cẩm nang mở rộng game](EXTENDING-GAME.md) để có quy trình thêm Pokémon,
UI, tính năng hệ thống, vật phẩm, kỹ năng, NPC, nhiệm vụ và bản đồ.
Trang này giúp tìm code và đối chiếu tên; các tên thân thiện là tên đặt lại sau
dịch ngược, không phải API được nhà phát triển gốc công bố.

## 1. Mã nào được đóng gói?

Toàn bộ 71 file trong [src/main/java](../src/main/java/) được compile: 68 class
phục hồi và 3 helper phát triển. Engine/UI dùng package `game`, billing dùng
`game.billing`, messaging giữ `lavax`.

| Nguồn | Vai trò |
| --- | --- |
| [GameMIDLet.java](../src/main/java/game/GameMIDLet.java) | Entry point; tạo Canvas và khởi tạo cấu hình tốc độ |
| [GameSpeedConfig.java](../src/main/java/GameSpeedConfig.java) | Cấu hình tốc độ, AWT hotkey, watcher menu |
| [GameEngineBridge.java](../src/main/java/GameEngineBridge.java) | API UI và ghi trực tiếp `BaseScreen.frameDelayMs` |
| [src/main/resources](../src/main/resources/) | Asset và manifest; không chứa bytecode |
| [original/game.jar](../original/game.jar), [runtime-patches](../reference/runtime-patches/) | Oracle lịch sử, không tham gia build |
| [reference/decompiled](../reference/decompiled/) | Reference CFR lịch sử, không tham gia build |

[project.py](../project.py) compile với API emulator rồi đóng gói class mới và
resources. Không merge JAR gốc; build từ chối `.class` trong resources.
Xem [phục hồi và kiểm chứng source](SOURCE-BUILD.md).

## 2. Tìm theo hệ thống

Đường dẫn bên trái là **source đang build**. Cột tên gốc bên phải dùng để tra
bytecode lịch sử; ví dụ `game.b` nay là `game.Pet`. Các class default package gốc
như `aa`, `an`, `ab` nay cũng thuộc package `game`.

| Source hiện tại | Tên binary gốc | Đọc để hiểu |
| --- | --- | --- |
| [GameCanvas](../src/main/java/game/GameCanvas.java) | `game.e` | Vòng lặp update/repaint/sleep, bàn phím/con trỏ |
| [GameStateController](../src/main/java/game/GameStateController.java) | `game.i` | State cấp game, chuyển màn hình, âm thanh |
| [TitleScreen](../src/main/java/game/TitleScreen.java) | `game.f` | Menu đầu game, bắt đầu/tải game |
| [WorldManager](../src/main/java/game/WorldManager.java) | `game.k` | Scene/room, actor, encounter, điều phối và save/load |
| [OverworldScreen](../src/main/java/game/OverworldScreen.java) | `game.c` | Gameplay trên map, điều kiện và interpreter event/quest |
| [BattleScreen](../src/main/java/game/BattleScreen.java) | `game.d` | Trận đấu, lượt, bắt thú và kết quả |
| [ScriptEngine](../src/main/java/game/ScriptEngine.java) | `game.h` | Menu, hội thoại, tương tác, shop; không phải nơi duy nhất thực thi event |
| [Player](../src/main/java/game/Player.java) | `game.g` | Đội thú, túi, tiền, huy chương, thu thập, cưỡi thú |
| [Pet](../src/main/java/game/Pet.java) | `game.b` | Init/chỉ số, học chiêu, tiến hóa, dữ liệu instance, tính sát thương |
| [NpcEntity](../src/main/java/game/NpcEntity.java) | `game.a` | Actor/NPC trên map |
| [TileMapRenderer](../src/main/java/game/TileMapRenderer.java) | `game.j` | Render tile/layer |
| [MapEngine](../src/main/java/game/MapEngine.java) | `j` | Nạp map/tileset, dữ liệu ô và camera |
| [GameDatabase](../src/main/java/game/GameDatabase.java) | `aq` | Database, bảng sprite, text và ảnh nền |
| [EngineUtils](../src/main/java/game/EngineUtils.java) | `ae` | Đọc bảng nhị phân, ảnh, chuỗi, RNG, hàm hỗ trợ |
| [ResourceStream](../src/main/java/game/ResourceStream.java) | `aj` | Mở resource và theo dõi stream |
| [ImageCache](../src/main/java/game/ImageCache.java) | `am` | Cache ảnh theo image ID |
| [AnimationCache](../src/main/java/game/AnimationCache.java) | `aa` | Nạp metadata và tạo frame thú 86–185 |
| [SpriteData](../src/main/java/game/SpriteData.java) | `o` | Các mảng module/frame/animation/vùng |
| [SpriteRenderer](../src/main/java/game/SpriteRenderer.java) | `d` | Nạp sprite, tiến animation, transform và vẽ |
| [BaseEntity](../src/main/java/game/BaseEntity.java) | `n` | Vị trí, chỉ số và trạng thái entity |
| [WorldEntity](../src/main/java/game/WorldEntity.java) | `f` | Hướng/di chuyển và sprite của entity |
| [BaseInputHandler](../src/main/java/game/BaseInputHandler.java) | `ap` | Keycode và mask input |
| [BaseScreen](../src/main/java/game/BaseScreen.java) | `an` | Cơ sở màn hình, delay, kích thước và trạng thái dùng chung |
| [UIManager](../src/main/java/game/UIManager.java) | `ab` | Mở/đóng/cache/stack UI |
| [UILayoutView](../src/main/java/game/UILayoutView.java) | `ao` | Parser layout UI, component lookup, điều hướng và vẽ |
| [UIComponent](../src/main/java/game/UIComponent.java) | `w` | Interface của widget |
| [MenuWidget](../src/main/java/game/MenuWidget.java) | `al` | Container, type 0 của layout |
| [ItemListWidget](../src/main/java/game/ItemListWidget.java) | `af` | Widget type 1, chữ/icon |
| [MessageBox](../src/main/java/game/MessageBox.java) | `ac` | Widget type 2, lưới/hộp |
| [UIStyle](../src/main/java/game/UIStyle.java) | `k` | Thuộc tính chữ/nền/icon của widget |
| [UISelectionConfig](../src/main/java/game/UISelectionConfig.java) | `z` | Cấu hình chọn/điều hướng |
| [SpriteWidget](../src/main/java/game/SpriteWidget.java) | `m` | Sprite trong UI |
| [ScriptCommand](../src/main/java/game/ScriptCommand.java) | `ad` | Opcode, tham số số và chuỗi |
| [ScriptSequence](../src/main/java/game/ScriptSequence.java) | `p` | Chuỗi lệnh, program counter, trạng thái thực thi |
| [ScriptEventListener](../src/main/java/game/ScriptEventListener.java) | `i` | Callback `a(int[])` |
| [SkillEffect](../src/main/java/game/SkillEffect.java) | `ah` | Hiệu ứng chiêu thức |
| [ParticleEffect](../src/main/java/game/ParticleEffect.java) | `ai` | Hiệu ứng hạt |
| [SaveStorage](../src/main/java/game/SaveStorage.java) | `ar` | RecordStore/RMS |

Registry tên reference lịch sử nằm trong [reference-names.json](../tools/reference-names.json).
Mapping đầy đủ sau khi lan truyền tên qua kế thừa nằm trong
[names.tsv](../reference/decompiled/names.tsv), gồm owner, tên gốc và descriptor JVM.
Xem [quy trình đặt tên](REFACTORING.md) để bổ sung hoặc tái tạo reference.
Các script regex cũ được giữ làm lịch sử và đã chặn chạy trực tiếp.

## 3. Các chữ ký gốc để tra bytecode

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

`GameDatabase.spriteTable`, `Pet.initPet`, `UIManager.openUI` nay là API Java
đang build. Bảng trên mô tả chữ ký **gốc**, không dùng nguyên tên đó khi viết
source mới. Mapping của source nằm trong [source-names.tsv](../tools/source-names.tsv). Tên sai nghĩa trước đây đã sửa: `Pet.spriteId` thay `growthRate`; tham số
`hasExtendedAnimationSteps` thay `loop`. Chữ ký cùng tên bị overload phải đối chiếu
cả kiểu tham số và kiểu trả về. Các tên chưa xác minh vẫn giữ tên gốc.

## 4. Tài nguyên và công cụ

| Đường dẫn dưới `src/main/resources/data` | Loader/điểm tham khảo |
| --- | --- |
| `script/db.mid`, `script/chs.mid`, `script/sprite.mid` | GameDatabase, EngineUtils |
| `img/`, `tex/`, `spr/` | ImageCache, EngineUtils, AnimationCache, SpriteRenderer |
| `ui/*.ui` | UILayoutView + UIManager; binary, không phải XML |
| `event/scene_*.mid` | WorldManager, ScriptCommand, ScriptSequence, OverworldScreen |
| `map/`, `mod/` | MapEngine, TileMapRenderer |
| `script/petArea.mid`, `script/petRide.mid` | WorldManager |
| `script/mTask.mid`, `script/bTask.mid`, `script/bqTask.mid` | OverworldScreen |

[recover_assets.py](../tools/recover_assets.py) giải mã nhiều asset thành PNG/JSON,
nhưng chưa có writer nhập lại. [ReferenceProbe.java](../tools/ReferenceProbe.java)
dùng bytecode gốc làm đối chiếu; nó không phải bộ test đầy đủ của bản mod.
Không mặc định file `.mid` nào cũng là âm thanh hoặc dùng chung một schema.

## 5. Giới hạn reference lịch sử

CFR ghi lỗi control flow/kiểu tại chín class, đối chiếu với
[summary.txt](../reference/decompiled/summary.txt):

| Runtime | Tên file hiện tại |
| --- | --- |
| `ao` | UILayoutView |
| `b` | ScreenView |
| `q` | BillingCanvas |
| `a.h` | SecurityHelper |
| `game.b` | Pet |
| `game.c` | OverworldScreen |
| `game.d` | BattleScreen |
| `game.j` | TileMapRenderer |
| `game.k` | WorldManager |

Các cảnh báo này thuộc bản CFR trong `reference/decompiled/`, không phải lỗi
compile của `src/main/java/` hiện tại. Source runtime được phục hồi riêng bằng
Vineflower, sửa theo bytecode và kiểm chứng hành vi; xem [SOURCE-BUILD](SOURCE-BUILD.md).
Round-trip remap không tự chứng minh Java đúng hoặc tên có nghĩa đúng. Source
hiện tại còn các tên member/local chưa xác minh và chưa được kiểm thử toàn bộ gameplay.
