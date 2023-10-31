package dev.neuralnexus.taterlib.common.event.pluginmessages;

import dev.neuralnexus.taterlib.common.player.AbstractPlayer;

/**
 * Abstract class for plugin message events.
 */
public interface AbstractPluginMessageEvent {
    /**
     * Gets the channel of the plugin message.
     * @return The channel of the plugin message.
     */
    String getChannel();

    /**
     * Gets the data of the plugin message.
     * @return The data of the plugin message.
     */
    byte[] getData();

    /**
     * Abstract class for player plugin message events.
     */
    interface AbstractPlayerPluginMessageEvent extends AbstractPluginMessageEvent {
        /**
         * Gets the player of the plugin message.
         * @return The player of the plugin message.
         */
        AbstractPlayer getPlayer();
    }

    /**
     * Abstract class for server plugin message events.
     */
    interface AbstractServerPluginMessageEvent extends AbstractPluginMessageEvent {
        /**
         * Gets the server of the plugin message.
         * TODO: AbstractServer
         * @return The server of the plugin message.
         */
        Object getServer();
    }
}
