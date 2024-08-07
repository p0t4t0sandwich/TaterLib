/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8.bungee.listeners.network;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.PluginMessageEventImpl;
import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterapi.network.impl.CustomPayloadImpl;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_8.bungee.entity.player.BungeePlayer;
import dev.neuralnexus.taterlib.v1_8.bungee.server.BungeeServer;

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
        CustomPayload payload =
                new CustomPayloadImpl(ResourceKey.of(event.getTag()), event.getData());
        NetworkEvents.PLUGIN_MESSAGE.invoke(new PluginMessageEventImpl(payload));
        if (event.getReceiver() instanceof ProxiedPlayer) {
            NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                    new PluginMessageEventImpl.Player(
                            payload, new BungeePlayer((ProxiedPlayer) event.getReceiver())));
        } else if (event.getReceiver() instanceof Server) {
            NetworkEvents.SERVER_PLUGIN_MESSAGE.invoke(
                    new PluginMessageEventImpl.Server(
                            payload, new BungeeServer(((Server) event.getReceiver()).getInfo())));
        }
    }
}
