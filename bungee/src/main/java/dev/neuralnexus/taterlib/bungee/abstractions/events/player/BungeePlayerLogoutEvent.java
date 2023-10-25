package dev.neuralnexus.taterlib.bungee.abstractions.events.player;

import dev.neuralnexus.taterlib.bungee.abstractions.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;

/**
 * Bungee implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class BungeePlayerLogoutEvent implements AbstractPlayerLogoutEvent {
    private final PlayerDisconnectEvent event;

    public BungeePlayerLogoutEvent(PlayerDisconnectEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new BungeePlayer(event.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        return event.getPlayer() + " left the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {}
}
