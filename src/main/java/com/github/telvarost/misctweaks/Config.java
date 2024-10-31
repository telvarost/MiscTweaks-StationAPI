package com.github.telvarost.misctweaks;

import net.glasslauncher.mods.gcapi3.api.*;

public class Config {

    @ConfigRoot(value = "config", visibleName = "MiscTweaks")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigEntry(
                name = "Add Double Stone Slab Crafting Recipe",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableDoubleStoneSlabCraftingRecipe = false;

        @ConfigEntry(
                name = "Allow Chests To Open Even When Blocked",
                description = "Chests can open when a block is above them",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableChestsOpenWithBlockAbove = false;

        @ConfigEntry(
                name = "Allow Coloring Signs With Dye",
                description = "Dye will be consumed on use",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableColorSignsWithDye = false;

        @ConfigEntry(
                name = "Allow Editing Signs With A Feather",
                description = "Feather will be consumed on use",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableEditSignsWithFeathers = false;

        @ConfigEntry(
                name = "Allow Ghast Fireballs To Insta-Kill Ghasts",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableGhastFireballsToInstaKillGhasts = false;

        @ConfigEntry(
                name = "Allow Random Stick Drop From Dead Bushes",
                description = "Chance is the same as seeds from tall grass",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableRandomStickDropFromDeadBushes = false;

        @ConfigEntry(
                name = "Allow Shears To Collect Dead Bushes",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableShearsCollectDeadBush = false;

        @ConfigEntry(
                name = "Allow Shears To Collect Ferns",
                description = "Requires BHCreative as item is non-vanilla",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableShearsCollectFern = false;

        @ConfigEntry(
                name = "Allow Shears To Collect Tall Grass",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableShearsCollectTallGrass = false;

        @ConfigEntry(
                name = "Allow TNT Defusing With Shears",
                description = "Use Left-Click with shears to defuse",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableDefusingTnt = false;

        @ConfigEntry(
                name = "Apple Drop Chance From Oak Leaves 0.X%",
                description = "0 = disabled, 5 = modern drop chance",
                maxLength = 10,
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(integerValue = 0)
        public Integer appleDropChance = 0;

        @ConfigEntry(
                name = "Chance Zombies Drop Config Item",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableZombieDropItem = false;

        @ConfigEntry(
                name = "Chance Zombie Pigmen Drop Config Item",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableZombiePigmanDropItem = false;

        @ConfigEntry(
                name = "Chance Zombies Drop This Item",
                description = "Only works if setting is enabled above",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(integerValue = 0)
        public ZombieDropEnum zombieDropItem = ZombieDropEnum.FEATHER;

        @ConfigEntry(
                name = "Chance Zombie Pigmen Drop This Item",
                description = "Only works if setting is enabled above",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(integerValue = 0)
        public ZombiePigmanDropEnum zombiePigmanDropItem = ZombiePigmanDropEnum.COOKED_PORKCHOP;

        @ConfigEntry(
                name = "Disable All Explosions Breaking Blocks",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableAllExplosionsBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Creeper Explosion Breaking Blocks",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableCreeperExplosionBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Ghast Explosion Breaking Blocks",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableGhastExplosionBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Ghast Explosion Causing Fire",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableGhastExplosionCausingFire = false;

        @ConfigEntry(
                name = "Disable TNT Explosion Breaking Blocks",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTntExplosionBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Trampling Farmland",
                description = "Player/mobs will no longer trample farmland",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTramplingFarmland = false;

        @ConfigEntry(name = "Do Not Trample Farmland When Barefoot",
                description = "Only affects player without boots",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTrampleFarmlandWhenBarefoot = false;

        @ConfigEntry(
                name = "Do Not Trample Farmland With Leather Boots",
                description = "Only affects player with leather boots",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean disableTrampleFarmlandWithLeatherBoots = false;

        @ConfigEntry(
                name = "Enable Log Rotation (Restart required)",
                description = "Disabling converts rotated logs to weird oak",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableLogRotation = false;

        @ConfigEntry(
                name = "Enable Player Placed Leaf Persistence",
                description = "Disables leaf decay for player placed leaves",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePlayerPlacedLeafPersistence = false;

        @ConfigEntry(
                name = "Equalize Base Armor Durability",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean equalizeBaseArmorDurability = false;

        @ConfigEntry(
                name = "Modern Armor Defense Points",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean modernArmorDefensePoints = false;
    }
}
