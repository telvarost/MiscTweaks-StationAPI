package com.github.telvarost.misctweaks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityDispenser;

import java.util.ArrayList;
import java.util.Random;

public class ModHelper {
    public static void SetStackSizeOfItems()
    {
        ItemBase waterItem = BlockBase.FLOWING_WATER.asItem();
        ItemBase lavaItem  = BlockBase.FLOWING_LAVA.asItem();

        if (Config.ConfigFields.moddedDispenserFluidPlacement) {
            if (null != waterItem) {
                if (1 != waterItem.getMaxStackSize()) {
                    waterItem.setMaxStackSize(1);
                }
            }

            if (null != lavaItem) {
                if (1 != lavaItem.getMaxStackSize()) {
                    lavaItem.setMaxStackSize(1);
                }
            }
        }
        else
        {
            if (null != waterItem) {
                if (64 != waterItem.getMaxStackSize()) {
                    waterItem.setMaxStackSize(64);
                }
            }

            if (null != lavaItem) {
                if (64 != lavaItem.getMaxStackSize()) {
                    lavaItem.setMaxStackSize(64);
                }
            }
        }
    }

    public static void AddNewDispenseAction(int itemId, DispenseAction dispenseAction)
    {
        ModHelper.ModHelperFields.dispenseNewItemIds.add(itemId);
        ModHelper.ModHelperFields.dispenseNewItemFunctions.add(dispenseAction);
    }

    public static void RemoveDispenseAction(int itemId)
    {
        int positionToRemove = -1;

        for (int dispenseActionIndex = 0; dispenseActionIndex < ModHelper.ModHelperFields.dispenseNewItemIds.size(); dispenseActionIndex++)
        {
            if (itemId == ModHelperFields.dispenseNewItemIds.get(dispenseActionIndex))
            {
                positionToRemove = dispenseActionIndex;
            }
        }

        if (-1 < positionToRemove)
        {
            ModHelper.ModHelperFields.dispenseNewItemIds.remove(positionToRemove);
            ModHelper.ModHelperFields.dispenseNewItemFunctions.remove(positionToRemove);
        }
    }

    public interface DispenseAction {
        /** - Return - true = skip checking other item ids, false = check remaining item ids */
        boolean dispenseAction(TileEntityDispenser dispenserEntity, Level arg, int i, int j, int k, int x_axis, int z_axis, Random random);
    }

    public static class ModHelperFields {
        public static ArrayList<Integer> dispenseNewItemIds = new ArrayList<>();

        public static ArrayList<DispenseAction> dispenseNewItemFunctions = new ArrayList<>();

        public static Boolean blocksAndItemsRegistered = false;

        public static Integer emptySlotAvailable = -1;

        public static Integer lastSlotDispensed = -1;
    }
}
