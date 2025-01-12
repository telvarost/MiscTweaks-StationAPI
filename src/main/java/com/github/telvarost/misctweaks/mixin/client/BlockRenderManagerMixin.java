package com.github.telvarost.misctweaks.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {

    @Shadow private BlockView blockView;

    @Shadow private int eastFaceRotation;

    @Shadow private int westFaceRotation;

    @Shadow private int southFaceRotation;

    @Shadow private int northFaceRotation;

    @Shadow private int topFaceRotation;

    @Shadow private int bottomFaceRotation;

    @Shadow public abstract boolean renderBlock(Block block, int x, int y, int z);

    @WrapOperation(
            method = "render(Lnet/minecraft/block/Block;IF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getRenderType()I"
            )
    )
    public int render(Block instance, Operation<Integer> original) {
        int renderType = original.call(instance);

        if (renderType == 18) {
            renderType = 0;
        }

        return renderType;
    }

    @Inject(
            method = "isSideLit",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void isSideLit(int renderType, CallbackInfoReturnable<Boolean> cir) {
        if (renderType == 18) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "render(Lnet/minecraft/block/Block;III)Z",
            at = @At("TAIL"),
            cancellable = true
    )
    public void render(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            int var5 = block.getRenderType();

            if (var5 == 18) {
                block.updateBoundingBox(this.blockView, x, y, z);
                cir.setReturnValue(renderLog(block, x, y, z));
            }
        }
    }

    @Unique
    private boolean renderLog(Block block, int x, int y, int z) {
        int blockMeta = this.blockView.getBlockMeta(x, y, z);
        int logRotation = blockMeta & 0xC;

        if (0x4 == logRotation) {
            this.eastFaceRotation = 0;
            this.westFaceRotation = 0;
            this.southFaceRotation = 1;
            this.northFaceRotation = 2;
            this.topFaceRotation = 0;
            this.bottomFaceRotation = 0;
        } else if (0x8 == logRotation) {
            this.eastFaceRotation = 2;
            this.westFaceRotation = 1;
            this.southFaceRotation = 0;
            this.northFaceRotation = 0;
            this.topFaceRotation = 1;
            this.bottomFaceRotation = 2;
        }

        this.renderBlock(block, x, y, z);

        this.eastFaceRotation = 0;
        this.westFaceRotation = 0;
        this.southFaceRotation = 0;
        this.northFaceRotation = 0;
        this.topFaceRotation = 0;
        this.bottomFaceRotation = 0;

        return true;
    }
}
