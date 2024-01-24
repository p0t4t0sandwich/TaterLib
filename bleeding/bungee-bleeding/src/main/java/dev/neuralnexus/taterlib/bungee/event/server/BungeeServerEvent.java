package dev.neuralnexus.taterlib.bungee.event.server;

import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;

import net.md_5.bungee.api.ProxyServer;

/** Bungee implementation of {@link ServerEvent}. */
public class BungeeServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer getServer() {
        return new BungeeProxyServer(ProxyServer.getInstance());
    }
}
