/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.server;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Velocity implementation of {@link ProxyServer}. */
public class VelocityProxyServer
        implements ProxyServer, Wrapped<com.velocitypowered.api.proxy.ProxyServer> {
    private static VelocityProxyServer instance;

    /**
     * Gets the instance.
     *
     * @return The instance.
     */
    public static VelocityProxyServer instance() {
        if (instance == null) {
            instance = new VelocityProxyServer();
        }
        return instance;
    }

    @Override
    public com.velocitypowered.api.proxy.ProxyServer unwrap() {
        return (com.velocitypowered.api.proxy.ProxyServer) Loader.instance().server();
    }

    @Override
    public String brand() {
        // TODO: See if there's a better way to get the server brand
        return "Velocity";
    }

    @Override
    public List<User> onlinePlayers() {
        return this.unwrap().getAllPlayers().stream()
                .map(VelocityPlayer::new)
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
        return this.unwrap().getAllServers().stream()
                .map(VelocityServer::new)
                .collect(Collectors.toList());
    }
}
