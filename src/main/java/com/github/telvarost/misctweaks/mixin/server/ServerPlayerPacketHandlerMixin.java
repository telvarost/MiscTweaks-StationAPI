package com.github.telvarost.misctweaks.mixin.server;

import com.github.telvarost.misctweaks.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.PacketHandler;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.network.ServerPlayerPacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.SERVER)
@Mixin(ServerPlayerPacketHandler.class)
public abstract class ServerPlayerPacketHandlerMixin extends PacketHandler implements CommandSource {

    @Redirect(
            method = "onUpdateSign",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;length()I",
                    ordinal = 0
            )
    )
    public int miscTweaks_onUpdateSignCheckLength(String instance) {
        if (Config.ConfigFields.enableColorSignsWithDye && instance.contains("§")) {
            return instance.length() - 2;
        } else {
            return instance.length();
        }
    }

    @Redirect(
            method = "onUpdateSign",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;length()I",
                    ordinal = 1
            )
    )
    public int miscTweaks_onUpdateSignCheckCharacters(String instance) {
        if (Config.ConfigFields.enableColorSignsWithDye && instance.contains("§")) {
            return 0;
        } else {
            return instance.length();
        }
    }
}
