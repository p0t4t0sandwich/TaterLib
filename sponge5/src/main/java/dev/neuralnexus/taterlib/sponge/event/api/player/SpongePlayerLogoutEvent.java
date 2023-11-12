package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/**
 * Sponge implementation of {@link PlayerLogoutEvent}.
 */
public class SpongePlayerLogoutEvent implements PlayerLogoutEvent {
    private final ClientConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ClientConnectionEvent.Disconnect event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return event.getMessage().toPlain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
