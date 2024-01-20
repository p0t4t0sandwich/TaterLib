package dev.neuralnexus.taterlib.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.lifecycle.LifecycleEvent;

/** Sponge implementation of {@link ServerEvent}. */
public class SpongeServerEvent implements ServerEvent {
    private final LifecycleEvent event;

    public SpongeServerEvent(LifecycleEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new SpongeServer(Sponge.server());
    }
}
