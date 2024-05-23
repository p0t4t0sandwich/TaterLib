package dev.neuralnexus.taterlib.v1_13_2.bukkit.listeners.server;

import dev.neuralnexus.taterlib.v1_13_2.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.event.api.ServerEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

/** Listens for server events. */
public class BukkitServerListener implements Listener {
    /**
     * Called when the server is starting.
     *
     * @param event The event.
     */
    @EventHandler
    public void onServerStarted(ServerLoadEvent event) {
        if (event.getType() == ServerLoadEvent.LoadType.STARTUP) {
            ServerEvents.STARTED.invoke(new BukkitServerStartedEvent(event));
        }
    }
}