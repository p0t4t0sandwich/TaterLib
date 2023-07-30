package dev.neuralnexus.taterapi.bukkit.listeners.player;

import dev.neuralnexus.taterapi.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.listeners.player.CommonPlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitPlayerListener implements Listener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        // Pass TaterPlayer to helper function
        CommonPlayerListener.onPlayerLogin(new BukkitPlayer(event.getPlayer()));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        // Pass TaterPlayer to helper function
        CommonPlayerListener.onPlayerLogout(new BukkitPlayer(event.getPlayer()));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        if (TaterAPI.cancelChat) event.setCancelled(true);
        // Send message to message relay
        CommonPlayerListener.onPlayerMessage(new BukkitPlayer(event.getPlayer()), event.getMessage(), TaterAPI.cancelChat);
    }
}
