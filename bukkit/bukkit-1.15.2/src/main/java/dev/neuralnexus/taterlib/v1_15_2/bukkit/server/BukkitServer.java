/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.server;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.world.BukkitWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
        return Bukkit.getServer().getOnlinePlayers().stream()
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
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
