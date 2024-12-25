package com.github.telvarost.misctweaks.mixin;


import com.github.telvarost.misctweaks.Config;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemBaseMixin {

    @Shadow public static Item FEATHER;
    @Shadow public static ShearsItem SHEARS;

    @Inject(method = "getAttackDamage", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_getAttack(Entity arg, CallbackInfoReturnable<Integer> cir) {
        if (!Config.config.EXPLOSION_AND_FIRE_CONFIG.enableDefusingTnt) {
            return;
        }

        Item thisItem = ((Item) (Object)this);

        if (thisItem.id == SHEARS.id) {
            if (null != arg && TntEntity.class == arg.getClass()) {
                TntEntity curTnt = (TntEntity) arg;

                if (!curTnt.world.isRemote) {
                    ItemEntity var24 = new ItemEntity(curTnt.world, curTnt.x, curTnt.y, curTnt.z, new ItemStack(Block.TNT));
                    var24.velocityY = 0.20000000298023224;
                    curTnt.world.spawnEntity(var24);
                } else {
                    PlayerEntity player = PlayerHelper.getPlayerFromGame();
                    if (player == null) {
                        ItemEntity var24 = new ItemEntity(curTnt.world, curTnt.x, curTnt.y, curTnt.z, new ItemStack(Block.TNT));
                        var24.velocityY = 0.20000000298023224;
                        curTnt.world.spawnEntity(var24);
                    }
                }

                curTnt.markDead();
            }
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_useOnTile(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.config.BLOCK_ENTITY_CONFIG.enableEditSignsWithFeathers) {
            return;
        }

        if (FEATHER.id == arg.itemId) {
            int blockId = arg3.getBlockId(i, j, k);

            if (  (Block.SIGN.id == blockId)
               || (Block.WALL_SIGN.id == blockId)
            ) {
                --arg.count;

                SignBlockEntity var8 = (SignBlockEntity)arg3.getBlockEntity(i, j, k);
                if (var8 != null) {
                    PlayerEntity player = PlayerHelper.getPlayerFromGame();
                    if (player == null) {
                        var8.setEditable(true);
                    }
                    arg2.openEditSignScreen(var8);
                }

                cir.setReturnValue(true);
            }
        }
    }
}
