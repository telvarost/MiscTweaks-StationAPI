package com.github.telvarost.misctweaks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;

public class ModHelper {
    public static void AttemptToSetStackSizeOfFluids()
    {
        if (ModHelper.ModHelperFields.blocksAndItemsRegistered)
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
    }

    public static class ModHelperFields {
        public static Boolean blocksAndItemsRegistered = false;

        public static Integer emptySlotAvailable = -1;

        public static Integer lastSlotDispensed = -1;
    }
}
