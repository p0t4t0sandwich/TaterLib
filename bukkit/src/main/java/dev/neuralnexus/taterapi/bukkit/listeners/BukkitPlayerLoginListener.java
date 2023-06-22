package dev.neuralnexus.taterapi.bukkit.listeners;

import dev.neuralnexus.taterapi.bukkit.player.BukkitTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.PlayerLoginListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listens for player logins and sends them to the message relay.
 */
public class BukkitPlayerLoginListener implements Listener, PlayerLoginListener {
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
