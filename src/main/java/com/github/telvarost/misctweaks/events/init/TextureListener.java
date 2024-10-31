package com.github.telvarost.misctweaks.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {

    public static int SIDEWAYS_CW_OAK = 0;

    public static int SIDEWAYS_CW_SPRUCE = 0;

    public static int SIDEWAYS_CW_BIRCH = 0;

    public static int SIDEWAYS_CCW_OAK = 0;

    public static int SIDEWAYS_CCW_SPRUCE = 0;

    public static int SIDEWAYS_CCW_BIRCH = 0;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        SIDEWAYS_CW_OAK = Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, "block/sideways_cw_oak")).index;
        SIDEWAYS_CW_SPRUCE = Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, "block/sideways_cw_spruce")).index;
        SIDEWAYS_CW_BIRCH = Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, "block/sideways_cw_birch")).index;
        SIDEWAYS_CCW_OAK = Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, "block/sideways_ccw_oak")).index;
        SIDEWAYS_CCW_SPRUCE = Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, "block/sideways_ccw_spruce")).index;
        SIDEWAYS_CCW_BIRCH = Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, "block/sideways_ccw_birch")).index;
    }
}
