package dev.neuralnexus.taterapi.bukkit.listeners.player;

import dev.neuralnexus.taterapi.bukkit.player.BukkitTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.PlayerLogoutListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listens for player logouts and sends them to the message relay.
 */
public class BukkitPlayerLogoutListener implements Listener, PlayerLogoutListener {
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
