package dev.neuralnexus.taterlib.v1_8_8.bukkit.listeners.network;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.event.network.BukkitPluginMessageEvent;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        NetworkEvents.PLUGIN_MESSAGE.invoke(new BukkitPluginMessageEvent(channel, bytes));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new BukkitPluginMessageEvent.Player(channel, bytes, player));
    }
}
