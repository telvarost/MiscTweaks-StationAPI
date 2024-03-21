package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.item.ItemBase;
import net.minecraft.item.armour.Armour;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Armour.class)
public abstract class ArmourMixin extends ItemBase {

    @Shadow @Final private static int[] MAXIMUM_DAMAGE;

    @Mutable
    @Shadow @Final public int maximumDamage;

    @Shadow @Final public int armourSlot;

    public ArmourMixin(int i, int j, int k, int l) {
        super(i);
    }

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void miscTweaks_changeArmorDurability(int i, int j, int k, int l, CallbackInfo ci) {
        if (Config.config.equalizeBaseArmorDurability) {
            this.setDurability(14 * 3 << j);
        }

        if (Config.config.modernArmorDefensePoints) {
            if (4 == k) {
                MAXIMUM_DAMAGE[0] = 2;
                MAXIMUM_DAMAGE[1] = 5;
                MAXIMUM_DAMAGE[2] = 3;
                MAXIMUM_DAMAGE[3] = 1;
            } else if (3 == k) {
                MAXIMUM_DAMAGE[0] = 3;
                MAXIMUM_DAMAGE[1] = 8;
                MAXIMUM_DAMAGE[2] = 6;
                MAXIMUM_DAMAGE[3] = 3;
            } else if (2 == k) {
                MAXIMUM_DAMAGE[0] = 2;
                MAXIMUM_DAMAGE[1] = 6;
                MAXIMUM_DAMAGE[2] = 5;
                MAXIMUM_DAMAGE[3] = 2;
            } else if (1 == k) {
                MAXIMUM_DAMAGE[0] = 2;
                MAXIMUM_DAMAGE[1] = 5;
                MAXIMUM_DAMAGE[2] = 4;
                MAXIMUM_DAMAGE[3] = 1;
            } else if (0 == k) {
                MAXIMUM_DAMAGE[0] = 1;
                MAXIMUM_DAMAGE[1] = 3;
                MAXIMUM_DAMAGE[2] = 2;
                MAXIMUM_DAMAGE[3] = 1;
            }

            this.maximumDamage = MAXIMUM_DAMAGE[l];
        }
    }
}
