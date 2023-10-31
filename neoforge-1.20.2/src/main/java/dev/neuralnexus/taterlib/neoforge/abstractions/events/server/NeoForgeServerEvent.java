package dev.neuralnexus.taterlib.neoforge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerEvent;
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
