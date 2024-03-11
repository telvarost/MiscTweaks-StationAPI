package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.ModHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.level.dimension.Dimension;
import net.minecraft.level.dimension.DimensionData;
import net.minecraft.packet.play.CreateExplosion0x3CS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sortme.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.SERVER)
@Mixin(ServerLevel.class)
public class ServerLevelMixin extends Level {

    @Shadow private MinecraftServer minecraftServer;

    ServerLevelMixin(MinecraftServer minecraftServer, DimensionData arg, String string, int i, long l) {
        super(arg, string, l, Dimension.getByID(i));
    }


    @Inject(method = "createExplosion", at = @At("HEAD"), cancellable = true)
    public void createExplosion(EntityBase arg, double d, double e, double f, float g, boolean bl, CallbackInfoReturnable<Explosion> cir) {
        if (0 < ModHelper.ModHelperFields.cancelDestroyBlocksPacket) {
            Explosion var10 = new Explosion(this, arg, d, e, f, g);
            var10.causeFires = bl;
            var10.kaboomPhase1();

            /** - Custom kaboomPhase2/packet that doesn't destroy blocks */
            var10.damagedTiles.clear();
            this.minecraftServer.serverPlayerConnectionManager.method_550(d, e, f, 64.0, this.dimension.id, new CreateExplosion0x3CS2CPacket(d, e, f, 0, var10.damagedTiles));

            /** - End of custom kaboomPhase2/packet and end of method */
            cir.setReturnValue(var10);
            ModHelper.ModHelperFields.cancelDestroyBlocksPacket--;
        }
    }
}
