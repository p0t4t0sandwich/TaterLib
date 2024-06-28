/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.bukkit.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import org.bukkit.event.server.ServerLoadEvent;

/** Bukkit implementation of {@link ServerStartedEvent}. */
public class BukkitServerStartedEvent extends BukkitServerEvent implements ServerStartedEvent {
    public final ServerLoadEvent event;

    public BukkitServerStartedEvent(ServerLoadEvent event) {
        super(event);
        this.event = event;
    }
}
