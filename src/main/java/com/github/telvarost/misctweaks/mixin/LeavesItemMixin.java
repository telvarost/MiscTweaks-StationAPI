package com.github.telvarost.misctweaks.mixin;

import net.minecraft.item.Block;
import net.minecraft.item.Leaves;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Leaves.class)
public class LeavesItemMixin extends Block {
    public LeavesItemMixin(int i) {
        super(i);
        this.setDurability(0);
        this.setHasSubItems(true);
    }


    @Inject(
            method = "getMetaData",
            at = @At("RETURN"),
            cancellable = true
    )
    public void miscTweaks_getMetaData(int i, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(i | 0x4);
    }
}
