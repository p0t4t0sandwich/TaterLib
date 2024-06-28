/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_2_5.bukkit.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerStartingEvent}. */
public class BukkitServerStartingEvent implements ServerStartingEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new BukkitServer(Bukkit.getServer());
    }
}
