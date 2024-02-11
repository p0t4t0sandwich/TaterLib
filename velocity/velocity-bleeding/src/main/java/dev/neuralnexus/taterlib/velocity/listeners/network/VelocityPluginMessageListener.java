package dev.neuralnexus.taterlib.velocity.listeners.network;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.velocity.event.network.VelocityPluginMessageEvent;

/** Listens for plugin messages. */
public class VelocityPluginMessageListener {
    /**
     * Called when a plugin message is received.
     *
     * @param event The event.
     */
    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        NetworkEvents.PLUGIN_MESSAGE.invoke(new VelocityPluginMessageEvent(event));
        if (event.getSource() instanceof Player) {
            NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                    new VelocityPluginMessageEvent.Player(event));
        } else if (event.getSource() instanceof ServerConnection) {
            NetworkEvents.SERVER_PLUGIN_MESSAGE.invoke(
                    new VelocityPluginMessageEvent.Server(event));
        }
    }
}
