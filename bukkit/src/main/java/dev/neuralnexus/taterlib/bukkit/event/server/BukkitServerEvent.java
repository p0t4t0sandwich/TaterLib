package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerEvent;
import org.bukkit.event.server.ServerEvent;

/**
 * Bukkit implementation of {@link AbstractServerEvent}.
 */
public class BukkitServerEvent implements AbstractServerEvent {
    private ServerEvent event = null;

    public BukkitServerEvent(ServerEvent event) {
        this.event = event;
    }

    public BukkitServerEvent() {}
}
