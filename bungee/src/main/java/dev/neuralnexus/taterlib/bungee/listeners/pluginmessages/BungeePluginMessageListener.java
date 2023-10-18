package dev.neuralnexus.taterlib.bungee.listeners.pluginmessages;

import dev.neuralnexus.taterlib.bungee.abstractions.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.listeners.pluginmessages.PluginMessageListener;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeePluginMessageListener implements Listener {
    /**
     * Called when a plugin message is received.
     * @param event The event.
     */
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        PluginMessageListener.onPluginMessage(event.getTag(), event.getData());
        if (event.getReceiver() instanceof Server) {
            PluginMessageListener.onServerPluginMessage(event.getTag(), event.getData());
        } else if (event.getReceiver() instanceof ProxiedPlayer) {
            PluginMessageListener.onPlayerPluginMessage(event.getTag(), new BungeePlayer((ProxiedPlayer) event.getReceiver()), event.getData());
        }
    }
}
