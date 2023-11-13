package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import org.spongepowered.api.event.lifecycle.EngineLifecycleEvent;

/**
 * Sponge implementation of {@link ServerEvent}.
 */
public class SpongeServerEvent implements ServerEvent {
    private final EngineLifecycleEvent event;

    public SpongeServerEvent(EngineLifecycleEvent event) {
        this.event = event;
    }
}
