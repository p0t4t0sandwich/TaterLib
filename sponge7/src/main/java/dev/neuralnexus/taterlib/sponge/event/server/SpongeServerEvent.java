package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import org.spongepowered.api.event.game.state.GameStateEvent;

/**
 * Sponge implementation of {@link ServerEvent}.
 */
public class SpongeServerEvent implements ServerEvent {
    private final GameStateEvent event;

    public SpongeServerEvent(GameStateEvent event) {
        this.event = event;
    }
}
