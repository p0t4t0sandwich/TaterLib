/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.bungee.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterapi.player.ProxyPlayer;
import dev.neuralnexus.taterlib.v1_20.bungee.player.BungeePlayer;

import net.md_5.bungee.api.event.ServerSwitchEvent;

/** Bungee implementation of {@link PlayerLoginEvent}. */
public class BungeePlayerLoginEvent implements PlayerLoginEvent {
    private final ServerSwitchEvent event;
    private String loginMessage = "";

    public BungeePlayerLoginEvent(ServerSwitchEvent event) {
        this.event = event;
    }

    @Override
    public ProxyPlayer player() {
        return new BungeePlayer(event.getPlayer());
    }

    @Override
    public String loginMessage() {
        if (!loginMessage.isEmpty()) {
            return loginMessage;
        }
        return event.getPlayer() + " joined the game";
    }

    @Override
    public void setLoginMessage(String message) {
        loginMessage = message;
    }
}
