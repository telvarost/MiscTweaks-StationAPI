package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.living.FlyingBase;
import net.minecraft.entity.monster.Ghast;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Ghast.class)
public class GhastMixin extends FlyingBase implements MonsterEntityType {

    public GhastMixin(Level arg) {
        super(arg);
        this.texture = "/mob/ghast.png";
        this.setSize(4.0F, 4.0F);
        this.immuneToFire = true;
    }

    @Inject(
            method = "tickHandSwing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;spawnEntity(Lnet/minecraft/entity/EntityBase;)Z"
            )
    )
    public void miscTweaks_spawnGhastFireball(CallbackInfo ci) {
        if (Config.config.disableGhastExplosionBreakingBlocks)
        {
            ModHelper.ModHelperFields.numberOfGhastFireballs++;
        }
    }

}
