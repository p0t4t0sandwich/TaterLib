package dev.neuralnexus.taterlib.bukkit.listeners.pluginmessages;

import dev.neuralnexus.taterlib.bukkit.event.pluginmessages.BukkitPluginMessageEvent;
import dev.neuralnexus.taterlib.event.api.PluginMessageEvents;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/** Listener for plugin messages. */
public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(new BukkitPluginMessageEvent(channel, bytes));
        PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new BukkitPluginMessageEvent.Player(channel, bytes, player));
    }
}
