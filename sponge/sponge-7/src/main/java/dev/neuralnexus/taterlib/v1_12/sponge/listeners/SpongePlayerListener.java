/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12.sponge.listeners;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.sponge.legacy.event.player.SpongePlayerLoginEvent;
import dev.neuralnexus.taterlib.sponge.legacy.event.player.SpongePlayerLogoutEvent;
import dev.neuralnexus.taterlib.sponge.legacy.event.player.SpongePlayerMessageEvent;
import dev.neuralnexus.taterlib.sponge.legacy.event.player.SpongePlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_12.sponge.event.player.SpongePlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_12.sponge.event.player.SpongePlayerDeathEvent;

import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/** Listens to player events. */
public class SpongePlayerListener {
    /**
     * Called when a player progresses in an advancement.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerAdvancement(AdvancementEvent.Grant event) {
        DisplayInfo display = event.getAdvancement().getDisplayInfo().orElse(null);
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
        if ((event.getTargetEntity() instanceof Player)) {
            PlayerEvents.DEATH.invoke(new SpongePlayerDeathEvent(event));
        }
    }

    /**
     * Called when a player logs in.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerLogin(ClientConnectionEvent.Join event) {
        PlayerEvents.LOGIN.invoke(new SpongePlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ClientConnectionEvent.Disconnect event) {
        PlayerEvents.LOGOUT.invoke(new SpongePlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(MessageEvent event, @All(ignoreEmpty = false) Player[] players) {
        if (players.length != 1) return;
        PlayerEvents.MESSAGE.invoke(new SpongePlayerMessageEvent(event, players));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerRespawn(RespawnPlayerEvent event) {
        PlayerEvents.RESPAWN.invoke(new SpongePlayerRespawnEvent(event));
    }
}
