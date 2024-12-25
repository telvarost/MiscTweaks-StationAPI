package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Monster;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GhastEntity.class)
public class GhastMixin extends FlyingEntity implements Monster {

    public GhastMixin(World arg) {
        super(arg);
        this.texture = "/mob/ghast.png";
        this.setBoundingBoxSpacing(4.0F, 4.0F);
        this.fireImmune = true;
    }

    @Inject(
            method = "tickLiving",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    public void miscTweaks_spawnGhastFireball(CallbackInfo ci) {
        if (Config.config.EXPLOSION_AND_FIRE_CONFIG.disableGhastExplosionBreakingBlocks)
        {
            ModHelper.ModHelperFields.numberOfGhastFireballs++;
        }
    }

}
