package dev.neuralnexus.taterlib.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/** Sponge implementation of {@link PlayerLoginEvent}. */
public class SpongePlayerLoginEvent implements PlayerLoginEvent {
    private final ServerSideConnectionEvent.Join event;
    private String loginMessage = "";

    public SpongePlayerLoginEvent(ServerSideConnectionEvent.Join event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.player());
    }

    /** {@inheritDoc} */
    @Override
    public String loginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
