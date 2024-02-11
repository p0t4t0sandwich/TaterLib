package dev.neuralnexus.taterlib.bungee.event.server;

import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.ProxyServer;

/** Bungee implementation of {@link ServerEvent}. */
public class BungeeServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public ProxyServer getServer() {
        return new BungeeProxyServer(net.md_5.bungee.api.ProxyServer.getInstance());
    }
}
