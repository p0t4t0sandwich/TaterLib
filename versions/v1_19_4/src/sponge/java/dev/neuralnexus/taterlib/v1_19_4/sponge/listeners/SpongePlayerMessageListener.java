/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_19_4.sponge.listeners;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_19_4.sponge.event.player.SpongePlayerMessageEvent;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.message.PlayerChatEvent;

/** Listens to player events. */
public class SpongePlayerMessageListener {
    /**
     * Called when a player sends a message.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(
            PlayerChatEvent.Submit event, @All(ignoreEmpty = false) Player[] players) {
        if (players.length != 1) return;
        PlayerEvents.MESSAGE.invoke(new SpongePlayerMessageEvent(event, players));
    }
}
