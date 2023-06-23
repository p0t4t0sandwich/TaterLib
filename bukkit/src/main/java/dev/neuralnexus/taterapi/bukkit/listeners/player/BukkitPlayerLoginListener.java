package dev.neuralnexus.taterapi.bukkit.listeners.player;

import dev.neuralnexus.taterapi.bukkit.player.BukkitTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLoginListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listens for player logins and adds the TaterPlayer to the cache.
 */
public class BukkitPlayerLoginListener implements Listener, TaterPlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogin(new BukkitTaterPlayer(event.getPlayer()));
    }
}
