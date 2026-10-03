import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.zip.*;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;
import org.recompile.mobile.*;

/** Run separately against original patched bytecode and the source-built JAR. */
public class SourceParityProbe {
    static ClassLoader loader;
    static final Map<String,String> classes = new HashMap<>(), members = new HashMap<>();
    static PrintWriter output;
    static int cases, failed;
    static String key(String owner, String name, String desc) { return owner+"\t"+name+"\t"+desc; }
    static Class<?> cls(String owner) throws Exception {
        return Class.forName(classes.getOrDefault(owner,owner).replace('/','.'),true,loader);
    }
    static Class<?>[] arguments(String descriptor) throws Exception {
        List<Class<?>> result = new ArrayList<>();
        for (int i=1;descriptor.charAt(i)!=')';) {
            char type=descriptor.charAt(i++);
            if(type=='L') {
                int end=descriptor.indexOf(';',i); result.add(cls(descriptor.substring(i,end))); i=end+1;
            } else if(type=='[') {
                int start=i-1;
                while(descriptor.charAt(i)=='[')i++;
                if(descriptor.charAt(i)=='L') {
                    int end=descriptor.indexOf(';',i);
                    String owner=descriptor.substring(i+1,end);
                    String value=descriptor.substring(start,i+1)+classes.getOrDefault(owner,owner)+";";
                    result.add(Class.forName(value.replace('/','.'),true,loader)); i=end+1;
                } else { i++;result.add(Class.forName(descriptor.substring(start,i))); }
            } else {
                result.add(type=='I'?int.class:type=='B'?byte.class:type=='S'?short.class:
                    type=='Z'?boolean.class:type=='J'?long.class:type=='C'?char.class:
                    type=='F'?float.class:double.class);
            }
        }
        return result.toArray(new Class<?>[0]);
    }
    static Object call(String owner,Object target,String name,String desc,Object... args) throws Exception {
        Method method=cls(owner).getDeclaredMethod(members.getOrDefault("method\t"+key(owner,name,desc),name),arguments(desc));
        String expected=desc.substring(desc.indexOf(')')+1);
        for(Map.Entry<String,String> entry:classes.entrySet())
            expected=expected.replace("L"+entry.getKey()+";","L"+entry.getValue()+";");
        if(!typeDescriptor(method.getReturnType()).equals(expected))
            throw new AssertionError("Invalid probe descriptor: "+owner+"."+name+desc);
        method.setAccessible(true);
        try { return method.invoke(target,args); }
        catch(InvocationTargetException ex) {
            Throwable cause=ex.getCause();
            if(cause instanceof Exception)throw (Exception)cause;
            throw (Error)cause;
        }
    }
    static Field field(String owner,String name,String desc) throws Exception {
        Field f=cls(owner).getDeclaredField(members.getOrDefault("field\t"+key(owner,name,desc),name));
        f.setAccessible(true);return f;
    }
    static Object get(String owner,Object target,String name,String desc) throws Exception { return field(owner,name,desc).get(target); }
    static void set(String owner,Object target,String name,String desc,Object value) throws Exception { field(owner,name,desc).set(target,value); }
    static Object create(String owner) throws Exception { return cls(owner).getConstructor().newInstance(); }
    static String typeDescriptor(Class<?> type) {
        if(type.isArray())return type.getName().replace('.','/');
        if(!type.isPrimitive())return "L"+type.getName().replace('.','/')+";";
        if(type==void.class)return "V";
        if(type==int.class)return "I";if(type==byte.class)return "B";if(type==short.class)return "S";
        if(type==boolean.class)return "Z";if(type==long.class)return "J";if(type==char.class)return "C";
        return type==float.class?"F":"D";
    }
    static String value(Object v) {
        if(v==null)return "null";
        if(v.getClass().isArray()) {
            StringBuilder s=new StringBuilder("[");
            for(int i=0;i<Array.getLength(v);i++){if(i>0)s.append(',');s.append(value(Array.get(v,i)));}
            return s.append(']').toString();
        }
        return String.valueOf(v);
    }
    interface Check { Object run() throws Exception; }
    static void check(String name,Check action) {
        cases++;
        try { output.println(name+"\t"+value(action.run())); }
        catch(Throwable error){
            if(error instanceof ReflectiveOperationException || error instanceof LinkageError || error instanceof AssertionError)
                throw new RuntimeException("Probe failed at "+name,error);
            failed++;output.println(name+"\tERROR:"+error.getClass().getName());
            if(name.equals("sprite/208") || name.equals("ui/data/ui/answer.ui"))error.printStackTrace();
        }
    }
    static Object pet(int id,int level,int quality,byte variant) throws Exception {
        Object p=create("game/b");
        call("game/b",p,"a","(IISBSB)V",id,level,(short)-1,(byte)2,(short)quality,variant);
        return p;
    }
    static final class Draws extends Random {
        final int draw; int index;
        Draws(int draw){this.draw=draw;}
        public int nextInt(){index++;return draw<<1;}
    }
    static String imageHash(Image image) throws Exception {
        int[] pixels=new int[image.getWidth()*image.getHeight()];
        image.getRGB(pixels,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());
        MessageDigest digest=MessageDigest.getInstance("SHA-256");
        for(int p:pixels){digest.update((byte)(p>>24));digest.update((byte)(p>>16));digest.update((byte)(p>>8));digest.update((byte)p);}
        StringBuilder out=new StringBuilder();for(byte b:digest.digest())out.append(String.format("%02x",b&255));return out.toString();
    }
    static Object widgetTree(Object widget) throws Exception {
        if(widget==null)return null;
        Object style=call("w",widget,"h","()Lk;");
        List<Object> result=new ArrayList<>();
        for(String method:new String[]{"a","b","c","d","e","i","j"})
            result.add(call("w",widget,method,"()I"));
        result.add(style==null?null:get("k",style,"a","Ljava/lang/String;"));
        Object[] children=(Object[])call("w",widget,"g","()[Lw;");
        if(children!=null)for(Object child:children){if(child==null)break;result.add(widgetTree(child));}
        return result.toString();
    }
    public static void main(String[] args) throws Exception {
        Path jar=Paths.get(args[0]).toAbsolutePath(), out=Paths.get(args[2]).toAbsolutePath();
        Files.createDirectories(out.getParent());
        if(!args[1].equals("-"))for(String line:Files.readAllLines(Paths.get(args[1]))) {
            String[] row=line.split("\t");
            if(row[0].equals("class"))classes.put(row[1],row[2]);
            else if(row.length==5)members.put(row[0]+"\t"+key(row[1],row[2],row[3]),row[4]);
        }
        Mobile.minLogLevel=Mobile.LOG_ERROR;Mobile.sound=false;
        MobilePlatform platform=new MobilePlatform(240,320);
        Mobile.setPlatform(platform,()->{});platform.setPainter(()->{});
        platform.dataPath=out.getParent().resolve("rms").toString()+"/";
        if(!platform.load(jar.toUri().toString()))throw new AssertionError("JAR failed to load");
        loader=platform.loader;
        output=new PrintWriter(Files.newBufferedWriter(out));
        if(args.length>3 && args[3].equals("startup")) {
            startup(platform,out.getParent());
            output.close();System.exit(0);
        }
        call("an",null,"a","(SS)V",(short)240,(short)320);
        if(args.length>3 && args[3].equals("flow")) {
            flow(platform,out.getParent());
            output.close();System.exit(0);
        }
        call("aq",null,"a","()V");
        call("am",null,"a","()V");call("aa",null,"a","()V");
        check("database",()->get("aq",null,"c","[[[S"));
        check("strings",()->get("aq",null,"d","[Ljava/lang/String;"));
        short[][][] database=(short[][][])get("aq",null,"c","[[[S");
        for(int id=0;id<database[0].length;id++)for(int level:new int[]{1,7,20,49,50})
            for(int quality:new int[]{1,5})for(byte variant:new byte[]{-1,7,8,9}) {
                final int species=id,lvl=level,q=quality;final byte v=variant;
                check("pet/"+id+"/"+level+"/"+quality+"/"+variant,()->{
                    Object p=pet(species,lvl,q,v);
                    int[] save=(int[])call("game/b",p,"P","()[I");
                    Object restored=create("game/b");call("game/b",restored,"a","([I)V",save);
                    if(!Arrays.equals(save,(int[])call("game/b",restored,"P","()[I")))throw new AssertionError("Pet save mismatch");
                    return new Object[]{get("n",p,"c","[S"),save};
                });
            }
        for(int id=0;id<100;id++)for(int target=0;target<100;target++) {
            final int a=id,b=target;
            check("affinity/"+id+"/"+target,()->call("game/b",pet(a,20,3,(byte)-1),"a","(Lgame/b;)B",pet(b,20,3,(byte)-1)));
        }
        int[] elements={0,16,32,48,64,76,88};
        for(int skill=0;skill<database[1].length;skill++)for(int species:elements)for(int target:elements)
            for(int draw:new int[]{0,25,50,51,99}) {
                final int sk=skill,a=species,b=target,r=draw;
                check("damage/"+skill+"/"+species+"/"+target+"/"+draw,()->{
                    Object p=pet(a,20,3,(byte)-1),defender=pet(b,20,3,(byte)-1);
                    call("game/b",p,"a","(BLgame/b;)V",(byte)sk,defender);
                    Draws random=new Draws(r);set("ae",null,"f","Ljava/util/Random;",random);
                    Object damage=call("game/b",p,"b","(Lgame/b;)[I",defender);
                    return new Object[]{damage,get("n",defender,"d","[S"),get("game/b",defender,"w","[[S"),random.index};
                });
            }
        short[][] spriteTable=(short[][])get("aq",null,"a","[[S");
        for(int id=0;id<spriteTable.length;id++) {
            final int spriteId=id;
            check("sprite/"+id,()->{
                Object renderer=create("d");call("d",renderer,"a","(IZ)Z",spriteId,false);
                Image image=Image.createImage(240,320);Graphics g=image.getGraphics();g.setColor(0x182522);g.fillRect(0,0,240,320);
                Object data=get("d",renderer,"l","Lo;");
                short[][] frames=(short[][])get("o",data,"e","[[S");
                for(int frame=0;frame<frames.length;frame++)for(byte transform:new byte[]{0,1,3,4})
                    call("d",renderer,"a","(Ljavax/microedition/lcdui/Graphics;IIIB)V",g,frame,120,200,transform);
                String hash=imageHash(image);call("d",renderer,"a","()V");return hash;
            });
        }
        List<String> layouts=new ArrayList<>();
        try(ZipFile z=new ZipFile(jar.toFile())){z.stream().filter(e->e.getName().endsWith(".ui")).forEach(e->layouts.add(e.getName()));}
        Collections.sort(layouts);
        Object textPainter=create("y");
        Object listener=Proxy.newProxyInstance(loader,new Class<?>[]{cls("i")},(proxy,method,a)->null);
        for(String layout:layouts)check("ui-tree/"+layout,()->{
            Object view=cls("ao").getConstructor(cls("i")).newInstance(listener);
            call("ao",view,"a","(Ly;)V",textPainter);
            call("ao",view,"a","(Ljava/lang/String;I)V","/"+layout,0);
            return widgetTree(call("ao",view,"a","()Lal;"));
        });
        for(String layout:layouts)check("ui/"+layout,()->{
            Object view=cls("ao").getConstructor(cls("i")).newInstance(listener);
            call("ao",view,"a","(Ly;)V",textPainter);
            call("ao",view,"a","(Ljava/lang/String;I)V","/"+layout,0);
            Image image=Image.createImage(240,320);call("ao",view,"a","(Ljavax/microedition/lcdui/Graphics;)V",image.getGraphics());
            List<Object> states=new ArrayList<>();states.add(imageHash(image));
            for(int input:new int[]{0,1,2,3,5,7}) {
                states.add(call("ao",view,"b","(I)Z",input));
                call("ao",view,"a","(Ljavax/microedition/lcdui/Graphics;)V",image.getGraphics());states.add(imageHash(image));
            }
            call("ao",view,"b","()V");return states;
        });
        for(int size:new int[]{0,1,17,256,4096}) {
            final int length=size;
            check("rms/"+size,()->{
                Object store=cls("ar").getConstructor(String.class).newInstance("source-parity-"+length);
                ByteArrayOutputStream bytes=new ByteArrayOutputStream();for(int i=0;i<length;i++)bytes.write(i);
                call("ar",store,"a","(Ljava/io/ByteArrayOutputStream;)V",bytes);
                Object reopened=cls("ar").getConstructor(String.class).newInstance("source-parity-"+length);
                byte[] result=(byte[])call("ar",reopened,"a","()[B");
                if(!Arrays.equals(result,bytes.toByteArray()))throw new AssertionError("RMS mismatch");return result;
            });
        }
        check("billing/table",()->{
            Object table=cls("a/h").getConstructor(byte.class).newInstance((byte)0);
            List<Object> result=new ArrayList<>();
            for(int i=0;i<200;i++)call("a/h",table,"a","(Ljava/lang/Object;Ljava/lang/Object;)V",new Integer(i*17),"value-"+i);
            for(int i=0;i<200;i++)result.add(call("a/h",table,"a","(Ljava/lang/Object;)Ljava/lang/Object;",new Integer(i*17)));
            return result;
        });
        check("billing/equality",()->call("a/g",null,"c","(Ljava/lang/Object;Ljava/lang/Object;)Z",new Integer(500),new Integer(500)));
        List<Integer> maps=new ArrayList<>();
        try(ZipFile z=new ZipFile(jar.toFile())){
            z.stream().filter(e->e.getName().matches("data/map/map_[0-9]+\\.mid"))
                .forEach(e->maps.add(Integer.parseInt(e.getName().replaceAll("\\D",""))));
        }
        Collections.sort(maps);
        Object map=call("j",null,"a","()Lj;");
        for(int id:maps)check("map/"+id,()->{
            call("j",map,"a","(I)V",id);
            int width=((Number)get("j",map,"c","I")).intValue(),height=((Number)get("j",map,"d","I")).intValue();
            List<Object> result=new ArrayList<>();
            result.add(value(get("j",map,"A","[[[S")));
            for(int[] center:new int[][]{{0,0},{width/2,height/2},{width,height}}) {
                call("j",map,"a","(II)V",center[0],center[1]);
                Image image=Image.createImage(240,320);
                int layers=((Number)get("j",map,"y","B")).intValue();
                for(int layer=0;layer<layers;layer++)call("j",map,"a","(Ljavax/microedition/lcdui/Graphics;II)V",image.getGraphics(),layer,0);
                result.add(imageHash(image));
                result.add(call("j",map,"b","(II)B",center[0],center[1]));
            }
            return result;
        });
        output.close();System.out.println("Parity cases="+cases+" exceptions="+failed+" output="+out);System.exit(0);
    }

    /** Advance real state handlers and renderers deterministically, without a Canvas thread. */
    static void flow(MobilePlatform platform,Path out) throws Exception {
        Object controller=call("game/i",null,"a","()Lgame/i;");
        call("game/i",controller,"d","()Z");
        call("game/i",controller,"c","(Z)V",true);
        call("game/i",controller,"a","(B)V",(byte)7);
        call("an",null,"u","()V");
        Object world=call("game/k",null,"a","()Lgame/k;");
        set("ae",null,"f","Ljava/util/Random;",new Random(1847));
        Image image=Image.createImage(240,320);
        for(int tick=0;tick<6000;tick++) {
            Object dialogue=call("game/j",null,"a","()Lgame/j;");
            if(((Number)get("game/j",dialogue,"D","J")).longValue()>0)set("game/j",dialogue,"D","J",1L);
            int state=((Number)call("game/i",controller,"e","()B")).intValue();
            if(tick%8==0) {
                call("ap",controller,"i","(I)V",-6);
                call("ap",controller,"i","(I)V",-5);
                call("ap",controller,"i","(I)V",48);
            }
            if(tick%8==2) {
                call("ap",controller,"j","(I)V",-6);
                call("ap",controller,"j","(I)V",-5);
                call("ap",controller,"j","(I)V",48);
            }
            call("game/i",controller,"b","()V");
            // This fixture renders explicitly; there is no Canvas for the game's repaint timer.
            Timer timer=(Timer)get("an",null,"i","Ljava/util/Timer;");
            if(timer!=null)timer.cancel();
            call("game/i",controller,"b","(Ljavax/microedition/lcdui/Graphics;)V",image.getGraphics());
            int after=((Number)call("game/i",controller,"e","()B")).intValue();
            if(tick%80==0 || state!=after) {
                output.println("flow/"+tick+"\t"+after+"/"+get("game/k",world,"f","I")+"/"+get("game/k",world,"g","I")+"/"+imageHash(image));
                javax.imageio.ImageIO.write(((PlatformImage)image).getCanvas(),"png",out.resolve("flow-"+tick+".png").toFile());
            }
        }
        for(String field:new String[]{"d","e","f"})set("game/i",controller,field,"J",0L);
        Object player=call("game/g",null,"o","()Lgame/g;");
        output.println("save/player\t"+call("game/k",world,"k","()Z"));
        output.println("save/party\t"+call("game/k",world,"j","()Z"));
        Object[] stores=(Object[])get("game/k",null,"af","[Lar;");
        List<Object> records=new ArrayList<>();
        for(Object store:stores)records.add(call("ar",store,"a","()[B"));
        output.println("save/records\t"+value(records.toArray()));
        Object[] party=(Object[])get("game/g",player,"z","[Lgame/b;");
        int count=((Number)get("game/g",player,"A","I")).intValue();
        List<Object> before=new ArrayList<>();
        for(int index=0;index<count;index++)before.add(value(call("game/b",party[index],"P","()[I")));
        if(!Boolean.TRUE.equals(call("game/k",world,"aj","()Z")))throw new AssertionError("Party load failed");
        party=(Object[])get("game/g",player,"z","[Lgame/b;");
        List<Object> after=new ArrayList<>();
        for(int index=0;index<count;index++)after.add(value(call("game/b",party[index],"P","()[I")));
        if(!before.equals(after))throw new AssertionError("Party changed after reload");
        output.println("reload/party\t"+after);
        System.out.println("Flow completed: "+out);
    }

    static void startup(MobilePlatform platform,Path out) throws Exception {
        List<Throwable> errors=Collections.synchronizedList(new ArrayList<>());
        Thread.setDefaultUncaughtExceptionHandler((thread,error)->{
            errors.add(error);
            error.printStackTrace();
            if(thread.getName().equals("main"))System.exit(1);
        });
        platform.runJar();
        Thread.sleep(6500);
        Object canvas=call("game/e",null,"a","()Lgame/e;");
        Object midlet=get("game/GameMIDLet",null,"a","Lgame/GameMIDLet;");
        if(canvas==null || midlet==null || !errors.isEmpty())throw new AssertionError("Startup failed: "+errors);
        Object controller=call("game/i",null,"a","()Lgame/i;");
        output.println("startup/state\t"+call("game/i",controller,"e","()B"));
        Class<?> speed=Class.forName("GameSpeedConfig",true,loader);
        for(int multiplier=1;multiplier<=4;multiplier++) {
            speed.getMethod("setMultiplier",int.class).invoke(null,multiplier);
            int expected=((Number)speed.getMethod("calculateDelay",int.class).invoke(null,multiplier)).intValue();
            if(((Number)get("an",null,"c","I")).intValue()!=expected)throw new AssertionError("Speed hook failed");
            output.println("startup/speed/"+multiplier+"\t"+expected);
        }
        javax.imageio.ImageIO.write(platform.getLcdFrontbuffer().getCanvas(),"png",out.resolve("startup.png").toFile());
        output.println("startup/uncaught-errors\t"+errors.size());
        System.out.println("Real MIDlet, Canvas thread and speed integration: OK");
    }
}
