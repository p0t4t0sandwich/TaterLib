package dev.neuralnexus.taterlib.v1_13_2.forge.event.pluginmessage;

import dev.neuralnexus.taterlib.event.network.RegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.networking.ModMessages;

import java.util.Collections;
import java.util.Set;

/** Forge implementation of {@link RegisterPluginMessagesEvent}. */
public class ForgeRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
    /** {@inheritDoc} */
    @Override
    public void registerPluginChannel(String channel) {
        ModMessages.addChannels(Collections.singleton(channel));
    }

    /** {@inheritDoc} */
    @Override
    public void registerPluginChannels(Set<String> channels) {
        ModMessages.addChannels(channels);
    }
}
