package dev.neuralnexus.taterlib.velocity.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.ProxyServer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

import java.util.Set;
import java.util.stream.Collectors;

/** Velocity implementation of {@link ProxyServer}. */
public class VelocityProxyServer implements ProxyServer {
    private final com.velocitypowered.api.proxy.ProxyServer server;

    public VelocityProxyServer(com.velocitypowered.api.proxy.ProxyServer server) {
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
        // TODO: See if there's a better way to get the server brand
        return "Velocity";
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> onlinePlayers() {
        return server.getAllPlayers().stream().map(VelocityPlayer::new).collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public Set<Server> servers() {
        return server.getAllServers().stream().map(VelocityServer::new).collect(Collectors.toSet());
    }
}
