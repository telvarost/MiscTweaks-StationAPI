package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireballEntity.class)
abstract class FireballMixin extends Entity {

    public FireballMixin(World arg) {
        super(arg);
        this.setBoundingBoxSpacing(1.0F, 1.0F);
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZ)Lnet/minecraft/world/explosion/Explosion;"
            )
    )
    public Explosion miscTweaks_handleExplosion(World instance, Entity arg, double d, double e, double f, float g, boolean bl) {
        if (0 < ModHelper.ModHelperFields.numberOfGhastFireballs)
        {
            bl = (bl && !Config.config.disableGhastExplosionCausingFire);
            ModHelper.ModHelperFields.cancelDestroyBlocks++;
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket++;
            ModHelper.ModHelperFields.numberOfGhastFireballs--;
        }

        return instance.createExplosion(arg, d, e, f, g, bl);
    }

}
