package dev.neuralnexus.taterlib.v1_8.bungee.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.v1_8.bungee.player.BungeePlayer;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;

/** Bungee implementation of {@link PlayerLogoutEvent}. */
public class BungeePlayerLogoutEvent implements PlayerLogoutEvent {
    private final PlayerDisconnectEvent event;
    private String logoutMessage = "";

    public BungeePlayerLogoutEvent(PlayerDisconnectEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        return new BungeePlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String logoutMessage() {
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
