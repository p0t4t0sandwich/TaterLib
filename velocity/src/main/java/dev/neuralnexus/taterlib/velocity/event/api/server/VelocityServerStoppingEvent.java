package dev.neuralnexus.taterlib.velocity.event.api.server;

import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import dev.neuralnexus.taterlib.common.event.server.AbstractServerStoppingEvent;

/**
 * Velocity implementation of {@link AbstractServerStoppingEvent}.
 */
public class VelocityServerStoppingEvent implements AbstractServerStoppingEvent {
    private final ProxyShutdownEvent event;

    public VelocityServerStoppingEvent(ProxyShutdownEvent event) {
        this.event = event;
    }
}
