/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit.server;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.world.BukkitWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<User> players() {
        return Arrays.stream(Bukkit.getServer().getOnlinePlayers())
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, UUID> whitelist() {
        Map<String, UUID> whitelist = new HashMap<>();
        for (OfflinePlayer player : Bukkit.getServer().getWhitelistedPlayers()) {
            whitelist.put(player.getName(), player.getPlayer().getUniqueId());
        }
        return whitelist;
    }

    @Override
    public Map<String, UUID> playercache() {
        Map<String, UUID> cache = new HashMap<>();
        for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
            cache.put(player.getName(), player.getPlayer().getUniqueId());
        }
        return cache;
    }

    @Override
    public List<ServerWorld> worlds() {
        return Bukkit.getServer().getWorlds().stream()
                .map(BukkitWorld::new)
                .collect(Collectors.toList());
    }
}
