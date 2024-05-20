package dev.neuralnexus.taterlib.v1_7_10.forge.event.server;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

/** Forge implementation of {@link ServerStartingEvent}. */
public class ForgeServerStartingEvent extends ForgeServerEvent implements ServerStartingEvent {
    public ForgeServerStartingEvent(FMLServerStartingEvent event) {
        super(event);
    }
}
