package dev.neuralnexus.taterlib.v1_8_8.bukkit.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.world.BukkitServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Bukkit implementation of {@link Server}. */
public class BukkitServer implements Server {
    private final org.bukkit.Server server;

    public BukkitServer(org.bukkit.Server server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        // Reflect to get ((CraftServer) server).getServer().getServerModName
        try {
            return (String)
                    Class.forName(
                                    "org.bukkit.craftbukkit."
                                            + server.getClass().getPackage().getName()
                                            + ".CraftServer")
                            .getMethod("getServer")
                            .invoke(server)
                            .getClass()
                            .getMethod("getServerModName")
                            .invoke(null);
        } catch (Exception e) {
            return "Bukkit";
        }
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public List<SimplePlayer> onlinePlayers() {
        // Server.getOnlinePlayers is ambiguous, time to reflect
        try {
            Method onlinePlayersMethod =
                    Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers");
            Stream<Player> players =
                    onlinePlayersMethod.getReturnType().equals(Collection.class)
                            ? ((Collection<Player>) onlinePlayersMethod.invoke(Bukkit.getServer()))
                                    .stream()
                            : Arrays.stream(
                                    ((Player[]) onlinePlayersMethod.invoke(Bukkit.getServer())));
            return players.map(BukkitPlayer::new).collect(Collectors.toList());
        } catch (NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | ClassNotFoundException e) {
            return Collections.emptyList();
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        return server.getWorlds().stream().map(BukkitServerWorld::new).collect(Collectors.toList());
    }
}
