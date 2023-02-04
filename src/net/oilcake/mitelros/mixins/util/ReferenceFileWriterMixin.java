package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.FileWriter;

@Mixin(ReferenceFileWriter.class)
public class ReferenceFileWriterMixin {
    private static void writeFoodValueFile(File dir) throws Exception
    {
        FileWriter fw = new FileWriter(dir.getPath() + "/food_value.txt");
        StringBuffer sb = new StringBuffer();
        sb.append("Food Value (satiation, nutrition, protein, phytonutrients, IR=Insulin Response)" + newline);
        sb.append("----------" + newline);

        for (int i = 0; i < Item.itemsList.length; ++i)
        {
            Item item = Item.getItem(i);

            if (item != null && item.isIngestable(0))
            {
                sb.append("Item[" + i + "] ");
                sb.append(item.getNameForReferenceFile() + ": " + item.getSatiation((EntityPlayer)null) + ", " + item.getNutrition() + "," + item.getWater());
                sb.append(", " + item.getProtein() + ", " + item.getPhytonutrients());
                int insulin_response = item.getInsulinResponse();

                if (insulin_response > 0)
                {
                    sb.append(" IR=" + insulin_response);
                }

                sb.append(newline);
            }
        }

        fw.write(sb.toString());
        fw.close();
    }
    @Shadow
    private static String newline;
//    @Overwrite
//    private static void writePlayerLevelsFile(File dir) throws Exception {
//        FileWriter fw = new FileWriter(dir.getPath() + "/player_levels.txt");
//        StringBuffer sb = new StringBuffer();
//        sb.append("The modifier shown in the last column applies to harvesting speed, melee damage, and crafting time reduction." + newline + newline);
//        sb.append("Level          Experience          Stats          Harvesting          Crafting          Melee Dmg" + newline);
//        sb.append("-----          ----------          -----          ----------          --------          ---------" + newline);
//
//        for(int level = -40; level <= EntityPlayer.getHighestPossibleLevel(); ++level) {
//            String line = new String();
//            line = line + level;
//            line = line + StringHelper.repeat(" ", 15 - line.length());
//            line = line + EntityPlayer.getExperienceRequired(level);
//            line = line + StringHelper.repeat(" ", 35 - line.length());
//            line = line + EntityPlayer.getHealthLimit(level);
//            line = line + StringHelper.repeat(" ", 50 - line.length());
//            line = line + EntityPlayer
//            line = line + StringHelper.repeat(" ", 60 - line.length());
//            int level_modifier = Math.round(EntityPlayer.getLevelModifier(level, EnumLevelBonus.HARVESTING) * 100.0F);
//            if (level_modifier > 0) {
//                line = line + "+";
//            }
//
//            line = line + level_modifier + "%";
//            line = line + StringHelper.repeat(" ", 70 - line.length());
//            level_modifier = Math.round(EntityPlayer.getLevelModifier(level, EnumLevelBonus.CRAFTING) * 100.0F);
//            if (level_modifier > 0) {
//                line = line + "+";
//            }
//
//            line = line + level_modifier + "%";
//            line = line + StringHelper.repeat(" ", 88 - line.length());
//            level_modifier = Math.round(EntityPlayer.getLevelModifier(level, EnumLevelBonus.MELEE_DAMAGE) * 100.0F);
//            if (level_modifier > 0) {
//                line = line + "+";
//            }
//
//            line = line + level_modifier + "%";
//            sb.append(line);
//            sb.append(newline);
//        }
//
//        fw.write(sb.toString());
//        fw.close();
//    }
}
