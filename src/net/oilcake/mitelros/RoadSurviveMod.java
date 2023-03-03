package net.oilcake.mitelros;

import net.oilcake.mitelros.experimental_tpa.EventListener;
import net.oilcake.mitelros.mixins.MinecraftMixin;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.events.LROSEvent;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Properties;

@Mod
public class RoadSurviveMod extends AbstractMod {
    public static String G;
    public static String MODs;
    public static String TPA;
    public static String TPALIST;
    public static String TPACONGFIG;
    public static String TPAYorN;
    public static String TPATIME;



    @Override
    public void preInit() {
    }

    public static void registerAllEvents() {
        MITEEvents.MITE_EVENT_BUS.register(new LROSEvent());
    }
    @Override
    public void postInit() {
        MITEEvents.MITE_EVENT_BUS.register(new EventListener());
        File file_Config = new File(MODs);
        file_Config.setWritable(true, false);
        if (!file_Config.exists() || !file_Config.isDirectory()) {
            file_Config.mkdir();
        }

        File file = new File(TPA);
        file.setWritable(true, false);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }

        File file_tpaList = new File(TPALIST);
        file_tpaList.setWritable(true, false);
        if (!file_tpaList.exists()) {
            try {
                file_tpaList.createNewFile();
                FileWriter fw = new FileWriter(TPALIST);
                fw.write("#此行不要删除。tpa白名单，将玩家名依次填入，每行一个。" + System.getProperty("line.separator"));
                fw.write("#中文名请转换为mc编码（unicode）,地精站可在线转码" + System.getProperty("line.separator"));
                fw.write("#名字后可以加”=注释“，举例：\\u5927_PI=大_PI" + System.getProperty("line.separator"));
                fw.close();
            } catch (IOException var15) {
                var15.printStackTrace();
            }
        }

        File file_tpaConfig = new File(TPACONGFIG);
        file_tpaConfig.setWritable(true, false);
        try {
            file_tpaConfig.createNewFile();
            FileWriter fw = new FileWriter(TPACONGFIG);
            fw.write("#无论如何，此配置文件只会以以下的设置生成，tp冷却时间为120秒并禁用home和back" + System.getProperty("line.separator"));
            fw.write("# admin=服主名，如果是中文名，要替换成mc编码(unicode)，地精站可在线转码" + System.getProperty("line.separator"));
            fw.write("home=0" + System.getProperty("line.separator"));
            fw.write("back=0" + System.getProperty("line.separator"));
            fw.write("time=120" + System.getProperty("line.separator"));
            fw.write("whitelist=1" + System.getProperty("line.separator"));
            fw.write("admin=" + System.getProperty("line.separator"));
            fw.close();
            file_tpaConfig.setReadOnly();
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        File file_tpaYorN = new File(TPAYorN);
        file_tpaYorN.setWritable(true, false);
        if (!file_tpaYorN.exists()) {
            try {
                file_tpaYorN.createNewFile();
            } catch (IOException var13) {
                var13.printStackTrace();
            }
        }

        File file_tpaTime = new File(TPATIME);
        file_tpaTime.setWritable(true, false);
        if (!file_tpaTime.exists()) {
            try {
                file_tpaTime.createNewFile();
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

        registerAllEvents();
    }
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            String value = pps.getProperty(key);
            System.out.println(key + " = " + value);
            return value;
        } catch (IOException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    @Nonnull
    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("MITE-LROS", MinecraftMixin.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    @Override
    public String modId() {
        return "RoadSurvive";
    }

    @Override
    public int modVerNum() {
        return Constant.VER_NUM;
    }

    @Override
    public String modVerStr() {
        return Constant.VERSION;
    }

    static {
        G = File.separator;
        MODs = "configs";
        TPA = MODs + G + "TPA";
        TPALIST = MODs + G + "TPA" + G + "tpaList.ini";
        TPACONGFIG = MODs + G + "TPA" + G + "tpaConfig.ini";
        TPAYorN = MODs + G + "TPA" + G + "tpaYorN.ga";
        TPATIME = MODs + G + "TPA" + G + "tpaTime.ga";
    }
}
