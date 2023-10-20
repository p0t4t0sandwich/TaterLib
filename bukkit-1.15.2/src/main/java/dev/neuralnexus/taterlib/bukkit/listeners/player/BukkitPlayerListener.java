package dev.neuralnexus.taterlib.bukkit.listeners.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listens for player events.
 */
public class BukkitPlayerListener implements Listener {
    /**
     * Called when a player completes an advancement.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        Advancement advancement = event.getAdvancement();
        // Send advancement to message relay
        PlayerListener.onPlayerAdvancement(new BukkitPlayer(event.getPlayer()), advancement.getKey().getKey());
        PlayerListener.onPlayerAdvancementFinished(new BukkitPlayer(event.getPlayer()), advancement.getKey().getKey());
    }

    /**
     * Called when a player dies.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Send death message through relay
        PlayerListener.onPlayerDeath(new BukkitPlayer(event.getEntity()), event.getDeathMessage());
    }

    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        // Pass player to helper function
        PlayerListener.onPlayerLogin(new BukkitPlayer(event.getPlayer()));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        // Pass player to helper function
        PlayerListener.onPlayerLogout(new BukkitPlayer(event.getPlayer()));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        if (TaterLib.cancelChat) event.setCancelled(true);
        // Pass player and message to helper function
        PlayerListener.onPlayerMessage(new BukkitPlayer(event.getPlayer()), event.getMessage(), TaterLib.cancelChat);
    }
}
