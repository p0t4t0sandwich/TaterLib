package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppedEvent;

/**
 * NeoForge implementation of {@link ServerStoppedEvent}.
 */
public class NeoForgeServerStoppedEvent extends NeoForgeServerEvent implements ServerStoppedEvent {
    public NeoForgeServerStoppedEvent(net.neoforged.neoforge.event.server.ServerStoppedEvent event) {
        super(event);
    }
}
