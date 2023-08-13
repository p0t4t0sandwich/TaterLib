package dev.neuralnexus.taterlib.bungee.listeners.player;

import dev.neuralnexus.taterlib.bungee.abstractions.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class BungeePlayerListener implements Listener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(ServerSwitchEvent event) {
        // If player is switching servers, don't run this function
        if (event.getFrom() != null) return;

        // Get Player and current server
        ProxiedPlayer player = event.getPlayer();
        String toServer = event.getPlayer().getServer().getInfo().getName();

        BungeePlayer bungeePlayer = new BungeePlayer(player);
        bungeePlayer.setServerName(toServer);

        // Pass player to helper function
        PlayerListener.onPlayerLogin(bungeePlayer);
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerDisconnectEvent event) {
        // Pass TaterPlayer to helper function
        PlayerListener.onPlayerLogout(new BungeePlayer(event.getPlayer()));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(ChatEvent event) {
        // If cancelled, or is a command, ignore
        if (event.isCancelled() || event.isCommand() || event.isProxyCommand()) return;
        if (TaterLib.cancelChat) event.setCancelled(true);

        // Get player, message and server
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        String message = event.getMessage();

        // Send message to message relay
        PlayerListener.onPlayerMessage(new BungeePlayer(player), message, TaterLib.cancelChat);
    }

    /**
     * Called when a player switches servers.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerSwitch(ServerSwitchEvent event) {
        // If player is just joining, don't run this function
        if (event.getFrom() == null) return;

        // Get Player and current server
        ProxiedPlayer player = event.getPlayer();
        String toServer = player.getServer().getInfo().getName();

        // Pass Player UUID and current server to helper function
        PlayerListener.onServerSwitch(new BungeePlayer(player), toServer);
    }
}
