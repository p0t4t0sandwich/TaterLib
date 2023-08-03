package dev.neuralnexus.taterlib.common.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.player.cache.PlayerCache;
import dev.neuralnexus.taterlib.common.relay.MessageRelay;

import java.util.HashMap;

import static dev.neuralnexus.taterlib.common.Utils.runTaskAsync;

public final class CommonPlayerListener {
    /**
     * Called when a player logs in.
     * @param abstractPlayer The player.
     */
    public static void onPlayerLogin(AbstractPlayer abstractPlayer) {
        runTaskAsync(() -> {
            try {
                // Add the TaterPlayer to the cache
                PlayerCache.setPlayerInCache(abstractPlayer.getUUID(), abstractPlayer);

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param abstractPlayer The player.
     */
    public static void onPlayerLogout(AbstractPlayer abstractPlayer) {
        runTaskAsync(() -> {
            try {
                // Remove the TaterPlayer from the cache
                PlayerCache.removePlayerFromCache(abstractPlayer.getUUID());

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    private static final HashMap<String, Long> chatFilter = new HashMap<>();

    /**
     * Filters a message.
     * @param message The message
     * @return Whether the message was filtered
     */
    static boolean filterMessage(String message) {
        // Check if message is in chat filter
        if (chatFilter.containsKey(message)) {
            // Check if message is still in chat filter
            if (chatFilter.get(message) > System.currentTimeMillis()) {
                // Cancel message
                return true;
            } else {
                // Remove message from chat filter
                chatFilter.remove(message);
            }
        } else {
            // Add message to chat filter
            chatFilter.put(message, System.currentTimeMillis() + 1000);
        }
        return false;
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param abstractPlayer The player
     * @param message The message
     * @param isCancelled Whether the message was cancelled
     */
    public static void onPlayerMessage(AbstractPlayer abstractPlayer, String message, boolean isCancelled) {
        runTaskAsync(() -> {
            try {
                MessageRelay messageRelay = TaterLib.getMessageRelay();
                if (messageRelay == null) return;

                messageRelay.sendPlayerMessage(abstractPlayer, abstractPlayer.getServerName(), message, isCancelled);

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param abstractPlayer The player.
     */
    public static void onServerSwitch(AbstractPlayer abstractPlayer, String toServer) {
        runTaskAsync(() -> {
            try {
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

                // TODO: Apply cross-API event system
                // Relay the server switch message
                // relay.sendPlayerServerSwitch(taterPlayer, fromServer, toServer);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
