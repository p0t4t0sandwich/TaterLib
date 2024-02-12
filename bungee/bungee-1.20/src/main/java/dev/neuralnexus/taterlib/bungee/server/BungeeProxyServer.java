package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.ProxyServer;
import dev.neuralnexus.taterlib.server.Server;

import java.util.Set;
import java.util.stream.Collectors;

/** Bungee implementation of {@link ProxyServer}. */
public class BungeeProxyServer implements ProxyServer {
    private final net.md_5.bungee.api.ProxyServer server;

    public BungeeProxyServer(net.md_5.bungee.api.ProxyServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return "local";
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        return server.getName();
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> onlinePlayers() {
        return server.getPlayers().stream().map(BungeePlayer::new).collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public Set<Server> servers() {
        return server.getServers().values().stream()
                .map(BungeeServer::new)
                .collect(Collectors.toSet());
    }
}
