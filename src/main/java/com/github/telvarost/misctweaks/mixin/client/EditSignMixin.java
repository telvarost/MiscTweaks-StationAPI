package com.github.telvarost.misctweaks.mixin.client;

import com.github.telvarost.misctweaks.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(SignEditScreen.class)
public class EditSignMixin extends Screen {

    @Shadow private int currentRow;

    @Shadow private SignBlockEntity sign;

    @ModifyConstant(method = "keyPressed", constant = @Constant(intValue = 15))
    protected int miscTweaks_keyPressed(int value) {
        if (!Config.config.BLOCK_ENTITY_CONFIG.enableColorSignsWithDye) {
            return 15;
        } else {
            int lineLimit = 15;

            /** - Allow first instance of a section character with its modifier */
            if (this.sign.texts[this.currentRow].contains("§")) {
                lineLimit = lineLimit + 2;
            }

            return lineLimit;
        }
    }
}
