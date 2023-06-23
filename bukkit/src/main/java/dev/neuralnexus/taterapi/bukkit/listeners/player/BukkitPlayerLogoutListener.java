package dev.neuralnexus.taterapi.bukkit.listeners.player;

import dev.neuralnexus.taterapi.bukkit.player.BukkitTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLogoutListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listens for player logouts and removes the TaterPlayer from the cache.
 */
public class BukkitPlayerLogoutListener implements Listener, TaterPlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogout(new BukkitTaterPlayer(event.getPlayer()));
    }
}
