//package com.github.telvarost.misctweaks.mixin;
//
//import com.github.telvarost.misctweaks.Config;
//import net.glasslauncher.mods.api.gcapi.api.GCAPI;
//import net.minecraft.block.BlockBase;
//import net.minecraft.item.ItemBase;
//import net.modificationstation.stationapi.api.util.Identifier;
//import org.jetbrains.annotations.Nullable;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(GCAPI.class)
//public class GCAPIMixin {
//    public GCAPIMixin() {
//    }
//
//    @Inject(method = "reloadConfig(Lnet/modificationstation/stationapi/api/util/Identifier;Ljava/lang/String;)V", at = @At("TAIL"), cancellable = true)
//    private static void reloadConfig(Identifier configID, String overrideConfigJson, CallbackInfo ci) {
//        ItemBase fluidItem = BlockBase.FLOWING_WATER.asItem();
//
//        if (Config.ConfigFields.moddedDispenserFluidPlacement) {
//            if (null != fluidItem) {
//                if (1 != fluidItem.getMaxStackSize()) {
//                    fluidItem.setMaxStackSize(1);
//                }
//            }
//        } else {
//            if (null != fluidItem) {
//                if (64 != fluidItem.getMaxStackSize()) {
//                    fluidItem.setMaxStackSize(64);
//                }
//            }
//        }
//    }
//}