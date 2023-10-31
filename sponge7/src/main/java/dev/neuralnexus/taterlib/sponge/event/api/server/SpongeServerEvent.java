package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerEvent;
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
