package dev.neuralnexus.taterlib.velocity.event.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/** Velocity implementation of {@link PlayerLoginEvent}. */
public class VelocityPlayerLoginEvent implements PlayerLoginEvent {
    private final ServerConnectedEvent event;
    private String loginMessage = "";

    public VelocityPlayerLoginEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        if (event.getPlayer().getCurrentServer().isPresent()) {
            return new VelocityPlayer(event.getPlayer());
        }
        return new VelocityPlayer(event.getPlayer(), event.getServer());
    }

    /** {@inheritDoc} */
    @Override
    public String loginMessage() {
        if (!loginMessage.isEmpty()) {
            return loginMessage;
        }
        return event.getPlayer() + " joined the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {
        loginMessage = message;
    }
}
