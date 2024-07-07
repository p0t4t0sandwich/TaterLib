/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.bungee.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterapi.player.ProxyPlayer;
import dev.neuralnexus.taterlib.v1_8.bungee.player.BungeePlayer;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;

/** Bungee implementation of {@link PlayerLogoutEvent}. */
public class BungeePlayerLogoutEvent implements PlayerLogoutEvent {
    private final PlayerDisconnectEvent event;
    private String logoutMessage = "";

    public BungeePlayerLogoutEvent(PlayerDisconnectEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        return new BungeePlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String logoutMessage() {
        if (!logoutMessage.isEmpty()) {
            return logoutMessage;
        }
        return event.getPlayer() + " left the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        logoutMessage = message;
    }
}
