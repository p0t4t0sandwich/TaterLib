/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.event.player;

import com.velocitypowered.api.event.connection.DisconnectEvent;

import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

/** Velocity implementation of {@link PlayerLogoutEvent}. */
public class VelocityPlayerLogoutEvent implements PlayerLogoutEvent {
    private final DisconnectEvent event;
    private String logoutMessage = "";

    public VelocityPlayerLogoutEvent(DisconnectEvent event) {
        this.event = event;
    }

    @Override
    public ProxyPlayer player() {
        return new VelocityPlayer(event.getPlayer());
    }

    @Override
    public String logoutMessage() {
        if (!logoutMessage.isEmpty()) {
            return logoutMessage;
        }
        return event.getPlayer() + " left the game";
    }

    @Override
    public void setLogoutMessage(String message) {
        logoutMessage = message;
    }
}
