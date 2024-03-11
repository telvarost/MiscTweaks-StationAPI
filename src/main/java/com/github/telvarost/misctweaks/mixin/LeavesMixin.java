package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.Leaves;
import net.minecraft.block.LeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Item;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Leaves.class)
public abstract class LeavesMixin extends LeavesBase {

    public LeavesMixin(int i, int j) {
        super(i, j, Material.LEAVES, false);
    }


    @Inject(method = "dropAndRemove", at = @At("HEAD"), cancellable = true)
    private void miscTweaks_dropAndRemove(Level arg, int i, int j, int k, CallbackInfo ci) {
        if (0 >= Config.ConfigFields.appleDropChance) {
            return;
        }

        /** - Special drop logic */
        int l = arg.getTileMeta(i, j, k);
        miscTweaks_rareAppleDrop(arg, i, j, k, (l & 3));
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_afterBreak(Level arg, PlayerBase arg2, int i, int j, int k, int l, CallbackInfo ci) {
        if (0 >= Config.ConfigFields.appleDropChance) {
            return;
        }

        if (!arg.isServerSide && arg2.getHeldItem() != null && arg2.getHeldItem().itemId == ItemBase.shears.id) {
            /** - Do nothing */
        } else {
            /** - Special drop logic */
            miscTweaks_rareAppleDrop(arg, i, j, k, (l & 3));
        }
    }

    @Unique
    private void miscTweaks_rareAppleDrop(Level arg, int i, int j, int k, int leafType) {
        if (0 == leafType) {
            Random random = new Random();
            boolean isAppleDropped = (random.nextInt(1000/Config.ConfigFields.appleDropChance) == 0) ? true : false;

            if (isAppleDropped) {
                ItemInstance arg2 = new ItemInstance(ItemBase.apple);

                if (!arg.isServerSide) {
                    float var6 = 0.7F;
                    double var7 = (double) (arg.rand.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    double var9 = (double) (arg.rand.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    double var11 = (double) (arg.rand.nextFloat() * var6) + (double) (1.0F - var6) * 0.5;
                    Item var13 = new Item(arg, (double) i + var7, (double) j + var9, (double) k + var11, arg2);
                    var13.pickupDelay = 10;
                    arg.spawnEntity(var13);
                }
            }
        }
    }

}
