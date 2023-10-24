package dev.neuralnexus.taterlib.common.listeners.pluginmessages;

import dev.neuralnexus.taterlib.common.abstractions.events.pluginmessages.AbstractPluginMessageEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvents;

public class PluginMessageListener {
    /**
     * Called when a plugin message is received.
     * @param event The event.
     */
    public static void onPluginMessage(AbstractPluginMessageEvent event) {
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(event);
    }

    /**
     * Called when a plugin message is received from a player.
     * @param event The event.
     */
    public static void onPlayerPluginMessage(AbstractPluginMessageEvent.AbstractPlayerPluginMessageEvent event) {
        PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(event);
    }

    /**
     * Called when a plugin message is received from a server.
     * @param event The event.
     */
    public static void onServerPluginMessage(AbstractPluginMessageEvent.AbstractServerPluginMessageEvent event) {
        PluginMessageEvents.SERVER_PLUGIN_MESSAGE.invoke(event);
    }
}
