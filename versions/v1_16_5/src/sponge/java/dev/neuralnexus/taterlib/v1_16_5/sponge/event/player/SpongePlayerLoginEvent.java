/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;

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
        return (Player) event.player();
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
