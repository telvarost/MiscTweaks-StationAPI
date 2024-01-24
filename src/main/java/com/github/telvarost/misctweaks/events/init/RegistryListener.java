package com.github.telvarost.misctweaks.events.init;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import com.github.telvarost.misctweaks.NewDispenseActions;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityDispenser;
import net.modificationstation.stationapi.api.event.registry.AfterBlockAndItemRegisterEvent;

import java.util.Random;

public class RegistryListener {

    @EventListener
    public void handleAfterBlockAndItemRegisterEvent(AfterBlockAndItemRegisterEvent event)
    {
        ModHelper.ModHelperFields.blocksAndItemsRegistered = true;
        ModHelper.SetStackSizeOfItems();
        NewDispenseActions.ToggleDispenseActions();
    }
}

