/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.bukkit.server;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.world.BukkitServerWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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

    @Override
    public Map<String, UUID> whitelist() {
        Map<String, UUID> whitelist = new HashMap<>();
        for (OfflinePlayer player : server.getWhitelistedPlayers()) {
            whitelist.put(player.getName(), player.getPlayer().getUniqueId());
        }
        return whitelist;
    }

    @Override
    public Map<String, UUID> playercache() {
        Map<String, UUID> cache = new HashMap<>();
        for (OfflinePlayer player : server.getOfflinePlayers()) {
            cache.put(player.getName(), player.getPlayer().getUniqueId());
        }
        return cache;
    }

    @Override
    public List<ServerWorld> worlds() {
        return server.getWorlds().stream().map(BukkitServerWorld::new).collect(Collectors.toList());
    }
}
