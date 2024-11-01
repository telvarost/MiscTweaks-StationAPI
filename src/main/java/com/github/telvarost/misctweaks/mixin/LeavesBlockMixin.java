package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends TransparentBlock {

    public LeavesBlockMixin(int i, int j) {
        super(i, j, Material.LEAVES, false);
    }

    @Inject(
            method = "onTick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void miscTweaks_onScheduledTick(World level, int x, int y, int z, Random random, CallbackInfo ci) {
        if (Config.config.enablePlayerPlacedLeafPersistence) {
            if (!level.isRemote) {
                int tileMeta = level.getBlockMeta(x, y, z);
                if (0x4 == (0x4 & tileMeta)) {
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "breakLeaves", at = @At("HEAD"), cancellable = true)
    private void miscTweaks_dropAndRemove(World arg, int i, int j, int k, CallbackInfo ci) {
        if (0 >= Config.config.appleDropChance) {
            return;
        }

        /** - Special drop logic */
        int l = arg.getBlockMeta(i, j, k);
        miscTweaks_rareAppleDrop(arg, i, j, k, (l & 3));
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_afterBreak(World arg, PlayerEntity arg2, int i, int j, int k, int l, CallbackInfo ci) {
        if (0 >= Config.config.appleDropChance) {
            return;
        }

        if (!arg.isRemote && arg2.getHand() != null && arg2.getHand().itemId == Item.SHEARS.id) {
            /** - Do nothing */
        } else {
            /** - Special drop logic */
            miscTweaks_rareAppleDrop(arg, i, j, k, (l & 3));
        }
    }

    @Unique
    private void miscTweaks_rareAppleDrop(World arg, int i, int j, int k, int leafType) {
        if (0 == leafType) {
            Random random = new Random();
            boolean isAppleDropped = (random.nextInt(1000/Config.config.appleDropChance) == 0) ? true : false;

            if (isAppleDropped) {
                ItemStack arg2 = new ItemStack(Item.APPLE);

                if (!arg.isRemote) {
                    float var6 = 0.7F;
                    double var7 = (double) (arg.random.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    double var9 = (double) (arg.random.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    double var11 = (double) (arg.random.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    ItemEntity var13 = new ItemEntity(arg, (double) i + var7, (double) j + var9, (double) k + var11, arg2);
                    var13.pickupDelay = 10;
                    arg.spawnEntity(var13);
                }
            }
        }
    }

}
