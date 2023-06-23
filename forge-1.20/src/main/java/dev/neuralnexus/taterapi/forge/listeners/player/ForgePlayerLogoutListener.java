package dev.neuralnexus.taterapi.forge.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.PlayerLogoutListener;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player logout and sends it to the message relay.
 */
public class ForgePlayerLogoutListener implements PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogout(new ForgeTaterPlayer(event.getEntity()));
    }
}
