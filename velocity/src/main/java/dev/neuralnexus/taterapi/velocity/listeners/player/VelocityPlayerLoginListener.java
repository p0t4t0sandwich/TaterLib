package dev.neuralnexus.taterapi.velocity.listeners.player;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLoginListener;
import dev.neuralnexus.taterapi.velocity.player.VelocityTaterPlayer;

/**
 * Listens for player logins and adds the TaterPlayer to the cache.
 */
public class VelocityPlayerLoginListener implements TaterPlayerLoginListener {
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

        VelocityTaterPlayer taterPlayer = new VelocityTaterPlayer(player);
        taterPlayer.setServerName(toServer);

        // Pass TaterPlayer to helper function
        taterPlayerLogin(taterPlayer);
    }
}
