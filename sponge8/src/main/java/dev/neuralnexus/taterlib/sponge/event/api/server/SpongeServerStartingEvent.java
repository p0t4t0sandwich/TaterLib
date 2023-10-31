package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartingEvent;
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
