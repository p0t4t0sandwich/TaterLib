package dev.neuralnexus.taterapi.forge.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.PlayerLoginListener;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player login and sends it to the message relay.
 */
public class ForgePlayerLoginListener implements PlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // Pass TaterPlayer to helper function
        taterPlayerLogin(new ForgeTaterPlayer(event.getEntity()));
    }
}
