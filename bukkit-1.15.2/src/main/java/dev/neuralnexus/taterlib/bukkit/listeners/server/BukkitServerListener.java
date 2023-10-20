package dev.neuralnexus.taterlib.bukkit.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
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
            // Run server started event
            ServerListener.onServerStarted();
        }
    }
}
