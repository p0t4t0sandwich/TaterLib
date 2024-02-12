package dev.neuralnexus.taterlib.velocity.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

import java.util.Set;
import java.util.stream.Collectors;

/** Velocity implementation of {@link Server}. */
public class VelocityServer implements Server {
    private final com.velocitypowered.api.proxy.server.RegisteredServer server;

    public VelocityServer(com.velocitypowered.api.proxy.server.RegisteredServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return server.getServerInfo().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        // TODO: Pass this information up through plugin messages as a way to sync the server brand
        return "Unknown";
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> onlinePlayers() {
        return server.getPlayersConnected().stream()
                .map(VelocityPlayer::new)
                .collect(Collectors.toSet());
    }
}
