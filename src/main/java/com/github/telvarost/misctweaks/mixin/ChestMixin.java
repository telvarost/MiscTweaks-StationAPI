package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChestBlock.class)
public abstract class ChestMixin extends BlockWithEntity {

    public ChestMixin(int i) {
        super(i, Material.WOOD);
    }

    @Redirect(
            method = "onUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;shouldSuffocate(III)Z"
            )
    )
    public boolean miscTweaks_canUseWithBlockAbove(World instance, int i, int j, int k) {
        if (Config.config.enableChestsOpenWithBlockAbove) {
            return false;
        } else {
            return instance.shouldSuffocate(i, j, k);
        }
    }
}
