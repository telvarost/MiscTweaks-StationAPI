package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow private World world;

    @Shadow public double x;

    @Shadow public double y;

    @Shadow public double z;

    @Inject(method = "playExplosionSound", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_cancelAllExplosionTileDamage(boolean renderParticles, CallbackInfo ci) {
        if (Config.config.EXPLOSION_AND_FIRE_CONFIG.disableAllExplosionsBreakingBlocks) {
            if (renderParticles) {
                world.playSound(x, y, z, "random.explode", 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
                world.addParticle("explode", x, y, z, 0, 1, 0);
                world.addParticle("smoke", x, y, z, 0, 1, 0);
                ci.cancel();
            } else {
                world.playSound(x, y, z, "random.explode", 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
                ci.cancel();
            }
        }
    }

    @ModifyConstant(method = "playExplosionSound", constant = @Constant(floatValue = 0.3F))
    public float playExplosionSound(float constant) {
        if (0.0F < Config.config.EXPLOSION_AND_FIRE_CONFIG.chanceBlocksDropWhenExploded) {
            return Config.config.EXPLOSION_AND_FIRE_CONFIG.chanceBlocksDropWhenExploded;
        } else {
            return -1.0F;
        }
    }

}
