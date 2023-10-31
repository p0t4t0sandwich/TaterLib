package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;
import org.bukkit.event.server.ServerLoadEvent;

/**
 * Bukkit implementation of {@link ServerStartedEvent}.
 */
public class BukkitServerStartedEvent extends BukkitServerEvent implements ServerStartedEvent {
    public final ServerLoadEvent event;

    public BukkitServerStartedEvent(ServerLoadEvent event) {
        this.event = event;
    }
}
