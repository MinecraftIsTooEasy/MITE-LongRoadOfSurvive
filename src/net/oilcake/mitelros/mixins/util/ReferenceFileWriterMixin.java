package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.io.File;
import java.io.FileWriter;

import static net.minecraft.StringHelper.formatFloat;

@Mixin(ReferenceFileWriter.class)
public class ReferenceFileWriterMixin {
    @ModifyConstant(method = {
            "writeBlockMaterialFile",
            "writeBlockConstantsFile",
            "writeBlockHardnessFile",
            "writeBlockMetadataFile",
            "writeBlockDissolveTimeFile",
            "writeSilkHarvestFile",
            "writeHarvestLevelFile",
            "writeToolDecayRateFiles",
            "writeToolHarvestEfficiencyFiles",
            "writeBlockOpacityFile",
            "writeIsOpaqueStandardFormCubeFile",
            "writeNormalCubeFile",
            "writeBlockMetadataToSubtypeFile",
            "writeAllowsGrassBeneathFile",
            "writeUseNeighborBrightnessFile",
            "writeBlockRenderTypeFile"
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
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

    @Shadow
    private static String getToolDecayRateString(ItemTool item_tool, ItemStack item_stack, boolean as_subtype) {
        return null;
    }


    @Shadow
    private static String getToolHarvestEfficiencyString(ItemTool item_tool, ItemStack item_stack, boolean as_subtype) {return null;}
}
