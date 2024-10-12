package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ZombiePigmanDropEnum;
import net.minecraft.block.Block;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PigZombieEntity.class)
public class ZombiePigmanMixin extends MonsterEntity {

    public ZombiePigmanMixin(World arg) {
        super(arg);
    }

    @Override
    protected void dropItems() {
        int var2 = this.random.nextInt(3);

        if (ZombiePigmanDropEnum.GOLD_SWORD == Config.config.zombiePigmanDropItem) {
            var2 = this.random.nextInt(2);
        }

        for(int var3 = 0; var3 < var2; ++var3) {
            ItemStack var1 = null;

            if (ZombiePigmanDropEnum.COOKED_PORKCHOP == Config.config.zombiePigmanDropItem) {
                var1 = new ItemStack(Item.COOKED_PORKCHOP, 1);
            } else if (ZombiePigmanDropEnum.RAW_PORKCHOP == Config.config.zombiePigmanDropItem) {
                var1 = new ItemStack(Item.RAW_PORKCHOP, 1);
            } else if (ZombiePigmanDropEnum.BROWN_MUSHROOM == Config.config.zombiePigmanDropItem) {
                var1 = new ItemStack(Block.BROWN_MUSHROOM, 1);
            } else if (ZombiePigmanDropEnum.GOLD_SWORD == Config.config.zombiePigmanDropItem) {
                var1 = new ItemStack(Item.GOLDEN_SWORD, 1, random.nextInt(30));
            } else if (ZombiePigmanDropEnum.BONE_MEAL == Config.config.zombiePigmanDropItem) {
                var1 = new ItemStack(Item.DYE, 1, 15);
            } else if (ZombiePigmanDropEnum.BRICK == Config.config.zombiePigmanDropItem) {
                var1 = new ItemStack(Item.BRICK, 1);
            } else {
                /** - Drop nothing */
            }

            if (null != var1) {
                this.dropItem(var1, 0.0F);
            }
        }
    }
}
