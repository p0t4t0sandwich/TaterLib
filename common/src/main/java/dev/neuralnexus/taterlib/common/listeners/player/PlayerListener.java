package dev.neuralnexus.taterlib.common.listeners.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.common.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.player.cache.PlayerCache;

/**
 * Listens for player events.
 */
public final class PlayerListener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    public static void onPlayerLogin(PlayerLoginEvent event) {
        // Add the Player to the cache
        PlayerCache.setPlayerInCache(event.getPlayer().getUUID(), event.getPlayer());
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    public static void onPlayerLogout(PlayerLogoutEvent event) {
        // Remove the Player from the cache
        PlayerCache.removePlayerFromCache(event.getPlayer().getUUID());
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param event The event.
     */
    public static void onServerSwitch(PlayerServerSwitchEvent event) {
        Player player = event.getPlayer();

        // Get Player from cache
        Player cachedPlayer = PlayerCache.getPlayerFromCache(player.getUUID());
        if (cachedPlayer == null) {
            return;
        }

        // Update the server name and Player object
        player.setServerName(event.getToServer());
        PlayerCache.setPlayerInCache(player.getUUID(), player);
    }
}
