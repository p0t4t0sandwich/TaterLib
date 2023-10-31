package dev.neuralnexus.taterlib.bungee.listeners.pluginmessages;

import dev.neuralnexus.taterlib.bungee.event.pluginmessages.BungeePluginMessageEvent;
import dev.neuralnexus.taterlib.common.event.api.PluginMessageEvents;
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
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(new BungeePluginMessageEvent(event));
        if (event.getReceiver() instanceof ProxiedPlayer) {
            PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(new BungeePluginMessageEvent.BungeePlayer(event));
        } else if (event.getReceiver() instanceof Server) {
            PluginMessageEvents.SERVER_PLUGIN_MESSAGE.invoke(new BungeePluginMessageEvent.BungeeServer(event));
        }
    }
}
