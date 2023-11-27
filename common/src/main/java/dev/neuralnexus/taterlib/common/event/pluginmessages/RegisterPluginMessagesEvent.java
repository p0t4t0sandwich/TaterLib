package dev.neuralnexus.taterlib.common.event.pluginmessages;

import java.util.Set;

/** Event for registering plugin messages */
public interface RegisterPluginMessagesEvent {
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
