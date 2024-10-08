/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.network;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.event.network.impl.S2PCustomPacketEventImpl;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.network.impl.CustomPayloadPacketImpl;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

/** Listens for plugin messages. */
public class VelocityPluginMessageListener {
    /**
     * Called when a plugin message is received.
     *
     * @param event The event.
     */
    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        CustomPayloadPacket packet =
                new CustomPayloadPacketImpl(
                        ResourceKey.of(event.getIdentifier().getId()), event.getData());
        if (event.getSource() instanceof Player) {
            NetworkEvents.C2S_CUSTOM_PACKET.invoke(
                    new C2SCustomPacketEventImpl(
                            packet,
                            new VelocityPlayer(
                                    (com.velocitypowered.api.proxy.Player) event.getSource())));
        } else if (event.getSource() instanceof ServerConnection) {
            NetworkEvents.S2P_CUSTOM_PACKET.invoke(
                    new S2PCustomPacketEventImpl(
                            packet, new VelocityServer((RegisteredServer) event.getSource())));
        }
    }
}
