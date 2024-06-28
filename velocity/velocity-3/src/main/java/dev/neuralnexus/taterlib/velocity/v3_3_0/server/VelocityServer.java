/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.server;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;
import dev.neuralnexus.taterlib.world.ServerWorld;

import java.util.List;
import java.util.stream.Collectors;

/** Velocity implementation of {@link Server}. */
public class VelocityServer implements Server {
    private final com.velocitypowered.api.proxy.server.RegisteredServer server;

    public VelocityServer(com.velocitypowered.api.proxy.server.RegisteredServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return server.getServerInfo().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        // TODO: Pass this information up through plugin messages as a way to sync the server brand
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getPlayersConnected().stream()
                .map(VelocityPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        throw new VersionFeatureNotSupportedException();
    }
}
