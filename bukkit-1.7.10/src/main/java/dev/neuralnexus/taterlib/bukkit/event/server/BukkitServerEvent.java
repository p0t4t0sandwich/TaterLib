package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerEvent;

/**
 * Bukkit implementation of {@link ServerEvent}.
 */
public class BukkitServerEvent implements ServerEvent {
    private org.bukkit.event.server.ServerEvent event = null;

    public BukkitServerEvent(org.bukkit.event.server.ServerEvent event) {
        this.event = event;
    }

    public BukkitServerEvent() {}
}
