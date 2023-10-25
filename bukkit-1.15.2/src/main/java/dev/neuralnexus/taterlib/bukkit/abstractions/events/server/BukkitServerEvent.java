package dev.neuralnexus.taterlib.bukkit.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerEvent;
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
