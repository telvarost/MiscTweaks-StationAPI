package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SignBlockEntity.class)
public class TileEntitySignMixin extends BlockEntity {

    @WrapOperation(
            method = "readNbt",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;substring(II)Ljava/lang/String;"
            )
    )
    public String miscTweaks_readIdentifyingDataSubstring(String instance, int beginIndex, int endIndex, Operation<String> original) {
        if (!Config.config.enableColorSignsWithDye) {
            return instance.substring(beginIndex, endIndex);
        } else {
            int lineLimit = 15;

            /** - Allow first instance of a section character with its modifier */
            if (instance.contains("§")) {
                lineLimit = lineLimit + 2;
            }

            return original.call(instance, beginIndex, lineLimit);
        }
    }
}
