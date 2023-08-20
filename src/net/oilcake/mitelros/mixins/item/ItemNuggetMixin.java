package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemIngot;
import net.minecraft.ItemNugget;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemNugget.class)
public class ItemNuggetMixin extends ItemIngot  {
    protected ItemNuggetMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    private void injectXP(int id, Material material, CallbackInfo callbackInfo){
        if(material == Material.copper || material == Material.silver ||  material == Material.iron ||  material == Materials.nickel){
            this.setXPReward(1);
        } else if (material == Material.gold){
            this.setXPReward(2);
        } else if (material == Material.mithril){
            this.setXPReward(4);
        } else if (material == Materials.tungsten){
            this.setXPReward(7);
        } else if (material == Materials.adamantium){
            this.setXPReward(10);
        } else if (material == Materials.uru){
            this.setXPReward(15);
        }
    }

    @Overwrite
    public ItemNugget getForMaterial(Material material) {
        return (material == Materials.wolf_fur ? (ItemNugget) Items.Wolf_fur :(material == Material.copper ? Item.copperNugget : (material == Material.silver ? Item.silverNugget : (material == Material.gold ? Item.goldNugget : (material == Material.iron ? Item.ironNugget : (material == Material.mithril ? Item.mithrilNugget : (material == Material.adamantium ? Item.adamantiumNugget : (material == Material.ancient_metal ? Item.ancientMetalNugget : (material == Materials.ancient_metal_sacred ? (ItemNugget) Items.AncientmetalArmorPiece :
                (material == Materials.nickel ? Items.nickelNugget :(material == Materials.uru ? Items.UruNugget :(material == Materials.tungsten ? Items.tungstenNugget : null))))))))))));
    }

}
