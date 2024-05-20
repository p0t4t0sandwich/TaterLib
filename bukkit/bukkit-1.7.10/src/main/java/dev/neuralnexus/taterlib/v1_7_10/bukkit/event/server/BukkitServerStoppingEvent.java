package dev.neuralnexus.taterlib.v1_7_10.bukkit.event.server;

import dev.neuralnexus.taterlib.v1_7_10.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerStoppingEvent}. */
public class BukkitServerStoppingEvent implements ServerStoppingEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new BukkitServer(Bukkit.getServer());
    }
}
