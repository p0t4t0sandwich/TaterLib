/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.resources.Identifier;

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
    default void register(Identifier channel) {
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
