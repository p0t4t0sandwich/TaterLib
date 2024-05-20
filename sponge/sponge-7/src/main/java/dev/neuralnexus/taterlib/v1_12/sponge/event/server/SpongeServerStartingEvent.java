package dev.neuralnexus.taterlib.v1_12.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

import org.spongepowered.api.event.game.state.GameStartingServerEvent;

/** Sponge implementation of {@link ServerStartingEvent}. */
public class SpongeServerStartingEvent extends SpongeServerEvent implements ServerStartingEvent {
    public SpongeServerStartingEvent(GameStartingServerEvent event) {
        super(event);
    }
}
