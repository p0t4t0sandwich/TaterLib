package dev.neuralnexus.taterlib.common.listeners.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerEvents;
import dev.neuralnexus.taterlib.common.player.cache.PlayerCache;

/**
 * Listens for player events.
 */
public final class PlayerListener {
    /**
     * Called when a player advances an advancement.
     * @param player The player.
     * @param advancement The advancement.
     */
    public static void onPlayerAdvancement(AbstractPlayer player, String advancement) {
        // Fire cross-API event
        PlayerEvents.ADVANCEMENT.invoke(new Object[]{player, advancement});
    }

    /**
     * Called when a player finishes an advancement.
     * @param player The player.
     * @param advancement The advancement.
     */
    public static void onPlayerAdvancementFinished(AbstractPlayer player, String advancement) {
        // Fire cross-API event
        PlayerEvents.ADVANCEMENT_FINISHED.invoke(new Object[]{player, advancement});
    }

    /**
     * Called when a player dies.
     * @param player The player.
     * @param source The source of the death.
     */
    public static void onPlayerDeath(AbstractPlayer player, String source) {
        // Fire cross-API event
        PlayerEvents.DEATH.invoke(new Object[]{player, source});
    }

    /**
     * Called when a player logs in.
     * @param player The player.
     */
    public static void onPlayerLogin(AbstractPlayer player) {
        // Add the TaterPlayer to the cache
        PlayerCache.setPlayerInCache(player.getUUID(), player);

        // Fire cross-API event
        PlayerEvents.LOGIN.invoke(new Object[]{player});
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param abstractPlayer The player.
     */
    public static void onPlayerLogout(AbstractPlayer abstractPlayer) {
        // Remove the TaterPlayer from the cache
        PlayerCache.removePlayerFromCache(abstractPlayer.getUUID());

        // Fire cross-API event
        PlayerEvents.LOGOUT.invoke(new Object[]{abstractPlayer});
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param abstractPlayer The player
     * @param message The message
     * @param isCancelled Whether the message was cancelled
     */
    public static void onPlayerMessage(AbstractPlayer abstractPlayer, String message, boolean isCancelled) {
        // Fire cross-API event
        PlayerEvents.MESSAGE.invoke(new Object[]{abstractPlayer, message, isCancelled});
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param abstractPlayer The player.
     */
    public static void onServerSwitch(AbstractPlayer abstractPlayer, String toServer) {
        // Get TaterPlayer from cache
        AbstractPlayer cachedTaterPlayer = PlayerCache.getPlayerFromCache(abstractPlayer.getUUID());
        if (cachedTaterPlayer == null) {
            return;
        }

        // Get fromServer
        String fromServer = abstractPlayer.getServerName();

        // Update the server name and TaterPlayer object
        abstractPlayer.setServerName(toServer);
        PlayerCache.setPlayerInCache(abstractPlayer.getUUID(), abstractPlayer);

        // Fire cross-API event
        PlayerEvents.SERVER_SWITCH.invoke(new Object[]{abstractPlayer, fromServer, toServer});
    }
}
