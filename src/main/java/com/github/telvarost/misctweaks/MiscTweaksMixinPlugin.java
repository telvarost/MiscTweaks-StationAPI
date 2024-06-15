package com.github.telvarost.misctweaks;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public final class MiscTweaksMixinPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (ModHelper.ModHelperFields.loadMixinConfigs) {
            ModHelper.ModHelperFields.loadMixinConfigs = false;

            try {
                JsonObject configObject = Jankson
                        .builder()
                        .build()
                        .load(new File("config/misctweaks", "config.json"));

                Config.config.enableGhastFireballsToInstaKillGhasts = configObject.getBoolean("enableGhastFireballsToInstaKillGhasts", false);
                Config.config.enableZombieDropItem = configObject.getBoolean("enableZombieDropItem", false);
                Config.config.enableZombiePigmanDropItem = configObject.getBoolean("enableZombiePigmanDropItem", false);
            } catch (IOException ex) {
                System.out.println("Couldn't read the config file" + ex.toString());
            } catch (SyntaxError error) {
                System.out.println("Couldn't read the config file" + error.getMessage());
                System.out.println(error.getLineMessage());
            }
        }

        if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.GhastDamageMixin")) {
            return Config.config.enableGhastFireballsToInstaKillGhasts;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.ZombieMixin")) {
            return Config.config.enableZombieDropItem;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.ZombiePigmanMixin")) {
            return Config.config.enableZombiePigmanDropItem;
        } else {
            return true;
        }
    }

    // Boilerplate

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}