package com.github.telvarost.misctweaks.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.LogBlockItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LogBlockItem.class)
public class LogItemMixin extends BlockItem {
    public LogItemMixin(int i) {
        super(i);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        boolean b = super.useOnBlock(stack, user, world, x, y, z, side);
        int blockMeta;

        if (world.getBlockId(x, y, z) == Block.SNOW.id) {
            side = 0;
        } else {
            if (side == 2) {
                --z;

                blockMeta = world.getBlockMeta(x, y, z) | 0x4;
                world.setBlockMeta(x, y, z, blockMeta);
            }

            if (side == 3) {
                ++z;

                blockMeta = world.getBlockMeta(x, y, z) | 0x4;
                world.setBlockMeta(x, y, z, blockMeta);
            }

            if (side == 4) {
                --x;

                blockMeta = world.getBlockMeta(x, y, z) | 0x8;
                world.setBlockMeta(x, y, z, blockMeta);
            }

            if (side == 5) {
                ++x;

                blockMeta = world.getBlockMeta(x, y, z) | 0x8;
                world.setBlockMeta(x, y, z, blockMeta);
            }
        }

        return b;
    }
}
