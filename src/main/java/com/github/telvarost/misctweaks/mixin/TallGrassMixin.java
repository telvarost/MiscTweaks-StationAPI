package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(TallPlantBlock.class)
class TallGrassMixin extends PlantBlock {

    @Unique
    private int brokenByShears = 0;

    public TallGrassMixin(int i, int j) {
        super(i, j);
    }

    @Override
    public void onPlaced(World arg, int i, int j, int k) {
        arg.setBlockMeta(i, j, k, 1);
    }

    @Override
    public void afterBreak(World arg, PlayerEntity player, int i, int j, int k, int l) {
        if (Config.config.enableShearsCollectTallGrass) {
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

        if (  (Config.config.enableShearsCollectFern)
           && (FabricLoader.getInstance().isModLoaded("bhcreative"))
           && (l == 2)
        ) {
            int fernId = ModHelper.identifierToItemId("bhcreative:fern");
            if (0 < fernId) {
                this.dropStack(arg, i, j, k, new ItemStack(fernId, 1, 0));
            } else {
                this.dropStacks(arg, i, j, k, l);
            }
        } else {
            this.dropStacks(arg, i, j, k, l);
        }
    }

    @Inject(at = @At("RETURN"), method = "getDroppedItemId", cancellable = true)
    public void miscTweaks_getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (!Config.config.enableShearsCollectTallGrass) {
            return;
        }

        if (0 < brokenByShears) {
            cir.setReturnValue(id);
            brokenByShears--;
        }
    }
}

