package dev.neuralnexus.taterlib.fabric.server;

import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

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
    @SuppressWarnings("unchecked")
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return ((List<PlayerEntity>) server.getPlayerManager().players)
                .stream().map(FabricPlayer::new).collect(Collectors.toList());
    }
}
