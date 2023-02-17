package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.FileWriter;

@Mixin(ReferenceFileWriter.class)
public class ReferenceFileWriterMixin {
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
    @Shadow
    private static String newline;
}
