package dev.neuralnexus.taterlib.velocity.server;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

import java.util.Set;

/** Velocity implementation of {@link Server}. */
public class VelocityServer implements Server {
    private final com.velocitypowered.api.proxy.server.RegisteredServer server;

    public VelocityServer(com.velocitypowered.api.proxy.server.RegisteredServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return server.getServerInfo().getName();
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getPlayersConnected().stream()
                .map(VelocityPlayer::new)
                .collect(java.util.stream.Collectors.toSet());
    }
}
