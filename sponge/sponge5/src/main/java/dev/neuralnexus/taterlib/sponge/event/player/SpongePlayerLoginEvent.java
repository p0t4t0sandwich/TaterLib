package dev.neuralnexus.taterlib.sponge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

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
    public Player getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /** {@inheritDoc} */
    @Override
    public String getLoginMessage() {
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
