package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterlib.server.Server;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerStoppedEvent}. */
public class BukkitServerStoppedEvent implements ServerStoppedEvent {
    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return new BukkitServer(Bukkit.getServer());
    }
}
