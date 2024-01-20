package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GameStateEvent;

/** Sponge implementation of {@link ServerEvent}. */
public class SpongeServerEvent implements ServerEvent {
    private final GameStateEvent event;

    public SpongeServerEvent(GameStateEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new SpongeServer(Sponge.getServer());
    }
}
