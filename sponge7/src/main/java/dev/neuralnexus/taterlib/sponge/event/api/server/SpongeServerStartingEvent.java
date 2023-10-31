package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartingEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;

/**
 * Sponge implementation of {@link AbstractServerStartingEvent}.
 */
public class SpongeServerStartingEvent extends SpongeServerEvent implements AbstractServerStartingEvent {
    public SpongeServerStartingEvent(GameStartingServerEvent event) {
        super(event);
    }
}
