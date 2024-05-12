package dev.neuralnexus.taterlib.v1_15_2.bukkit.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartedEvent;

import org.bukkit.event.server.ServerLoadEvent;

/** Bukkit implementation of {@link ServerStartedEvent}. */
public class BukkitServerStartedEvent extends BukkitServerEvent implements ServerStartedEvent {
    public final ServerLoadEvent event;

    public BukkitServerStartedEvent(ServerLoadEvent event) {
        super(event);
        this.event = event;
    }
}