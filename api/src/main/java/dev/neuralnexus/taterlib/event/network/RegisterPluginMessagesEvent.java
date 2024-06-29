/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.network;

import dev.neuralnexus.taterlib.event.Event;

import java.util.Set;

/** Event for registering plugin messages */
public interface RegisterPluginMessagesEvent extends Event {
    /**
     * Register a plugin message
     *
     * @param channel The channel
     */
    void registerPluginChannel(String channel);

    /**
     * Register plugin messages
     *
     * @param channels The channels
     */
    void registerPluginChannels(Set<String> channels);
}
