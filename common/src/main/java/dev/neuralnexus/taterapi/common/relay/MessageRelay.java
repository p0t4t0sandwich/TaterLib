package dev.neuralnexus.taterapi.common.relay;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterapi.common.player.TaterPlayer;

import java.util.HashMap;
import java.util.UUID;

/**
 * Class for relaying messages to all players.
 */
public class MessageRelay {
    /**
     * Properties of the MessageRelay class.
     * singleton: The singleton instance of the MessageRelay class
     * pronounPlayerCache: A cache of TaterPlayer objects
     */
    private static MessageRelay singleton = null;
    private final HashMap<UUID, TaterPlayer> taterPlayerCache = new HashMap<>();
    private final HashMap<String, String> formatting;

    /**
     * Constructor for the MessageRelay class.
     */
    public MessageRelay(HashMap<String, String> formatting) {
        singleton = this;
        this.formatting = formatting;
    }

    /**
     * Getter for the PronounPlayer cache.
     * @param uuid The UUID of the player
     * @return The PronounPlayer cache
     */
    public TaterPlayer getTaterPlayerFromCache(UUID uuid) {
        return this.taterPlayerCache.get(uuid);
    }

    /**
     * Setter for the PronounPlayer cache.
     * @param uuid The UUID of the player
     * @param taterPlayer The PronounPlayer object
     */
    public void setTaterPlayerInCache(UUID uuid, TaterPlayer taterPlayer) {
        this.taterPlayerCache.put(uuid, taterPlayer);
    }

    /**
     * Removes a PronounPlayer object from the cache.
     * @param uuid The UUID of the player
     */
    public void removeTaterPlayerFromCache(UUID uuid) {
        this.taterPlayerCache.remove(uuid);
    }

    /**
     * Getter for the singleton instance of the MessageRelay class.
     * @return The singleton instance
     */
    public static MessageRelay getInstance() {
        return singleton;
    }

    /**
     * Relays a message to all players.
     * @param player The player
     * @param message The message
     */
    public void sendMessage(TaterPlayer player, String message) {
        // Message formatting
        String formattedMessage = player.parsePlaceholders(this.formatting.get("global")).parseString("message", message).getResult();
        TaterAPI.useLogger(PlaceholderParser.stripSectionSign(formattedMessage));

        // Relay message to each PronounPlayer in the cache
        for (TaterPlayer taterPlayer : this.taterPlayerCache.values()) {
            taterPlayer.sendMessage(formattedMessage);
        }
    }

    /**
     * Relays a message from the Minecraft server to Discord, or to a remote server.
     * @param player The player
     * @param server The server
     * @param message The message
     * @param isCancelled Whether the message was cancelled
     */
    public void sendMessage(TaterPlayer player, String server, String message, boolean isCancelled) {
        // Message
        String formattedMessage = player.parsePlaceholders(this.formatting.get("global")).parseString("message", message).getResult();
        TaterAPI.useLogger(PlaceholderParser.stripSectionSign(formattedMessage));

        // Relay message to each TaterPlayer on every other server (Global chat)
        for (TaterPlayer taterPlayer : this.taterPlayerCache.values()) {
            if (isCancelled || !taterPlayer.getServerName().equals(server)) {
                taterPlayer.sendMessage(formattedMessage);
            }
        }
        // TODO: Fire message event
    }

    /**
     * Relay a system message from the Minecraft server to Discord.
     * @param server The server
     * @param message The message
     */
    public void sendSystemMessage(String server, String message) {
        // TODO: Fire system message event
    }

    /**
     * Relay a player switch event from the Minecraft server to Discord.
     * @param player The player
     * @param fromServer The server the player is switching from
     * @param toServer The server the player is switching to
     */
    public void sendPlayerServerSwitch(TaterPlayer player, String fromServer, String toServer) {
        if (fromServer != null) {
            // Relay player logout to Discord
            this.sendSystemMessage(player.getServerName(), player.getDisplayName() + " left the game");
        }

        // Relay player login to Discord
        this.sendSystemMessage(toServer, player.getDisplayName() + " joined the game");
    }
}
