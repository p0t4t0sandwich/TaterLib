package dev.neuralnexus.taterapi.velocity.listeners.player;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.listeners.player.CommonPlayerListener;
import dev.neuralnexus.taterapi.velocity.player.VelocityPlayer;

public class VelocityPlayerListener {
    /**
     * Called when a player logs in.
     * @param event The player login event
     */
    @Subscribe
    public void onPlayerLogin(ServerConnectedEvent event) {
        // If player is switching servers, don't run this function
        if (event.getPreviousServer().isPresent()) return;

        // Get Player and current server
        Player player = event.getPlayer();
        String toServer = event.getServer().getServerInfo().getName();

        VelocityPlayer taterPlayer = new VelocityPlayer(player);
        taterPlayer.setServerName(toServer);

        // Pass TaterPlayer to helper function
        CommonPlayerListener.onPlayerLogin(taterPlayer);
    }

    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @Subscribe
    public void onPlayerLogout(DisconnectEvent event) {
        // Pass TaterPlayer to helper function
        CommonPlayerListener.onPlayerLogout(new VelocityPlayer(event.getPlayer()));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param event The player message event
     */
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerMessage(PlayerChatEvent event) {
        if (TaterAPI.cancelChat) event.setResult(PlayerChatEvent.ChatResult.denied());

        // Get player and message
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (!player.getCurrentServer().isPresent()) return;

        // Send message to message relay
        CommonPlayerListener.onPlayerMessage(new VelocityPlayer(player), message, TaterAPI.cancelChat);
    }

    /**
     * Called when a player switches servers.
     * @param event The player server switch event
     */
    @Subscribe
    public void onServerSwitch(ServerConnectedEvent event) {
        // If player is just joining, don't run this function
        if (!event.getPreviousServer().isPresent()) return;

        // Get Player and current server
        Player player = event.getPlayer();
        String toServer = event.getServer().getServerInfo().getName();

        // Pass Player and current server to helper function
        CommonPlayerListener.onServerSwitch(new VelocityPlayer(player), toServer);
    }
}
