package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.Set;

/**
 * Bungee implementation of {@link Server}.
 */
public class BungeeServer implements Server {
    private final ServerInfo server;

    public BungeeServer(ServerInfo server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return server.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getPlayers().stream().map(BungeePlayer::new).collect(java.util.stream.Collectors.toSet());
    }
}
