package dev.neuralnexus.taterlib.forge.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.CommonPlayerListener;
import dev.neuralnexus.taterlib.forge.abstrations.player.ForgePlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgePlayerListener {
    /**
     * Called when a player logs in.
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // Pass TaterPlayer to helper function
        CommonPlayerListener.onPlayerLogin(new ForgePlayer(event.getPlayer()));
    }

    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        // Pass TaterPlayer to helper function
        CommonPlayerListener.onPlayerLogout(new ForgePlayer(event.getPlayer()));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        if (TaterLib.cancelChat) event.setCanceled(true);

        // Send message to message relay
        CommonPlayerListener.onPlayerMessage(new ForgePlayer(event.getPlayer()), event.getMessage(), TaterLib.cancelChat);
    }
}
