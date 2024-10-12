package com.github.telvarost.misctweaks;

import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;
import java.util.Optional;

public class ModHelper {

    public static int identifierToItemId(String n) {
        Optional<Item> item = ItemRegistry.INSTANCE.getOrEmpty(Identifier.of(n));
        return item.map(itemBase -> itemBase.id).orElse(-1);
    }

    public static class ModHelperFields {

        public static Integer cancelDestroyBlocks = 0;

        public static Integer cancelDestroyBlocksPacket = 0;

        public static Integer numberOfGhastFireballs = 0;

        public static Boolean loadMixinConfigs = true;
    }
}
