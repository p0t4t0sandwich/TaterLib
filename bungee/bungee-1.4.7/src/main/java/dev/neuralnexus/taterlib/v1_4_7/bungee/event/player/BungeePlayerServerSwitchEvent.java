package dev.neuralnexus.taterlib.v1_4_7.bungee.event.player;

import dev.neuralnexus.taterlib.v1_4_7.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.v1_4_7.bungee.server.BungeeServer;
import dev.neuralnexus.taterlib.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.server.Server;

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
    public Server toServer() {
        return new BungeeServer(event.getPlayer().getServer().getInfo());
    }

    /** {@inheritDoc} */
    @Override
    public Server fromServer() {
        return new BungeeServer(event.getServer().getInfo());
    }
}
