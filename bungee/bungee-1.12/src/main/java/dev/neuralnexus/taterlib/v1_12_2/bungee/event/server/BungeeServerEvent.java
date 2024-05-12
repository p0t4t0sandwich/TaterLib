package dev.neuralnexus.taterlib.v1_12_2.bungee.event.server;

import dev.neuralnexus.taterlib.v1_12_2.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.ProxyServer;

/** Bungee implementation of {@link ServerEvent}. */
public class BungeeServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public ProxyServer server() {
        return BungeeProxyServer.instance();
    }
}
