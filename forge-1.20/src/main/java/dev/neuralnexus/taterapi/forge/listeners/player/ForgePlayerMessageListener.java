package dev.neuralnexus.taterapi.forge.listeners.player;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.listeners.player.PlayerMessageListener;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player messages and sends it to the message relay.
 */
public class ForgePlayerMessageListener implements PlayerMessageListener {
    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        if (TaterAPI.cancelChat) event.setCanceled(true);

        // Send message to message relay
        taterPlayerMessage(new ForgeTaterPlayer(event.getPlayer()), event.getMessage().getString(), TaterAPI.cancelChat);
    }
}
