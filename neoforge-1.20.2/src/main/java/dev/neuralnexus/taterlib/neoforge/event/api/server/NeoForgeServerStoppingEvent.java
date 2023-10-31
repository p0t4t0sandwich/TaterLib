package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppingEvent;

/**
 * NeoForge implementation of {@link ServerStoppingEvent}.
 */
public class NeoForgeServerStoppingEvent extends NeoForgeServerEvent implements ServerStoppingEvent {
    public NeoForgeServerStoppingEvent(net.neoforged.neoforge.event.server.ServerStoppingEvent event) {
        super(event);
    }
}
