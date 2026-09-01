/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.event.pluginmessage;

import dev.neuralnexus.taterapi.event.network.RegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.networking.ModMessages;

import java.util.Collections;

/** Forge implementation of {@link RegisterPacketChannelsEvent}. */
public class ForgeRegisterPacketChannelsEvent implements RegisterPacketChannelsEvent {
    @Override
    public void register(String channel) {
        ModMessages.addChannels(Collections.singleton(channel));
    }
}
