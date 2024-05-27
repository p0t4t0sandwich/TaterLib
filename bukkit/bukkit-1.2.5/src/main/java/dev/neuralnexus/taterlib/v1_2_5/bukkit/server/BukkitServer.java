package dev.neuralnexus.taterlib.v1_2_5.bukkit.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.world.BukkitServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;

import org.bukkit.craftbukkit.CraftServer;

import java.util.Arrays;
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
        return ((CraftServer) server).getServer().getServerModName();
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return Arrays.stream(server.getOnlinePlayers())
                .map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        return server.getWorlds().stream().map(BukkitServerWorld::new).collect(Collectors.toList());
    }
}
