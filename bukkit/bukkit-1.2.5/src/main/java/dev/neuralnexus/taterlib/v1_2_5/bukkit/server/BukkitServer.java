/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.server;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.world.BukkitWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.CraftServer;

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
        return ((CraftServer) Bukkit.getServer()).getServer().getServerModName();
    }

    @Override
    public List<SimplePlayer> onlinePlayers() {
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
