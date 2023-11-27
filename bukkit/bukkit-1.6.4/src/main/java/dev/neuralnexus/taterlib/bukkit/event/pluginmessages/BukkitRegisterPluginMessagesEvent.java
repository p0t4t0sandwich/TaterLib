package dev.neuralnexus.taterlib.bukkit.event.pluginmessages;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.listeners.pluginmessages.BukkitPluginMessageListener;
import dev.neuralnexus.taterlib.common.event.pluginmessages.RegisterPluginMessagesEvent;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;

import java.util.Set;

/** Bukkit implementation of {@link RegisterPluginMessagesEvent}. */
public class BukkitRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
    /** {@inheritDoc} */
    @Override
    public void registerPluginChannel(String channel) {
        Plugin plugin = BukkitTaterLibPlugin.getInstance();
        Messenger messenger = plugin.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(plugin, channel, new BukkitPluginMessageListener());
        messenger.registerOutgoingPluginChannel(plugin, channel);
    }

    /** {@inheritDoc} */
    @Override
    public void registerPluginChannels(Set<String> channels) {
        channels.forEach(this::registerPluginChannel);
    }
}
