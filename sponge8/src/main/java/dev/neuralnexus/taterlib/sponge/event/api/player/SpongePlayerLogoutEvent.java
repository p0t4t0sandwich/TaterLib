package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

/**
 * Sponge implementation of {@link PlayerLogoutEvent}.
 */
public class SpongePlayerLogoutEvent implements PlayerLogoutEvent {
    private final ServerSideConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ServerSideConnectionEvent.Disconnect event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(event.player());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
