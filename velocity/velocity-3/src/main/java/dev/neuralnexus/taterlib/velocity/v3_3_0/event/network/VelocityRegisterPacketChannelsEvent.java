/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.event.network;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import dev.neuralnexus.taterapi.event.network.RegisterPacketChannelsEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;

/** Velocity implementation of {@link RegisterPacketChannelsEvent}. */
public class VelocityRegisterPacketChannelsEvent implements RegisterPacketChannelsEvent {
    @Override
    public void register(String channel) {
        ((ProxyServer) MetaAPI.instance().server())
                .getChannelRegistrar()
                .register(MinecraftChannelIdentifier.from(channel));
    }
}
