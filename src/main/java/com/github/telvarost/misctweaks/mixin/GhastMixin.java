package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.living.FlyingBase;
import net.minecraft.entity.monster.Ghast;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Ghast.class)
public class GhastMixin extends FlyingBase implements MonsterEntityType {

    public GhastMixin(Level arg) {
        super(arg);
        this.texture = "/mob/ghast.png";
        this.setSize(4.0F, 4.0F);
        this.immuneToFire = true;
    }

    @Redirect(
            method = "tickHandSwing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;spawnEntity(Lnet/minecraft/entity/EntityBase;)Z"
            )
    )
    public boolean miscTweaks_spawnGhastFireball(Level instance, EntityBase entityBase) {
        if (Config.ConfigFields.disableGhastExplosionBreakingBlocks)
        {
            ModHelper.ModHelperFields.numberOfGhastFireballs++;
        }

        return instance.spawnEntity(entityBase);
    }

}
