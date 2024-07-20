/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.bungee.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_12_2.bungee.event.player.BungeePlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_12_2.bungee.event.player.BungeePlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_12_2.bungee.event.player.BungeePlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_12_2.bungee.event.player.BungeePlayerServerSwitchEvent;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/** Listens for player events. */
public class BungeePlayerListener implements Listener {
    /**
     * Called when a player logs in.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(ServerConnectedEvent event) {
        // If player is switching servers, don't run this function
        if (event.getPlayer().getServer() != null) return;
        PlayerEvents.LOGIN.invoke(new BungeePlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerDisconnectEvent event) {
        PlayerEvents.LOGOUT.invoke(new BungeePlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     *
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(ChatEvent event) {
        // If it's a command or not a player, don't run this function
        if (event.isCommand() || !(event.getSender() instanceof ProxiedPlayer)) return;
        PlayerEvents.MESSAGE.invoke(new BungeePlayerMessageEvent(event));
    }

    /**
     * Called when a player switches servers.
     *
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerSwitch(ServerConnectedEvent event) {
        // If player is just joining, don't run this function
        if (event.getPlayer().getServer() == null) return;
        PlayerEvents.SERVER_SWITCH.invoke(new BungeePlayerServerSwitchEvent(event));
    }
}
