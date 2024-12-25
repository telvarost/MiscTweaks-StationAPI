package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireballEntity.class)
abstract class FireballMixin extends Entity {

    public FireballMixin(World arg) {
        super(arg);
        this.setBoundingBoxSpacing(1.0F, 1.0F);
    }

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZ)Lnet/minecraft/world/explosion/Explosion;"
            )
    )
    public Explosion miscTweaks_handleExplosion(World instance, Entity source, double x, double y, double z, float power, boolean fire, Operation<Explosion> original) {
        if (0 < ModHelper.ModHelperFields.numberOfGhastFireballs)
        {
            fire = (fire && !Config.config.EXPLOSION_AND_FIRE_CONFIG.disableGhastExplosionCausingFire);
            ModHelper.ModHelperFields.cancelDestroyBlocks++;
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket++;
            ModHelper.ModHelperFields.numberOfGhastFireballs--;
        }

        return original.call(instance, source, x, y, z, power, fire);
    }

}
