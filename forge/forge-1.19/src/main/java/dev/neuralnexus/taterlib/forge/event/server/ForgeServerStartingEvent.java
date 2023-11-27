package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartingEvent;

/** Forge implementation of {@link ServerStartingEvent}. */
public class ForgeServerStartingEvent extends ForgeServerEvent implements ServerStartingEvent {
    public ForgeServerStartingEvent(net.minecraftforge.event.server.ServerStartingEvent event) {
        super(event);
    }
}
