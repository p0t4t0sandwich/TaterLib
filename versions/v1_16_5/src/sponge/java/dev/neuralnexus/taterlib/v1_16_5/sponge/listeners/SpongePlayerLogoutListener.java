/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.listeners;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_16_5.sponge.event.player.SpongePlayerLogoutEvent;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/** Listens to player events. */
public class SpongePlayerLogoutListener {
    /**
     * Called when a player logs out.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ServerSideConnectionEvent.Disconnect event) {
        PlayerEvents.LOGOUT.invoke(new SpongePlayerLogoutEvent(event));
    }
}
