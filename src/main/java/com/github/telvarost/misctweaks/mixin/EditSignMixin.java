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
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.util.CharacterUtils.validCharacters;

@Environment(EnvType.CLIENT)
@Mixin(EditSign.class)
public class EditSignMixin extends ScreenBase {

    @Shadow private int cursorPos;

    @Shadow private TileEntitySign entity;

    @ModifyConstant(method = "keyPressed", constant = @Constant(intValue = 15))
    protected int miscTweaks_keyPressed(int value) {
        if (!Config.ConfigFields.enableColorSignsWithDye) {
            return 15;
        } else {
            int lineLimit = 15;

            /** - Allow first instance of a section character with its modifier */
            if (this.entity.lines[this.cursorPos].contains("§")) {
                lineLimit = lineLimit + 2;
            }

            return lineLimit;
        }
    }
}
