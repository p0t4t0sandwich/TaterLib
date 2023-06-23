package dev.neuralnexus.taterapi.velocity.listeners.player;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerServerSwitchListener;
import dev.neuralnexus.taterapi.velocity.player.VelocityTaterPlayer;

/**
 * Listens for proxy server switches and updates the TaterPlayer's server name.
 */
public class VelocityPlayerServerSwitchListener implements TaterPlayerServerSwitchListener {
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
        taterServerSwitch(new VelocityTaterPlayer(player), toServer);
    }
}
