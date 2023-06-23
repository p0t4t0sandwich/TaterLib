package dev.neuralnexus.taterapi.bungee.listeners.player;

import dev.neuralnexus.taterapi.bungee.player.BungeeTaterPlayer;
import dev.neuralnexus.taterapi.common.listeners.player.PlayerLoginListener;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Listens for player logins and sends them to the message relay.
 */
public class BungeePlayerLoginListener implements Listener, PlayerLoginListener {
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

        BungeeTaterPlayer taterPlayer = new BungeeTaterPlayer(player);
        taterPlayer.setServerName(toServer);

        // Pass TaterPlayer to helper function
        taterPlayerLogin(taterPlayer);
    }
}
