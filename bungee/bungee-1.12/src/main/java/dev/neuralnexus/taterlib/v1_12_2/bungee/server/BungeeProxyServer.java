package dev.neuralnexus.taterlib.v1_12_2.bungee.server;

import dev.neuralnexus.taterlib.v1_12_2.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.ProxyServer;
import dev.neuralnexus.taterlib.server.Server;

import java.util.List;
import java.util.stream.Collectors;

/** Bungee implementation of {@link ProxyServer}. */
public class BungeeProxyServer implements ProxyServer {
    private static final net.md_5.bungee.api.ProxyServer server =
            net.md_5.bungee.api.ProxyServer.getInstance();
    private static BungeeProxyServer instance;

    /**
     * Gets the instance.
     *
     * @return The instance.
     */
    public static BungeeProxyServer instance() {
        if (instance == null) {
            instance = new BungeeProxyServer();
        }
        return instance;
    }

    /**
     * Gets the server.
     *
     * @return The server.
     */
    // TODO: Add dependency
    //    @ApiStatus.Internal
    public static net.md_5.bungee.api.ProxyServer server() {
        return server;
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        return server.getName();
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getPlayers().stream().map(BungeePlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Server> servers() {
        return server.getServers().values().stream()
                .map(BungeeServer::new)
                .collect(Collectors.toList());
    }
}
