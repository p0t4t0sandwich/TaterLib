/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.server.SimpleServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/** API wrapper class */
public class TaterAPI {
    private Supplier<SimpleServer> minecraftServer = () -> null;

    /**
     * Get the minecraft server
     *
     * @return The minecraft server
     */
    public SimpleServer server() {
        return minecraftServer.get();
    }

    /**
     * Set the minecraftServer supplier
     *
     * @param minecraftServer The minecraftServer supplier
     */
    @ApiStatus.Internal
    public void setServer(Supplier<SimpleServer> minecraftServer) {
        this.minecraftServer = minecraftServer;
    }
}
