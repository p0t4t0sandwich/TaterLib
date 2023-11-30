package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;

/** Bukkit implementation of {@link ServerEvent}. */
public class BukkitServerEvent implements ServerEvent {
    private final org.bukkit.event.server.ServerEvent event;

    public BukkitServerEvent(org.bukkit.event.server.ServerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new BukkitServer(BukkitTaterLibPlugin.getInstance().getServer());
    }
}
