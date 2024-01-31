package com.github.telvarost.misctweaks.mixin;


import com.github.telvarost.misctweaks.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.ingame.EditSign;
import net.minecraft.tileentity.TileEntitySign;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.util.CharacterUtils.validCharacters;

@Environment(EnvType.CLIENT)
@Mixin(EditSign.class)
public class EditSignMixin extends ScreenBase {

    @Shadow private int cursorPos;

    @Shadow private TileEntitySign entity;

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    protected void keyPressed(char c, int i, CallbackInfo ci) {
        if (!Config.ConfigFields.enableColorSignsWithDye) {
            return;
        }

        if (i == 200) {
            this.cursorPos = this.cursorPos - 1 & 3;
        }

        if (i == 208 || i == 28) {
            this.cursorPos = this.cursorPos + 1 & 3;
        }

        if (i == 14 && this.entity.lines[this.cursorPos].length() > 0) {
            this.entity.lines[this.cursorPos] = this.entity.lines[this.cursorPos].substring(0, this.entity.lines[this.cursorPos].length() - 1);
        }

        /** - Skip first instance of a section character with its modifier */
        int lineLength = this.entity.lines[this.cursorPos].length();
        if (this.entity.lines[this.cursorPos].contains("§")) {
            lineLength = lineLength - 2;
        }

        if (validCharacters.indexOf(c) >= 0 && lineLength < 15) {
            StringBuilder var10000 = new StringBuilder();
            String[] var10002 = this.entity.lines;
            int var10004 = this.cursorPos;
            var10002[var10004] = var10000.append(var10002[var10004]).append(c).toString();
        }

        ci.cancel();
    }
}
