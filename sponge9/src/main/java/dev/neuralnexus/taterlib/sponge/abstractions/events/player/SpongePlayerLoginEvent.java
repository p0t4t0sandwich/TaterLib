package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLoginEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/**
 * Sponge implementation of {@link AbstractPlayerLoginEvent}.
 */
public class SpongePlayerLoginEvent implements AbstractPlayerLoginEvent {
    private final ServerSideConnectionEvent.Join event;
    private String loginMessage = "";

    public SpongePlayerLoginEvent(ServerSideConnectionEvent.Join event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(event.player());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLoginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
