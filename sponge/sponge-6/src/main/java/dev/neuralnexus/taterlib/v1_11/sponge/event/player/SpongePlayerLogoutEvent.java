package dev.neuralnexus.taterlib.v1_11.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_11.sponge.player.SpongePlayer;

import org.spongepowered.api.event.network.ClientConnectionEvent;

/** Sponge implementation of {@link PlayerLogoutEvent}. */
public class SpongePlayerLogoutEvent implements PlayerLogoutEvent {
    private final ClientConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ClientConnectionEvent.Disconnect event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /** {@inheritDoc} */
    @Override
    public String logoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return event.getMessage().toPlain();
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
