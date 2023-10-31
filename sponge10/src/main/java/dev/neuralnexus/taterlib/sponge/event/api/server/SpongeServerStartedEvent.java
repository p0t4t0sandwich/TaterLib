package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartedEvent;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;

/**
 * Sponge implementation of {@link AbstractServerStartedEvent}.
 */
public class SpongeServerStartedEvent extends SpongeServerEvent implements AbstractServerStartedEvent {
    public SpongeServerStartedEvent(StartedEngineEvent<Server> event) {
        super(event);
    }
}
