package dev.neuralnexus.taterlib.velocity.server;

import dev.neuralnexus.taterlib.player.Player;
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
    public String getName() {
        return "local";
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getAllPlayers().stream().map(VelocityPlayer::new).collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public Set<Server> getServers() {
        return server.getAllServers().stream().map(VelocityServer::new).collect(Collectors.toSet());
    }
}
