package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import net.md_5.bungee.api.ProxyServer;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Bungee implementation of {@link Server}.
 */
public class BungeeServer implements Server {
    private final ProxyServer server;

    public BungeeServer(ProxyServer server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getPlayers().stream().map(BungeePlayer::new).collect(Collectors.toSet());
    }
}
