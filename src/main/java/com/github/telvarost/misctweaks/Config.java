package com.github.telvarost.misctweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "MiscTweaks")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Allow Chests To Open Even When Blocked")
        @Comment("Chests can open when a block is above them")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableChestsOpenWithBlockAbove = false;

        @ConfigName("Allow Coloring Signs With Dye")
        @Comment("Dye will be consumed on use")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableColorSignsWithDye = false;

        @ConfigName("Allow Editing Signs With A Feather")
        @Comment("Feather will be consumed on use")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableEditSignsWithFeathers = false;

        @ConfigName("Allow TNT Defusing With Shears")
        @Comment("Use Left-Click with shears to defuse")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableDefusingTnt = false;

        @ConfigName("Apple Drop Chance From Oak Leaves 0.X%")
        @Comment("0 = disabled, 5 = modern drop chance")
        @MaxLength(10)
        @MultiplayerSynced
        @ValueOnVanillaServer(integerValue = 0)
        public static Integer appleDropChance = 0;

        @ConfigName("Disable Creeper Explosion Breaking Blocks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean disableCreeperExplosionBreakingBlocks = false;

        @ConfigName("Disable Ghast Explosion Breaking Blocks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean disableGhastExplosionBreakingBlocks = false;

        @ConfigName("Disable Ghast Explosion Causing Fire")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean disableGhastExplosionCausingFire = false;

        @ConfigName("Disable Trampling Farmland")
        @Comment("Player/mobs will no longer trample farmland")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean disableTramplingFarmland = false;

        @ConfigName("Do Not Trample Farmland When Barefoot")
        @Comment("Only affects player without boots")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean disableTrampleFarmlandWhenBarefoot = false;

        @ConfigName("Do Not Trample Farmland With Leather Boots")
        @Comment("Only affects player with leather boots")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean disableTrampleFarmlandWithLeatherBoots = false;

        @ConfigName("Equalize Base Armor Durability")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean equalizeBaseArmorDurability = false;

        @ConfigName("Modern Armor Defense Points")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean modernArmorDefensePoints = false;

        @ConfigName("Shapeless Jack o’ Lantern Recipe")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableShapelessJackOLanternRecipe = true;

        @ConfigName("Stairs Crafting Recipe Output: 1-16")
        @Comment("Restart required for changes to take effect")
        @MaxLength(16)
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Integer stairsOutput = 4;

        @ConfigName("Use Right Click To Equip Armor")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean rightClickEquipArmor = true;
    }
}
