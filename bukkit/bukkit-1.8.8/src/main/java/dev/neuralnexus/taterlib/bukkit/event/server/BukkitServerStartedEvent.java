package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;
import dev.neuralnexus.taterlib.server.Server;

/** Bukkit implementation of {@link ServerStartedEvent}. */
public class BukkitServerStartedEvent implements ServerStartedEvent {
    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new BukkitServer(BukkitTaterLibPlugin.getInstance().getServer());
    }
}
