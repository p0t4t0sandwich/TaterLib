/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_10_2.fabric.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link ServerStoppingEvent}. */
public class FabricServerStoppingEvent extends FabricServerEvent implements ServerStoppingEvent {
    public FabricServerStoppingEvent(MinecraftServer server) {
        super(server);
    }
}
