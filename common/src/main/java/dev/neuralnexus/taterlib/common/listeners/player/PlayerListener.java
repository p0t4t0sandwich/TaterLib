package dev.neuralnexus.taterlib.common.listeners.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLoginEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.player.cache.PlayerCache;

/**
 * Listens for player events.
 */
public final class PlayerListener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    public static void onPlayerLogin(AbstractPlayerLoginEvent event) {
        // Add the Player to the cache
        PlayerCache.setPlayerInCache(event.getPlayer().getUUID(), event.getPlayer());
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    public static void onPlayerLogout(AbstractPlayerLogoutEvent event) {
        // Remove the Player from the cache
        PlayerCache.removePlayerFromCache(event.getPlayer().getUUID());
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param event The event.
     */
    public static void onServerSwitch(AbstractPlayerServerSwitchEvent event) {
        AbstractPlayer abstractPlayer = event.getPlayer();

        // Get Player from cache
        AbstractPlayer cachedAbstractPlayer = PlayerCache.getPlayerFromCache(abstractPlayer.getUUID());
        if (cachedAbstractPlayer == null) {
            return;
        }

        // Update the server name and Player object
        abstractPlayer.setServerName(event.getToServer());
        PlayerCache.setPlayerInCache(abstractPlayer.getUUID(), abstractPlayer);
    }
}
