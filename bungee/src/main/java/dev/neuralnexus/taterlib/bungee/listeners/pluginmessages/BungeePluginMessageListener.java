package dev.neuralnexus.taterlib.bungee.listeners.pluginmessages;

import dev.neuralnexus.taterlib.bungee.abstractions.events.pluginmessages.BungeePluginMessageEvent;
import dev.neuralnexus.taterlib.common.listeners.pluginmessages.PluginMessageListener;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Listens for plugin messages.
 */
public class BungeePluginMessageListener implements Listener {
    /**
     * Called when a plugin message is received.
     * @param event The event.
     */
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        PluginMessageListener.onPluginMessage(new BungeePluginMessageEvent(event));
        if (event.getReceiver() instanceof Server) {
            PluginMessageListener.onServerPluginMessage(new BungeePluginMessageEvent.BungeeServerPluginMessageEvent(event));
        } else if (event.getReceiver() instanceof ProxiedPlayer) {
            PluginMessageListener.onPlayerPluginMessage(new BungeePluginMessageEvent.BungeePlayerPluginMessageEvent(event));
        }
    }
}
