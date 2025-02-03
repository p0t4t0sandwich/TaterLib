/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Bungee implementation of {@link ProxyServer}. */
public class BungeeProxyServer implements ProxyServer, Wrapped<net.md_5.bungee.api.ProxyServer> {
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
        return (net.md_5.bungee.api.ProxyServer) Loader.instance().server();
    }

    @Override
    public String brand() {
        return this.unwrap().getName();
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
        return this.unwrap().getPlayers().stream()
                .map(WrapperRegistry::wrap)
                .map(SimplePlayer.class::cast)
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
        return this.unwrap().getServers().values().stream()
                .map(BungeeServer::new)
                .collect(Collectors.toList());
    }
}
