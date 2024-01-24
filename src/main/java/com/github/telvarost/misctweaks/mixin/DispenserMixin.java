package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.block.BlockBase;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Dispenser;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Item;
import net.minecraft.entity.projectile.Arrow;
import net.minecraft.entity.projectile.Egg;
import net.minecraft.entity.projectile.Snowball;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityDispenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Dispenser.class)
abstract class DispenserMixin extends BlockWithEntity {
    private Random rand = new Random();

    public DispenserMixin(int i) {
        super(i, Material.STONE);
        this.texture = 45;
    }

    @Inject(method = "dispense", at = @At("HEAD"), cancellable = true)
    private void dispense(Level arg, int i, int j, int k, Random random, CallbackInfo ci) {
        if (  (!Config.ConfigFields.moddedDispenserFluidPlacement)
           && (!Config.ConfigFields.modernDispenserFluidPlacement)
           )
        {
            return;
        }

        int var6 = arg.getTileMeta(i, j, k);
        byte x_axis = 0;
        byte z_axis = 0;
        if (var6 == 3) {
            z_axis = 1;
        } else if (var6 == 2) {
            z_axis = -1;
        } else if (var6 == 5) {
            x_axis = 1;
        } else {
            x_axis = -1;
        }

        TileEntityDispenser var11 = (TileEntityDispenser)arg.getTileEntity(i, j, k);
        ItemInstance var12 = var11.getItemToDispense();
        double var13 = (double)i + (double)x_axis * 0.6 + 0.5;
        double var15 = (double)j + 0.5;
        double var17 = (double)k + (double)z_axis * 0.6 + 0.5;
        if (var12 == null) {
            arg.playLevelEvent(1001, i, j, k, 0);
        } else {
            int blockIdInFrontOfDispenser = arg.getTileId(i + x_axis, j, k + z_axis);

            if (var12.itemId == ItemBase.arrow.id) {
                Arrow var19 = new Arrow(arg, var13, var15, var17);
                var19.method_1291((double)x_axis, 0.10000000149011612, (double)z_axis, 1.1F, 6.0F);
                var19.spawnedByPlayer = true;
                arg.spawnEntity(var19);
                arg.playLevelEvent(1002, i, j, k, 0);
            } else if (var12.itemId == ItemBase.egg.id) {
                Egg var22 = new Egg(arg, var13, var15, var17);
                var22.method_1682((double)x_axis, 0.10000000149011612, (double)z_axis, 1.1F, 6.0F);
                arg.spawnEntity(var22);
                arg.playLevelEvent(1002, i, j, k, 0);
            } else if (var12.itemId == ItemBase.snowball.id) {
                Snowball var23 = new Snowball(arg, var13, var15, var17);
                var23.method_1656((double)x_axis, 0.10000000149011612, (double)z_axis, 1.1F, 6.0F);
                arg.spawnEntity(var23);
                arg.playLevelEvent(1002, i, j, k, 0);
            } else if (Config.ConfigFields.moddedDispenserFluidPlacement && var12.itemId == BlockBase.FLOWING_WATER.id) {
                if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid()) {
                    if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
                       && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
                       && ( (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
                          || (BlockBase.STILL_WATER.id == blockIdInFrontOfDispenser)
                          )
                    ) {
                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
                        arg.playLevelEvent(1000, i, j, k, 0);
                        var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_WATER, 1));
                        var11.setInventoryItem(ModHelper.ModHelperFields.emptySlotAvailable, new ItemInstance(BlockBase.FLOWING_WATER, 1));
                    } else {
                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, FLOWING_WATER.id, 0);
                        arg.playSound(i, j, k, "liquid.splash", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
                    }
                } else {
                    var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_WATER, 1));
                    arg.playSound(i, j, k, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
                    for (int var28 = 0; var28 < 8; ++var28) {
                        arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
                    }
                }
            } else if (Config.ConfigFields.moddedDispenserFluidPlacement && var12.itemId == BlockBase.FLOWING_LAVA.id) {
                if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid()) {
                    if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
                       && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
                       && (  (BlockBase.FLOWING_LAVA.id == blockIdInFrontOfDispenser)
                          || (BlockBase.STILL_LAVA.id == blockIdInFrontOfDispenser)
                          )
                    ) {
                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
                        arg.playLevelEvent(1000, i, j, k, 0);
                        var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
                        var11.setInventoryItem(ModHelper.ModHelperFields.emptySlotAvailable, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
                    } else {
                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, FLOWING_LAVA.id, 0);
                        arg.playLevelEvent(1002, i, j, k, 0);
                    }
                } else {
                    var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
                    arg.playSound(i + 0.5, j + 0.5, k + 0.5, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
                    for (int var28 = 0; var28 < 8; ++var28) {
                        arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
                    }
                }
            } else if (  (Config.ConfigFields.modernDispenserFluidPlacement)
                      && (var12.itemId == ItemBase.waterBucket.id)
                      && (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid())
                      )
            {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, FLOWING_WATER.id, 0);
                arg.playSound(i, j, k, "liquid.splash", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
                var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.bucket, 1));
            } else if (  (Config.ConfigFields.modernDispenserFluidPlacement)
                      && (var12.itemId == ItemBase.lavaBucket.id)
                      && (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid())
                      )
            {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, FLOWING_LAVA.id, 0);
                arg.playLevelEvent(1002, i, j, k, 0);
                var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.bucket, 1));
            } else if (  (Config.ConfigFields.modernDispenserFluidPlacement)
                      && (var12.itemId == ItemBase.bucket.id)
                      && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
                      && (  (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
                         || (BlockBase.STILL_WATER.id  == blockIdInFrontOfDispenser)
                         || (BlockBase.FLOWING_LAVA.id  == blockIdInFrontOfDispenser)
                         || (BlockBase.STILL_LAVA.id  == blockIdInFrontOfDispenser)
                         )
                      )
            {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
                arg.playLevelEvent(1000, i, j, k, 0);

                if (  (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
                   || (BlockBase.STILL_WATER.id  == blockIdInFrontOfDispenser)
                   )
                {
                    var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.waterBucket, 1));
                } else {
                    var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.lavaBucket, 1));
                }
            } else {
                Item var24 = new Item(arg, var13, var15 - 0.3, var17, var12);
                double var20 = random.nextDouble() * 0.1 + 0.2;
                var24.velocityX = (double)x_axis * var20;
                var24.velocityY = 0.20000000298023224;
                var24.velocityZ = (double)z_axis * var20;
                var24.velocityX += random.nextGaussian() * 0.007499999832361937 * 6.0;
                var24.velocityY += random.nextGaussian() * 0.007499999832361937 * 6.0;
                var24.velocityZ += random.nextGaussian() * 0.007499999832361937 * 6.0;
                arg.spawnEntity(var24);
                arg.playLevelEvent(1000, i, j, k, 0);
            }

            arg.playLevelEvent(2000, i, j, k, x_axis + 1 + (z_axis + 1) * 3);
        }
        ci.cancel();
    }
}