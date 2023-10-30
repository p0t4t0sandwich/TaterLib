package dev.neuralnexus.taterlib.sponge.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppedEvent;
import org.spongepowered.api.event.lifecycle.StoppedGameEvent;

/**
 * Sponge implementation of {@link AbstractServerStoppedEvent}.
 */
public class SpongeServerStoppedEvent implements AbstractServerStoppedEvent {
    public SpongeServerStoppedEvent(StoppedGameEvent event) {
    }
}
