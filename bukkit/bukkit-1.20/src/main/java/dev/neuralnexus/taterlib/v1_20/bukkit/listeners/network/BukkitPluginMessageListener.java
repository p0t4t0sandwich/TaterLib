/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.bukkit.listeners.network;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_20.bukkit.event.network.BukkitPluginMessageEvent;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/** Listener for plugin messages. */
public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        ResourceKey key = ResourceKey.of(channel);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new BukkitPluginMessageEvent(key, bytes));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new BukkitPluginMessageEvent.Player(key, bytes, player));
    }
}
