package dev.neuralnexus.taterlib.velocity.abstractions.events.server;

import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStoppingEvent;

/**
 * Velocity implementation of {@link AbstractServerStoppingEvent}.
 */
public class VelocityServerStoppingEvent implements AbstractServerStoppingEvent {
    private final ProxyShutdownEvent event;

    public VelocityServerStoppingEvent(ProxyShutdownEvent event) {
        this.event = event;
    }
}
