package com.github.telvarost.misctweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class Config {

    @GConfig(value = "config", visibleName = "MiscTweaks Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Allow Using Lava Block As Fuel Source")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableLavaBlockSmeltingRecipe = true;

        @ConfigName("Modded Dispenser Fluid Placement")
        @Comment("Craft Fluids & Use Dispensers as Pumps")
        public static Boolean moddedDispenserFluidPlacement = false;

        @ConfigName("Modern Dispenser Fluid Placement")
        @Comment("Restart required for modded variant only")
        public static Boolean modernDispenserFluidPlacement = false;

        @ConfigName("Stairs Crafting Recipe Output: 1-16")
        @Comment("Restart required for changes to take effect")
        @MaxLength(16)
        public static Integer stairsOutput = 4;

        @ConfigName("Shapeless Jack o’ Lantern Recipe")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableShapelessJackOLanternRecipe = true;
    }
}
