/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_8.sponge.player.SpongePlayer;

import org.spongepowered.api.event.network.ClientConnectionEvent;

/** Sponge implementation of {@link PlayerLoginEvent}. */
public class SpongePlayerLoginEvent implements PlayerLoginEvent {
    private final ClientConnectionEvent.Join event;
    private String loginMessage = "";

    public SpongePlayerLoginEvent(ClientConnectionEvent.Join event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /** {@inheritDoc} */
    @Override
    public String loginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return event.getMessage().toPlain();
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
