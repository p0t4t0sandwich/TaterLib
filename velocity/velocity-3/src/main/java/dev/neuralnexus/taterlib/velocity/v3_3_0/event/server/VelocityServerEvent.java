package dev.neuralnexus.taterlib.velocity.v3_3_0.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityProxyServer;

/** Velocity implementation of {@link ServerEvent}. */
public class VelocityServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return VelocityProxyServer.instance();
    }
}
