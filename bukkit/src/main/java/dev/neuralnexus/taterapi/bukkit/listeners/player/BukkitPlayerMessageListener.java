package dev.neuralnexus.taterapi.bukkit.listeners.player;

import dev.neuralnexus.taterapi.bukkit.player.BukkitTaterPlayer;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerMessageListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listens for player messages and sends them to the message relay.
 */
public class BukkitPlayerMessageListener implements Listener, TaterPlayerMessageListener {
    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        if (TaterAPI.cancelChat) event.setCancelled(true);
        // Send message to message relay
        taterPlayerMessage(new BukkitTaterPlayer(event.getPlayer()), event.getMessage(), TaterAPI.cancelChat);
    }
}
