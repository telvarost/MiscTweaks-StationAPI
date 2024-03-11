package com.github.telvarost.misctweaks.mixin;


import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
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
        if (!Config.ConfigFields.enableDefusingTnt) {
            return;
        }

        ItemBase thisItem = ((ItemBase) (Object)this);

        if (thisItem.id == shears.id) {
            if (null != arg && PrimedTnt.class == arg.getClass()) {
                PrimedTnt thisTnt = (PrimedTnt) arg;
//                Minecraft minecraft = MinecraftAccessor.getInstance();
//
//                if (!minecraft.level.isServerSide) {
//                    Item var24 = new Item(minecraft.level, thisTnt.x, thisTnt.y, thisTnt.z, new ItemInstance(BlockBase.TNT));
//                    var24.velocityY = 0.20000000298023224;
//                    minecraft.level.spawnEntity(var24);
//
//                }
                thisTnt.remove();
            }
        }
    }

    @Inject(method = "useOnTile", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.ConfigFields.enableEditSignsWithFeathers) {
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
                    arg2.openSignScreen(var8);
                }

                cir.setReturnValue(true);
            }
        }
    }
}
