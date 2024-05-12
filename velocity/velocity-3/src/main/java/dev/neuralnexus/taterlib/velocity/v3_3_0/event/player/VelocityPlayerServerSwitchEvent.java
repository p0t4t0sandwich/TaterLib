package dev.neuralnexus.taterlib.velocity.v3_3_0.event.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;

import dev.neuralnexus.taterlib.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

/** Velocity implementation of {@link PlayerServerSwitchEvent}. */
public class VelocityPlayerServerSwitchEvent implements PlayerServerSwitchEvent {
    private final ServerConnectedEvent event;

    public VelocityPlayerServerSwitchEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        return new VelocityPlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public Server toServer() {
        if (!event.getPlayer().getCurrentServer().isPresent()) return null;
        return new VelocityServer(event.getPlayer().getCurrentServer().get().getServer());
    }

    /** {@inheritDoc} */
    @Override
    public Server fromServer() {
        if (!event.getPreviousServer().isPresent()) return null;
        return new VelocityServer(event.getPreviousServer().get());
    }
}
