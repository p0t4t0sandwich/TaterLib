/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.bukkit.event.network;

import dev.neuralnexus.taterapi.event.network.RegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.network.BukkitPluginMessageListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;

/** Bukkit implementation of {@link RegisterPacketChannelsEvent}. */
public class BukkitRegisterPacketChannelsEvent implements RegisterPacketChannelsEvent {
    @Override
    public void register(String channel) {
        Plugin plugin = (Plugin) TaterLib.mod();
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(plugin, channel, new BukkitPluginMessageListener());
        messenger.registerOutgoingPluginChannel(plugin, channel);
    }
}
