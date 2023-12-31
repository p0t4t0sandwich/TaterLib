package dev.neuralnexus.taterlib.bukkit.server;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Bukkit implementation of {@link Server}.
 */
public class BukkitServer implements Server {
    private final org.bukkit.Server server;

    public BukkitServer(org.bukkit.Server server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "local";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getOnlinePlayers().stream().map(BukkitPlayer::new).collect(Collectors.toSet());
    }
}
