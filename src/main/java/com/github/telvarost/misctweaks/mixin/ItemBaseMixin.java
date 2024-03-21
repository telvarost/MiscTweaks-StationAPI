package com.github.telvarost.misctweaks.mixin;


import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.PrimedTnt;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.Shears;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntitySign;
import net.modificationstation.stationapi.api.client.item.StationRendererItem;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
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
    @Shadow public static Shears shears;

    @Inject(method = "getAttack", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_getAttack(EntityBase arg, CallbackInfoReturnable<Integer> cir) {
        if (!Config.config.enableDefusingTnt) {
            return;
        }

        ItemBase thisItem = ((ItemBase) (Object)this);

        if (thisItem.id == shears.id) {
            if (null != arg && PrimedTnt.class == arg.getClass()) {
                PrimedTnt curTnt = (PrimedTnt) arg;

                if (!curTnt.level.isServerSide) {
                    Item var24 = new Item(curTnt.level, curTnt.x, curTnt.y, curTnt.z, new ItemInstance(BlockBase.TNT));
                    var24.velocityY = 0.20000000298023224;
                    curTnt.level.spawnEntity(var24);
                } else {
                    PlayerBase player = PlayerHelper.getPlayerFromGame();
                    if (player == null) {
                        Item var24 = new Item(curTnt.level, curTnt.x, curTnt.y, curTnt.z, new ItemInstance(BlockBase.TNT));
                        var24.velocityY = 0.20000000298023224;
                        curTnt.level.spawnEntity(var24);
                    }
                }

                curTnt.remove();
            }
        }
    }

    @Inject(method = "useOnTile", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.enableEditSignsWithFeathers) {
            return;
        }

        if (feather.id == arg.itemId) {
            int blockId = arg3.getTileId(i, j, k);

            if (  (BlockBase.STANDING_SIGN.id == blockId)
               || (BlockBase.WALL_SIGN.id == blockId)
            ) {
                --arg.count;

                TileEntitySign var8 = (TileEntitySign)arg3.getTileEntity(i, j, k);
                if (var8 != null) {
                    PlayerBase player = PlayerHelper.getPlayerFromGame();
                    if (player == null) {
                        var8.setNeedsInitialized(true);
                    }
                    arg2.openSignScreen(var8);
                }

                cir.setReturnValue(true);
            }
        }
    }
}
