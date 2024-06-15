package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ZombiePigmanDropEnum;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.monster.ZombiePigman;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ZombiePigman.class)
public class ZombiePigmanMixin extends MonsterBase {

    public ZombiePigmanMixin(Level arg) {
        super(arg);
    }

    @Override
    protected void getDrops() {
        int var2 = this.rand.nextInt(3);

        if (ZombiePigmanDropEnum.GOLD_SWORD == Config.config.zombiePigmanDropItem) {
            var2 = this.rand.nextInt(2);
        }

        for(int var3 = 0; var3 < var2; ++var3) {
            ItemInstance var1 = null;

            if (ZombiePigmanDropEnum.COOKED_PORKCHOP == Config.config.zombiePigmanDropItem) {
                var1 = new ItemInstance(ItemBase.cookedPorkchop, 1);
            } else if (ZombiePigmanDropEnum.RAW_PORKCHOP == Config.config.zombiePigmanDropItem) {
                var1 = new ItemInstance(ItemBase.rawPorkchop, 1);
            } else if (ZombiePigmanDropEnum.BROWN_MUSHROOM == Config.config.zombiePigmanDropItem) {
                var1 = new ItemInstance(BlockBase.BROWN_MUSHROOM, 1);
            } else if (ZombiePigmanDropEnum.GOLD_SWORD == Config.config.zombiePigmanDropItem) {
                var1 = new ItemInstance(ItemBase.goldSword, 1, rand.nextInt(30));
            } else if (ZombiePigmanDropEnum.BONE_MEAL == Config.config.zombiePigmanDropItem) {
                var1 = new ItemInstance(ItemBase.dyePowder, 1, 15);
            } else if (ZombiePigmanDropEnum.BRICK == Config.config.zombiePigmanDropItem) {
                var1 = new ItemInstance(ItemBase.brick, 1);
            } else {
                /** - Drop nothing */
            }

            if (null != var1) {
                this.dropItem(var1, 0.0F);
            }
        }
    }
}
