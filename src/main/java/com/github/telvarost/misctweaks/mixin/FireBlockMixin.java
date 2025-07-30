package com.github.telvarost.misctweaks.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FireBlock.class)
public class FireBlockMixin extends Block {
    public FireBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        if (Block.GRASS_BLOCK.id == world.getBlockId(x, y - 1, z)) {
            world.setBlock(x, y - 1, z, Block.DIRT.id);
        }
    }
}
