package dev.neuralnexus.taterlib.v1_20.bukkit.listeners.network;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.v1_20.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.network.VanillaPluginMessageEvent;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

/** Listens for plugin messages. */
public class BukkitPluginMessageListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(
            @NotNull String channel, @NotNull Player player, byte[] bytes) {
        NetworkEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent(channel, bytes));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent.Player(
                        channel, bytes, BukkitAdapter.get().player(player)));
    }
}
