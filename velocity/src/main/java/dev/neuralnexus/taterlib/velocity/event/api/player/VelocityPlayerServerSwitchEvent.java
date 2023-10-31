package dev.neuralnexus.taterlib.velocity.event.api.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;
import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/**
 * Velocity implementation of {@link AbstractPlayerServerSwitchEvent}.
 */
public class VelocityPlayerServerSwitchEvent implements AbstractPlayerServerSwitchEvent {
    private final ServerConnectedEvent event;

    public VelocityPlayerServerSwitchEvent(ServerConnectedEvent event) {
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
    public String getToServer() {
        if (!event.getPlayer().getCurrentServer().isPresent()) return null;
        return event.getPlayer().getCurrentServer().get().getServerInfo().getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getFromServer() {
        if (!event.getPreviousServer().isPresent()) return null;
        return event.getPreviousServer().get().getServerInfo().getName();
    }
}
