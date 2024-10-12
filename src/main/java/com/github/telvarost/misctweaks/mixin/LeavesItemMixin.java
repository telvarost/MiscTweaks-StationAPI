package com.github.telvarost.misctweaks.mixin;

import net.minecraft.item.BlockItem;
import net.minecraft.item.LeavesBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlockItem.class)
public class LeavesItemMixin extends BlockItem {
    public LeavesItemMixin(int i) {
        super(i);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }


    @Inject(
            method = "getPlacementMetadata",
            at = @At("RETURN"),
            cancellable = true
    )
    public void miscTweaks_getMetaData(int i, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(i | 0x4);
    }
}
