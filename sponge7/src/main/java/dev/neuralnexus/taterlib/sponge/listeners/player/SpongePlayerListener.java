package dev.neuralnexus.taterlib.sponge.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.advancement.Advancement;
import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/**
 * Listens to player events.
 */
public class SpongePlayerListener {
    /**
     * Called when a player progresses in an advancement.
     * @param event The event.
     */
    @Listener
    public void onPlayerAdvancement(AdvancementEvent.Grant event) {
        SpongePlayer player = new SpongePlayer(event.getTargetEntity());
        Advancement advancement = event.getAdvancement();

        // Fire the generic advancement event
        PlayerListener.onPlayerAdvancement(player, advancement.getParent().toString());

        // Fire the advancement finished event if the advancement is done
        DisplayInfo display = advancement.getDisplayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            PlayerListener.onPlayerAdvancementFinished(player, display.getTitle().toString());
        }
    }

    /**
     * Called when a player dies.
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        if ((event.getTargetEntity() instanceof Player)) {
            PlayerListener.onPlayerDeath(new SpongePlayer((Player) event.getTargetEntity()), event.getMessage().toPlain());
        }
    }

    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogin(ClientConnectionEvent.Join event) {
        PlayerListener.onPlayerLogin(new SpongePlayer(event.getTargetEntity()));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ClientConnectionEvent.Disconnect event) {
        PlayerListener.onPlayerLogout(new SpongePlayer(event.getTargetEntity()));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(MessageEvent event, @All(ignoreEmpty=false) Player[] players) {
        if (players.length != 1) return;
        if (TaterLib.cancelChat) event.setMessageCancelled(true);

        PlayerListener.onPlayerMessage(new SpongePlayer(players[0]), event.getMessage().toPlain(), TaterLib.cancelChat);
    }

    /**
     * Called when a player respawns.
     * @param event The event.
     */
    @Listener
    public void onPlayerRespawn(RespawnPlayerEvent event) {
        PlayerListener.onPlayerRespawn(new SpongePlayer(event.getTargetEntity()));
    }
}
