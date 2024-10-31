package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.github.telvarost.misctweaks.events.init.TextureListener.*;

@Mixin(LogBlock.class)
public class LogBlockMixin extends Block {

    @Unique
    final int OAK_TYPE = 0;

    @Unique
    final int SPRUCE_TYPE = 1;

    @Unique
    final int BIRCH_TYPE = 2;

    @Unique
    private final int CUT_LOG_TEXTURE = 21;

    @Unique
    private final int OAK_LOG_TEXTURE = 20;

    @Unique
    private final int SPRUCE_LOG_TEXTURE = 116;

    @Unique
    private final int BIRCH_LOG_TEXTURE = 117;

    public LogBlockMixin(int id) {
        super(id, Material.WOOD);
        this.textureId = 20;
    }

    @Inject(
            method = "getTexture",
            at = @At("HEAD"),
            cancellable = true
    )
    public void miscTweaks_getTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        if (!Config.config.enableLogRotation) {
            return;
        }

        int textureId;
        int textureUpwards;
        int textureSidewaysCW;
        int textureSidewaysCCW;

        int textureTop = CUT_LOG_TEXTURE;
        int logType = meta & 0x3;
        int logRotation = meta & 0xC;

        if (SPRUCE_TYPE == logType) {
            textureUpwards = SPRUCE_LOG_TEXTURE;
            textureSidewaysCW = SIDEWAYS_CW_SPRUCE;
            textureSidewaysCCW = SIDEWAYS_CCW_SPRUCE;
        } else if (BIRCH_TYPE == logType) {
            textureUpwards = BIRCH_LOG_TEXTURE;
            textureSidewaysCW = SIDEWAYS_CW_BIRCH;
            textureSidewaysCCW = SIDEWAYS_CCW_BIRCH;
        } else {
            textureUpwards = OAK_LOG_TEXTURE;
            textureSidewaysCW = SIDEWAYS_CW_OAK;
            textureSidewaysCCW = SIDEWAYS_CCW_OAK;
        }

        if (0x4 == logRotation) {

            if (side == 2 || side == 3) {
                textureId = textureTop;
            } else if (side == 0 || side == 1) {
                textureId = textureUpwards;
            } else if (side == 5) {
                textureId = textureSidewaysCW;
            } else {
                textureId = textureSidewaysCCW;
            }

        } else if (0x8 == logRotation) {

            if (side == 4 || side == 5) {
                textureId = textureTop;
            } else if (side == 0 || side == 1) {
                textureId = textureSidewaysCW;
            } else if (side == 3) {
                textureId = textureSidewaysCW;
            } else {
                textureId = textureSidewaysCCW;
            }

        } else {
            if (side == 1 || side == 0) {
                textureId = textureTop;
            } else {
                textureId = textureUpwards;
            }
        }

        cir.setReturnValue(textureId);
    }

    @Inject(
            method = "getDroppedItemMeta",
            at = @At("RETURN"),
            cancellable = true
    )
    protected void getDroppedItemMeta(int blockMeta, CallbackInfoReturnable<Integer> cir) {
        if (!Config.config.enableLogRotation) {
            return;
        }

        cir.setReturnValue(blockMeta & 0x3);
    }
}
