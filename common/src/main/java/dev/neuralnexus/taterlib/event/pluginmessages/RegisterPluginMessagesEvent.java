package dev.neuralnexus.taterlib.event.pluginmessages;

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
