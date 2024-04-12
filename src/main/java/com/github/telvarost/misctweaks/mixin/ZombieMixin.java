package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ZombieDropEnum;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.monster.Zombie;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Zombie.class)
public class ZombieMixin extends MonsterBase {

    public ZombieMixin(Level arg) {
        super(arg);
    }

    @Override
    protected void getDrops() {
        int var2 = this.rand.nextInt(3);

        for(int var3 = 0; var3 < var2; ++var3) {
            ItemInstance var1 = null;

            if (ZombieDropEnum.FEATHER == Config.config.zombieDropItem) {
                var1 = new ItemInstance(ItemBase.feather, 1);
            } else if (ZombieDropEnum.RED_MUSHROOM == Config.config.zombieDropItem) {
                var1 = new ItemInstance(BlockBase.RED_MUSHROOM, 1);
            } else if (ZombieDropEnum.CYAN_DYE == Config.config.zombieDropItem) {
                var1 = new ItemInstance(ItemBase.dyePowder, 1, 6);
            } else if (ZombieDropEnum.GREEN_DYE == Config.config.zombieDropItem) {
                var1 = new ItemInstance(ItemBase.dyePowder, 1, 2);
            } else if (ZombieDropEnum.CLAY == Config.config.zombieDropItem) {
                var1 = new ItemInstance(ItemBase.clay, 1);
            } else if (ZombieDropEnum.PAPER == Config.config.zombieDropItem) {
                var1 = new ItemInstance(ItemBase.paper, 1);
            } else {
                /** - Drop nothing */
            }

            if (null != var1) {
                this.dropItem(var1, 0.0F);
            }
        }
    }
}
