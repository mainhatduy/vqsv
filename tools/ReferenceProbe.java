import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;
import org.recompile.mobile.*;

/** Executes original bytecode as an oracle, never shipped with the Godot game. */
public class ReferenceProbe {
    static ClassLoader loader;
    static Class<?> cls(String n) throws Exception { return Class.forName(n, true, loader); }
    static Object call(Object target, String name, Class<?>[] types, Object... args) throws Exception {
        Class<?> c = target instanceof Class ? (Class<?>)target : target.getClass();
        Method m = c.getDeclaredMethod(name, types); m.setAccessible(true);
        return m.invoke(target instanceof Class ? null : target, args);
    }
    static Object field(Object target, String name) throws Exception {
        Class<?> c = target instanceof Class ? (Class<?>)target : target.getClass();
        while (c != null) {
            try { Field f = c.getDeclaredField(name); f.setAccessible(true); return f.get(target instanceof Class ? null : target); }
            catch (NoSuchFieldException e) { c = c.getSuperclass(); }
        }
        throw new NoSuchFieldException(name);
    }
    static void set(Object target, String name, Object value) throws Exception {
        Class<?> c = target instanceof Class ? (Class<?>)target : target.getClass();
        Field f = c.getDeclaredField(name); f.setAccessible(true); f.set(target instanceof Class ? null : target, value);
    }
    static short[][] rows(DataInputStream d) throws Exception {
        short[][] a = new short[d.readShort()][];
        for (int i=0; i<a.length; i++) { a[i]=new short[d.readShort()]; for(int j=0;j<a[i].length;j++) a[i][j]=d.readShort(); }
        return a;
    }
    static class Draws extends Random {
        final int[] draws; int index;
        Draws(int... d) { draws=d; }
        public int nextInt() { return draws[(index++) % draws.length] << 1; }
    }
    static Object pet(int id, int level, int quality) throws Exception {
        Object p=cls("game.b").getConstructor().newInstance();
        call(p,"a",new Class[]{int.class,int.class,short.class,byte.class,short.class,byte.class}, id,level,(short)-1,(byte)2,(short)quality,(byte)-1);
        return p;
    }
    public static void main(String[] args) throws Exception {
        Path root=Paths.get(args[0]).toAbsolutePath(), out=Paths.get(args[1]).toAbsolutePath();
        Files.createDirectories(out);
        Mobile.minLogLevel=Mobile.LOG_ERROR; Mobile.sound=false;
        MobilePlatform platform=new MobilePlatform(240,320);
        Mobile.setPlatform(platform, () -> {}); platform.setPainter(() -> {});
        platform.dataPath=out.resolve("rms").toString()+"/";
        platform.load(root.resolve("original/game.jar").toUri().toString()); loader=platform.loader;
        short[][][] db=new short[9][][];
        try(DataInputStream d=new DataInputStream(Files.newInputStream(root.resolve("src/main/resources/data/script/db.mid")))) {
            for(int i=0;i<9;i++) db[i]=rows(d);
        }
        set(cls("aq"),"c",db);
        if(args.length>2 && args[2].equals("flow")) { flow(platform,out); return; }
        StringBuilder json=new StringBuilder("{\"stats\":[");
        int[][] pets={{68,7,2},{5,20,4}};
        for(int i=0;i<pets.length;i++) {
            Object p=pet(pets[i][0],pets[i][1],pets[i][2]);
            if(i>0)json.append(',');
            json.append("{\"id\":").append(pets[i][0]).append(",\"level\":").append(pets[i][1]).append(",\"quality\":").append(pets[i][2]).append(",\"values\":").append(Arrays.toString((short[])field(p,"c"))).append('}');
        }
        json.append("],\"damage\":[");
        boolean comma=false;
        for(int side=0;side<2;side++) for(int draw : new int[]{0,25,50,51,99}) {
            Object attacker=pet(pets[side][0],pets[side][1],pets[side][2]);
            Object defender=pet(pets[1-side][0],pets[1-side][1],pets[1-side][2]);
            int skill=side==0?40:0;
            call(attacker,"a",new Class[]{byte.class,cls("game.b")},(byte)skill,defender);
            set(cls("ae"),"f",new Draws(draw,draw));
            int[] result=(int[])call(attacker,"b",new Class[]{cls("game.b")},defender);
            if(comma)json.append(','); comma=true;
            json.append("{\"attacker\":").append(pets[side][0]).append(",\"skill\":").append(skill).append(",\"draws\":[").append(draw).append(',').append(draw).append("],\"result\":").append(Arrays.toString(result)).append('}');
        }
        json.append("]}"); Files.write(out.resolve("battle_oracle.json"),json.toString().getBytes("UTF-8"));
        call(cls("am"),"a",new Class[]{}); call(cls("aa"),"a",new Class[]{});
        try(DataInputStream d=new DataInputStream(Files.newInputStream(root.resolve("src/main/resources/data/script/sprite.mid")))) { set(cls("aq"),"a",rows(d)); }
        for(int id:new int[]{0,8,30,83,84,85,91,154,259}) {
            Object sprite=cls("d").getConstructor().newInstance();
            call(sprite,"a",new Class[]{int.class,boolean.class},id,false);
            Image img=Image.createImage(320,320);
            Graphics g=img.getGraphics(); g.setColor(0x182522); g.fillRect(0,0,320,320);
            call(sprite,"a",new Class[]{Graphics.class,int.class,int.class,int.class,byte.class},g,0,160,200,(byte)0);
            int[] rgb=new int[320*320]; img.getRGB(rgb,0,320,0,0,320,320);
            java.awt.image.BufferedImage png=new java.awt.image.BufferedImage(320,320,java.awt.image.BufferedImage.TYPE_INT_ARGB);
            png.setRGB(0,0,320,320,rgb,0,320); ImageIO.write(png,"png",out.resolve("sprite_"+id+".png").toFile());
        }
        System.out.println("Original-bytecode battle and sprite fixtures: "+out);
        System.exit(0);
    }
    static void flow(MobilePlatform platform, Path out) throws Exception {
        platform.runJar();
        String last=""; int imageIndex=0;
        for(int step=0;step<2400;step++) {
            Thread.sleep(130);
            Object controller=call(cls("game.i"),"a",new Class[]{});
            int state=((Number)call(controller,"e",new Class[]{})).intValue();
            Object world=call(cls("game.k"),"a",new Class[]{});
            String key="state="+state+" scene="+field(world,"f")+" room="+field(world,"g");
            Object events=field(world,"M");
            if(events!=null) {
                Object active=field(events,"z");
                if(active instanceof Vector && !((Vector<?>)active).isEmpty()) {
                    Object event=((Vector<?>)active).get(0);
                    key+=" event="+call(event,"b",new Class[]{})+" pc="+field(event,"d");
                }
            }
            if(!key.equals(last)) {
                System.out.println(key); last=key;
                ImageIO.write(platform.getLcdFrontbuffer().getCanvas(),"png",out.resolve(String.format("flow_%04d.png",imageIndex++)).toFile());
            }
            if(state==12) {
                Object battle=call(cls("game.d"),"a",new Class[]{});
                System.out.println("BATTLE phase="+field(battle,"P"));
            }
            // Scripted harness drives confirm only; the original game still performs every update.
            MobilePlatform.keyPressed(-5); Thread.sleep(75); MobilePlatform.keyReleased(-5);
            if(state==11 && ((Number)field(world,"f")).intValue()==1 && ((Number)field(world,"g")).intValue()==0) {
                System.out.println("POST_BATTLE_ROOM_REACHED"); break;
            }
        }
        System.exit(0);
    }
}
