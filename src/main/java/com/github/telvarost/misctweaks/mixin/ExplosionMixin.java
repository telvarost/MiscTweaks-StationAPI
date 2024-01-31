package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.level.Level;
import net.minecraft.sortme.Explosion;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow public Set damagedTiles;


    @Inject(method = "kaboomPhase2", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_addBlocksToDamage(boolean par1, CallbackInfo ci) {
        if (ModHelper.ModHelperFields.cancelDestroyBlocks) {
            ModHelper.ModHelperFields.numberOfDamagedBlocks += this.damagedTiles.size();
            ModHelper.ModHelperFields.cancelDestroyBlocks = false;
        }
    }

    @ModifyVariable(
            method = "kaboomPhase2",
            at = @At(value = "STORE"),
            ordinal = 3
    )
    public int miscTweaks_kaboomPhase2(int tileId) {
        if (0 < ModHelper.ModHelperFields.numberOfDamagedBlocks) {
            ModHelper.ModHelperFields.numberOfDamagedBlocks--;
            return 0;
        } else {
            return tileId;
        }
    }

}
