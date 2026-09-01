/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;

import java.util.Collection;
import java.util.List;
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
        return (net.md_5.bungee.api.ProxyServer) MetaAPI.instance().server();
    }

    @Override
    public String brand() {
        return this.unwrap().getName();
    }

    @Override
    public List<User> players() {
        return this.unwrap().getPlayers().stream()
                .map(WrapperRegistry::wrap)
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<NameAndId> whitelist() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public Collection<NameAndId> playercache() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<Server> servers() {
        return this.unwrap().getServers().values().stream()
                .map(BungeeServer::new)
                .collect(Collectors.toList());
    }
}
