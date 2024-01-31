package com.github.telvarost.misctweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class Config {

    @GConfig(value = "config", visibleName = "MiscTweaks Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Allow Editing Signs With A Feather")
        @Comment("Feather will be consumed on use")
        public static Boolean enableEditSignsWithFeathers = true;

        @ConfigName("Allow Coloring Signs With Dye")
        @Comment("Dye will be consumed on use")
        public static Boolean enableColorSignsWithDye = true;

        @ConfigName("Disable Creeper Explosion Breaking Blocks")
        public static Boolean disableCreeperExplosionBreakingBlocks = false;

        @ConfigName("Disable Ghast Explosion Breaking Blocks")
        public static Boolean disableGhastExplosionBreakingBlocks = false;

        @ConfigName("Disable Ghast Explosion Causing Fire")
        public static Boolean disableGhastExplosionCausingFire = false;

        @ConfigName("Enable Lava Block As Fuel Source")
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
