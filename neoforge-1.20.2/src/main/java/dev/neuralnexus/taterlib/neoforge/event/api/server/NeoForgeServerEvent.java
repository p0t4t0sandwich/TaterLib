package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import net.neoforged.neoforge.event.server.ServerLifecycleEvent;

/**
 * NeoForge implementation of {@link ServerEvent}.
 */
public class NeoForgeServerEvent implements ServerEvent {
    private final ServerLifecycleEvent event;

    public NeoForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }
}
