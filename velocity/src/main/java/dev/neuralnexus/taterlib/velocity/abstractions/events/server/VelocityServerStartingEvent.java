package dev.neuralnexus.taterlib.velocity.abstractions.events.server;

import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartingEvent;

/**
 * Velocity implementation of {@link AbstractServerStartingEvent}.
 */
public class VelocityServerStartingEvent implements AbstractServerStartingEvent {
    private final ProxyInitializeEvent event;

    public VelocityServerStartingEvent(ProxyInitializeEvent event) {
        this.event = event;
    }
}
