package dev.neuralnexus.taterlib.bukkit.server;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
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
        // Server.getOnlinePlayers is ambiguous, time to reflect
        try {
            Method method = server.getClass().getMethod("getOnlinePlayers");
            Collection<? extends org.bukkit.entity.Player> players = (Collection<? extends org.bukkit.entity.Player>) method.invoke(server);
            return players.stream().map(BukkitPlayer::new).collect(Collectors.toSet());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return new HashSet<>();
        }
    }
}
