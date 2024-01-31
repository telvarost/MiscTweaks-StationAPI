package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.io.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntitySign.class)
public class TileEntitySignMixin extends TileEntityBase {

    @Shadow private boolean needsInitializing;

    @Shadow public String[] lines;

    @Inject(method = "readIdentifyingData", at = @At("HEAD"), cancellable = true)
    public void readIdentifyingData(CompoundTag arg, CallbackInfo ci) {
        if (!Config.ConfigFields.enableColorSignsWithDye) {
            return;
        }

        this.needsInitializing = false;
        super.readIdentifyingData(arg);

        for(int var2 = 0; var2 < 4; ++var2) {
            this.lines[var2] = arg.getString("Text" + (var2 + 1));

            /** - Allow first instance of a section character with its modifier */
            int lineLimit = 15;
            if (this.lines[var2].contains("§")) {
                lineLimit = lineLimit + 2;
            }

            if (this.lines[var2].length() > lineLimit) {
                this.lines[var2] = this.lines[var2].substring(0, lineLimit);
            }
        }

        ci.cancel();
    }
}
