package dev.neuralnexus.taterlib.bukkit.listeners.server;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.common.event.server.ServerEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

/**
 * Listens for server events.
 */
public class BukkitServerListener implements Listener {
    /**
     * Called when the server is starting.
     * @param event The event.
     */
    @EventHandler
    public void onServerStarted(ServerLoadEvent event) {
        if (event.getType() == ServerLoadEvent.LoadType.STARTUP) {
            ServerEvents.STARTED.invoke(new BukkitServerStartedEvent(event));
        }
    }
}
