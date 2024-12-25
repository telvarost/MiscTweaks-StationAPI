package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
abstract class PrimedTntMixin extends Entity {

    public PrimedTntMixin(World arg) {
        super(arg);
    }

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_method_1194(CallbackInfo ci) {
        if (Config.config.EXPLOSION_AND_FIRE_CONFIG.disableTntExplosionBreakingBlocks)
        {
            ModHelper.ModHelperFields.cancelDestroyBlocks++;
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket++;
        }
    }

//    @Override
//    public boolean interact(PlayerBase arg) {
//        ItemInstance var2 = arg.inventory.getHeldItem();
//        if (var2 != null && var2.itemId == ItemBase.shears.id) {
//            if (!this.level.isServerSide) {
//
//                Minecraft thisMinecraft = MinecraftAccessor.getInstance();
//                Item var24 = new Item(thisMinecraft.level, this.x, this.y, this.z, new ItemInstance(BlockBase.TNT));
//                var24.velocityY = 0.20000000298023224;
//                thisMinecraft.level.spawnEntity(var24);
//
//                this.remove();
//            }
//        }
//        return false;
//    }

}
