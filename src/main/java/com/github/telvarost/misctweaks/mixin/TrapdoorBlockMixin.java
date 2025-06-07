package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.Block;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.item.Item.SLIMEBALL;

@Mixin(TrapdoorBlock.class)
public class TrapdoorBlockMixin extends Block {

    public TrapdoorBlockMixin(int i, Material material) {
        super(i, material);
    }

    @Inject(
            method = "onUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;setBlockMeta(IIII)V"
            ),
            cancellable = true
    )
    public void miscTweaks_onUse(World world, int x, int y, int z, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.INTERACTIVE_BLOCK_CONFIG.enableGlueTrapdoorsWithSlimeballs) {
            return;
        }

        int blockMeta = world.getBlockMeta(x, y, z);

        if (  (null != player)
           && (null != player.inventory)
           && (null != player.inventory.getSelectedItem())
           && (SLIMEBALL.id == player.inventory.getSelectedItem().itemId)
        ) {
            cir.setReturnValue(false);
        } else if ((blockMeta & 8) != 0) {
            world.playSound(player,
                            soundGroup.getBreakSound(),
                            1.0F,
                            ((world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "setOpen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;setBlockMeta(IIII)V"
            ),
            cancellable = true
    )
    public void miscTweaks_setOpen(World world, int x, int y, int z, boolean open, CallbackInfo ci) {
        if (!Config.config.INTERACTIVE_BLOCK_CONFIG.enableGlueTrapdoorsWithSlimeballs) {
            return;
        }

        int blockMeta = world.getBlockMeta(x, y, z);

        if ((blockMeta & 8) != 0) {
            world.playSound((float)x + 0.5F,
                            (float)y + 0.5F,
                            (float)z + 0.5F,
                            soundGroup.getBreakSound(),
                            1.0F,
                            ((world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            ci.cancel();
        }
    }
}
