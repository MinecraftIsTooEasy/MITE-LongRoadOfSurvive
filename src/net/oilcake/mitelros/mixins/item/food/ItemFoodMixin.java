package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(ItemFood.class)
public class ItemFoodMixin extends Item {
    @Inject(method = "<init>(ILnet/minecraft/Material;IIZZZLjava/lang/String;)V", at = @At("RETURN"))
    private void injectInit(int id, Material material, int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture,CallbackInfo callbackInfo){
        this.setWater(resetWaterVal(id,material));
    }
    @Inject(method = "<init>(ILnet/minecraft/Material;IIIZZZLjava/lang/String;)V", at = @At("RETURN"))
    private void injectInit(int id, Material material, int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture,CallbackInfo callbackInfo){
        this.setWater(resetWaterVal(id,material));
    }

    public int resetWaterVal(int id,Material material){
            if (material == Material.fruit) {
                return (StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 1 : 3);
            }else if (id == 135) {
                return (StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 1 : 2);
            }else if (material == Materials.glowberries) {
                return (1);
            }else if(material == Material.cheese || id == 88){
                return (-1);
            }else if(material == (Material.bread) || material == (Material.desert)){
                return (-1);
            }else if(material == (Materials.agave)){
                return (1);
            }else{
                return (0);
            }
    }

    @Shadow
    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if(this.itemID==rottenFlesh.itemID){
                player.addPotionEffect((new MobEffect(MobEffectList.confusion.id, 600, 0)));
            }
            if(this.hasMaterial(Material.bread) || this.hasMaterial(Material.desert)){
                player.addPotionEffect((new MobEffect(PotionExtend.thirsty.id, 1280, 0)));
            }
            if(this.hasMaterial(Materials.glowberries)){
                Random rand = new Random();
                if(rand.nextDouble()>(StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 0.5 : 1)){
                    player.addWater(-1);
                }
            }
            if(this.hasMaterial(Materials.agave)){
                Random rand = new Random();
                if(rand.nextDouble()>(StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 0.2 : 0.4)){
                    player.addWater(-1);
                }
            }
            if(this.itemID == Item.egg.itemID){
                Random rand = new Random();
                if(rand.nextDouble()>(StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 0.5 : 0.25)){
                    player.addWater(1);
                }
            }
            player.addFoodValue(this);
            world.playSoundAtEntity(player, "random.burp", 0.5F, player.getRand().nextFloat() * 0.1F + 0.9F);
            this.onEaten(item_stack, world, player);
        }

        super.onItemUseFinish(item_stack, world, player);
    }
//    @Inject(method = "<init>",at = @At("RETURN"))
//    private void injectInit(CallbackInfo callback){
//        if (this.hasMaterial(Material.fruit)) {
//            this.setWater(2);
//        }else if(this.hasMaterial(Material.bread)){
//            this.setWater(-2);
//        }else if(this.hasMaterial(Material.desert)){
//            this.setWater(-2);
//        }else{
//            this.setWater(0);
//        }
//    }

}
