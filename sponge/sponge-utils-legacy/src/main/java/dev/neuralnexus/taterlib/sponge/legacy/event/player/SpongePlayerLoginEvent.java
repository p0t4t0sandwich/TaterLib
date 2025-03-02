/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.sponge.legacy.entity.player.SpongePlayer;

import org.spongepowered.api.event.network.ClientConnectionEvent;

/** Sponge implementation of {@link PlayerLoginEvent}. */
public class SpongePlayerLoginEvent implements PlayerLoginEvent {
    private final ClientConnectionEvent.Join event;
    private String loginMessage = "";

    public SpongePlayerLoginEvent(ClientConnectionEvent.Join event) {
        this.event = event;
    }

    @Override
    public Player player() {
        return new SpongePlayer(event.getTargetEntity());
    }

    @Override
    public String loginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return event.getMessage().toPlain();
    }

    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
