package dev.neuralnexus.taterlib.bungee.listeners.player;

import dev.neuralnexus.taterlib.bungee.event.player.BungeePlayerLoginEvent;
import dev.neuralnexus.taterlib.bungee.event.player.BungeePlayerLogoutEvent;
import dev.neuralnexus.taterlib.bungee.event.player.BungeePlayerMessageEvent;
import dev.neuralnexus.taterlib.bungee.event.player.BungeePlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
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
    public void onPlayerLogin(ServerSwitchEvent event) {
        // If player is switching servers, don't run this function
        if (event.getFrom() != null) return;
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
        if (event.isCommand()
                || event.isProxyCommand()
                || !(event.getSender() instanceof ProxiedPlayer)) return;
        PlayerEvents.MESSAGE.invoke(new BungeePlayerMessageEvent(event));
    }

    /**
     * Called when a player switches servers.
     *
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerSwitch(ServerSwitchEvent event) {
        // If player is just joining, don't run this function
        if (event.getFrom() == null) return;
        PlayerEvents.SERVER_SWITCH.invoke(new BungeePlayerServerSwitchEvent(event));
    }
}
