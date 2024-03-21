package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Creeper.class)
public class CreeperMixin extends MonsterBase {

    public CreeperMixin(Level arg) {
        super(arg);
        this.texture = "/mob/creeper.png";
    }

    @Redirect(
            method = "tryAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/monster/Creeper;isCharged()Z"
            )
    )
    public boolean miscTweaks_tryAttackBeforeCreateExplosion(Creeper instance) {
        if (Config.config.disableCreeperExplosionBreakingBlocks)
        {
            ModHelper.ModHelperFields.cancelDestroyBlocks++;
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket++;
        }

        return instance.isCharged();
    }

}
