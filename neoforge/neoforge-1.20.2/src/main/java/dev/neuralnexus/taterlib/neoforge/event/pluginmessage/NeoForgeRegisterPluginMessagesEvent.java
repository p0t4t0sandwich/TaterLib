package dev.neuralnexus.taterlib.neoforge.event.pluginmessage;

import dev.neuralnexus.taterlib.common.event.pluginmessages.RegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.neoforge.networking.ModMessages;

import java.util.Set;

/** NeoForge implementation of {@link RegisterPluginMessagesEvent}. */
public class NeoForgeRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
    /** {@inheritDoc} */
    @Override
    public void registerPluginChannel(String channel) {
        ModMessages.addChannels(Set.of(channel));
    }

    /** {@inheritDoc} */
    @Override
    public void registerPluginChannels(Set<String> channels) {
        ModMessages.addChannels(channels);
    }
}
