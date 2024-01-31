package com.github.telvarost.misctweaks.mixin;


import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntitySign;
import net.modificationstation.stationapi.api.client.item.StationRendererItem;
import net.modificationstation.stationapi.api.item.StationFlatteningItem;
import net.modificationstation.stationapi.api.item.StationItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBase.class)
public class ItemBaseMixin implements StationFlatteningItem, StationItem, StationRendererItem {

    @Shadow public static ItemBase feather;

    @Shadow public static ItemBase dyePowder;

    @Inject(method = "useOnTile", at = @At("HEAD"), cancellable = true)
    public void useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ConfigFields.enableEditSignsWithFeathers) {
            if (feather.id == arg.itemId) {
                int blockId = arg3.getTileId(i, j, k);

                if (  (BlockBase.STANDING_SIGN.id == blockId)
                   || (BlockBase.WALL_SIGN.id == blockId)
                ) {
                    --arg.count;

                    TileEntitySign var8 = (TileEntitySign)arg3.getTileEntity(i, j, k);
                    if (var8 != null) {
                        arg2.openSignScreen(var8);
                    }

                    cir.setReturnValue(true);
                }
            }
        }
    }
}
