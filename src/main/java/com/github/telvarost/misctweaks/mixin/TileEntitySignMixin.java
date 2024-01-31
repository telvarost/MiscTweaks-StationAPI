package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.io.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntitySign.class)
public class TileEntitySignMixin extends TileEntityBase {

    @Shadow private boolean needsInitializing;

    @Shadow public String[] lines;

    @Redirect(
            method = "readIdentifyingData",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;substring(II)Ljava/lang/String;"
            )
    )
    public String miscTweaks_readIdentifyingDataSubstring(String instance, int beginIndex, int endIndex) {
        if (!Config.ConfigFields.enableColorSignsWithDye) {
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
