# MiscTweaks StationAPI Edition for Minecraft Beta 1.7.3

A StationAPI mod for Minecraft Beta 1.7.3 that tweaks random things that did not fit in my other mods such as allowing stairs recipe output to be changed.

**If you're looking for modern Minecraft recipes:** https://github.com/telvarost/MostlyModernRecipes-StationAPI

# Miscellaneous Tweaks

You will need ModMenu and GlassConfigAPI to change stairs recipe output and tweak other parts of the mod. See installation instructions below.

**There's a very high chance this mod will not work in multiplayer, as of right now it is purely for singleplayer**

## List of changes

All changes are default false/vanilla behavior unless otherwise specified.
* Allow chests to be opened even when a solid block is above them.
//* Allow editing signs with a feather (feather is consumed on use).
//* Allow coloring signs with dye (dye is consumed on use).
  * Coloring signs is incompatible with MojangFix.
  * Use [MojangFixStationAPI](https://modrinth.com/mod/misctweaks-stationapi)'s config to disable MojangFix signs to use colored signs.
    * Config is available through [ModMenu](https://modrinth.com/mod/modmenu-beta) and [GlassConfigAPI](https://modrinth.com/mod/glass-config-api).
//* Allow defusing TNT with shears (use left-click to defuse).
* Add chance for apples to drop from oak leaves.
  * Note: There currently seems to be a bug with GlassConfigAPI where Integer configs need to be saved twice to take effect
* Disable creeper explosions breaking blocks.
* Disable ghast explosions causing fire.
* Disable ghast explosions breaking blocks.
* Disable player/mobs trampling farmland.
* Disable player trampling farmland if player is not wearing boots.
* Disable player trampling farmland if player is wearing leather boots.
* Equalize base armor durability.
  * Helmet, Chestplate, Leggings, and Boots will all have the same durability
* Modern armor defense points.
  * Lower tiers have less defense and defense does not decrease with durability loss
* Shapeless Jack o’ Lanterns, default true.
* Stairs recipes craft 1-16 stairs.
  * Note: There currently seems to be a bug with GlassConfigAPI where Integer configs need to be saved twice to take effect
* Use right click to equip/swap pieces of armor, default true.

## Dispenser changes moved to DispenserTweaks
* See: https://github.com/telvarost/DispenserTweaks-StationAPI

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/babric/prism-instance
2. Install Java 17, set the instance to use it, and disable compatibility checks on the instance: https://adoptium.net/temurin/releases/
3. Add StationAPI to the mod folder for the instance: https://jenkins.glass-launcher.net/job/StationAPI/lastSuccessfulBuild/
4. (Optional) Add Mod Menu to the mod folder for the instance: https://github.com/calmilamsy/ModMenu/releases
5. (Optional) Add GlassConfigAPI 1.1.6+ to the mod folder for the instance: https://maven.glass-launcher.net/#/releases/net/glasslauncher/mods/GlassConfigAPI
6. Add this mod to the mod folder for the instance: https://github.com/telvarost/MiscTweaks-StationAPI/releases
7. Run and enjoy! 👍

## Feedback

Got any suggestions on what should be added next? Feel free to share it by [creating an issue](https://github.com/telvarost/MiscTweaks-StationAPI/issues/new). Know how to code and want to do it yourself? Then look below on how to get started.

## Contributing

Thanks for considering contributing! To get started fork this repository, make your changes, and create a PR. 

If you are new to StationAPI consider watching the following videos on Babric/StationAPI Minecraft modding: https://www.youtube.com/watch?v=9-sVGjnGJ5s&list=PLa2JWzyvH63wGcj5-i0P12VkJG7PDyo9T
