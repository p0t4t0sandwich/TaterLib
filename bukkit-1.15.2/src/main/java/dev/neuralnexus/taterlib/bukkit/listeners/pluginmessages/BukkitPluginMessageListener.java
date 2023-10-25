package dev.neuralnexus.taterlib.bukkit.listeners.pluginmessages;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.pluginmessages.BukkitPluginMessageEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        dev.neuralnexus.taterlib.common.listeners.pluginmessages.PluginMessageListener.onPluginMessage(new BukkitPluginMessageEvent(channel, bytes));
        dev.neuralnexus.taterlib.common.listeners.pluginmessages.PluginMessageListener.onPlayerPluginMessage(new BukkitPluginMessageEvent.BukkitPlayerPluginMessageEvent(channel, bytes, player));
    }
}
