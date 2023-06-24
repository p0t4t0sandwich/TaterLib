package dev.neuralnexus.taterapi.forge.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLogoutListener;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player logouts and removes the TaterPlayer from the cache.
 */
public class ForgePlayerLogoutListener implements TaterPlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogout(new ForgeTaterPlayer(event.getPlayer()));
    }
}
