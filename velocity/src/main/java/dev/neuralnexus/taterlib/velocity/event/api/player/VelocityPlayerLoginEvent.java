package dev.neuralnexus.taterlib.velocity.event.api.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;
import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerLoginEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/**
 * Velocity implementation of {@link AbstractPlayerLoginEvent}.
 */
public class VelocityPlayerLoginEvent implements AbstractPlayerLoginEvent {
    private final ServerConnectedEvent event;
    private String loginMessage = "";

    public VelocityPlayerLoginEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        AbstractPlayer player = new VelocityPlayer(event.getPlayer());
        if (event.getPlayer().getCurrentServer().isPresent()) {
            player.setServerName(event.getPlayer().getCurrentServer().get().getServerInfo().getName());
        }
        return player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLoginMessage() {
        if (!loginMessage.isEmpty()) {
            return loginMessage;
        }
        return event.getPlayer() + " joined the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {
        loginMessage = message;
    }
}
