package dev.neuralnexus.taterlib.forge.event.server;

import cpw.mods.fml.common.event.FMLServerStartedEvent;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

/** Forge implementation of {@link ServerStartedEvent}. */
public class ForgeServerStartedEvent extends ForgeServerEvent implements ServerStartedEvent {
    public ForgeServerStartedEvent(FMLServerStartedEvent event) {
        super(event);
    }
}
