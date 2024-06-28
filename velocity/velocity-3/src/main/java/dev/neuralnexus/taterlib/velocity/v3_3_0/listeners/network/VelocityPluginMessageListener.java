/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.network;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.network.VelocityPluginMessageEvent;

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
