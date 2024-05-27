package dev.neuralnexus.taterlib.v1_13.sponge.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_13.sponge.event.player.*;

import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.living.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.message.PlayerChatEvent;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/** Listens to player events. */
public class SpongePlayerListener {
    /**
     * Called when a player progresses in an advancement.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerAdvancement(AdvancementEvent.Grant event) {
        DisplayInfo display = event.advancement().displayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new SpongePlayerAdvancementEvent.AdvancementFinished(event));
        } else {
            PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                    new SpongePlayerAdvancementEvent.AdvancementProgress(event));
        }
    }

    /**
     * Called when a player dies.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        if ((event.entity() instanceof Player)) {
            PlayerEvents.DEATH.invoke(new SpongePlayerDeathEvent(event));
        }
    }

    /**
     * Called when a player logs in.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerLogin(ServerSideConnectionEvent.Join event) {
        PlayerEvents.LOGIN.invoke(new SpongePlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ServerSideConnectionEvent.Disconnect event) {
        PlayerEvents.LOGOUT.invoke(new SpongePlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(PlayerChatEvent event, @All(ignoreEmpty = false) Player[] players) {
        if (players.length != 1) return;
        PlayerEvents.MESSAGE.invoke(new SpongePlayerMessageEvent(event, players));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerRespawn(RespawnPlayerEvent.Recreate event) {
        PlayerEvents.RESPAWN.invoke(new SpongePlayerRespawnEvent(event));
    }
}
