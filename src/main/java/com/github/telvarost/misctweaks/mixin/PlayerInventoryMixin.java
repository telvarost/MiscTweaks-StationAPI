package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory {

    @WrapOperation(
            method = "getTotalArmorDurability",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getDamage2()I"
            )
    )
    public int miscTweaks_getArmourValue(ItemStack instance, Operation<Integer> original) {
        if (Config.config.modernArmorDefensePoints) {
            return 0;
        } else {
            return original.call(instance);
        }
    }
}
