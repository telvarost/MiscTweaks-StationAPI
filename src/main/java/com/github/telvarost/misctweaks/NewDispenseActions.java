package com.github.telvarost.misctweaks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

public class NewDispenseActions {

    public static void ToggleDispenseActions()
    {
        /** - Update modded dispenser fluid placement */
        ModHelper.RemoveDispenseAction(BlockBase.FLOWING_WATER.id);
        ModHelper.RemoveDispenseAction(BlockBase.FLOWING_LAVA.id);
        if (Config.ConfigFields.moddedDispenserFluidPlacement) {
            ModHelper.AddNewDispenseAction(BlockBase.FLOWING_WATER.id, NewDispenseActions.flowingWaterDispenseAction);
            ModHelper.AddNewDispenseAction(BlockBase.FLOWING_LAVA.id, NewDispenseActions.flowingWaterDispenseAction);
        }

        /** - Update modern dispenser fluid placement */
        ModHelper.RemoveDispenseAction(ItemBase.waterBucket.id);
        ModHelper.RemoveDispenseAction(ItemBase.lavaBucket.id);
        ModHelper.RemoveDispenseAction(ItemBase.bucket.id);
        if (Config.ConfigFields.modernDispenserFluidPlacement) {
            ModHelper.AddNewDispenseAction(ItemBase.waterBucket.id, NewDispenseActions.waterBucketDispenseAction);
            ModHelper.AddNewDispenseAction(ItemBase.lavaBucket.id, NewDispenseActions.lavaBucketDispenseAction);
            ModHelper.AddNewDispenseAction(ItemBase.bucket.id, NewDispenseActions.bucketDispenseAction);
        }
    }

    public static ModHelper.DispenseAction flowingWaterDispenseAction = (dispenserEntity, arg, i, j, k, x_axis, z_axis, random) -> {
        int blockIdInFrontOfDispenser = arg.getTileId(i + x_axis, j, k + z_axis);

        if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid()) {
            if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
                    && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
                    && ( (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
                    || (BlockBase.STILL_WATER.id == blockIdInFrontOfDispenser)
            )
            ) {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
                arg.playLevelEvent(1000, i, j, k, 0);
                dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_WATER, 1));
                dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.emptySlotAvailable, new ItemInstance(BlockBase.FLOWING_WATER, 1));
            } else {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, BlockBase.FLOWING_WATER.id, 0);
                arg.playSound(i, j, k, "liquid.splash", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            }
        } else {
            dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_WATER, 1));
            arg.playSound(i, j, k, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            for (int var28 = 0; var28 < 8; ++var28) {
                arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
            }
        }

        return true;
    };

    public static ModHelper.DispenseAction flowingLavaDispenseAction = (dispenserEntity, arg, i, j, k, x_axis, z_axis, random) -> {
        int blockIdInFrontOfDispenser = arg.getTileId(i + x_axis, j, k + z_axis);

        if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid()) {
            if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
                    && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
                    && (  (BlockBase.FLOWING_LAVA.id == blockIdInFrontOfDispenser)
                    || (BlockBase.STILL_LAVA.id == blockIdInFrontOfDispenser)
            )
            ) {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
                arg.playLevelEvent(1000, i, j, k, 0);
                dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
                dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.emptySlotAvailable, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
            } else {
                arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, BlockBase.FLOWING_LAVA.id, 0);
                arg.playLevelEvent(1002, i, j, k, 0);
            }
        } else {
            dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
            arg.playSound(i + 0.5, j + 0.5, k + 0.5, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            for (int var28 = 0; var28 < 8; ++var28) {
                arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
            }
        }

        return true;
    };

    public static ModHelper.DispenseAction waterBucketDispenseAction = (dispenserEntity, arg, i, j, k, x_axis, z_axis, random) -> {
        int blockIdInFrontOfDispenser = arg.getTileId(i + x_axis, j, k + z_axis);

        if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid())
        {
            arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, BlockBase.FLOWING_WATER.id, 0);
            arg.playSound(i, j, k, "liquid.splash", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.bucket, 1));
            return true;
        }
        else
        {
            return false;
        }
    };

    public static ModHelper.DispenseAction lavaBucketDispenseAction = (dispenserEntity, arg, i, j, k, x_axis, z_axis, random) -> {
        int blockIdInFrontOfDispenser = arg.getTileId(i + x_axis, j, k + z_axis);

        if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid())
        {
            arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, BlockBase.FLOWING_LAVA.id, 0);
            arg.playLevelEvent(1002, i, j, k, 0);
            dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.bucket, 1));
            return true;
        }
        else
        {
            return false;
        }
    };

    public static ModHelper.DispenseAction bucketDispenseAction = (dispenserEntity, arg, i, j, k, x_axis, z_axis, random) -> {
        int blockIdInFrontOfDispenser = arg.getTileId(i + x_axis, j, k + z_axis);

        if (  (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
           && (  (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
              || (BlockBase.STILL_WATER.id   == blockIdInFrontOfDispenser)
              || (BlockBase.FLOWING_LAVA.id  == blockIdInFrontOfDispenser)
              || (BlockBase.STILL_LAVA.id    == blockIdInFrontOfDispenser)
              )
           )
        {
            arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
            arg.playLevelEvent(1000, i, j, k, 0);

            if (  (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
               || (BlockBase.STILL_WATER.id   == blockIdInFrontOfDispenser)
               )
            {
                dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.waterBucket, 1));
            } else {
                dispenserEntity.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(ItemBase.lavaBucket, 1));
            }
            return true;
        }
        else
        {
            return false;
        }
    };
}
