package dev.neuralnexus.taterlib.v1_17.fabric.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_17.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.v1_17.fabric.world.FabricServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;

import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
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
        return server.getPlayerManager().getPlayerList().stream()
                .map(FabricPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        server.getWorlds().forEach(world -> worlds.add(new FabricServerWorld(world)));
        return worlds;
    }
}
