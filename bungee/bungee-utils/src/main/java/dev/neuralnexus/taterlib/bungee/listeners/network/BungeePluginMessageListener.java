/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.listeners.network;

import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.event.network.impl.S2PCustomPacketEventImpl;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.network.impl.CustomPayloadPacketImpl;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.bungee.server.BungeeServer;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/** Listens for plugin messages. */
public class BungeePluginMessageListener implements Listener {
    /**
     * Called when a plugin message is received.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        CustomPayloadPacket packet =
                new CustomPayloadPacketImpl(ResourceKey.of(event.getTag()), event.getData());
        if (event.getReceiver() instanceof ProxiedPlayer) {
            NetworkEvents.C2S_CUSTOM_PACKET.invoke(
                    new C2SCustomPacketEventImpl(
                            packet, WrapperRegistry.wrap((ProxiedPlayer) event.getReceiver())));
        } else if (event.getReceiver() instanceof Server) {
            NetworkEvents.S2P_CUSTOM_PACKET.invoke(
                    new S2PCustomPacketEventImpl(
                            packet, new BungeeServer(((Server) event.getReceiver()).getInfo())));
        }
    }
}
