package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;

/**
 * Sponge implementation of {@link ServerStartedEvent}.
 */
public class SpongeServerStartedEvent extends SpongeServerEvent implements ServerStartedEvent {
    public SpongeServerStartedEvent(StartedEngineEvent<Server> event) {
        super(event);
    }
}
