package net.oilcake.mitelros.util;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StuckTagConfig {
    public static Map<String, ConfigItem> Tags = new HashMap<>();

    public static class ConfigItem<T>{
        public String ConfigKey;
        public T ConfigValue;
        public T min;
        public T max;
        public boolean isNeedCompare = false;
        public String ConfigComment;
        ConfigItem(String key, T value, String comment){
            this.ConfigKey = key;
            this.ConfigValue = value;
            this.ConfigComment = comment;
        }
        ConfigItem(String key, T value, String comment, T min, T max){
            this.ConfigKey = key;
            this.ConfigValue = value;
            this.isNeedCompare = true;
            this.min = min;
            this.max = max;
            this.ConfigComment = comment + " [范围："+ min + "-" + max +"]";
        }
        public void setConfigValue(T configValue) {
            ConfigValue = configValue;
        }
        public T getConfigValue() {
            return this.ConfigValue;
        }
    }

    public static class TagConfig {
        public static ConfigItem <Boolean> TagNoWeatherPredict = new ConfigItem<>("NoWeatherPredict", false, "(LVL1)阴晴无定：删除天气预报");
        public static ConfigItem <Boolean> TagHeatStroke = new ConfigItem<>("HeatStroke",false,"(LVL1)酷暑代价：水分自然消耗的速度提升200%");
        public static ConfigItem <Boolean> TagInstinctSurvival = new ConfigItem<>("InstinctSurvival",false,"(LVL2)防御本能：怪物享受护甲防御的比率提升25%");
        public static ConfigItem <Boolean> TagFallenInMine = new ConfigItem<>("FallenInMine",false,"(LVL2)矿难群体：亡灵的生命值提升50%，攻击力提升25%，且召唤僵尸支援");
        public static ConfigItem <Boolean> TagBattleSuffer = new ConfigItem<>("BattleSuffer",false,"(LVL2)久经沙场：骷髅领主的生命值提升50%，攻击力提升40%，召唤的支援获得强化");
        public static ConfigItem <Boolean> TagInvisibleFollower = new ConfigItem<>("InvisibleFollower",false,"(LVL1)无形跟随：更低层数的爬行者将被替换为潜伏爬行者");
        public static ConfigItem <Boolean> TagLegendFreeze = new ConfigItem<>("LegendFreeze",false,"(LVL1)刺骨寒风：寒冷惩罚的积累速度提升200%");
        public static ConfigItem <Boolean> TagUnstableConvection = new ConfigItem<>("UnstableConvection",false,"(LVL1)不稳定对流：闪电的触发频率提升300%");
        public static ConfigItem <Boolean> TagEternalRaining = new ConfigItem<>("EternalRaining",false,"(LVL1)阴雨连绵：雨的最长持续时间提升300%，最短持续时间提升700%");


        //*这个有问题*//        public static ConfigItem <Boolean> TagWorshipDark = new ConfigItem<>("WorshipDark",false,"(LVL2)崇尚黑暗：僵尸将尝试摧毁其沿途可见的火把");
        //*这个有问题*//        public static ConfigItem <Boolean> TagApocalypse = new ConfigItem<>("Apocalypse",false,"(LVL3)灾厄余生：不再生成可提供肉类的动物");
        //public static ConfigItem <Boolean> = new ConfigItem("Tag",false,"(LVL)");
    }

    public static void loadConfigs(){
        System.out.println("Tags Was put in HASHMAP");
        Tags.put("NoWeatherPredict",TagConfig.TagNoWeatherPredict);
        Tags.put("HeatStroke",TagConfig.TagHeatStroke);
        Tags.put("InstinctSurvival",TagConfig.TagInstinctSurvival);
        Tags.put("FallenInMine",TagConfig.TagFallenInMine);
        Tags.put("BattleSuffer",TagConfig.TagBattleSuffer);
        Tags.put("InvisibleFollower",TagConfig.TagInvisibleFollower);
        Tags.put("LegendFreeze",TagConfig.TagLegendFreeze);
        Tags.put("UnstableConvection",TagConfig.TagUnstableConvection);
        Tags.put("EternalRaining",TagConfig.TagEternalRaining);
//      Tags.put("WorshipDark",TagConfig.TagWorshipDark);
//      Tags.put("Apocalypse",TagConfig.TagApocalypse);
        String filePth = "StuckChallenge.cfg";
        File file_mite = new File(filePth);
        if (file_mite.exists()) {
            System.out.println("READING SETTINGS FILE");
            Properties properties = new Properties();
            FileReader fr = null;
            try {
                fr = new FileReader(file_mite.getName());
                properties.load(fr);
                fr.close();
                readConfigFromFile(file_mite, properties);
                packConfigFile(file_mite, properties);
            } catch (FileNotFoundException var6) {
                System.out.println("READING FAILED TP1");
                var6.printStackTrace();
            } catch (IOException var7) {
                System.out.println("READING FAILED TP2");
                var7.printStackTrace();
            }
        } else {
            System.out.println("GENERATING SETTINGS FILE");
            try {
                if (file_mite.createNewFile()){
                    file_mite.setExecutable(true);//设置可执行权限
                    file_mite.setReadable(true);//设置可读权限
                    file_mite.setWritable(true);//设置可写权限
                    generateConfigFile(file_mite);
                }
            } catch (IOException e) {
                System.out.println("GENERATING FAILED");
                e.printStackTrace();
                JFrame jFrame = new JFrame();
                jFrame.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jFrame, "词条读取失败，尝试删除配置文件……", "错误", 0);
                System.exit(0);
            }
        }
    }

    public static void  readConfigFromFile(File file_mite, Properties properties) {
        for (String key : properties.stringPropertyNames()) {
            ConfigItem configItem = Tags.get(key);
            if(configItem != null) {
                if(configItem.ConfigValue instanceof Boolean) {
                    configItem.setConfigValue(Boolean.parseBoolean(properties.getProperty(key)));
                } else if(configItem.ConfigValue instanceof Float) {
                    float value = Float.parseFloat(properties.getProperty(key));
                    if(configItem.isNeedCompare) {
                        value = value > (float)configItem.max ? (float) configItem.max : Math.max(value, (float) configItem.min);
                    }
                    configItem.setConfigValue(value);
                } else if(configItem.ConfigValue instanceof Double) {
                    double value = Double.parseDouble(properties.getProperty(key));
                    if(configItem.isNeedCompare) {
                        value = value > (double)configItem.max ? (double) configItem.max : Math.max(value, (double) configItem.min);
                    }
                    configItem.setConfigValue(value);
                } else if(configItem.ConfigValue instanceof Integer) {
                    int value = Integer.parseInt(properties.getProperty(key));
                    if(configItem.isNeedCompare) {
                        value = value > (int)configItem.max ? (int) configItem.max : Math.max(value, (int) configItem.min);
                    }
                    configItem.setConfigValue(value);
                } else {
                    configItem.setConfigValue(properties.getProperty(key));
                }
            }
        }
    }

    public static void packConfigFile(File file,Properties properties) {
        try{
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            for (Map.Entry<String, ConfigItem> entry: Tags.entrySet()) {
                ConfigItem value = entry.getValue();
                String localValue = properties.getProperty(value.ConfigKey);
                if(localValue == null) {
                    fileWritter.write("// " + value.ConfigComment + "\n");
                    fileWritter.write(value.ConfigKey + "=" + value.ConfigValue + "\n\n");
                }
            }
            fileWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateConfigFile(File file) {
        try{
            FileWriter fileWritter = new FileWriter(file.getName());
            fileWritter.write("// 在每一项配置后填入true或者false来选择，不开放任何完全自定义选项 \n");
            for (Map.Entry<String, ConfigItem> entry: Tags.entrySet()) {
                ConfigItem value = entry.getValue();
                fileWritter.write("// " + value.ConfigComment + "\n");
                fileWritter.write(value.ConfigKey + "=" + value.ConfigValue + "\n\n");
            }
            fileWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
