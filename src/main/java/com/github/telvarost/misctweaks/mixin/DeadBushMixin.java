package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DeadBushBlock.class)
class DeadBushMixin extends PlantBlock {

    @Unique
    private int brokenByShears = 0;

    public DeadBushMixin(int i, int j) {
        super(i, j);
    }

    @Override
    public void afterBreak(World arg, PlayerEntity player, int i, int j, int k, int l) {
        if (Config.config.enableShearsCollectDeadBush) {
            if (  (null != player)
               && (null != player.inventory)
               && (null != player.inventory.getSelectedItem())
               && (Item.SHEARS.id == player.inventory.getSelectedItem().itemId)
            ) {
                player.inventory.getSelectedItem().damage(1, player);
                brokenByShears++;
            }
        }

        player.increaseStat(Stats.MINE_BLOCK[this.id], 1);
        this.dropStacks(arg, i, j, k, l);
    }

    @Inject(at = @At("RETURN"), method = "getDroppedItemId", cancellable = true)
    public void miscTweaks_getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (  (Config.config.enableShearsCollectDeadBush)
           && (0 < brokenByShears)
        ) {
            cir.setReturnValue(id);
            brokenByShears--;
        } else if (Config.config.enableRandomStickDropFromDeadBushes) {
            int itemDropId = random.nextInt(8) == 0 ? Item.STICK.id : -1;
            cir.setReturnValue(itemDropId);
        }
    }
}

