package net.oilcake.mitelros.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SpecializedTPAConfig {
    public static String G;
    public static String MODs;
    public static String TPA;
    public static String TPALIST;
    public static String TPACONGFIG;
    public static String TPAYorN;
    public static String TPAHOME;
    public static String TPABACK;
    public static String TPATIME;
    static {
        G = File.separator;
        MODs = "configs";
        TPA = MODs + G + "TPA";
        TPALIST = MODs + G + "TPA" + G + "tpaList.ini";
        TPACONGFIG = MODs + G + "TPA" + G + "tpaConfig.ini";
        TPAYorN = MODs + G + "TPA" + G + "tpaYorN.ga";
        TPAHOME = MODs + G + "TPA" + G + "tpaHome.ga";
        TPABACK = MODs + G + "TPA" + G + "tpaBack.ga";
        TPATIME = MODs + G + "TPA" + G + "tpaTime.ga";
    }
    public static void FileInit() {
        System.out.println("SPECIALIZED TPA CONFIG LOADED");
        File file_Config = new File(MODs);
        file_Config.setWritable(false, false);
        file_Config.mkdir();
        File file = new File(TPA);
        file.setWritable(false, false);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }

        File file_tpaList = new File(TPALIST);
        file_tpaList.setWritable(false, false);
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
        file_tpaConfig.setWritable(false, false);
        if (!file_tpaConfig.exists()) {
            try {
                file_tpaConfig.createNewFile();
                FileWriter fw = new FileWriter(TPACONGFIG);
                fw.write("#ITF预设配置，暂时不启用更改，如有特殊情况可考虑删除其他配置文件来重置冷却等" + System.getProperty("line.separator"));
                fw.write("# admin=服主名，如果是中文名，要替换成mc编码(unicode)，地精站可在线转码" + System.getProperty("line.separator"));
                fw.write("home=1" + System.getProperty("line.separator"));
                fw.write("back=0" + System.getProperty("line.separator"));
                fw.write("time=600" + System.getProperty("line.separator"));
                fw.write("whitelist=0" + System.getProperty("line.separator"));
                fw.write("admin=" + System.getProperty("line.separator"));
                fw.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        }

        File file_tpaYorN = new File(TPAYorN);
        file_tpaYorN.setWritable(false, false);
        if (!file_tpaYorN.exists()) {
            try {
                file_tpaYorN.createNewFile();
            } catch (IOException var13) {
                var13.printStackTrace();
            }
        }

        File file_tpaHome = new File(TPAHOME);
        file_tpaHome.setWritable(false, false);
        if (!file_tpaHome.exists()) {
            try {
                file_tpaHome.createNewFile();
            } catch (IOException var12) {
                var12.printStackTrace();
            }
        }

        File file_tpaBack = new File(TPABACK);
        file_tpaBack.setWritable(false, false);
        if (!file_tpaBack.exists()) {
            try {
                file_tpaBack.createNewFile();
            } catch (IOException var11) {
                var11.printStackTrace();
            }
        }

        File file_tpaTime = new File(TPATIME);
        file_tpaTime.setWritable(false, false);
        if (!file_tpaTime.exists()) {
            try {
                file_tpaTime.createNewFile();
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

    }
}
