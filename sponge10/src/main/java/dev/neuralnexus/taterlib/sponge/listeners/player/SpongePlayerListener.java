package dev.neuralnexus.taterlib.sponge.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import dev.neuralnexus.taterlib.sponge.util.SpongeTranslationUtils;
import net.kyori.adventure.text.TranslatableComponent;
import org.spongepowered.api.advancement.Advancement;
import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.living.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.message.PlayerChatEvent;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

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
        SpongePlayer player = new SpongePlayer(event.player());
        Advancement advancement = event.advancement();

        // Fire the generic advancement event
        PlayerListener.onPlayerAdvancement(player, advancement.parent().toString());

        // Fire the advancement finished event if the advancement is done
        DisplayInfo display = advancement.displayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            PlayerListener.onPlayerAdvancementFinished(player, SpongeTranslationUtils.translate((TranslatableComponent) display.title()));
        }
    }

    /**
     * Called when a player dies.
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        if ((event.entity() instanceof Player)) {
            PlayerListener.onPlayerDeath(new SpongePlayer((Player) event.entity()), ((Player) event.entity()).name() + " " + SpongeTranslationUtils.translate((TranslatableComponent) event.message()));
        }
    }

    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogin(ServerSideConnectionEvent.Join event) {
        PlayerListener.onPlayerLogin(new SpongePlayer(event.player()));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ServerSideConnectionEvent.Disconnect event) {
        PlayerListener.onPlayerLogout(new SpongePlayer(event.player()));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(PlayerChatEvent.Submit event, @All(ignoreEmpty=false) Player[] players) {
        if (players.length != 1) return;
        if (TaterLib.cancelChat) event.setCancelled(true);

        PlayerListener.onPlayerMessage(new SpongePlayer(players[0]), event.message().toString(), TaterLib.cancelChat);
    }

    /**
     * Called when a player respawns.
     * @param event The event.
     */
    @Listener
    public void onPlayerRespawn(RespawnPlayerEvent event) {
        PlayerListener.onPlayerRespawn(new SpongePlayer(event.entity()));
    }
}
