package com.github.telvarost.misctweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class Config {

    @GConfig(value = "config", visibleName = "MiscTweaks Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Stairs Crafting Recipe Output: 1-16")
        @Comment("Restart required for changes to take effect")
        public static Integer stairsOutput = 4;
    }
}
