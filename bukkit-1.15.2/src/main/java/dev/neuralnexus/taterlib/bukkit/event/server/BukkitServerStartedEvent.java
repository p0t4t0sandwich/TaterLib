package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.common.event.server.AbstractServerStartedEvent;
import org.bukkit.event.server.ServerLoadEvent;

/**
 * Bukkit implementation of {@link AbstractServerStartedEvent}.
 */
public class BukkitServerStartedEvent extends BukkitServerEvent implements AbstractServerStartedEvent {
    public final ServerLoadEvent event;

    public BukkitServerStartedEvent(ServerLoadEvent event) {
        this.event = event;
    }
}
