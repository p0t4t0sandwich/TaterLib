package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;

/**
 * Sponge implementation of {@link AbstractServerStoppingEvent}.
 */
public class SpongeServerStoppingEvent extends SpongeServerEvent implements AbstractServerStoppingEvent {
    public SpongeServerStoppingEvent(StoppingEngineEvent<Server> event) {
        super(event);
    }
}
