package dev.neuralnexus.taterlib.v1_8.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_8.sponge.server.SpongeServer;

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
    public SimpleServer server() {
        return new SpongeServer(Sponge.getServer());
    }
}
