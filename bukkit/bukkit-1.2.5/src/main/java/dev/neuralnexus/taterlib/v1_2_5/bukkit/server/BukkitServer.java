/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.server;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.world.BukkitWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.CraftServer;

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
        return ((CraftServer) Bukkit.getServer()).getServer().getServerModName();
    }

    @Override
    public List<User> players() {
        return Arrays.stream(Bukkit.getServer().getOnlinePlayers())
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<NameAndId> whitelist() {
        return Bukkit.getServer().getWhitelistedPlayers().stream()
                .map(
                        p -> {
                            if (p.getPlayer() != null) { // TODO: Alternate method
                                return new NameAndId(TaterAPI.NIL_UUID, p.getName());
                            } else {
                                return new NameAndId(p.getPlayer().getUniqueId(), p.getName());
                            }
                        })
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<NameAndId> playercache() {
        return Arrays.stream(Bukkit.getServer().getOfflinePlayers())
                .filter(OfflinePlayer::hasPlayedBefore)
                .map(
                        p -> {
                            if (p.getPlayer() != null) { // TODO: Alternate method
                                return new NameAndId(TaterAPI.NIL_UUID, p.getName());
                            } else {
                                return new NameAndId(p.getPlayer().getUniqueId(), p.getName());
                            }
                        })
                .collect(Collectors.toSet());
    }

    @Override
    public List<ServerWorld> worlds() {
        return Bukkit.getServer().getWorlds().stream()
                .map(BukkitWorld::new)
                .collect(Collectors.toList());
    }
}
