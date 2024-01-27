package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.level.Level;
import net.minecraft.sortme.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;
import java.util.List;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Redirect(
            method = "kaboomPhase2",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z"
            )
    )
    public boolean miscTweaks_addBlocksToDamage(List instance, Collection<?> es) {
        if (ModHelper.ModHelperFields.cancelDestroyBlocks) {
            ModHelper.ModHelperFields.numberOfDamagedBlocks += es.size();
            ModHelper.ModHelperFields.cancelDestroyBlocks = false;
        }

        return instance.addAll(es);
    }

    @Redirect(
            method = "kaboomPhase2",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;getTileId(III)I"
            )
    )
    public int miscTweaks_kaboomPhase2(Level instance, int j, int k, int i) {
        if (0 < ModHelper.ModHelperFields.numberOfDamagedBlocks) {
            ModHelper.ModHelperFields.numberOfDamagedBlocks--;
            return 0;
        } else {
            return instance.getTileId(i, j , k);
        }
    }

}
