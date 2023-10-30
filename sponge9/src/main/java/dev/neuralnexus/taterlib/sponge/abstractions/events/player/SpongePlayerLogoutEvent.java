package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/**
 * Sponge implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class SpongePlayerLogoutEvent implements AbstractPlayerLogoutEvent {
    private final ServerSideConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ServerSideConnectionEvent.Disconnect event) {
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
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
