package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import net.md_5.bungee.api.event.ServerSwitchEvent;

/**
 * Bungee implementation of {@link PlayerLoginEvent}.
 */
public class BungeePlayerLoginEvent implements PlayerLoginEvent {
    private final ServerSwitchEvent event;
    private String loginMessage = "";

    public BungeePlayerLoginEvent(ServerSwitchEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        Player player = new BungeePlayer(event.getPlayer());
        player.setServerName(event.getPlayer().getServer().getInfo().getName());
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoginMessage() {
        if (!loginMessage.isEmpty()) {
            return loginMessage;
        }
        return event.getPlayer() + " joined the game";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoginMessage(String message) {
        loginMessage = message;
    }
}
