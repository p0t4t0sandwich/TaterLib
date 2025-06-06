/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/** Sponge implementation of {@link PlayerLogoutEvent}. */
public class SpongePlayerLogoutEvent implements PlayerLogoutEvent {
    private final ServerSideConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ServerSideConnectionEvent.Disconnect event) {
        this.event = event;
    }

    @Override
    public Player player() {
        return (Player) event.player();
    }

    @Override
    public String logoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
