/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.bungee.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.v1_12_2.bungee.entity.player.BungeePlayer;
import dev.neuralnexus.taterlib.v1_12_2.bungee.server.BungeeServer;

import net.md_5.bungee.api.event.ServerConnectedEvent;

/** Bungee implementation of {@link PlayerServerSwitchEvent}. */
public class BungeePlayerServerSwitchEvent implements PlayerServerSwitchEvent {
    private final ServerConnectedEvent event;

    public BungeePlayerServerSwitchEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    @Override
    public ProxyPlayer player() {
        return new BungeePlayer(event.getPlayer());
    }

    @Override
    public Server toServer() {
        return new BungeeServer(event.getPlayer().getServer().getInfo());
    }

    @Override
    public Server fromServer() {
        return new BungeeServer(event.getServer().getInfo());
    }
}
