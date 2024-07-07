/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_18.vanilla.event.server;

import dev.neuralnexus.taterapi.event.server.ServerEvent;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_18.vanilla.server.VanillaServer;

import net.minecraft.server.MinecraftServer;

public class VanillaServerEvent implements ServerEvent {
    public VanillaServerEvent(MinecraftServer server) {
        new VanillaServer(server);
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return VanillaServer.instance();
    }
}
