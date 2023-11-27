package dev.neuralnexus.taterlib.common.event.pluginmessages;

/** Abstract class for plugin message events. */
public interface PluginMessageEvent {
    /**
     * Gets the channel of the plugin message.
     *
     * @return The channel of the plugin message.
     */
    String getChannel();

    /**
     * Gets the data of the plugin message.
     *
     * @return The data of the plugin message.
     */
    byte[] getData();

    /** Abstract class for player plugin message events. */
    interface Player extends PluginMessageEvent {
        /**
         * Gets the player of the plugin message.
         *
         * @return The player of the plugin message.
         */
        dev.neuralnexus.taterlib.common.player.Player getPlayer();
    }

    /** Abstract class for server plugin message events. */
    interface Server extends PluginMessageEvent {
        /**
         * Gets the server of the plugin message. TODO: AbstractServer
         *
         * @return The server of the plugin message.
         */
        Object getServer();
    }
}
