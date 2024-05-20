package dev.neuralnexus.taterlib.v1_11.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import org.spongepowered.api.event.game.state.GameStoppedServerEvent;

/** Sponge implementation of {@link ServerStoppedEvent}. */
public class SpongeServerStoppedEvent extends SpongeServerEvent implements ServerStoppedEvent {
    public SpongeServerStoppedEvent(GameStoppedServerEvent event) {
        super(event);
    }
}
