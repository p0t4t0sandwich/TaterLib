package dev.neuralnexus.taterlib.v1_7_10.bukkit.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerStartedEvent}. */
public class BukkitServerStartedEvent implements ServerStartedEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new BukkitServer(Bukkit.getServer());
    }
}
