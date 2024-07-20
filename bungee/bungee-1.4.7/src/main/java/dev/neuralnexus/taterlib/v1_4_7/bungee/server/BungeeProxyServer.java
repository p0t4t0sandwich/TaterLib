/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_4_7.bungee.server;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.v1_4_7.bungee.entity.player.BungeePlayer;

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

    @Override
    public String brand() {
        return server.getName();
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getPlayers().stream().map(BungeePlayer::new).collect(Collectors.toList());
    }

    /** {@inhersitDoc} */
    @Override
    public List<Server> servers() {
        return server.getServers().values().stream()
                .map(BungeeServer::new)
                .collect(Collectors.toList());
    }
}
