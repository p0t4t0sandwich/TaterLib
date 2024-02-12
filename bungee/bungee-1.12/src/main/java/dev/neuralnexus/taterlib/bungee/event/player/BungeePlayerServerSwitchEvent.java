package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

import net.md_5.bungee.api.event.ServerConnectedEvent;

/** Bungee implementation of {@link PlayerServerSwitchEvent}. */
public class BungeePlayerServerSwitchEvent implements PlayerServerSwitchEvent {
    private final ServerConnectedEvent event;

    public BungeePlayerServerSwitchEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        return new BungeePlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String toServer() {
        return event.getServer().getInfo().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String fromServer() {
        return event.getPlayer().getServer().getInfo().getName();
    }
}
