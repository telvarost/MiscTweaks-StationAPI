package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.ModHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

@Mixin(World.class)
public class WorldMixin {

    WorldMixin() {
        /** - Do nothing */
    }

    @Inject(
            method = "createExplosion(Lnet/minecraft/entity/Entity;DDDFZ)Lnet/minecraft/world/explosion/Explosion;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void miscTweaks_createExplosion(Entity arg, double d, double e, double f, float g, boolean bl, CallbackInfoReturnable<Explosion> cir) {
        if (0 < ModHelper.ModHelperFields.cancelDestroyBlocks) {
            World curLevel = ((World) (Object)this);
            Explosion var10 = new Explosion(curLevel, arg, d, e, f, g);
            var10.fire = bl;
            var10.explode();

            /** - Custom kaboomPhase2 that doesn't destroy blocks */
            curLevel.playSound(d, e, f, "random.explode", 4.0F, (1.0F + (curLevel.random.nextFloat() - curLevel.random.nextFloat()) * 0.2F) * 0.7F);
            ArrayList var2 = new ArrayList();
            var2.addAll(var10.damagedBlocks);

            for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
                BlockPos var4 = (BlockPos)var2.get(var3);
                int var5 = var4.x;
                int var6 = var4.y;
                int var7 = var4.z;
                if (bl) {
                    double var9 = (double)((float)var5 + curLevel.random.nextFloat());
                    double var11 = (double)((float)var6 + curLevel.random.nextFloat());
                    double var13 = (double)((float)var7 + curLevel.random.nextFloat());
                    double var15 = var9 - var10.x;
                    double var17 = var11 - var10.y;
                    double var19 = var13 - var10.z;
                    double var21 = (double) MathHelper.sqrt(var15 * var15 + var17 * var17 + var19 * var19);
                    var15 /= var21;
                    var17 /= var21;
                    var19 /= var21;
                    double var23 = 0.5 / (var21 / (double)var10.power + 0.1);
                    var23 *= (double)(curLevel.random.nextFloat() * curLevel.random.nextFloat() + 0.3F);
                    var15 *= var23;
                    var17 *= var23;
                    var19 *= var23;
                    curLevel.addParticle("explode", (var9 + var10.x * 1.0) / 2.0, (var11 + var10.y * 1.0) / 2.0, (var13 + var10.z * 1.0) / 2.0, var15, var17, var19);
                    curLevel.addParticle("smoke", var9, var11, var13, var15, var17, var19);
                }
            }

            /** - End of custom kaboomPhase2 and end of method */
            cir.setReturnValue(var10);
            ModHelper.ModHelperFields.cancelDestroyBlocks--;
        }
    }
}
