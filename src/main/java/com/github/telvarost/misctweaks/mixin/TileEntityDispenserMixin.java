package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntityDispenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(TileEntityDispenser.class)
abstract class TileEntityDispenserMixin extends TileEntityBase implements InventoryBase {

    @Shadow private ItemInstance[] contents;

    @Shadow private Random rand;

    public TileEntityDispenserMixin() {
    }


    @Inject(method = "getItemToDispense", at = @At("HEAD"), cancellable = true)
    private void dispense(CallbackInfoReturnable<ItemInstance> cir) {
        if (  (!Config.ConfigFields.moddedDispenserFluidPlacement)
           && (!Config.ConfigFields.modernDispenserFluidPlacement)
           )
        {
            return;
        }

        int var1 = -1;
        int var2 = 1;
        ModHelper.ModHelperFields.emptySlotAvailable = -1;

        for(int var3 = 0; var3 < this.contents.length; ++var3) {
            if (this.contents[var3] != null) {
                if (this.rand.nextInt(var2++) == 0) {
                    var1 = var3;
                    ModHelper.ModHelperFields.lastSlotDispensed = var1;
                }
            }
            else
            {
                ModHelper.ModHelperFields.emptySlotAvailable = var3;
            }
        }

        if (var1 >= 0) {
            cir.setReturnValue(this.takeInventoryItem(var1, 1));
        } else {
            cir.setReturnValue(null);
        }
    }
}
