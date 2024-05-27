package dev.neuralnexus.taterlib.v1_12_2.bungee.listeners.network;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.v1_12_2.bungee.event.network.BungeePluginMessageEvent;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/** Listens for plugin messages. */
public class BungeePluginMessageListener implements Listener {
    /**
     * Called when a plugin message is received.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        NetworkEvents.PLUGIN_MESSAGE.invoke(new BungeePluginMessageEvent(event));
        if (event.getReceiver() instanceof ProxiedPlayer) {
            NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(new BungeePluginMessageEvent.Player(event));
        } else if (event.getReceiver() instanceof Server) {
            NetworkEvents.SERVER_PLUGIN_MESSAGE.invoke(new BungeePluginMessageEvent.Server(event));
        }
    }
}
