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

    public LeavesBlockMixin(int id, int textureId) {
        super(id, textureId, Material.LEAVES, false);
    }

    @Inject(
            method = "onTick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void miscTweaks_onScheduledTick(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        if (Config.config.FLORA_CONFIG.enablePlayerPlacedLeafPersistence) {
            if (!world.isRemote) {
                int tileMeta = world.getBlockMeta(x, y, z);
                if (0x4 == (0x4 & tileMeta)) {
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "breakLeaves", at = @At("HEAD"), cancellable = true)
    private void miscTweaks_dropAndRemove(World world, int x, int y, int z, CallbackInfo ci) {
        if (0 >= Config.config.FLORA_CONFIG.appleDropChance) {
            return;
        }

        /** - Special drop logic */
        int meta = world.getBlockMeta(x, y, z);
        miscTweaks_rareAppleDrop(world, x, y, z, (meta & 3));
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_afterBreak(World world, PlayerEntity playerEntity, int x, int y, int z, int meta, CallbackInfo ci) {
        if (0 >= Config.config.FLORA_CONFIG.appleDropChance) {
            return;
        }

        if (!world.isRemote && playerEntity.getHand() != null && playerEntity.getHand().itemId == Item.SHEARS.id) {
            /** - Do nothing */
        } else {
            /** - Special drop logic */
            miscTweaks_rareAppleDrop(world, x, y, z, (meta & 3));
        }
    }

    @Unique
    private void miscTweaks_rareAppleDrop(World world, int i, int j, int k, int leafType) {
        if (0 == leafType) {
            if ((Config.config.FLORA_CONFIG.appleDropChance/100.0F) > world.random.nextFloat()) {
                ItemStack arg2 = new ItemStack(Item.APPLE);

                if (!world.isRemote) {
                    float var6 = 0.7F;
                    double var7 = (double) (world.random.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    double var9 = (double) (world.random.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    double var11 = (double) (world.random.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    ItemEntity var13 = new ItemEntity(world, (double) i + var7, (double) j + var9, (double) k + var11, arg2);
                    var13.pickupDelay = 10;
                    world.spawnEntity(var13);
                }
            }
        }
    }

}
