package com.github.telvarost.misctweaks;

import net.glasslauncher.mods.gcapi3.api.*;

public class Config {

    @ConfigRoot(value = "config", visibleName = "MiscTweaks")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigCategory(
                name = "Armor Config",
                description = "Restart required for changes to take effect"
        )
        public ArmorConfig ARMOR_CONFIG = new ArmorConfig();

        @ConfigCategory(
                name = "Block Entity Config",
                description = "Changes for chests and signs"
        )
        public BlockEntityConfig BLOCK_ENTITY_CONFIG = new BlockEntityConfig();

        @ConfigCategory(
                name = "Explosion And Fire Config",
                description = "Changes for tnt, creepers, ghasts, etc."
        )
        public ExplosionAndFireConfig EXPLOSION_AND_FIRE_CONFIG = new ExplosionAndFireConfig();

        @ConfigCategory(
                name = "Flora Config",
                description = "Changes for leaves, logs, dead bushes, etc."
        )
        public FloraConfig FLORA_CONFIG = new FloraConfig();

        @ConfigCategory(
                name = "Mob Config",
                description = "Changes for zombies, farmland trampling, etc."
        )
        public MobConfig MOB_CONFIG = new MobConfig();
    }

    public static class ArmorConfig {
        @ConfigEntry(
                name = "Equalize Base Armor Durability",
                multiplayerSynced = true
        )
        public Boolean equalizeBaseArmorDurability = false;

        @ConfigEntry(
                name = "Modern Armor Defense Points",
                multiplayerSynced = true
        )
        public Boolean modernArmorDefensePoints = false;
    }

    public static class BlockEntityConfig {
        @ConfigEntry(
                name = "Allow Chests To Open Even When Blocked",
                description = "Chests can open when a block is above them",
                multiplayerSynced = true
        )
        public Boolean enableChestsOpenWithBlockAbove = false;

        @ConfigEntry(
                name = "Allow Coloring Signs With Dye",
                description = "Dye will be consumed on use",
                multiplayerSynced = true
        )
        public Boolean enableColorSignsWithDye = false;

        @ConfigEntry(
                name = "Allow Editing Signs With A Feather",
                description = "Feather will be consumed on use",
                multiplayerSynced = true
        )
        public Boolean enableEditSignsWithFeathers = false;
    }

    public static class ExplosionAndFireConfig {
        @ConfigEntry(
                name = "Allow Ghast Fireballs To Insta-Kill Ghasts",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        public Boolean enableGhastFireballsToInstaKillGhasts = false;

        @ConfigEntry(
                name = "Allow TNT Defusing With Shears",
                description = "Use Left-Click with shears to defuse",
                multiplayerSynced = true
        )
        public Boolean enableDefusingTnt = false;

        @ConfigEntry(
                name = "Disable All Explosions Breaking Blocks",
                multiplayerSynced = true
        )
        public Boolean disableAllExplosionsBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Creeper Explosion Breaking Blocks",
                multiplayerSynced = true
        )
        public Boolean disableCreeperExplosionBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Ghast Explosion Breaking Blocks",
                multiplayerSynced = true
        )
        public Boolean disableGhastExplosionBreakingBlocks = false;

        @ConfigEntry(
                name = "Disable Ghast Explosion Causing Fire",
                multiplayerSynced = true
        )
        public Boolean disableGhastExplosionCausingFire = false;

        @ConfigEntry(
                name = "Disable Lightning Strike Causing Fire",
                multiplayerSynced = true
        )
        public Boolean disableLightningStrikeCausingFire = false;

        @ConfigEntry(
                name = "Disable TNT Explosion Breaking Blocks",
                multiplayerSynced = true
        )
        public Boolean disableTntExplosionBreakingBlocks = false;
    }

    public static class FloraConfig {
        @ConfigEntry(
                name = "Allow Random Stick Drop From Dead Bushes",
                description = "Chance is the same as seeds from tall grass",
                multiplayerSynced = true
        )
        public Boolean enableRandomStickDropFromDeadBushes = false;

        @ConfigEntry(
                name = "Allow Shears To Collect Dead Bushes",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        public Boolean enableShearsCollectDeadBush = false;

        @ConfigEntry(
                name = "Allow Shears To Collect Ferns",
                description = "Requires BHCreative as item is non-vanilla",
                multiplayerSynced = true
        )
        public Boolean enableShearsCollectFern = false;

        @ConfigEntry(
                name = "Allow Shears To Collect Tall Grass",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        public Boolean enableShearsCollectTallGrass = false;

        @ConfigEntry(
                name = "Apple Drop Chance From Oak Leaves 0.X%",
                description = "0 = disabled, 5 = modern drop chance",
                maxLength = 10,
                multiplayerSynced = true
        )
        public Integer appleDropChance = 0;

        @ConfigEntry(
                name = "Enable Log Rotation (Restart required)",
                description = "Disabling converts rotated logs to weird oak",
                multiplayerSynced = true
        )
        public Boolean enableLogRotation = false;

        @ConfigEntry(
                name = "Enable Player Placed Leaf Persistence",
                description = "Disables leaf decay for player placed leaves",
                multiplayerSynced = true
        )
        public Boolean enablePlayerPlacedLeafPersistence = false;
    }

    public static class MobConfig {
        @ConfigEntry(
                name = "Chance Zombies Drop Config Item",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        public Boolean enableZombieDropItem = false;

        @ConfigEntry(
                name = "Chance Zombie Pigmen Drop Config Item",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        public Boolean enableZombiePigmanDropItem = false;

        @ConfigEntry(
                name = "Chance Zombies Drop This Item",
                description = "Only works if setting is enabled above",
                multiplayerSynced = true
        )
        public ZombieDropEnum zombieDropItem = ZombieDropEnum.FEATHER;

        @ConfigEntry(
                name = "Chance Zombie Pigmen Drop This Item",
                description = "Only works if setting is enabled above",
                multiplayerSynced = true
        )
        public ZombiePigmanDropEnum zombiePigmanDropItem = ZombiePigmanDropEnum.COOKED_PORKCHOP;

        @ConfigEntry(
                name = "Disable Trampling Farmland",
                description = "Player/mobs will no longer trample farmland",
                multiplayerSynced = true
        )
        public Boolean disableTramplingFarmland = false;

        @ConfigEntry(name = "Do Not Trample Farmland When Barefoot",
                description = "Only affects player without boots",
                multiplayerSynced = true
        )
        public Boolean disableTrampleFarmlandWhenBarefoot = false;

        @ConfigEntry(
                name = "Do Not Trample Farmland With Leather Boots",
                description = "Only affects player with leather boots",
                multiplayerSynced = true
        )
        public Boolean disableTrampleFarmlandWithLeatherBoots = false;
    }
}
