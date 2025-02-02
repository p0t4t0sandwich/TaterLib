/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8.bungee.server;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.v1_8.bungee.entity.player.BungeePlayer;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Bungee implementation of {@link ProxyServer}. */
public class BungeeProxyServer implements ProxyServer, Wrapped<net.md_5.bungee.api.ProxyServer> {
    private final net.md_5.bungee.api.ProxyServer server =
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

    @Override
    public net.md_5.bungee.api.ProxyServer unwrap() {
        return this.server;
    }

    @Override
    public String brand() {
        return this.server.getName();
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
        return this.server.getPlayers().stream()
                .map(BungeePlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, UUID> whitelist() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public Map<String, UUID> playercache() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<Server> servers() {
        return this.server.getServers().values().stream()
                .map(BungeeServer::new)
                .collect(Collectors.toList());
    }
}
