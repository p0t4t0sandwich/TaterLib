package dev.neuralnexus.taterlib.v1_10_2.fabric.server;

import dev.neuralnexus.taterlib.v1_10_2.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.v1_10_2.fabric.world.FabricServerWorld;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;

import dev.neuralnexus.taterlib.world.ServerWorld;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Fabric implementation of {@link Server}. */
public class FabricServer implements Server {
    private final MinecraftServer server;

    public FabricServer(MinecraftServer server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        return server.getServerModName();
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getPlayerManager().getPlayers().stream()
                .map(FabricPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        return Arrays.stream(server.worlds)
                .map(FabricServerWorld::new)
                .collect(Collectors.toList());
    }
}
