package dev.neuralnexus.taterlib.neoforge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerEvent;
import net.neoforged.neoforge.event.server.ServerLifecycleEvent;

/**
 * NeoForge implementation of {@link AbstractServerEvent}.
 */
public class NeoForgeServerEvent implements AbstractServerEvent {
    private final ServerLifecycleEvent event;

    public NeoForgeServerEvent(ServerLifecycleEvent event) {
        this.event = event;
    }
}
