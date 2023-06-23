package dev.neuralnexus.taterapi.bungee.listeners.player;

import dev.neuralnexus.taterapi.bungee.player.BungeeTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLogoutListener;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Listens for player logouts and removes the TaterPlayer from the cache.
 */
public class BungeePlayerLogoutListener implements Listener, TaterPlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerDisconnectEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogout(new BungeeTaterPlayer(event.getPlayer()));
    }
}
