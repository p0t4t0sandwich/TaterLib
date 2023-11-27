package dev.neuralnexus.taterlib.neoforge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartingEvent;

/** NeoForge implementation of {@link ServerStartingEvent}. */
public class NeoForgeServerStartingEvent extends NeoForgeServerEvent
        implements ServerStartingEvent {
    public NeoForgeServerStartingEvent(
            net.neoforged.neoforge.event.server.ServerStartingEvent event) {
        super(event);
    }
}
