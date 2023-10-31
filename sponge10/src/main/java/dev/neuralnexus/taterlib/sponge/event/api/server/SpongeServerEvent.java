package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerEvent;
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
