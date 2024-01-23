package com.github.telvarost.misctweaks.events;

import com.github.telvarost.misctweaks.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;

import static java.lang.Math.floor;

public class ItemUsedInCraftingListener {

    /**
     * Allows for consuming the bucket when crafting source blocks
     *
     * @param event Item used in crafting event which fires whenever an item is consumed in crafting or an item is crafted
     */
    @EventListener
    public void combineDurability(ItemUsedInCraftingEvent event) {
        if (!Config.ConfigFields.moddedDispenserFluidPlacement) {
            return;
        }

        if (  (null != event.itemCrafted)
           && (null != event.itemUsed)
           )
        {
            if (BlockBase.FLOWING_WATER.id == event.itemCrafted.itemId)
            {
                event.craftingMatrix.setInventoryItem(event.itemOrdinal, null);
            }
            else if (BlockBase.FLOWING_LAVA.id == event.itemCrafted.itemId)
            {
                event.craftingMatrix.setInventoryItem(event.itemOrdinal, null);
            }
        }
    }
}