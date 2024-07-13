/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13.sponge.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.v1_13.sponge.entity.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/** Sponge implementation of {@link PlayerLoginEvent}. */
public class SpongePlayerLoginEvent implements PlayerLoginEvent {
    private final ServerSideConnectionEvent.Join event;
    private String loginMessage = "";

    public SpongePlayerLoginEvent(ServerSideConnectionEvent.Join event) {
        this.event = event;
    }

    @Override
    public Player player() {
        return new SpongePlayer(event.player());
    }

    @Override
    public String loginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
