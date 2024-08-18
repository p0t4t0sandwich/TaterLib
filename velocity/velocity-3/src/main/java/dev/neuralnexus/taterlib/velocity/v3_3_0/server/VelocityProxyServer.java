/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.server;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;
import dev.neuralnexus.taterloader.Loader;

import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Velocity implementation of {@link ProxyServer}. */
public class VelocityProxyServer implements ProxyServer {
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

    /**
     * Gets the server.
     *
     * @return The server.
     */
    @ApiStatus.Internal
    public static com.velocitypowered.api.proxy.ProxyServer server() {
        return (com.velocitypowered.api.proxy.ProxyServer) Loader.instance().server();
    }

    @Override
    public String brand() {
        // TODO: See if there's a better way to get the server brand
        return "Velocity";
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server().getAllPlayers().stream()
                .map(VelocityPlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, UUID> whitelist() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, UUID> playercache() {
        return Collections.emptyMap();
    }

    @Override
    public List<Server> servers() {
        return server().getAllServers().stream()
                .map(VelocityServer::new)
                .collect(Collectors.toList());
    }
}
