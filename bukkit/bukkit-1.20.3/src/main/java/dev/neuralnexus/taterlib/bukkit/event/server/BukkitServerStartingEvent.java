package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;
import dev.neuralnexus.taterlib.server.Server;

/** Bukkit implementation of {@link ServerStartingEvent}. */
public class BukkitServerStartingEvent implements ServerStartingEvent {
    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new BukkitServer(BukkitTaterLibPlugin.getInstance().getServer());
    }
}
