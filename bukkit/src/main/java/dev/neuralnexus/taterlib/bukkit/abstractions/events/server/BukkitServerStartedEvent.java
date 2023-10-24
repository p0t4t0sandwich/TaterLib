package dev.neuralnexus.taterlib.bukkit.abstractions.events.server;

import dev.neuralnexus.taterlib.common.abstractions.events.server.AbstractServerStartedEvent;
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
