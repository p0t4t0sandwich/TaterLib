package dev.neuralnexus.taterlib.bukkit.listeners.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.player.BukkitPlayerLoginEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.player.BukkitPlayerLogoutEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.player.BukkitPlayerMessageEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.events.player.BukkitPlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Listens for player events.
 */
public class BukkitPlayerListener extends org.bukkit.event.player.PlayerListener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerListener.onPlayerLogin(new BukkitPlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        PlayerListener.onPlayerLogout(new BukkitPlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @Override
    public void onPlayerChat(PlayerChatEvent event) {
        PlayerListener.onPlayerMessage(new BukkitPlayerMessageEvent(event));
    }

    /**
     * Called when a player respawns.
     * @param event The event.
     */
    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        PlayerListener.onPlayerRespawn(new BukkitPlayerRespawnEvent(event));
    }
}
