package dev.neuralnexus.taterlib.v1_19.forge.event.pluginmessage;

import dev.neuralnexus.taterlib.event.network.RegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.v1_19.forge.networking.ModMessages;

import java.util.Set;

/** Forge implementation of {@link RegisterPluginMessagesEvent}. */
public class ForgeRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
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
