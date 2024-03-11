package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.level.Level;
import net.minecraft.sortme.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow private Level level;

    @Shadow public double x;

    @Shadow public double y;

    @Shadow public double z;

    @Inject(method = "kaboomPhase2", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_cancelAllExplosionTileDamage(boolean renderParticles, CallbackInfo ci) {
        if (Config.ConfigFields.disableAllExplosionsBreakingBlocks) {
            if (renderParticles) {
                this.level.playSound(this.x, this.y, this.z, "random.explode", 4.0F, (1.0F + (this.level.rand.nextFloat() - this.level.rand.nextFloat()) * 0.2F) * 0.7F);
                this.level.addParticle("explode", this.x, this.y, this.z, 0, 1, 0);
                this.level.addParticle("smoke", this.x, this.y, this.z, 0, 1, 0);
                ci.cancel();
            } else {
                this.level.playSound(this.x, this.y, this.z, "random.explode", 4.0F, (1.0F + (this.level.rand.nextFloat() - this.level.rand.nextFloat()) * 0.2F) * 0.7F);
                ci.cancel();
            }
        }
    }

}
