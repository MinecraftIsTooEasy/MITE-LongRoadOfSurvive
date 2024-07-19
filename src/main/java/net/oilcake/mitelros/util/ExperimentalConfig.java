package net.oilcake.mitelros.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExperimentalConfig {
   public static Map Tags = new HashMap();

   public static void loadConfigs() {
      System.out.println("Experimental settings were put in HASHMAP");
      Tags.put("CreaturesV2", ExperimentalConfig.TagConfig.TagCreaturesV2);
      Tags.put("SpawningV2", ExperimentalConfig.TagConfig.TagSpawningV2);
      Tags.put("BenchingV2", ExperimentalConfig.TagConfig.TagBenchingV2);
      Tags.put("FinalChallenge", ExperimentalConfig.TagConfig.FinalChallenge);
      Tags.put("Realistic", ExperimentalConfig.TagConfig.Realistic);
      Tags.put("MovingV2", ExperimentalConfig.TagConfig.TagMovingV2);
      Tags.put("NewRenderPlayer", ExperimentalConfig.TagConfig.TagNewRenderPlayer);
      Tags.put("TorchExtinguish", ExperimentalConfig.TagConfig.TagTorchExtinguish);
      String filePth = "ExperimentalOption.cfg";
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
            FileNotFoundException var6 = var6;
            System.out.println("READING FAILED TP1");
            var6.printStackTrace();
         } catch (IOException var7) {
            IOException var7 = var7;
            System.out.println("READING FAILED TP2");
            var7.printStackTrace();
         }
      } else {
         System.out.println("GENERATING SETTINGS FILE");

         try {
            if (file_mite.createNewFile()) {
               file_mite.setExecutable(true);
               file_mite.setReadable(true);
               file_mite.setWritable(true);
               generateConfigFile(file_mite);
            }
         } catch (IOException var5) {
            IOException e = var5;
            System.out.println("GENERATING FAILED");
            e.printStackTrace();
            JFrame jFrame = new JFrame();
            jFrame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jFrame, "实验性玩法读取失败，尝试删除配置文件……", "错误", 0);
            System.exit(0);
         }
      }

   }

   public static void readConfigFromFile(File file_mite, Properties properties) {
      Iterator var2 = properties.stringPropertyNames().iterator();

      while(var2.hasNext()) {
         String key = (String)var2.next();
         ConfigItem configItem = (ConfigItem)Tags.get(key);
         if (configItem != null) {
            if (configItem.ConfigValue instanceof Boolean) {
               configItem.setConfigValue(Boolean.parseBoolean(properties.getProperty(key)));
            } else if (configItem.ConfigValue instanceof Float) {
               float value = Float.parseFloat(properties.getProperty(key));
               if (configItem.isNeedCompare) {
                  value = value > (Float)configItem.max ? (Float)configItem.max : Math.max(value, (Float)configItem.min);
               }

               configItem.setConfigValue(value);
            } else if (configItem.ConfigValue instanceof Double) {
               double value = Double.parseDouble(properties.getProperty(key));
               if (configItem.isNeedCompare) {
                  value = value > (Double)configItem.max ? (Double)configItem.max : Math.max(value, (Double)configItem.min);
               }

               configItem.setConfigValue(value);
            } else if (configItem.ConfigValue instanceof Integer) {
               int value = Integer.parseInt(properties.getProperty(key));
               if (configItem.isNeedCompare) {
                  value = value > (Integer)configItem.max ? (Integer)configItem.max : Math.max(value, (Integer)configItem.min);
               }

               configItem.setConfigValue(value);
            } else {
               configItem.setConfigValue(properties.getProperty(key));
            }
         }
      }

   }

   public static void packConfigFile(File file, Properties properties) {
      try {
         FileWriter fileWritter = new FileWriter(file.getName(), true);
         Iterator var3 = Tags.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry entry = (Map.Entry)var3.next();
            ConfigItem value = (ConfigItem)entry.getValue();
            String localValue = properties.getProperty(value.ConfigKey);
            if (localValue == null) {
               fileWritter.write("// " + value.ConfigComment + "\n");
               fileWritter.write(value.ConfigKey + "=" + value.ConfigValue + "\n\n");
            }
         }

         fileWritter.close();
      } catch (IOException var7) {
         IOException e = var7;
         e.printStackTrace();
      }

   }

   public static void generateConfigFile(File file) {
      try {
         FileWriter fileWritter = new FileWriter(file.getName());
         fileWritter.write("// 在每一项配置后填入true或者false来选择，不建议在游玩中途更改设置 \n");
         Iterator var2 = Tags.entrySet().iterator();

         while(var2.hasNext()) {
            Map.Entry entry = (Map.Entry)var2.next();
            ConfigItem value = (ConfigItem)entry.getValue();
            fileWritter.write("// " + value.ConfigComment + "\n");
            fileWritter.write(value.ConfigKey + "=" + value.ConfigValue + "\n\n");
         }

         fileWritter.close();
      } catch (IOException var5) {
         IOException e = var5;
         e.printStackTrace();
      }

   }

   public static class TagConfig {
      public static ConfigItem TagCreaturesV2 = new ConfigItem("CreaturesV2", true, "新动物生成机制");
      public static ConfigItem TagSpawningV2 = new ConfigItem("SpawningV2", true, "新怪物生成频率");
      public static ConfigItem TagBenchingV2 = new ConfigItem("BenchingV2", false, "工作站废料回收");
      public static ConfigItem FinalChallenge = new ConfigItem("FinalChallenge", false, "终极挑战模式");
      public static ConfigItem Realistic = new ConfigItem("Realistic", false, "真实状态模拟");
      public static ConfigItem TagMovingV2 = new ConfigItem("MovingV2", true, "新移动模式");
      public static ConfigItem TagNewRenderPlayer = new ConfigItem("NewRenderPlayer", false, "1.8玩家模型");
      public static ConfigItem TagTorchExtinguish = new ConfigItem("TorchExtinguish", false, "火把自然熄灭");
   }

   public static class ConfigItem {
      public String ConfigKey;
      public Object ConfigValue;
      public Object min;
      public Object max;
      public boolean isNeedCompare = false;
      public String ConfigComment;

      ConfigItem(String key, Object value, String comment) {
         this.ConfigKey = key;
         this.ConfigValue = value;
         this.ConfigComment = comment;
      }

      ConfigItem(String key, Object value, String comment, Object min, Object max) {
         this.ConfigKey = key;
         this.ConfigValue = value;
         this.isNeedCompare = true;
         this.min = min;
         this.max = max;
         this.ConfigComment = comment + " [范围：" + min + "-" + max + "]";
      }

      public void setConfigValue(Object configValue) {
         this.ConfigValue = configValue;
      }

      public Object getConfigValue() {
         return this.ConfigValue;
      }
   }
}
