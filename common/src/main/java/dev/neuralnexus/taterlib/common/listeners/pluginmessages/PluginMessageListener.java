package dev.neuralnexus.taterlib.common.listeners.pluginmessages;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvents;

public class PluginMessageListener {
    /**
     * Called when a plugin message is received.
     * @param channel The channel.
     * @param data The data.
     */
    public static void onPluginMessage(String channel, byte[] data) {
        // Fire cross-API event
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(new Object[]{channel, data});
    }

    /**
     * Called when a plugin message is received from a server.
     * @param channel The channel.
     * @param data The data.
     */
    public static void onServerPluginMessage(String channel, byte[] data) {
        // Fire cross-API event
        PluginMessageEvents.SERVER_PLUGIN_MESSAGE.invoke(new Object[]{channel, data});
    }

    /**
     * Called when a plugin message is received from a player.
     * @param channel The channel.
     * @param data The data.
     */
    public static void onPlayerPluginMessage(String channel, AbstractPlayer player, byte[] data) {
        // Fire cross-API event
        PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(new Object[]{channel, player, data});
    }
}
