/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_4_7.bungee.server;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_4_7.bungee.entity.player.BungeePlayer;

import net.md_5.bungee.api.config.ServerInfo;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Bungee implementation of {@link Server}. */
public class BungeeServer implements Server, Wrapped<ServerInfo> {
    private final ServerInfo server;

    public BungeeServer(ServerInfo server) {
        this.server = server;
    }

    @Override
    public ServerInfo unwrap() {
        return this.server;
    }

    @Override
    public String name() {
        return this.server.getName();
    }

    @Override
    public String brand() {
        // TODO: Pass this information up through plugin messages as a way to sync the server brand
        throw new VersionFeatureNotSupportedException();
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
    public List<ServerWorld> worlds() {
        throw new VersionFeatureNotSupportedException();
    }
}
