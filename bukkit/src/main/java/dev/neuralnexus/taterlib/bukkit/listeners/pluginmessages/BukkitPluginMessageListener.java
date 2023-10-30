package dev.neuralnexus.taterlib.bukkit.listeners.pluginmessages;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.pluginmessages.BukkitPluginMessageEvent;
import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvents;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

/**
 * Listens for plugin messages.
 */
public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte[] bytes) {
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(new BukkitPluginMessageEvent(channel, bytes));
        PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(new BukkitPluginMessageEvent.BukkitPlayerPluginMessageEvent(channel, bytes, player));
    }
}
