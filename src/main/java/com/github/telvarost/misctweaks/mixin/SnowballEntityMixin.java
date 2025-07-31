package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResultType;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowballEntity.class)
public abstract class SnowballEntityMixin extends Entity {
    public SnowballEntityMixin(World world) {
        super(world);
    }

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/util/hit/HitResult;entity:Lnet/minecraft/entity/Entity;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    private Entity miscTweaks_tick(HitResult instance, Operation<Entity> original) {
        if (Config.config.EXPLOSION_AND_FIRE_CONFIG.enableSnowballsExtinguishFire) {
            if (HitResultType.BLOCK == instance.type) {
                this.world.extinguishFire(null, instance.blockX, instance.blockY, instance.blockZ, instance.side);
            }
        }

        return original.call(instance);
    }
}
