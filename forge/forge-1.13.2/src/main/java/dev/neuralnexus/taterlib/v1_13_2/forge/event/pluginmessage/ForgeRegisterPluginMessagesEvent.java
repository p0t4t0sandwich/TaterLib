/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

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
