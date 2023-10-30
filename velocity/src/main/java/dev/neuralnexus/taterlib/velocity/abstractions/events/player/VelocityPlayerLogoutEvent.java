package dev.neuralnexus.taterlib.velocity.abstractions.events.player;

import com.velocitypowered.api.event.connection.DisconnectEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.velocity.abstractions.player.VelocityPlayer;

/**
 * Velocity implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class VelocityPlayerLogoutEvent implements AbstractPlayerLogoutEvent {
    private final DisconnectEvent event;
    private String logoutMessage = "";

    public VelocityPlayerLogoutEvent(DisconnectEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new VelocityPlayer(event.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        if (!logoutMessage.isEmpty()) {
            return logoutMessage;
        }
        return event.getPlayer() + " left the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        logoutMessage = message;
    }
}
