/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.event.network;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import dev.neuralnexus.taterapi.event.network.RegisterPluginMessagesEvent;
import dev.neuralnexus.taterloader.Loader;

import java.util.Set;

/** Velocity implementation of {@link RegisterPluginMessagesEvent}. */
public class VelocityRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
    @Override
    public void registerPluginChannel(String channel) {
        ((ProxyServer) Loader.instance().server())
                .getChannelRegistrar()
                .register(MinecraftChannelIdentifier.from(channel));
    }

    @Override
    public void registerPluginChannels(Set<String> channels) {
        channels.forEach(this::registerPluginChannel);
    }
}
