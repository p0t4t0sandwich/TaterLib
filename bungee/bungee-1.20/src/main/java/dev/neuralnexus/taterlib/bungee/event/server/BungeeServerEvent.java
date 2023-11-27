package dev.neuralnexus.taterlib.bungee.event.server;

import dev.neuralnexus.taterlib.bungee.BungeeTaterLibPlugin;
import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.common.event.server.ServerEvent;
import dev.neuralnexus.taterlib.common.server.Server;

/** Bungee implementation of {@link ServerEvent}. */
public class BungeeServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new BungeeProxyServer(BungeeTaterLibPlugin.getProxyServer());
    }
}
