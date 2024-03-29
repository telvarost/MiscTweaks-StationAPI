package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public class CreeperMixin extends MonsterBase {

    public CreeperMixin(Level arg) {
        super(arg);
        this.texture = "/mob/creeper.png";
    }

    @Inject(
            method = "tryAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/monster/Creeper;isCharged()Z"
            )
    )
    public void miscTweaks_tryAttackBeforeCreateExplosion(EntityBase f, float par2, CallbackInfo ci) {
        if (Config.config.disableCreeperExplosionBreakingBlocks)
        {
            ModHelper.ModHelperFields.cancelDestroyBlocks++;
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket++;
        }
    }

}
