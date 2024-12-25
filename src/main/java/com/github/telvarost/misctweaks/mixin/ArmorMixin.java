package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorItem.class)
public abstract class ArmorMixin extends Item {

    @Shadow @Final private static int[] PROTECTION_BY_SLOT;

    @Mutable
    @Shadow @Final public int maxProtection;

    public ArmorMixin(int i, int j, int k, int l) {
        super(i);
    }

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void miscTweaks_changeArmorDurability(int i, int j, int k, int l, CallbackInfo ci) {
        if (Config.config.ARMOR_CONFIG.equalizeBaseArmorDurability) {
            this.setMaxDamage(14 * 3 << j);
        }

        if (Config.config.ARMOR_CONFIG.modernArmorDefensePoints) {
            if (4 == k) {
                PROTECTION_BY_SLOT[0] = 2;
                PROTECTION_BY_SLOT[1] = 5;
                PROTECTION_BY_SLOT[2] = 3;
                PROTECTION_BY_SLOT[3] = 1;
            } else if (3 == k) {
                PROTECTION_BY_SLOT[0] = 3;
                PROTECTION_BY_SLOT[1] = 8;
                PROTECTION_BY_SLOT[2] = 6;
                PROTECTION_BY_SLOT[3] = 3;
            } else if (2 == k) {
                PROTECTION_BY_SLOT[0] = 2;
                PROTECTION_BY_SLOT[1] = 6;
                PROTECTION_BY_SLOT[2] = 5;
                PROTECTION_BY_SLOT[3] = 2;
            } else if (1 == k) {
                PROTECTION_BY_SLOT[0] = 2;
                PROTECTION_BY_SLOT[1] = 5;
                PROTECTION_BY_SLOT[2] = 4;
                PROTECTION_BY_SLOT[3] = 1;
            } else if (0 == k) {
                PROTECTION_BY_SLOT[0] = 1;
                PROTECTION_BY_SLOT[1] = 3;
                PROTECTION_BY_SLOT[2] = 2;
                PROTECTION_BY_SLOT[3] = 1;
            }

            this.maxProtection = PROTECTION_BY_SLOT[l];
        }
    }
}
