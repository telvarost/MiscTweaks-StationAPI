package com.github.telvarost.misctweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "MiscTweaks")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Add Double Stone Slab Crafting Recipe")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableDoubleStoneSlabCraftingRecipe = false;

        @ConfigName("Allow Chests To Open Even When Blocked")
        @Comment("Chests can open when a block is above them")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableChestsOpenWithBlockAbove = false;

        @ConfigName("Allow Coloring Signs With Dye")
        @Comment("Dye will be consumed on use")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableColorSignsWithDye = false;

        @ConfigName("Allow Editing Signs With A Feather")
        @Comment("Feather will be consumed on use")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableEditSignsWithFeathers = false;

        @ConfigName("Allow Random Stick Drop From Dead Bushes")
        @Comment("Chance is the same as seeds from tall grass")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableRandomStickDropFromDeadBushes = false;

        @ConfigName("Allow Shears To Collect Dead Bushes")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableShearsCollectDeadBush = false;

        @ConfigName("Allow Shears To Collect Tall Grass")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableShearsCollectTallGrass = false;

        @ConfigName("Allow TNT Defusing With Shears")
        @Comment("Use Left-Click with shears to defuse")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableDefusingTnt = false;

        @ConfigName("Apple Drop Chance From Oak Leaves 0.X%")
        @Comment("0 = disabled, 5 = modern drop chance")
        @MaxLength(10)
        @MultiplayerSynced
        @ValueOnVanillaServer(integerValue = 0)
        public Integer appleDropChance = 0;

        @ConfigName("Disable All Explosions Breaking Blocks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableAllExplosionsBreakingBlocks = false;

        @ConfigName("Disable Creeper Explosion Breaking Blocks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableCreeperExplosionBreakingBlocks = false;

        @ConfigName("Disable Ghast Explosion Breaking Blocks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableGhastExplosionBreakingBlocks = false;

        @ConfigName("Disable TNT Explosion Breaking Blocks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTntExplosionBreakingBlocks = false;

        @ConfigName("Disable Ghast Explosion Causing Fire")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableGhastExplosionCausingFire = false;

        @ConfigName("Disable Trampling Farmland")
        @Comment("Player/mobs will no longer trample farmland")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTramplingFarmland = false;

        @ConfigName("Do Not Trample Farmland When Barefoot")
        @Comment("Only affects player without boots")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTrampleFarmlandWhenBarefoot = false;

        @ConfigName("Do Not Trample Farmland With Leather Boots")
        @Comment("Only affects player with leather boots")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTrampleFarmlandWithLeatherBoots = false;

        @ConfigName("Equalize Base Armor Durability")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean equalizeBaseArmorDurability = false;

        @ConfigName("Modern Armor Defense Points")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean modernArmorDefensePoints = false;
    }
}
