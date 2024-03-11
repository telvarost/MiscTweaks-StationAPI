package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.projectile.Fireball;
import net.minecraft.level.Level;
import net.minecraft.sortme.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Fireball.class)
abstract class FireballMixin extends EntityBase {

    public FireballMixin(Level arg) {
        super(arg);
        this.setSize(1.0F, 1.0F);
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;createExplosion(Lnet/minecraft/entity/EntityBase;DDDFZ)Lnet/minecraft/sortme/Explosion;"
            )
    )
    public Explosion miscTweaks_handleExplosion(Level instance, EntityBase arg, double d, double e, double f, float g, boolean bl) {
        if (0 < ModHelper.ModHelperFields.numberOfGhastFireballs)
        {
            bl = (bl && !Config.ConfigFields.disableGhastExplosionCausingFire);
            ModHelper.ModHelperFields.cancelDestroyBlocks = true;
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket = true;
            ModHelper.ModHelperFields.numberOfGhastFireballs--;
        }

        return instance.createExplosion(arg, d, e, f, g, bl);
    }

}
