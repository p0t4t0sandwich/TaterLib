/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.server;

import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/** Velocity implementation of {@link Server}. */
public class VelocityServer implements Server, Wrapped<RegisteredServer> {
    private final RegisteredServer server;

    public VelocityServer(RegisteredServer server) {
        this.server = server;
    }

    @Override
    public RegisteredServer unwrap() {
        return this.server;
    }

    @Override
    public String name() {
        return this.server.getServerInfo().getName();
    }

    @Override
    public String brand() {
        // TODO: Pass this information up through plugin messages as a way to sync the server brand
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<User> players() {
        return this.server.getPlayersConnected().stream()
                .map(VelocityPlayer::new)
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
