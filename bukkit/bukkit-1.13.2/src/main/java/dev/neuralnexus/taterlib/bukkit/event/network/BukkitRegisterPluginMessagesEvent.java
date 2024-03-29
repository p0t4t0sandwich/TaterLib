package dev.neuralnexus.taterlib.bukkit.event.network;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.listeners.network.BukkitPluginMessageListener;
import dev.neuralnexus.taterlib.event.network.RegisterPluginMessagesEvent;

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
