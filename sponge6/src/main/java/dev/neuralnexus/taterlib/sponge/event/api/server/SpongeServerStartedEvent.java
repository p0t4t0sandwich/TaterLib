package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartedEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

/**
 * Sponge implementation of {@link AbstractServerStartedEvent}.
 */
public class SpongeServerStartedEvent extends SpongeServerEvent implements AbstractServerStartedEvent {
    public SpongeServerStartedEvent(GameStartedServerEvent event) {
        super(event);
    }
}
