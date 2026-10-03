import java.io.*;
import java.util.Vector;
import org.recompile.mobile.*;

/** Re-enter a waiting script dialogue with no in-memory pagination state. */
public final class DialogueRecoveryCheck extends SourceParityProbe {
    private static Object sequence(int opcode, String text) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeShort(2);
        data.writeShort(opcode);
        int numbers = opcode == 84 ? 3 : 2;
        data.writeByte(numbers + 2);
        data.writeByte(numbers);
        data.writeShort(0);
        data.writeShort(-1);
        if (opcode == 84) data.writeShort(1);
        data.writeShort(0);
        data.writeShort(1);
        data.writeShort(14);
        data.writeByte(0);
        data.writeByte(0);
        Object sequence = create("p");
        call("p", sequence, "a", "(Ljava/io/DataInputStream;BI[Ljava/lang/String;)V",
             new DataInputStream(new ByteArrayInputStream(bytes.toByteArray())),
             (byte)0, 0, new String[]{"NPC", text});
        call("p", sequence, "a", "(B)V", (byte)5);
        return sequence;
    }

    private static void check(Object overworld, Object world, int opcode, String text, String expectedText) throws Exception {
        Object event = sequence(opcode, text);
        Vector active = (Vector)get("game/c", overworld, "z", "Ljava/util/Vector;");
        active.removeAllElements();
        active.addElement(event);
        set("ae", null, "a", "[Ljava/lang/String;", null);
        call("ap", world, "P", "()V");
        call("ap", world, "i", "(I)V", -5);
        call("ap", world, "S", "()V");
        // The confirm key must restore the dialogue, not discard its first page.
        call("game/c", overworld, "n", "()V");
        String[] lines = (String[])get("ae", null, "a", "[Ljava/lang/String;");
        if (lines == null || ((Number)get("ae",null,"b","I")).intValue() != 1
            || ((Number)call("p",event,"a","()B")).intValue() != 5
            || ((Number)call("ad",call("p",event,"c","()Lad;"),"a","()S")).intValue() != opcode)
            throw new AssertionError("Dialogue was not restored at its first page");
        StringBuilder restored = new StringBuilder();
        for (String line : lines) restored.append(line);
        if (!expectedText.equals(restored.toString())) throw new AssertionError("Dialogue content changed");
        Object manager = call("ab",null,"a","()Lab;");
        if (!Boolean.TRUE.equals(call("ab",manager,"b","(Ljava/lang/String;)Z","/data/ui/dialog.ui")))
            throw new AssertionError("Dialogue UI missing");
        int pages = ((Number)call("ae",null,"b","()I")).intValue();
        if (pages < 2) throw new AssertionError("Fixture should span multiple pages");
        for (int page = 2; page <= pages; page++) {
            call("game/c",overworld,"n","()V");
            if (((Number)get("ae",null,"b","I")).intValue() != page
                || ((Number)call("ad",call("p",event,"c","()Lad;"),"a","()S")).intValue() != opcode)
                throw new AssertionError("Page skipped at " + page);
        }
        call("game/c",overworld,"n","()V");
        if (((Number)call("ad",call("p",event,"c","()Lad;"),"a","()S")).intValue() != 14
            || ((Number)call("p",event,"a","()B")).intValue() != 1
            || Boolean.TRUE.equals(call("ab",manager,"b","(Ljava/lang/String;)Z","/data/ui/dialog.ui")))
            throw new AssertionError("Dialogue did not finish after its last page");
    }

    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((thread,error) -> {error.printStackTrace(); if(thread.getName().equals("main")) System.exit(1);});
        for (String line : java.nio.file.Files.readAllLines(java.nio.file.Paths.get(args[1]))) {
            String[] row = line.split("\t");
            if (row[0].equals("class")) classes.put(row[1],row[2]);
            else if (row.length == 5) members.put(row[0]+"\t"+key(row[1],row[2],row[3]),row[4]);
        }
        Mobile.minLogLevel = Mobile.LOG_ERROR;
        Mobile.sound = false;
        MobilePlatform platform = new MobilePlatform(240, 320);
        Mobile.setPlatform(platform, () -> {});
        platform.setPainter(() -> {});
        platform.dataPath = "rms/";
        if (!platform.load(new File(args[0]).toURI().toString())) throw new AssertionError("Load failed");
        loader = platform.loader;
        call("an",null,"a","(SS)V",(short)240,(short)320);
        call("game/i",call("game/i",null,"a","()Lgame/i;"),"d","()Z");
        Object world = call("game/k",null,"a","()Lgame/k;");
        call("game/k",world,"d","()Z");
        call("an",null,"t","()V");
        Object overworld = call("game/c",null,"a","()Lgame/c;");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 20; i++) text.append("Dialogue text must survive pagination. ");
        check(overworld, world, 4, text.toString(), text.toString());
        set("game/c", overworld, "p", "B", (byte)0);
        check(overworld, world, 84, "Count: %s, remaining: %s. " + text,
              "Count: 0, remaining: 5. " + text);
        System.out.println("Waiting dialogue recovery, all pages and script completion: OK");
        System.exit(0);
    }
}
