package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppingEvent;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;

/**
 * Sponge implementation of {@link ServerStoppingEvent}.
 */
public class SpongeServerStoppingEvent extends SpongeServerEvent implements ServerStoppingEvent {
    public SpongeServerStoppingEvent(StoppingEngineEvent<Server> event) {
        super(event);
    }
}
