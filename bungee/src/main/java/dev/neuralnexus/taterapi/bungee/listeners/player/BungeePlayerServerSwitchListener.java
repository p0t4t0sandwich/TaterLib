package dev.neuralnexus.taterapi.bungee.listeners.player;

import dev.neuralnexus.taterapi.bungee.player.BungeeTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerServerSwitchListener;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**
 * Listens for proxy server switches and updates the TaterPlayer's server name.
 */
public class BungeePlayerServerSwitchListener implements Listener, TaterPlayerServerSwitchListener {
    /**
     * Called when a player switches servers.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerSwitch(ServerSwitchEvent event) {
        // If player is just joining, don't run this function
        if (event.getFrom() == null) return;

        // Get Player and current server
        ProxiedPlayer player = event.getPlayer();
        String toServer = player.getServer().getInfo().getName();

        // Pass Player UUID and current server to helper function
        taterServerSwitch(new BungeeTaterPlayer(player), toServer);
    }
}
