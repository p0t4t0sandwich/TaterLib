package dev.neuralnexus.taterlib.velocity.event.player;

import com.velocitypowered.api.event.connection.DisconnectEvent;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/** Velocity implementation of {@link PlayerLogoutEvent}. */
public class VelocityPlayerLogoutEvent implements PlayerLogoutEvent {
    private final DisconnectEvent event;
    private String logoutMessage = "";

    public VelocityPlayerLogoutEvent(DisconnectEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer getPlayer() {
        return new VelocityPlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String getLogoutMessage() {
        if (!logoutMessage.isEmpty()) {
            return logoutMessage;
        }
        return event.getPlayer() + " left the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        logoutMessage = message;
    }
}
