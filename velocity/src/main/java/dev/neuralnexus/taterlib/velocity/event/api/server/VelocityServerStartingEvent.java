package dev.neuralnexus.taterlib.velocity.event.api.server;

import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import dev.neuralnexus.taterlib.common.event.server.ServerStartingEvent;

/**
 * Velocity implementation of {@link ServerStartingEvent}.
 */
public class VelocityServerStartingEvent implements ServerStartingEvent {
    private final ProxyInitializeEvent event;

    public VelocityServerStartingEvent(ProxyInitializeEvent event) {
        this.event = event;
    }
}