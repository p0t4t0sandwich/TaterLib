/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;

import dev.neuralnexus.taterapi.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterapi.player.ProxyPlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

/** Velocity implementation of {@link PlayerServerSwitchEvent}. */
public class VelocityPlayerServerSwitchEvent implements PlayerServerSwitchEvent {
    private final ServerConnectedEvent event;

    public VelocityPlayerServerSwitchEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    @Override
    public ProxyPlayer player() {
        return new VelocityPlayer(event.getPlayer());
    }

    @Override
    public Server toServer() {
        if (!event.getPlayer().getCurrentServer().isPresent()) return null;
        return new VelocityServer(event.getPlayer().getCurrentServer().get().getServer());
    }

    @Override
    public Server fromServer() {
        if (!event.getPreviousServer().isPresent()) return null;
        return new VelocityServer(event.getPreviousServer().get());
    }
}
