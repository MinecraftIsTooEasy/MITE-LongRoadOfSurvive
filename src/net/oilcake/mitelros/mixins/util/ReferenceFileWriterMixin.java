package net.oilcake.mitelros.mixins.util;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import net.minecraft.*;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import static net.minecraft.StringHelper.formatFloat;

@Mixin(ReferenceFileWriter.class)
public class ReferenceFileWriterMixin {
    @Shadow
    private static String newline;
    @Overwrite
    private static void writeFoodValueFile(File dir) throws Exception
    {
        FileWriter fw = new FileWriter(dir.getPath() + "/food_value.txt");
        StringBuffer sb = new StringBuffer();
        sb.append("Food Value (satiation, nutrition, water, protein, phytonutrients, IR=Insulin Response)" + newline);
        sb.append("----------" + newline);

        for (int i = 0; i < Item.itemsList.length; ++i)
        {
            Item item = Item.getItem(i);

            if (item != null && item.isIngestable(0))
            {
                sb.append("Item[" + i + "] ");
                sb.append(item.getNameForReferenceFile() + ": " + item.getSatiation((EntityPlayer)null) + ", " + item.getNutrition() + ", " + item.getWater());
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
    @Overwrite
    private static void writeItemReachFile(File dir) throws Exception
    {
        FileWriter fw = new FileWriter(dir.getPath() + "/item_reach.txt");
        StringBuffer sb = new StringBuffer();
        sb.append("The player has a base reach of 2.75 vs blocks and 1.5 vs entities.").append(newline).append(newline);
        sb.append("Only items that have a reach bonus are listed.").append(newline).append(newline);
        sb.append("Reach Bonus").append(newline);
        sb.append("-----------").append(newline);
        for (int i = 0; i < Item.itemsList.length; ++i) {
            Item item = Item.getItem(i);
            if (item != null) {
                String name = item.getNameForReferenceFile();
                float reach_bonus = item.getReachBonus();
                if (reach_bonus > 0.0F) {
                    sb.append("Item[").append(i).append("] ");
                    sb.append(name).append(": +").append(formatFloat(reach_bonus, 1, 3));
                    sb.append(newline);
                }
            }
        }
        fw.write(sb.toString());
        fw.close();
    }
//    @Overwrite
//    private static void writeToolHarvestEfficiencyFiles(File dir) throws Exception {
//        dir = new File(dir, "tool_harvest_efficiency");
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//
//        for(int i = 0; i < Item.itemsList.length; ++i) {
//            Item item = Item.getItem(i);
//            if (item instanceof ItemTool) {
//                ItemTool item_tool = (ItemTool)item;
//                if (item_tool.getToolMaterial() == Material.adamantium) {
//                    FileWriter fw = new FileWriter(dir.getPath() + "/" + item_tool.getToolType() + ".txt");
//                    StringBuffer sb = new StringBuffer();
//                    sb.append("Only the blocks that this tool is effective against are listed." + newline + newline);
//                    sb.append("Harvest Efficiency" + newline);
//                    sb.append("------------------" + newline);
//
//                    for(int block_index = 0; block_index < Constant.Extended_Block_ID; ++block_index) {
//                        Block block = Block.blocksList[block_index];
//                        if (block != null && item_tool.isEffectiveAgainstBlock(block, 0)) {
//                            List list = block.getItemStacks();
//                            ItemStack item_stack = (ItemStack)list.get(0);
//                            if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
//                                sb.append(getToolHarvestEfficiencyString(item_tool, item_stack, false));
//                            } else {
//                                Iterator iterator = list.iterator();
//
//                                while(iterator.hasNext()) {
//                                    item_stack = (ItemStack)iterator.next();
//                                    sb.append(getToolHarvestEfficiencyString(item_tool, item_stack, true));
//                                }
//                            }
//                        }
//                    }
//
//                    fw.write(sb.toString());
//                    fw.close();
//                }
//            }
//        }
//
//    }
//    @Overwrite
//    private static void writeToolDecayRateFiles(File dir) throws Exception {
//        dir = new File(dir, "tool_decay_rates");
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//
//        for(int i = 0; i < Item.itemsList.length; ++i) {
//            Item item = Item.getItem(i);
//            if (item instanceof ItemTool) {
//                ItemTool item_tool = (ItemTool)item;
//                if (item_tool.getToolMaterial() == Material.adamantium) {
//                    FileWriter fw = new FileWriter(dir.getPath() + "/" + item_tool.getToolType() + ".txt");
//                    StringBuffer sb = new StringBuffer();
//                    sb.append("Only the blocks that this tool is effective against are listed." + newline + newline);
//                    sb.append("Decay Rate vs Entity" + newline);
//                    sb.append("--------------------" + newline);
//                    sb.append("General Factor: x" + formatFloat(item_tool.getBaseDecayRateForAttackingEntity((ItemStack)null)) + newline);
//                    sb.append("All: " + item_tool.getToolDecayFromAttackingEntity((ItemStack)null, (EntityLiving)null));
//                    sb.append(newline + newline);
//                    sb.append("Decay Rate vs Block" + newline);
//                    sb.append("-------------------" + newline);
//                    sb.append("General Factor: x" + formatFloat(item_tool.getBaseDecayRateForBreakingBlock((Block)null)) + newline);
//
//                    for(int block_index = 0; block_index < 256; ++block_index) {
//                        Block block = Block.blocksList[block_index];
//                        if (block != null && item_tool.isEffectiveAgainstBlock(block, 0)) {
//                            List list = block.getItemStacks();
//                            ItemStack item_stack = (ItemStack)list.get(0);
//                            if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
//                                sb.append(getToolDecayRateString(item_tool, item_stack, false));
//                            } else {
//                                Iterator iterator = list.iterator();
//
//                                while(iterator.hasNext()) {
//                                    item_stack = (ItemStack)iterator.next();
//                                    sb.append(getToolDecayRateString(item_tool, item_stack, true));
//                                }
//                            }
//                        }
//                    }
//
//                    fw.write(sb.toString());
//                    fw.close();
//                }
//            }
//        }
//
//    }
    @Shadow
    private static String getToolDecayRateString(ItemTool item_tool, ItemStack item_stack, boolean as_subtype) {
        return null;
    }

//    @Overwrite
//    private static void writeToolHarvestEfficiencyFiles(File dir) throws Exception {
//        dir = new File(dir, "tool_harvest_efficiency");
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//
//        for(int i = 0; i < Item.itemsList.length; ++i) {
//            Item item = Item.getItem(i);
//            if (item instanceof ItemTool) {
//                ItemTool item_tool = (ItemTool)item;
//                if (item_tool.getToolMaterial() == Material.adamantium) {
//                    FileWriter fw = new FileWriter(dir.getPath() + "/" + item_tool.getToolType() + ".txt");
//                    StringBuffer sb = new StringBuffer();
//                    sb.append("Only the blocks that this tool is effective against are listed." + newline + newline);
//                    sb.append("Harvest Efficiency" + newline);
//                    sb.append("------------------" + newline);
//
//                    for(int block_index = 0; block_index < 256; ++block_index) {
//                        Block block = Block.blocksList[block_index];
//                        if (block != null && item_tool.isEffectiveAgainstBlock(block, 0)) {
//                            List list = block.getItemStacks();
//                            ItemStack item_stack = (ItemStack)list.get(0);
//                            if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
//                                sb.append(getToolHarvestEfficiencyString(item_tool, item_stack, false));
//                            } else {
//                                Iterator iterator = list.iterator();
//
//                                while(iterator.hasNext()) {
//                                    item_stack = (ItemStack)iterator.next();
//                                    sb.append(getToolHarvestEfficiencyString(item_tool, item_stack, true));
//                                }
//                            }
//                        }
//                    }
//                    fw.write(sb.toString());
//                    fw.close();
//                }
//            }
//        }
//    }
    @Shadow
    private static String getToolHarvestEfficiencyString(ItemTool item_tool, ItemStack item_stack, boolean as_subtype) {return null;}
}
