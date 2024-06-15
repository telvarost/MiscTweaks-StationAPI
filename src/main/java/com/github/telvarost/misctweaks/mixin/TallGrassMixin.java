package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Plant;
import net.minecraft.block.TallGrass;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.stat.Stats;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(TallGrass.class)
class TallGrassMixin extends Plant {

    @Unique
    private int brokenByShears = 0;

    public TallGrassMixin(int i, int j) {
        super(i, j);
    }

    @Override
    public void onBlockPlaced(Level arg, int i, int j, int k) {
        arg.setTileMeta(i, j, k, 1);
    }

    @Override
    public void afterBreak(Level arg, PlayerBase player, int i, int j, int k, int l) {
        if (Config.config.enableShearsCollectTallGrass) {
            if (  (null != player)
               && (null != player.inventory)
               && (null != player.inventory.getHeldItem())
               && (ItemBase.shears.id == player.inventory.getHeldItem().itemId)
            ) {
                player.inventory.getHeldItem().applyDamage(1, player);
                brokenByShears++;
            }
        }

        player.increaseStat(Stats.mineBlock[this.id], 1);

        if (  (Config.config.enableShearsCollectFern)
           && (FabricLoader.getInstance().isModLoaded("bhcreative"))
           && (l == 2)
        ) {
            int fernId = ModHelper.identifierToItemId("bhcreative:fern");
            if (0 < fernId) {
                this.drop(arg, i, j, k, new ItemInstance(fernId, 1, 0));
            } else {
                this.drop(arg, i, j, k, l);
            }
        } else {
            this.drop(arg, i, j, k, l);
        }
    }

    @Inject(at = @At("RETURN"), method = "getDropId", cancellable = true)
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

