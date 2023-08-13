package dev.neuralnexus.taterlib.bungee.listeners.server;

import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;

public class BungeeServerListener implements Listener {
    /**
     * Called when the server is starting.
     */
    public void onServerStarting(ServerSwitchEvent event) {
        // Run server starting event
        ServerListener.onServerStarting();
    }
}
