package dev.neuralnexus.taterlib.neoforge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;

/** NeoForge implementation of {@link ServerStartedEvent}. */
public class NeoForgeServerStartedEvent extends NeoForgeServerEvent implements ServerStartedEvent {
    public NeoForgeServerStartedEvent(
            net.neoforged.neoforge.event.server.ServerStartedEvent event) {
        super(event);
    }
}
