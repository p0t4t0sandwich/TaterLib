package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppingEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;

/**
 * Sponge implementation of {@link AbstractServerStoppingEvent}.
 */
public class SpongeServerStoppingEvent extends SpongeServerEvent implements AbstractServerStoppingEvent {
    public SpongeServerStoppingEvent(GameStoppingEvent event) {
        super(event);
    }
}
