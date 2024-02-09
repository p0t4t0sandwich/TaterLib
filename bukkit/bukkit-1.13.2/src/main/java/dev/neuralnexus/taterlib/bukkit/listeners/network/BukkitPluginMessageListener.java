package dev.neuralnexus.taterlib.bukkit.listeners.network;

import dev.neuralnexus.taterlib.bukkit.event.network.BukkitPluginMessageEvent;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/** Listener for plugin messages. */
public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        NetworkEvents.PLUGIN_MESSAGE.invoke(new BukkitPluginMessageEvent(channel, bytes));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new BukkitPluginMessageEvent.Player(channel, bytes, player));
    }
}
