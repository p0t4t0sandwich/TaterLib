package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppedEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;

/**
 * Sponge implementation of {@link AbstractServerStoppedEvent}.
 */
public class SpongeServerStoppedEvent implements AbstractServerStoppedEvent {
    public SpongeServerStoppedEvent(GameStoppedServerEvent event) {
    }
}
