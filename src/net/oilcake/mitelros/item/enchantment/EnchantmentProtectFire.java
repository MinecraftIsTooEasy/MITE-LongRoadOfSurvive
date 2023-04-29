//package net.oilcake.mitelros.item.enchantment;
//
//import net.minecraft.*;
//
//import java.util.Random;
//
//import static net.minecraft.EnchantmentManager.getMaxEnchantmentLevel;
//
//public class EnchantmentProtectFire extends Enchantment {
//    protected EnchantmentProtectFire(int id, yq rarity, int difficulty) {
//        super(id, rarity, difficulty);
//    }
//    @Override
//    public int getNumLevels() {
//        return 5;
//    }
//    @Override
//    public boolean canApplyTogether(Enchantment par1Enchantment) {
//        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId == fireProtection.effectId;
//    }
//    @Override
//    public String getNameSuffix() {
//        return "protectofnether";
//    }
//
//    @Override
//    public boolean canEnchantItem(Item item) {
//        return item instanceof ItemArmor;
//    }
//
//    @Override
//    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
//        return creativeModeTab == CreativeModeTab.tabCombat;
//    }
//    public static boolean getChanceProtect(int par0, Random par1Random)
//    {
//        return par0 > 0 && par1Random.nextFloat() <= 0.2F * (float) par0;
//    }
//    public static void PerformProtect(Entity par0Entity, EntityLiving par1EntityLivingBase, Random par2Random) {
//        if (par1EntityLivingBase.onClient() && (Minecraft.O.h == null || !Minecraft.O.h.isMITEmigo())) {
//            Minecraft.setErrorMessage("Thorns.func_92096_a: called on client? (" + (par0Entity == null ? "null" : par0Entity.getEntityName()) + " vs " + (par1EntityLivingBase == null ? "null" : par1EntityLivingBase.getEntityName()) + ", " + Minecraft.temp_debug + ")");
//        }
//        int var3 = getMaxEnchantmentLevel(Enchantments.enchantmentProtectFire.effectId, par1EntityLivingBase.getInventory());
//        ItemStack var4 = EnchantmentManager.func_92099_a(Enchantments.enchantmentProtectFire, par1EntityLivingBase);
//        if (getChanceProtect(var3, par2Random)) {
//            if(par0Entity != null){
//                par0Entity.setFire(200);
//            }
//            if (var4 != null) {
//                var4.tryDamageItem(DamageSource.generic, 3, par1EntityLivingBase);
//            }
//        } else if (var4 != null) {
//            var4.tryDamageItem(DamageSource.generic, 1, par1EntityLivingBase);
//        }
//
//    }
//}
