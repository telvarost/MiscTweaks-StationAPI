package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.Dye;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntitySign;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Dye.class)
public class DyeMixin extends ItemBase {

    public DyeMixin(int i) {
        super(i);
        this.setHasSubItems(true);
        this.setDurability(0);
    }

    @Inject(method = "useOnTile", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.enableColorSignsWithDye) {
            return;
        }

        if (null == arg) {
            return;
        }

        int blockId = arg3.getTileId(i, j, k);

        if (  (BlockBase.STANDING_SIGN.id == blockId)
           || (BlockBase.WALL_SIGN.id == blockId)
        ) {
            boolean dyeWasUsed = false;
            char[] dyeColor = new char[]{'0', '4', '2', '9', '1', '5', '3', '7', '8', 'c', 'a', 'e', 'b', 'd', '6', 'f'};

            TileEntitySign var8 = (TileEntitySign)arg3.getTileEntity(i, j, k);
            if (var8 != null) {
                for (int lineIndex = 0; lineIndex < var8.lines.length; lineIndex++) {
                    if (!var8.lines[lineIndex].contains("§")) {
                        var8.lines[lineIndex] = "§" + dyeColor[arg.getDamage()] + var8.lines[lineIndex];
                        dyeWasUsed = true;
                    } else if (!var8.lines[lineIndex].contains("§" + dyeColor[arg.getDamage()])) {
                        String tempString = var8.lines[lineIndex].substring(2);
                        var8.lines[lineIndex] = "§" + dyeColor[arg.getDamage()] + tempString;
                        dyeWasUsed = true;
                    }
                }
            }

            if (dyeWasUsed) {
                --arg.count;
            }

            cir.setReturnValue(true);
        }
    }
}
