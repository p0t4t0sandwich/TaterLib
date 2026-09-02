/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_8.bukkit.server;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.world.BukkitWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Bukkit implementation of {@link Server}. */
public class BukkitServer implements Server {
    private static final BukkitServer instance = new BukkitServer();

    public static BukkitServer instance() {
        return instance;
    }

    @Override
    public String brand() {
        // Reflect to get ((CraftServer) server).getServer().getServerModName
        try {
            return (String)
                    Class.forName(
                                    "org.bukkit.craftbukkit."
                                            + Bukkit.getServer().getClass().getPackage().getName()
                                            + ".CraftServer")
                            .getMethod("getServer")
                            .invoke(Bukkit.getServer())
                            .getClass()
                            .getMethod("getServerModName")
                            .invoke(null);
        } catch (Exception e) {
            return "Bukkit";
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> players() {
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

    @Override
    public Collection<NameAndId> whitelist() {
        return Bukkit.getServer().getWhitelistedPlayers().stream()
                .map(p -> new NameAndId(p.getUniqueId(), p.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<NameAndId> playercache() {
        return Arrays.stream(Bukkit.getServer().getOfflinePlayers())
                .filter(OfflinePlayer::hasPlayedBefore)
                .map(p -> new NameAndId(p.getUniqueId(), p.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public List<ServerWorld> worlds() {
        return Bukkit.getServer().getWorlds().stream()
                .map(BukkitWorld::new)
                .collect(Collectors.toList());
    }
}
