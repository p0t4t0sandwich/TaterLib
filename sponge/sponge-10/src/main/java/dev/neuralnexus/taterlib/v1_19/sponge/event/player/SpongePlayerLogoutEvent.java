/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_19.sponge.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/** Sponge implementation of {@link PlayerLogoutEvent}. */
public class SpongePlayerLogoutEvent implements PlayerLogoutEvent {
    private final ServerSideConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ServerSideConnectionEvent.Disconnect event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.player());
    }

    /** {@inheritDoc} */
    @Override
    public String logoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
