package com.github.telvarost.misctweaks.mixin.server;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.packet.AbstractPacket;
import net.minecraft.packet.play.UpdateSign0x82C2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataInputStream;
import java.io.IOException;

@Mixin(UpdateSign0x82C2SPacket.class)
public abstract class UpdateSign0x82C2SPacketMixin extends AbstractPacket {

    @Shadow public int x;

    @Shadow public int y;

    @Shadow public int z;

    @Shadow public String[] text;

    @Inject(method = "read", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_read(DataInputStream dataInputStream, CallbackInfo ci) throws IOException {
        if (!Config.config.enableColorSignsWithDye) {
            return;
        }

        this.x = dataInputStream.readInt();
        this.y = dataInputStream.readShort();
        this.z = dataInputStream.readInt();
        this.text = new String[4];

        for(int var2 = 0; var2 < 4; ++var2) {
            this.text[var2] = readString(dataInputStream, 17);
        }
        ci.cancel();
    }
}
