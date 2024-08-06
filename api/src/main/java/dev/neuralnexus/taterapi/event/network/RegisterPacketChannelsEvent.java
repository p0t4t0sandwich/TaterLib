/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.Set;

/** Event for registering plugin messages */
public interface RegisterPacketChannelsEvent extends Event {
    /**
     * Register a packet channel
     *
     * @param channel The channel
     */
    void register(String channel);

    /**
     * Register a packet channel
     *
     * @param channel The channel
     */
    default void register(ResourceKey channel) {
        this.register(channel.asString());
    }

    /**
     * Register packet channel
     *
     * @param channels The channels
     */
    default void register(Set<String> channels) {
        channels.forEach(this::register);
    }
}
