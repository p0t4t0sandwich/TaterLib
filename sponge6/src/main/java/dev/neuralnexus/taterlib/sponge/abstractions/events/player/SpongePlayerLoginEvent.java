package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLoginEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/**
 * Sponge implementation of {@link AbstractPlayerLoginEvent}.
 */
public class SpongePlayerLoginEvent implements AbstractPlayerLoginEvent {
    private final ClientConnectionEvent.Join event;
    private String loginMessage = "";

    public SpongePlayerLoginEvent(ClientConnectionEvent.Join event) {
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
    public String getLoginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return event.getMessage().toPlain();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
