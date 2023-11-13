package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppedEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;

/**
 * Sponge implementation of {@link ServerStoppedEvent}.
 */
public class SpongeServerStoppedEvent implements ServerStoppedEvent {
    public SpongeServerStoppedEvent(GameStoppedServerEvent event) {
    }
}
