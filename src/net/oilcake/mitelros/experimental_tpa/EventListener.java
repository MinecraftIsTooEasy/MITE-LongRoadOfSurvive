//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.oilcake.mitelros.experimental_tpa;

import com.google.common.eventbus.Subscribe;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import net.minecraft.EntityPlayer;
import net.minecraft.ICommandListener;
import net.minecraft.World;
import net.xiaoyu233.fml.reload.event.HandleChatCommandEvent;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

public class EventListener {
    public boolean isP;
    public boolean isW;
    public boolean isTIME;
    public boolean isAdmin;
    public boolean isWOpen;
    public static String G;
    public static String MODs;
    public static String TPAYorN;
    public static String TPAHOME;
    public static String TPABACK;
    public static String TPATIME;
    public static String TPALIST;
    public static String TPACONGFIG;

    public EventListener() {
    }

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        event.getPlayer().addChatMessage("§c请不要在该整合包中加载任何TPAmod!");
        event.getPlayer().addChatMessage("§9可用指令：/tpaccept , /tpdeny , /tpa [playername]");
        event.getPlayer().addChatMessage("§9管理员可用指令：/tpadd [playername]");
    }

    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {
        String par2Str = event.getCommand();
        EntityPlayer player = event.getPlayer();
        ICommandListener par1ICommandSender = event.getListener();
        World world = event.getWorld();
        EntityPlayer MMPlayer = event.getPlayer();
        String value;
        String Name;
        EntityPlayer playerTemp;
        if (par2Str.startsWith("tpa ")) {
            System.out.println(par2Str);
            value = par2Str.substring(4);
            Name = GetValueByKey(TPAYorN, value);
            if (Name == null) {
                this.isP = false;
            } else {
                this.isP = Name.equals("1");
            }

            this.isW = getWhiteList(MMPlayer.getEntityName());
            this.isWOpen = getWhiteListOpen();
            Iterator<EntityPlayer> i = world.getAsWorldServer().p().getConfigurationManager().playerEntityList.iterator();
            List<String> PlayerList = new ArrayList();
            EntityPlayer TPer = null;

            while(i.hasNext()) {
                playerTemp = (EntityPlayer)i.next();
                PlayerList.add(playerTemp.getEntityName());
                if (playerTemp.getEntityName().equals(value)) {
                    TPer = playerTemp;
                }
            }

            double poses0 = 0.5;
            double poses1 = 0.5;
            double poses2 = 0.5;
            if (TPer == null) {
                MMPlayer.addChatMessage("[Server]:§c 无法找到名为§r " + value + "§r §c的在线玩家§r");
            } else {
                long timeNow = getSystemTime();
                long timeYet;
                if (GetValueByKey(TPATIME, MMPlayer.getEntityName()) != null) {
                    timeYet = Long.parseLong(GetValueByKey(TPATIME, MMPlayer.getEntityName()));
                } else {
                    timeYet = 0L;
                }

                if (timeNow - timeYet <= 3000L) {
                    if (timeNow - timeYet < getTimeTick()) {
                        this.isTIME = false;
                    } else {
                        this.isTIME = true;
                    }
                } else {
                    this.isTIME = true;
                }

                if (!this.isTIME) {
                    MMPlayer.addChatMessage("[Server]:§6传送冷却中，还需 §r" + (getTimeTick() - (timeNow - timeYet)) + " §6秒§r");
                } else if (this.isW && this.isWOpen) {
                    MMPlayer.addChatMessage("[Server]:§4你没有使用该命令的权限§r");
                } else if (value.equals(MMPlayer.getEntityName())) {
                    MMPlayer.addChatMessage("[Server]:§c 无法以自己为目标§r");
                } else if (this.isP) {
                    String Location = MMPlayer.posX + " " + MMPlayer.posY + " " + MMPlayer.posZ + " " + MMPlayer.getWorld().getDimensionId();

                    try {
                        WriteProperties(TPABACK, MMPlayer.getEntityName(), Location);
                    } catch (IOException var42) {
                        var42.printStackTrace();
                    }

                    long timeStamp = getSystemTime();

                    try {
                        WriteProperties(TPATIME, MMPlayer.getEntityName(), String.valueOf(timeStamp));
                    } catch (IOException var41) {
                        var41.printStackTrace();
                    }

                    int worldID = 0;
                    if (TPer.getWorld().isOverworld()) {
                        worldID = 0;
                    } else if (TPer.getWorld().isUnderworld()) {
                        worldID = -2;
                    } else if (TPer.getWorld().isTheNether()) {
                        worldID = -1;
                    } else if (TPer.getWorld().isTheEnd()) {
                        worldID = 1;
                    }

                    if (world.provider.dimensionId != worldID) {
                        poses0 = TPer.posX;
                        poses1 = TPer.posY;
                        poses2 = TPer.posZ;
                        player.travelToDimension(worldID);
                        player.setPositionAndUpdate(poses0, poses1, poses2);
                        MMPlayer.addChatMessage("§7§o[Server]:已传送至玩家§r §6§o" + value + "§r");
                        TPer.addChatMessage("§7§o[Server]:玩家§r §6§o" + MMPlayer.getEntityName() + " §r§7§o已传送到你的位置§r");
                    } else {
                        poses0 = TPer.posX;
                        poses1 = TPer.posY;
                        poses2 = TPer.posZ;
                        player.setPositionAndUpdate(poses0, poses1, poses2);
                        MMPlayer.addChatMessage("§7§o[Server]:已传送至玩家§r §6§o" + value + "§r");
                        TPer.addChatMessage("§7§o[Server]:玩家§r §6§o" + MMPlayer.getEntityName() + " §r§7§o已传送到你的位置§r");
                    }
                } else {
                    MMPlayer.addChatMessage("[Server]:§b 玩家§r §6§l" + value + "§r §b已自动拒绝传送§r");
                    TPer.addChatMessage("§7§o[Server]:玩家§r §6§o" + MMPlayer.getEntityName() + " §r§7§o想要传送到你这里，输入§9/tpayes§r§7§o自动接受所有传送§r");
                }
            }

            event.setExecuteSuccess(true);
        }

        File file_tpa;
        if (par2Str.startsWith("tpaccept")) {
            file_tpa = new File(TPAYorN);
            file_tpa.setWritable(true, false);
            if (!file_tpa.exists()) {
                try {
                    file_tpa.createNewFile();
                } catch (IOException var40) {
                    var40.printStackTrace();
                }
            }

            Name = MMPlayer.getEntityName();

            try {
                WriteProperties(TPAYorN, Name, "1");
            } catch (IOException var39) {
                var39.printStackTrace();
            }

            MMPlayer.addChatMessage("[Server]:§6目前您将自动接受传送请求§r");
            event.setExecuteSuccess(true);
        }

        if (par2Str.startsWith("tpdeny")) {
            file_tpa = new File(TPAYorN);
            file_tpa.setWritable(true, false);
            if (!file_tpa.exists()) {
                try {
                    file_tpa.createNewFile();
                } catch (IOException var38) {
                    var38.printStackTrace();
                }
            }

            Name = MMPlayer.getEntityName();

            try {
                WriteProperties(TPAYorN, Name, "0");
            } catch (IOException var37) {
                var37.printStackTrace();
            }

            MMPlayer.addChatMessage("[Server]:§6目前您将自动拒绝传送请求§r");
            event.setExecuteSuccess(true);
        }

        String Somebody;
        long timeNow;
        long timeYet;
        if (par2Str.startsWith("tpaadd ")) {
            value = MMPlayer.getEntityName();
            Name = GetValueByKey(TPACONGFIG, "admin");
            if (Name == null) {
                this.isAdmin = false;
            } else {
                this.isAdmin = Name.equals(MMPlayer.getEntityName());
            }

            if (!this.isAdmin) {
                MMPlayer.addChatMessage("[Server]:§6您不是管理员，无法使用此命令§r");
            } else {
                Somebody = par2Str.substring(7);
                if (!Somebody.equals("@a")) {
                    try {
                        WriteProperties(TPALIST, Somebody, Somebody);
                    } catch (IOException var29) {
                        var29.printStackTrace();
                    }

                    MMPlayer.addChatMessage("[Server]:白名单已添加玩家： §6§l" + Somebody + "§r");
                } else {
                    Iterator<EntityPlayer> i = world.getAsWorldServer().p().getConfigurationManager().playerEntityList.iterator();

                    int count;
                    for(count = 0; i.hasNext(); ++count) {
                        playerTemp = (EntityPlayer)i.next();

                        try {
                            WriteProperties(TPALIST, playerTemp.getEntityName(), playerTemp.getEntityName());
                        } catch (IOException var28) {
                            var28.printStackTrace();
                        }
                    }

                    MMPlayer.addChatMessage("[Server]:白名单已添加： §6§l" + count + "§r 名玩家");
                }
            }

            event.setExecuteSuccess(true);
        }
    }

    public static void WriteNullFile() {
        File file = new File(TPALIST);
        file.setWritable(true, false);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write("#");
            fw.close();
        } catch (Exception var2) {
        }

    }

    public static long getSystemTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public static long getTimeTick() {
        String timeRead = GetValueByKey(TPACONGFIG, "time");
        long TIMETICK;
        if (timeRead != null) {
            TIMETICK = Long.parseLong(timeRead);
            if (TIMETICK < 3L || TIMETICK > 3000L) {
                TIMETICK = 30L;
            }
        } else {
            TIMETICK = 30L;
        }

        return TIMETICK;
    }

    public static boolean getWhiteList(String PName) {
        String white = GetValueByKey(TPALIST, PName);
        return white == null;
    }

    public static boolean getWhiteListOpen() {
        String white = GetValueByKey(TPACONGFIG, "whitelist");
        if (white == null) {
            return true;
        } else {
            return !white.equals("0");
        }
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

    public static void GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames();

        while(en.hasMoreElements()) {
            String strKey = (String)en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }

    }

    public static void WriteProperties(String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        InputStream in = new FileInputStream(filePath);
        pps.load(in);
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        pps.store(out, "Update " + pKey + " name");
    }

    static {
        G = File.separator;
        MODs = "configs";
        TPAYorN = MODs + G + "TPA" + G + "tpaYorN.ga";
        TPAHOME = MODs + G + "TPA" + G + "tpaHome.ga";
        TPABACK = MODs + G + "TPA" + G + "tpaBack.ga";
        TPATIME = MODs + G + "TPA" + G + "tpaTime.ga";
        TPALIST = MODs + G + "TPA" + G + "tpaList.ini";
        TPACONGFIG = MODs + G + "TPA" + G + "tpaConfig.ini";
    }
}
