/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterapi.player.ProxyPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;

/** Velocity implementation of {@link PlayerLoginEvent}. */
public class VelocityPlayerLoginEvent implements PlayerLoginEvent {
    private final ServerConnectedEvent event;
    private String loginMessage = "";

    public VelocityPlayerLoginEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    @Override
    public ProxyPlayer player() {
        if (event.getPlayer().getCurrentServer().isPresent()) {
            return new VelocityPlayer(event.getPlayer());
        }
        return new VelocityPlayer(event.getPlayer(), event.getServer());
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
