package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartingEvent;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;

/**
 * Sponge implementation of {@link ServerStartingEvent}.
 */
public class SpongeServerStartingEvent extends SpongeServerEvent implements ServerStartingEvent {
    public SpongeServerStartingEvent(StartingEngineEvent<Server> event) {
        super(event);
    }
}
