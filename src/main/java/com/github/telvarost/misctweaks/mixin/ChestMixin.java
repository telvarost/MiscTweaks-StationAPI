package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Chest;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Chest.class)
public abstract class ChestMixin extends BlockWithEntity {

    public ChestMixin(int i) {
        super(i, Material.WOOD);
    }

    @Redirect(
            method = "canUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;canSuffocate(III)Z"
            )
    )
    public boolean miscTweaks_canUseWithBlockAbove(Level instance, int i, int j, int k) {
        if (Config.ConfigFields.enableChestsOpenWithBlockAbove) {
            return false;
        } else {
            return instance.canSuffocate(i, j, k);
        }
    }
}
