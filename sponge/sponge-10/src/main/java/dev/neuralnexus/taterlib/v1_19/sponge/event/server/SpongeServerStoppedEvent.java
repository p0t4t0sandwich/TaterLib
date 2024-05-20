package dev.neuralnexus.taterlib.v1_19.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import org.spongepowered.api.event.lifecycle.StoppedGameEvent;

/** Sponge implementation of {@link ServerStoppedEvent}. */
public class SpongeServerStoppedEvent extends SpongeServerEvent implements ServerStoppedEvent {
    public SpongeServerStoppedEvent(StoppedGameEvent event) {
        super(event);
    }
}
