/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.server;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.metrics.TPSProvider;
import dev.neuralnexus.taterapi.world.ServerWorld;

import java.util.List;
import java.util.Optional;

/** Abstracts a Minecraft server. */
public interface Server extends SimpleServer {
    /**
     * Get the server's current TPS.
     *
     * @return The server's current TPS.
     */
    default double currentTPS() {
        Optional<TPSProvider> provider = TaterAPI.getTPSProvider();
        return provider.map(TPSProvider::currentTPS).orElse(-1.0);
    }

    //    /**
    //     * Get the server's current tick.
    //     *
    //     * @return The server's current tick.
    //     */
    //    long currentTick();

    /**
     * Get the server's worlds.
     *
     * @return The server's worlds.
     */
    List<ServerWorld> worlds();

    /**
     * Get the server's world by dimension.
     *
     * @param dimension The dimension.
     * @return The server's world by dimension.
     */
    default Optional<ServerWorld> world(ResourceKey dimension) {
        return worlds().stream()
                .filter(world -> world.dimension().asString().equals(dimension.asString()))
                .findFirst();
    }

    /**
     * Get the server's world by dimension.
     *
     * @param dimension The dimension.
     * @return The server's world by dimension.
     */
    default Optional<ServerWorld> world(String dimension) {
        return worlds().stream()
                .filter(world -> world.dimension().asString().equals(dimension))
                .findFirst();
    }
}
