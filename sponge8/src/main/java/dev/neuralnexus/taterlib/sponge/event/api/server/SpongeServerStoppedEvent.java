package dev.neuralnexus.taterlib.sponge.event.api.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStoppedEvent;
import org.spongepowered.api.event.lifecycle.StoppedGameEvent;

/**
 * Sponge implementation of {@link ServerStoppedEvent}.
 */
public class SpongeServerStoppedEvent implements ServerStoppedEvent {
    public SpongeServerStoppedEvent(StoppedGameEvent event) {
    }
}
