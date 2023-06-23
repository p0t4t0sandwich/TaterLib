package dev.neuralnexus.taterapi.velocity.listeners.player;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.listeners.player.PlayerMessageListener;
import dev.neuralnexus.taterapi.velocity.player.VelocityTaterPlayer;

/**
 * Listens for player messages and sends it to the message relay.
 */
public class VelocityPlayerMessageListener implements PlayerMessageListener {
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
        taterPlayerMessage(new VelocityTaterPlayer(player), message, TaterAPI.cancelChat);
    }
}
