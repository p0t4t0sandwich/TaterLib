package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartingEvent;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;

/**
 * Sponge implementation of {@link AbstractServerStartingEvent}.
 */
public class SpongeServerStartingEvent extends SpongeServerEvent implements AbstractServerStartingEvent {
    public SpongeServerStartingEvent(StartingEngineEvent<Server> event) {
        super(event);
    }
}
