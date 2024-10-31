package com.github.telvarost.misctweaks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Environment(EnvType.CLIENT)
    @Override
    public int getRenderType() {
        return 18;
    }

    @Inject(
            method = "getTexture",
            at = @At("HEAD"),
            cancellable = true
    )
    public void miscTweaks_getTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        int textureId;
        int textureSideways;
        int textureTop = CUT_LOG_TEXTURE;
        int logType = meta & 0x3;
        int logRotation = meta & 0xC;

        if (SPRUCE_TYPE == logType) {
            textureSideways = SPRUCE_LOG_TEXTURE;
        } else if (BIRCH_TYPE == logType) {
            textureSideways = BIRCH_LOG_TEXTURE;
        } else {
            textureSideways = OAK_LOG_TEXTURE;
        }

        if (0x4 == logRotation) {

            if (side == 2 || side == 3) {
                textureId = textureTop;
            } else {
                textureId = textureSideways;
            }

        } else if (0x8 == logRotation) {

            if (side == 4 || side == 5) {
                textureId = textureTop;
            } else {
                textureId = textureSideways;
            }

        } else {
            if (side == 1 || side == 0) {
                textureId = textureTop;
            } else {
                textureId = textureSideways;
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
        cir.setReturnValue(blockMeta & 0x3);
    }
}
