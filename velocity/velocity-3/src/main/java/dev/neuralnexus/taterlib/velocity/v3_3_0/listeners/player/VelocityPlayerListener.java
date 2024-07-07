/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.player;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerLoginEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerLogoutEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerMessageEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.player.VelocityPlayerServerSwitchEvent;

/** Listens for player events. */
public class VelocityPlayerListener {
    /**
     * Called when a player logs in.
     *
     * @param event The player login event
     */
    @Subscribe
    public void onPlayerLogin(ServerConnectedEvent event) {
        // If player is switching servers, don't run this function
        if (event.getPreviousServer().isPresent()) return;
        PlayerEvents.LOGIN.invoke(new VelocityPlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The player logout event
     */
    @Subscribe
    public void onPlayerLogout(DisconnectEvent event) {
        PlayerEvents.LOGOUT.invoke(new VelocityPlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     *
     * @param event The player message event
     */
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerMessage(PlayerChatEvent event) {
        if (event.getMessage().startsWith("/")) return;
        PlayerEvents.MESSAGE.invoke(new VelocityPlayerMessageEvent(event));
    }

    /**
     * Called when a player switches servers.
     *
     * @param event The player server switch event
     */
    @Subscribe
    public void onServerSwitch(ServerConnectedEvent event) {
        // If player is just joining, don't run this function
        if (!event.getPreviousServer().isPresent()) return;
        PlayerEvents.SERVER_SWITCH.invoke(new VelocityPlayerServerSwitchEvent(event));
    }
}
