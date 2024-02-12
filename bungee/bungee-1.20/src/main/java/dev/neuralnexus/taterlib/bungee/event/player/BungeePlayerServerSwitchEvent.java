package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

import net.md_5.bungee.api.event.ServerSwitchEvent;

/** Bungee implementation of {@link PlayerServerSwitchEvent}. */
public class BungeePlayerServerSwitchEvent implements PlayerServerSwitchEvent {
    private final ServerSwitchEvent event;

    public BungeePlayerServerSwitchEvent(ServerSwitchEvent event) {
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
        return event.getPlayer().getServer().getInfo().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String fromServer() {
        return event.getFrom().getName();
    }
}
