package dev.neuralnexus.taterlib.forge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;

/** Forge implementation of {@link ServerStartedEvent}. */
public class ForgeServerStartedEvent extends ForgeServerEvent implements ServerStartedEvent {
    public ForgeServerStartedEvent(net.minecraftforge.event.server.ServerStartedEvent event) {
        super(event);
    }
}
