package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemBaseMixin {

    @Shadow public static Item FEATHER;
    @Shadow public static Item SLIMEBALL;
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
                    if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
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
    public void miscTweaks_useOnTile(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        if (FEATHER.id == stack.itemId) {
            if (Config.config.INTERACTIVE_BLOCK_CONFIG.enableEditSignsWithFeathers) {
                int blockId = world.getBlockId(x, y, z);

                if (  (Block.SIGN.id == blockId)
                   || (Block.WALL_SIGN.id == blockId)
                ) {
                    --stack.count;

                    SignBlockEntity var8 = (SignBlockEntity)world.getBlockEntity(x, y, z);
                    if (var8 != null) {
                        if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
                            var8.setEditable(true);
                        }
                        user.openEditSignScreen(var8);
                    }

                    cir.setReturnValue(true);
                }
            }
        } else if (SLIMEBALL.id == stack.itemId) {
            if (Config.config.INTERACTIVE_BLOCK_CONFIG.enableGlueTrapdoorsWithSlimeballs) {
                int blockId = world.getBlockId(x, y, z);

                if (Block.TRAPDOOR.id == blockId) {
                    int blockMeta = world.getBlockMeta(x, y, z);

                    if ((blockMeta & 8) == 0) {
                        --stack.count;

                        world.setBlockMeta(x, y, z, blockMeta | 8);
                        world.playSound(user, "mob.slime", 1.0F, 0.9F);

                        cir.setReturnValue(true);
                    } else {
                        world.playSound(user,
                                        Block.TRAPDOOR.soundGroup.getBreakSound(),
                                        1.0F,
                                        ((world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
                    }
                }
            }
        }
    }
}
