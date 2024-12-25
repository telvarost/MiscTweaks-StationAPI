package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DyeItem.class)
public class DyeMixin extends Item {

    public DyeMixin(int i) {
        super(i);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_useOnTile(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.BLOCK_ENTITY_CONFIG.enableColorSignsWithDye) {
            return;
        }

        if (null == arg) {
            return;
        }

        int blockId = arg3.getBlockId(i, j, k);

        if (  (Block.SIGN.id == blockId)
           || (Block.WALL_SIGN.id == blockId)
        ) {
            boolean dyeWasUsed = false;
            char[] dyeColor = new char[]{'0', '4', '2', '9', '1', '5', '3', '7', '8', 'c', 'a', 'e', 'b', 'd', '6', 'f'};

            SignBlockEntity var8 = (SignBlockEntity)arg3.getBlockEntity(i, j, k);
            if (var8 != null) {
                for (int lineIndex = 0; lineIndex < var8.texts.length; lineIndex++) {
                    if (!var8.texts[lineIndex].contains("§")) {
                        var8.texts[lineIndex] = "§" + dyeColor[arg.getDamage()] + var8.texts[lineIndex];
                        dyeWasUsed = true;
                    } else if (!var8.texts[lineIndex].contains("§" + dyeColor[arg.getDamage()])) {
                        String tempString = var8.texts[lineIndex].substring(2);
                        var8.texts[lineIndex] = "§" + dyeColor[arg.getDamage()] + tempString;
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
