package com.github.telvarost.misctweaks;

import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class MiscTweaksMixinPlugin implements IMixinConfigPlugin {
    public static GlassYamlFile config;

    @Override
    public void onLoad(String mixinPackage) {
        File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "misctweaks/config.yml");

        config = new GlassYamlFile();
        try {
            config.load(file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null; // null = default behaviour
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null; // null = I don't wish to append any mixin
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        Config.config.enableGhastFireballsToInstaKillGhasts = config.getBoolean("enableGhastFireballsToInstaKillGhasts", false);
        Config.config.enableZombieDropItem = config.getBoolean("enableZombieDropItem", false);
        Config.config.enableZombiePigmanDropItem = config.getBoolean("enableZombiePigmanDropItem", false);
        Config.config.enableLogRotation = config.getBoolean("enableLogRotation", false);

        if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.GhastDamageMixin")) {
            return Config.config.enableGhastFireballsToInstaKillGhasts;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.ZombieMixin")) {
            return Config.config.enableZombieDropItem;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.ZombiePigmanMixin")) {
            return Config.config.enableZombiePigmanDropItem;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.LogBlockMixin")) {
            return Config.config.enableLogRotation;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.LogItemMixin")) {
            return Config.config.enableLogRotation;
        } else if (mixinClassName.equals("com.github.telvarost.misctweaks.mixin.client.BlockRenderManagerMixin")) {
            return Config.config.enableLogRotation;
        } else {
            return true;
        }
    }
}
