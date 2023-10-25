package dev.neuralnexus.taterlib.common.listeners.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.*;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerEvents;
import dev.neuralnexus.taterlib.common.player.cache.PlayerCache;

/**
 * Listens for player events.
 */
public final class PlayerListener {
    /**
     * Called when a player progresses an advancement.
     * @param event The event.
     */
    public static void onPlayerAdvancementProgress(AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(event);
    }

    /**
     * Called when a player finishes an advancement.
     * @param event The event.
     */
    public static void onPlayerAdvancementFinished(AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent event) {
        PlayerEvents.ADVANCEMENT_FINISHED.invoke(event);
    }

    /**
     * Called when a player dies.
     * @param event The event.
     */
    public static void onPlayerDeath(AbstractPlayerDeathEvent event) {
        PlayerEvents.DEATH.invoke(event);
    }

    /**
     * Called when a player logs in.
     * @param event The event.
     */
    public static void onPlayerLogin(AbstractPlayerLoginEvent event) {
        // Add the TaterPlayer to the cache
        PlayerCache.setPlayerInCache(event.getPlayer().getUUID(), event.getPlayer());

        PlayerEvents.LOGIN.invoke(event);
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    public static void onPlayerLogout(AbstractPlayerLogoutEvent event) {
        // Remove the TaterPlayer from the cache
        PlayerCache.removePlayerFromCache(event.getPlayer().getUUID());

        PlayerEvents.LOGOUT.invoke(event);
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    public static void onPlayerMessage(AbstractPlayerMessageEvent event) {
        PlayerEvents.MESSAGE.invoke(event);
    }

    /**
     * Called when a player respawns.
     * @param event The event.
     */
    public static void onPlayerRespawn(AbstractPlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(event);
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param event The event.
     */
    public static void onServerSwitch(AbstractPlayerServerSwitchEvent event) {
        AbstractPlayer abstractPlayer = event.getPlayer();

        // Get AbstractPlayer from cache
        AbstractPlayer cachedAbstractPlayer = PlayerCache.getPlayerFromCache(abstractPlayer.getUUID());
        if (cachedAbstractPlayer == null) {
            return;
        }

        // Get fromServer
        String fromServer = abstractPlayer.getServerName();

        // Update the server name and TaterPlayer object
        abstractPlayer.setServerName(event.getToServer());
        PlayerCache.setPlayerInCache(abstractPlayer.getUUID(), abstractPlayer);

        PlayerEvents.SERVER_SWITCH.invoke(event);
    }
}
