package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;

/**
 * Sponge implementation of {@link AbstractServerStoppingEvent}.
 */
public class SpongeServerStoppingEvent extends SpongeServerEvent implements AbstractServerStoppingEvent {
    public SpongeServerStoppingEvent(GameStoppingEvent event) {
        super(event);
    }
}
