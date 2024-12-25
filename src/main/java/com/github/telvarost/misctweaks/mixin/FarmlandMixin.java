package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandMixin extends Block {

    public FarmlandMixin(int i) {
        super(i, Material.SOIL);
    }

    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_onSteppedOnByPlayer(World arg, int i, int j, int k, Entity arg2, CallbackInfo ci) {
        if (  (Config.config.MOB_CONFIG.disableTrampleFarmlandWhenBarefoot)
           || (Config.config.MOB_CONFIG.disableTrampleFarmlandWithLeatherBoots)
        ) {
            if (arg2 instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)arg2;

                if (  (Config.config.MOB_CONFIG.disableTrampleFarmlandWhenBarefoot)
                   && (null == player.inventory.armor[0])
                ) {
                    ci.cancel();
                }

                if (  (Config.config.MOB_CONFIG.disableTrampleFarmlandWithLeatherBoots)
                   && (null != player.inventory.armor[0])
                   && (Item.LEATHER_BOOTS.id == player.inventory.armor[0].itemId)
                ) {
                    ci.cancel();
                }
            }
        }
    }

    @WrapOperation(
            method = "onSteppedOn",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            )
    )
    public int miscTweaks_onSteppedOnDisableTrample(Random instance, int value, Operation<Integer> original) {
        if (Config.config.MOB_CONFIG.disableTramplingFarmland) {
            return -1;
        } else {
            return original.call(instance, value);
        }
    }
}
