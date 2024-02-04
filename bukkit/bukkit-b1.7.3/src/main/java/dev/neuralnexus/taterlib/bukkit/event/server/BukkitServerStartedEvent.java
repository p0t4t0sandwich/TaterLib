package dev.neuralnexus.taterlib.bukkit.event.server;

import dev.neuralnexus.taterlib.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerStartedEvent}. */
public class BukkitServerStartedEvent implements ServerStartedEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer getServer() {
        return new BukkitServer(Bukkit.getServer());
    }
}
