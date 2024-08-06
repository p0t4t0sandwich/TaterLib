/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.event.Event;

import java.util.Set;

/** Event for registering plugin messages */
public interface RegisterPluginMessagesEvent extends Event {
    /**
     * Register a packet channel
     *
     * @param channel The channel
     */
    void registerChannel(String channel);

    /**
     * Register packet channel
     *
     * @param channels The channels
     */
    default void registerChannels(Set<String> channels) {
        channels.forEach(this::registerChannel);
    }
}
