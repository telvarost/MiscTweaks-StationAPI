package com.github.telvarost.misctweaks.mixin.server;

import com.github.telvarost.misctweaks.ModHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.storage.WorldStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.SERVER)
@Mixin(ServerWorld.class)
public class ServerWorldMixin extends World {

    @Shadow private MinecraftServer server;

    ServerWorldMixin(MinecraftServer minecraftServer, WorldStorage arg, String string, int i, long l) {
        super(arg, string, l, Dimension.fromId(i));
    }


    @Inject(method = "createExplosion", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_createExplosion(Entity arg, double d, double e, double f, float g, boolean bl, CallbackInfoReturnable<Explosion> cir) {
        if (0 < ModHelper.ModHelperFields.cancelDestroyBlocksPacket) {
            Explosion var10 = new Explosion(this, arg, d, e, f, g);
            var10.fire = bl;
            var10.explode();

            /** - Custom kaboomPhase2/packet that doesn't destroy blocks */
            var10.damagedBlocks.clear();
            this.server.playerManager.sendToAround(d, e, f, 64.0, this.dimension.id, new ExplosionS2CPacket(d, e, f, 0, var10.damagedBlocks));

            /** - End of custom kaboomPhase2/packet and end of method */
            cir.setReturnValue(var10);
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket--;
        }
    }
}
