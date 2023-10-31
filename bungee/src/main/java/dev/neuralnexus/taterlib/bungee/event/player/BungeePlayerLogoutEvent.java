package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerLogoutEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;

/**
 * Bungee implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class BungeePlayerLogoutEvent implements AbstractPlayerLogoutEvent {
    private final PlayerDisconnectEvent event;
    private String logoutMessage = "";

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
