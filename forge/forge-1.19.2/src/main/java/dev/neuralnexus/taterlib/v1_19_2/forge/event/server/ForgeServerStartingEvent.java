package dev.neuralnexus.taterlib.v1_19_2.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

/** Forge implementation of {@link ServerStartingEvent}. */
public class ForgeServerStartingEvent extends ForgeServerEvent implements ServerStartingEvent {
    public ForgeServerStartingEvent(net.minecraftforge.event.server.ServerStartingEvent event) {
        super(event);
    }
}
