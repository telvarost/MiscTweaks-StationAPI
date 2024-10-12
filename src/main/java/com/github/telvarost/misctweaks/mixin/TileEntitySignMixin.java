package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SignBlockEntity.class)
public class TileEntitySignMixin extends BlockEntity {

    @Redirect(
            method = "readNbt",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;substring(II)Ljava/lang/String;"
            )
    )
    public String miscTweaks_readIdentifyingDataSubstring(String instance, int beginIndex, int endIndex) {
        if (!Config.config.enableColorSignsWithDye) {
            return instance.substring(beginIndex, endIndex);
        } else {
            int lineLimit = 15;

            /** - Allow first instance of a section character with its modifier */
            if (instance.contains("§")) {
                lineLimit = lineLimit + 2;
            }

            return instance.substring(beginIndex, lineLimit);
        }
    }
}
