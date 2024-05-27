package dev.neuralnexus.taterlib.v1_20.bungee.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_20.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.v1_20.bungee.server.BungeeServer;

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
    public Server toServer() {
        return new BungeeServer(event.getPlayer().getServer().getInfo());
    }

    /** {@inheritDoc} */
    @Override
    public Server fromServer() {
        return new BungeeServer(event.getFrom());
    }
}
