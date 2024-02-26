package dev.neuralnexus.taterlib.v1_20.bukkit.event.network;

import dev.neuralnexus.taterlib.event.network.RegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_20.bukkit.listeners.network.BukkitPluginMessageListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.messaging.Messenger;

import java.util.Set;

/** Bukkit implementation of {@link RegisterPluginMessagesEvent}. */
public class BukkitRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
    /** {@inheritDoc} */
    @Override
    public void registerPluginChannel(String channel) {
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(
                BukkitTaterLibPlugin.plugin, channel, new BukkitPluginMessageListener());
        messenger.registerOutgoingPluginChannel(BukkitTaterLibPlugin.plugin, channel);
    }

    /** {@inheritDoc} */
    @Override
    public void registerPluginChannels(Set<String> channels) {
        channels.forEach(this::registerPluginChannel);
    }
}
