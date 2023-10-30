package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
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
