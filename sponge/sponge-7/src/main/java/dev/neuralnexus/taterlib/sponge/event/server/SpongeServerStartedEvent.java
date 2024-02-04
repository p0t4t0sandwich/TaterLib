package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import org.spongepowered.api.event.game.state.GameStartedServerEvent;

/** Sponge implementation of {@link ServerStartedEvent}. */
public class SpongeServerStartedEvent extends SpongeServerEvent implements ServerStartedEvent {
    public SpongeServerStartedEvent(GameStartedServerEvent event) {
        super(event);
    }
}
