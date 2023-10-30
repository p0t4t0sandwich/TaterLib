package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppedEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;

/**
 * Sponge implementation of {@link AbstractServerStoppedEvent}.
 */
public class SpongeServerStoppedEvent implements AbstractServerStoppedEvent {
    public SpongeServerStoppedEvent(GameStoppedServerEvent event) {
    }
}
