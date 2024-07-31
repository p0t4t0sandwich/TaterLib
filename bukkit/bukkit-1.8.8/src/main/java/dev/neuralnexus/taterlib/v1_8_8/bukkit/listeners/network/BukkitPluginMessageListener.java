/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_8.bukkit.listeners.network;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.PluginMessageEventImpl;
import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterapi.network.impl.CustomPayloadImpl;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.entity.player.BukkitPlayer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        CustomPayload payload = new CustomPayloadImpl(ResourceKey.of(channel), bytes);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new PluginMessageEventImpl(payload));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new PluginMessageEventImpl.Player(payload, new BukkitPlayer(player)));
    }
}
