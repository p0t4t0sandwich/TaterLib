/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15_2.bukkit.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.world.BukkitServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;

import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getOnlinePlayers().stream()
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        return server.getWorlds().stream().map(BukkitServerWorld::new).collect(Collectors.toList());
    }
}
