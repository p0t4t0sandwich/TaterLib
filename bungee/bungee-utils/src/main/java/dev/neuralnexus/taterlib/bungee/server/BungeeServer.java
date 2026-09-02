/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;

import net.md_5.bungee.api.config.ServerInfo;

import java.util.Collection;
import java.util.List;
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
    public List<User> players() {
        return this.server.getPlayers().stream()
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
    public List<ServerWorld> worlds() {
        throw new VersionFeatureNotSupportedException();
    }
}
