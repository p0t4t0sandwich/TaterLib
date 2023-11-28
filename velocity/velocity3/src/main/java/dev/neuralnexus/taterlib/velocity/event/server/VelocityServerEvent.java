package dev.neuralnexus.taterlib.velocity.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin;
import dev.neuralnexus.taterlib.velocity.server.VelocityProxyServer;

/** Velocity implementation of {@link ServerEvent}. */
public class VelocityServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new VelocityProxyServer(VelocityTaterLibPlugin.getProxyServer());
    }
}
