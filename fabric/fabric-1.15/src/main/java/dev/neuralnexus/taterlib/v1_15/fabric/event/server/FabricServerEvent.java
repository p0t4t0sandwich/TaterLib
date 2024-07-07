/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15.fabric.event.server;

import dev.neuralnexus.taterapi.event.server.ServerEvent;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_15.fabric.server.FabricServer;

import net.minecraft.server.MinecraftServer;

public class FabricServerEvent implements ServerEvent {
    private final MinecraftServer server;

    public FabricServerEvent(MinecraftServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new FabricServer(server);
    }
}
