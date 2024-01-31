package com.github.telvarost.misctweaks.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {
    @Accessor(value = "instance")
    static Minecraft getInstance() {
        throw new UnsupportedOperationException();
    }
}
