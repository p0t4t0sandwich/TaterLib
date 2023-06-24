package dev.neuralnexus.taterapi.forge.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLoginListener;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player logins and adds the TaterPlayer to the cache.
 */
public class ForgePlayerLoginListener implements TaterPlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogin(new ForgeTaterPlayer(event.getPlayer()));
    }
}
