package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Fluid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Fluid.class)
abstract class FluidMixin extends BlockBase {
    @Shadow protected abstract void method_1222(Level arg, int i, int j, int k);

    public FluidMixin(int i, Material arg) {
        super(i, (arg == Material.LAVA ? 14 : 12) * 16 + 13, arg);
        float var3 = 0.0F;
        float var4 = 0.0F;
        this.setBoundingBox(0.0F + var4, 0.0F + var3, 0.0F + var4, 1.0F + var4, 1.0F + var3, 1.0F + var4);
        this.setTicksRandomly(true);
    }

    @Inject(method = "onBlockPlaced", at = @At("TAIL"), cancellable = true)
    public void miscTweaks_onBlockPlaced(Level arg, int i, int j, int k, CallbackInfo ci) {
        ItemBase fluidItem = this.asItem();
        //ItemBase fluidItem = BlockBase.FLOWING_WATER.asItem();
        if (Config.ConfigFields.moddedDispenserFluidPlacement) {
            if (null != fluidItem) {
                if (1 != fluidItem.getMaxStackSize()) {
                    fluidItem.setMaxStackSize(1);
                }
            }

            if (arg.isAir(i, j, k) || !arg.getMaterial(i, j, k).isSolid()) {
                int blockId = arg.getTileId(i, j, k);
                if (arg.dimension.evaporatesWater && blockId == BlockBase.FLOWING_WATER.id) {
                    PlayerBase player = PlayerHelper.getPlayerFromGame();
                    if (null != player) {
                        float var4 = 1.0F;
                        double var7 = player.prevX + (player.x - player.prevX) * (double) var4;
                        double var9 = player.prevY + (player.y - player.prevY) * (double) var4 + 1.62 - (double) player.standingEyeHeight;
                        double var11 = player.prevZ + (player.z - player.prevZ) * (double) var4;
                        arg.playSound(var7 + 0.5, var9 + 0.5, var11 + 0.5, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
                    }

                    for (int var28 = 0; var28 < 8; ++var28) {
                        arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
                    }
                    arg.placeBlockWithMetaData(i, j, k, 0, 0);
                }
            }
        }
        else
        {
            if (null != fluidItem) {
                if (64 != fluidItem.getMaxStackSize()) {
                    fluidItem.setMaxStackSize(64);
                }
            }
        }
    }
}
