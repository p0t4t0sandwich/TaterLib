/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.bukkit.listeners.network;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.network.impl.CustomPayloadPacketImpl;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.entity.player.BukkitPlayer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

/** Listener for plugin messages. */
public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(
            @NotNull String channel, @NotNull Player player, byte @NotNull [] bytes) {
        NetworkEvents.C2S_CUSTOM_PACKET.invoke(
                new C2SCustomPacketEventImpl(
                        new CustomPayloadPacketImpl(ResourceKey.of(channel), bytes),
                        new BukkitPlayer(player)));
    }
}
