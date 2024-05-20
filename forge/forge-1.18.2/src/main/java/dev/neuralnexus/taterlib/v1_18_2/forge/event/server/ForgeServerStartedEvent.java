package dev.neuralnexus.taterlib.v1_18_2.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

/** Forge implementation of {@link ServerStartedEvent}. */
public class ForgeServerStartedEvent extends ForgeServerEvent implements ServerStartedEvent {
    public ForgeServerStartedEvent(net.minecraftforge.event.server.ServerStartedEvent event) {
        super(event);
    }
}
