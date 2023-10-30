package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/**
 * Sponge implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class SpongePlayerLogoutEvent implements AbstractPlayerLogoutEvent {
    private final ClientConnectionEvent.Disconnect event;
    private String logoutMessage = "";

    public SpongePlayerLogoutEvent(ClientConnectionEvent.Disconnect event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return event.getMessage().toPlain();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
