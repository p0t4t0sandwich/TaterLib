package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import org.spongepowered.api.event.game.state.GameStoppingEvent;

/** Sponge implementation of {@link ServerStoppingEvent}. */
public class SpongeServerStoppingEvent extends SpongeServerEvent implements ServerStoppingEvent {
    public SpongeServerStoppingEvent(GameStoppingEvent event) {
        super(event);
    }
}
