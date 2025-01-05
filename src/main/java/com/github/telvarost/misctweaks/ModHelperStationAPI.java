package com.github.telvarost.misctweaks;

import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Optional;

public class ModHelperStationAPI {
    public static int identifierToItemId(String n) {
        Optional<Item> item = ItemRegistry.INSTANCE.getOrEmpty(Identifier.of(n));
        return item.map(itemBase -> itemBase.id).orElse(-1);
    }
}
