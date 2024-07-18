/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15.vanilla.event.server;

import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;

import net.minecraft.server.MinecraftServer;

/** Vanilla implementation of {@link ServerStoppingEvent}. */
public class VanillaServerStoppingEvent extends VanillaServerEvent implements ServerStoppingEvent {
    public VanillaServerStoppingEvent(MinecraftServer server) {
        super(server);
    }
}
