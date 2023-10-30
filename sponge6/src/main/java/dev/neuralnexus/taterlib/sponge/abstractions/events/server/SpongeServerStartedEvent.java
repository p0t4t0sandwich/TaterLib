package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

/**
 * Sponge implementation of {@link AbstractServerStartedEvent}.
 */
public class SpongeServerStartedEvent extends SpongeServerEvent implements AbstractServerStartedEvent {
    public SpongeServerStartedEvent(GameStartedServerEvent event) {
        super(event);
    }
}
