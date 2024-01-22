package com.github.telvarost.misctweaks.mixin;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Dispenser;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(Dispenser.class)
abstract class DispenserMixin extends BlockWithEntity {
    private Random rand = new Random();

    public DispenserMixin(int i) {
        super(i, Material.STONE);
        this.texture = 45;
    }
}