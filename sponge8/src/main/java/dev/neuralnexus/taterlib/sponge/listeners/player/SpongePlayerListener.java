package dev.neuralnexus.taterlib.sponge.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.PlayerChatEvent;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

public class SpongePlayerListener {
    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogin(ServerSideConnectionEvent.Join event) {
        // Pass AbstractPlayer to helper function
        PlayerListener.onPlayerLogin(new SpongePlayer(event.player()));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ServerSideConnectionEvent.Disconnect event) {
        // Pass AbstractPlayer to helper function
        PlayerListener.onPlayerLogout(new SpongePlayer(event.player()));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(PlayerChatEvent event, @All(ignoreEmpty=false) Player[] players) {
        if (players.length != 1) return;
        if (TaterLib.cancelChat) event.setCancelled(true);
        // Pass AbstractPlayer to helper function
        PlayerListener.onPlayerMessage(new SpongePlayer(players[0]), event.message().toString(), TaterLib.cancelChat);
    }
}
