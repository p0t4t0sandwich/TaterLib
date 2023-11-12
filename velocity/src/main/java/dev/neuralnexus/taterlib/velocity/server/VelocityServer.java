package dev.neuralnexus.taterlib.velocity.server;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Velocity implementation of {@link Server}.
 */
public class VelocityServer implements Server {
    private final ProxyServer server;

    public VelocityServer(ProxyServer server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getAllPlayers().stream().map(VelocityPlayer::new).collect(Collectors.toSet());
    }
}
