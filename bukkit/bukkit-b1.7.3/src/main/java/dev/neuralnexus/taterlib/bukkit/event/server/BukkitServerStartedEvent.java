package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.common.event.server.ServerStartedEvent;
import dev.neuralnexus.taterlib.common.server.Server;

/**
 * Bukkit implementation of {@link ServerStartedEvent}.
 */
public class BukkitServerStartedEvent implements ServerStartedEvent {
    /**
     * {@inheritDoc}
     */
    @Override
    public Server getServer() {
        return new BukkitServer(BukkitTaterLibPlugin.getInstance().getServer());
    }
}
