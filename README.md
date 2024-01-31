# MiscTweaks StationAPI Edition for Minecraft Beta 1.7.3

A StationAPI mod for Minecraft Beta 1.7.3 that tweaks random things that did not fit in my other mods such as allowing stairs recipe output to be changed.

**If you're looking for modern Minecraft recipes:** https://github.com/telvarost/MostlyModernRecipes-StationAPI

# Miscellaneous Tweaks

You will need ModMenu and GlassConfigAPI to change stairs recipe output and tweak other parts of the mod. See installation instructions below.

**There's a very high chance this mod will not work in multiplayer, as of right now it is purely for singleplayer**

## List of changes

* Allow editing signs with a feather (feather is consumed on use), default true.
* Allow coloring signs with dye (dye is consumed on use), default true.
* Disable creeper explosions breaking blocks, default false.
* Disable ghast explosions causing fire, default false.
* Disable ghast explosions breaking blocks, default false.
* Shapeless Jack o’ Lanterns, default true.
* Stairs recipes craft 1-16 stairs, default 4.
  * Note: There currently seems to be a bug with GlassConfigAPI where Integer configs need to be saved twice to take effect
* Modern Dispenser Fluid Placement, default false.
  * Allows dispensers to place and pick up water/lava with buckets
* Modded Dispenser Fluid Placement, default false.
  * Dispensers cannot pick up water/lava source blocks when dry
    * This is to fulfill the iron requirement of picking up water/lava and makes sense in terms of some real life fluid pumps that don't run when dry
  * Craft water/lava source blocks from water/lava buckets (bucket will be consumed) and use them in dispensers
    * Stack size of water/lava source blocks changed to 1
    * Water source blocks will evaporate if placed in the nether
    * Config option added for using lava source blocks as fuel in furnaces, default true
  * Dispensers will fizzle and fail to dispense water/lava blocks if there is a solid block in front of the dispenser

## Modern Dispenser Fluid Placement

![modern dispenser water](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/ModernDispenserWater.gif)
![modern dispenser lava](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/ModernDispenserLava.gif)

## Modded Dispenser Fluid Placement

![water block recipe](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/WaterBlockRecipe.PNG)
![lava block recipe](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/LavaBlockRecipe.PNG)
![lava block as fuel source](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/LavaBlockAsFuelSource.PNG)
### Water Pump Going Dry
![modded dispenser pump dry](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/ModdedDispenserWaterPumpDry.gif)
### Water Pump
![modded dispenser pump](https://github.com/telvarost/MiscTweaks-StationAPI/blob/main/images/ModdedDispenserWaterPump.gif)

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
