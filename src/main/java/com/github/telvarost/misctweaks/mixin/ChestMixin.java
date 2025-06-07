package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChestBlock.class)
public abstract class ChestMixin extends BlockWithEntity {

    public ChestMixin(int i) {
        super(i, Material.WOOD);
    }

    @WrapOperation(
            method = "onUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;shouldSuffocate(III)Z"
            )
    )
    public boolean miscTweaks_canUseWithBlockAbove(World instance, int x, int y, int z, Operation<Boolean> original) {
        if (Config.config.INTERACTIVE_BLOCK_CONFIG.enableChestsOpenWithBlockAbove) {
            return false;
        } else {
            return original.call(instance, x, y, z);
        }
    }
}
