package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Farmland;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Farmland.class)
public abstract class FarmlandMixin extends BlockBase {

    public FarmlandMixin(int i) {
        super(i, Material.DIRT);
    }


    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_onSteppedOnByPlayer(Level arg, int i, int j, int k, EntityBase arg2, CallbackInfo ci) {
        if (  (Config.ConfigFields.disableTrampleFarmlandWhenBarefoot)
           || (Config.ConfigFields.disableTrampleFarmlandWithLeatherBoots)
        ) {
            if (arg2 instanceof PlayerBase) {
                PlayerBase player = (PlayerBase)arg2;

                if (  (Config.ConfigFields.disableTrampleFarmlandWhenBarefoot)
                   && (null == player.inventory.armour[0])
                ) {
                    ci.cancel();
                }

                if (  (Config.ConfigFields.disableTrampleFarmlandWithLeatherBoots)
                   && (null != player.inventory.armour[0])
                   && (ItemBase.leatherBoots.id == player.inventory.armour[0].itemId)
                ) {
                    ci.cancel();
                }
            }
        }
    }

    @Redirect(
            method = "onSteppedOn",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            )
    )
    public int miscTweaks_onSteppedOnDisableTrample(Random rand, int value) {
        if (Config.ConfigFields.disableTramplingFarmland) {
            return -1;
        } else {
            return rand.nextInt(value);
        }
    }
}
