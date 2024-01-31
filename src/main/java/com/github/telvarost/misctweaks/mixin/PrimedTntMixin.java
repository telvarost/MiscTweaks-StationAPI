//package com.github.telvarost.misctweaks.mixin;
//
//import net.minecraft.block.BlockBase;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.EntityBase;
//import net.minecraft.entity.Item;
//import net.minecraft.entity.PrimedTnt;
//import net.minecraft.entity.player.PlayerBase;
//import net.minecraft.item.ItemBase;
//import net.minecraft.item.ItemInstance;
//import net.minecraft.level.Level;
//import org.spongepowered.asm.mixin.Mixin;
//
//@Mixin(PrimedTnt.class)
//abstract class PrimedTntMixin extends EntityBase {
//
//    public PrimedTntMixin(Level arg) {
//        super(arg);
//    }
//
//    @Override
//    public boolean interact(PlayerBase arg) {
//        ItemInstance var2 = arg.inventory.getHeldItem();
//        if (var2 != null && var2.itemId == ItemBase.shears.id) {
//            if (!this.level.isServerSide) {
//
//                Minecraft thisMinecraft = MinecraftAccessor.getInstance();
//                Item var24 = new Item(thisMinecraft.level, this.x, this.y, this.z, new ItemInstance(BlockBase.TNT));
//                var24.velocityY = 0.20000000298023224;
//                thisMinecraft.level.spawnEntity(var24);
//
//                this.remove();
//            }
//        }
//
//        return false;
//    }
//}
