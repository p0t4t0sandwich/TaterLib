/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.network;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.player.SimplePlayer;

/** Abstract class for plugin message events. */
public interface PluginMessageEvent extends Event {
    /**
     * Gets the channel of the plugin message.
     *
     * @return The channel of the plugin message.
     */
    String channel();

    /**
     * Gets the data of the plugin message.
     *
     * @return The data of the plugin message.
     */
    byte[] data();

    /** Abstract class for player plugin message events. */
    interface Player extends PluginMessageEvent {
        /**
         * Gets the player of the plugin message.
         *
         * @return The player of the plugin message.
         */
        SimplePlayer player();
    }

    /** Abstract class for server plugin message events. */
    interface Server extends PluginMessageEvent {
        /**
         * Gets the server of the plugin message.
         *
         * @return The server of the plugin message.
         */
        dev.neuralnexus.taterlib.server.Server server();
    }
}
