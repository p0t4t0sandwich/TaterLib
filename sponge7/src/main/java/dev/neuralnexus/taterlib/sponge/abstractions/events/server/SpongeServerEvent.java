package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerEvent;
import org.spongepowered.api.event.game.state.GameStateEvent;

/**
 * Sponge implementation of {@link AbstractServerEvent}.
 */
public class SpongeServerEvent implements AbstractServerEvent {
    private final GameStateEvent event;

    public SpongeServerEvent(GameStateEvent event) {
        this.event = event;
    }
}
