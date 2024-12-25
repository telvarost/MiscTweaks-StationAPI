package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ZombieDropEnum;
import net.minecraft.block.Block;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ZombieEntity.class)
public class ZombieMixin extends MonsterEntity {

    public ZombieMixin(World arg) {
        super(arg);
    }

    @Override
    protected void dropItems() {
        int var2 = this.random.nextInt(3);

        for(int var3 = 0; var3 < var2; ++var3) {
            ItemStack var1 = null;

            if (ZombieDropEnum.FEATHER == Config.config.MOB_CONFIG.zombieDropItem) {
                var1 = new ItemStack(Item.FEATHER, 1);
            } else if (ZombieDropEnum.RED_MUSHROOM == Config.config.MOB_CONFIG.zombieDropItem) {
                var1 = new ItemStack(Block.RED_MUSHROOM, 1);
            } else if (ZombieDropEnum.CYAN_DYE == Config.config.MOB_CONFIG.zombieDropItem) {
                var1 = new ItemStack(Item.DYE, 1, 6);
            } else if (ZombieDropEnum.GREEN_DYE == Config.config.MOB_CONFIG.zombieDropItem) {
                var1 = new ItemStack(Item.DYE, 1, 2);
            } else if (ZombieDropEnum.CLAY == Config.config.MOB_CONFIG.zombieDropItem) {
                var1 = new ItemStack(Item.CLAY, 1);
            } else if (ZombieDropEnum.PAPER == Config.config.MOB_CONFIG.zombieDropItem) {
                var1 = new ItemStack(Item.PAPER, 1);
            } else {
                /** - Drop nothing */
            }

            if ((Object)this instanceof PigZombieEntity) {
                var1 = new ItemStack(Item.COOKED_PORKCHOP, 1);
            }

            if (null != var1) {
                this.dropItem(var1, 0.0F);
            }
        }
    }
}
