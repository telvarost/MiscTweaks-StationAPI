package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.LightningEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

    @WrapOperation(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/FireBlock;canPlaceAt(Lnet/minecraft/world/World;III)Z"
            )
    )
    public boolean miscTweaks_lightningEntityInit(FireBlock instance, World world, int x, int y, int z, Operation<Boolean> original) {
        if (Config.config.EXPLOSION_AND_FIRE_CONFIG.disableLightningStrikeCausingFire) {
            return false;
        } else {
            return original.call(instance, world, x, y, z);
        }
    }

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/FireBlock;canPlaceAt(Lnet/minecraft/world/World;III)Z"
            )
    )
    public boolean miscTweaks_tick(FireBlock instance, World world, int x, int y, int z, Operation<Boolean> original) {
        if (Config.config.EXPLOSION_AND_FIRE_CONFIG.disableLightningStrikeCausingFire) {
            return false;
        } else {
            return original.call(instance, world, x, y, z);
        }
    }
}
