# MiscTweaks StationAPI Edition for Minecraft Beta 1.7.3

A mod for Minecraft Beta 1.7.3 babric that tweaks random things.

# Miscellaneous Tweaks

You will need ModMenu and GlassConfigAPI to change configs and use this mod. See installation instructions below.
* Mod works on Multiplayer with GlassConfigAPI version 3.0+ used to sync configs!

## List of changes

All changes are default false/vanilla behavior unless otherwise specified

### Equipment Changes
* Allow bows to have durability
  * Sets bow durability to 384 and uses 1 durability for each arrow fired
* Equalize base armor durability
  * Helmet, Chestplate, Leggings, and Boots will all have the same durability
* Modern armor defense points
  * Lower tiers have less defense and defense does not decrease with durability loss

### Explosion/Fire Changes
* Allow ghast fireballs to insta-kill ghasts
* Allow defusing TNT with shears (use left-click to defuse)
* Disable all explosions
  * Disable TNT explosions breaking blocks
  * Disable creeper explosions breaking blocks
  * Disable ghast explosions breaking blocks
* Disable ghast explosions causing fire
* Disable lightning strikes causing fire

### Flora Changes
* Allow collecting ferns with shears
  * [BHCreative](https://modrinth.com/mod/bh-creative) is required for this as b1.7.3 does not have an item for it
  * Item is non-vanilla and will disappear if BHCreative is removed
* Allow collecting tall grass with shears
* Allow collecting dead bushes with shears
* Allow random chance for dead bushes to drop a stick
  * The percent chance is the same as getting seeds from tall grass (12.5%)
* Allow random chance for apples to drop from oak leaves
  * The percent chance can be chosen via the config
* Disable leaf decay for player placed leaves
* Enable log rotation for oak, spruce, and birch logs
  * This uses the extra metadata bits for rotation
    * So when disabled the rotated logs will appear as oak, but drop a different block type similar to how crash slabs work

### Interactive Block Changes
* Allow chests to be opened even when a solid block is above them
* Allow editing signs with a feather (feather is consumed on use)
* Allow coloring signs with dye (dye is consumed on use)
  * Coloring signs is incompatible with MojangFix
  * Use [MojangFixStationAPI](https://modrinth.com/mod/misctweaks-stationapi)'s config to disable MojangFix signs to use colored signs
    * Config is available through [ModMenu](https://modrinth.com/mod/modmenu-beta) and [GlassConfigAPI](https://modrinth.com/mod/glass-config-api)
* Allow gluing trapdoors in place with a slimeball (slimeball is consumed on use)

### Mob Changes
* Add ability to enable different zombie pigman drops
  * Select between: cooked porkchops, raw porkchops, brown mushrooms, gold sword, bone meal, brick, or nothing
* Add ability to enable different zombie drops
  * Select between: feathers, red mushrooms, cyan dye, green dye, clay, paper, or nothing
* Disable player/mobs trampling farmland
* Disable player trampling farmland if player is not wearing boots
* Disable player trampling farmland if player is wearing leather boots

## Slab Changes moved to NowObtainableRecipes
* See: https://modrinth.com/mod/nowobtainablerecipes-stationapi

## Dispenser Changes moved to DispenserTweaks
* See: https://modrinth.com/mod/dispensertweaks-stationapi

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/Glass-Series/babric-prism-instance
2. Install Java 17 and set the instance to use it: https://adoptium.net/temurin/releases/
3. Add GlassConfigAPI 3.0.2+ to the mod folder for the instance: https://modrinth.com/mod/glass-config-api
4. Add Glass Networking to the mod folder for the instance: https://modrinth.com/mod/glass-networking
5. (Optional) Add StationAPI to the mod folder for the instance: https://modrinth.com/mod/stationapi
6. (Optional) Add Mod Menu to the mod folder for the instance: https://modrinth.com/mod/modmenu-beta
7. Add this mod to the mod folder for the instance: https://github.com/telvarost/BetaTweaks-StationAPI/releases
8. Run and enjoy! 👍

## Feedback

Got any suggestions on what should be added next? Feel free to share it by [creating an issue](https://github.com/telvarost/MiscTweaks-StationAPI/issues/new). Know how to code and want to do it yourself? Then look below on how to get started.

## Contributing

Thanks for considering contributing! To get started fork this repository, make your changes, and create a PR. 

If you are new to StationAPI consider watching the following videos on Babric/StationAPI Minecraft modding: https://www.youtube.com/watch?v=9-sVGjnGJ5s&list=PLa2JWzyvH63wGcj5-i0P12VkJG7PDyo9T
