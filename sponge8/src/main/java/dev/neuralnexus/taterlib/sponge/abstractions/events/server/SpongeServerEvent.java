package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerEvent;
import org.spongepowered.api.event.lifecycle.EngineLifecycleEvent;

/**
 * Sponge implementation of {@link AbstractServerEvent}.
 */
public class SpongeServerEvent implements AbstractServerEvent {
    private final EngineLifecycleEvent event;

    public SpongeServerEvent(EngineLifecycleEvent event) {
        this.event = event;
    }
}
