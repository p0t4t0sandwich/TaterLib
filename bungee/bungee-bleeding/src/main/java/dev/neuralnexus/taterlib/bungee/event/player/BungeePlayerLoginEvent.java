package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

import net.md_5.bungee.api.event.ServerSwitchEvent;

/** Bungee implementation of {@link PlayerLoginEvent}. */
public class BungeePlayerLoginEvent implements PlayerLoginEvent {
    private final ServerSwitchEvent event;
    private String loginMessage = "";

    public BungeePlayerLoginEvent(ServerSwitchEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer getPlayer() {
        ProxyPlayer player = new BungeePlayer(event.getPlayer());
        player.setServerName(event.getPlayer().getServer().getInfo().getName());
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public String getLoginMessage() {
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
