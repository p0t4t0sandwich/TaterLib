/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

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
