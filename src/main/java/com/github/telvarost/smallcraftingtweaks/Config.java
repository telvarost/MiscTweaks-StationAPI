package com.github.telvarost.smallcraftingtweaks;

import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class Config {

    @GConfig(value = "config", visibleName = "SmallCraftingTweaks Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

       @ConfigName("Player Crafting Grid Can Be Used As Inventory Slots")
       public static Boolean allowCraftingInventorySlots = true;

        @ConfigName("Stairs Crafting Recipe Output: 1-16 (Relaunch Minecraft)")
        public static Integer stairsOutput = 4;
    }
}
