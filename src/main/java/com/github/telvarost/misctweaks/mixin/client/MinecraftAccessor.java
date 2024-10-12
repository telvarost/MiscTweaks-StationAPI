package com.github.telvarost.misctweaks.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {
    @Accessor(value = "INSTANCE")
    static Minecraft getInstance() {
        throw new UnsupportedOperationException();
    }
}
