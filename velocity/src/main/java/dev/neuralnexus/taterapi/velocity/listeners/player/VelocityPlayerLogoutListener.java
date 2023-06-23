package dev.neuralnexus.taterapi.velocity.listeners.player;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import dev.neuralnexus.taterapi.common.listeners.player.PlayerLogoutListener;
import dev.neuralnexus.taterapi.velocity.player.VelocityTaterPlayer;

/**
 * Listens for player logout and sends it to the message relay.
 */
public class VelocityPlayerLogoutListener implements PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @Subscribe
    public void onPlayerLogout(DisconnectEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogout(new VelocityTaterPlayer(event.getPlayer()));
    }
}
