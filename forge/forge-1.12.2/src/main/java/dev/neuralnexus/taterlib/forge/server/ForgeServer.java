package dev.neuralnexus.taterlib.forge.server;

import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.forge.world.ForgeServerWorld;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.world.ServerWorld;

import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link Server}. */
public class ForgeServer implements Server {
    private final MinecraftServer server;

    public ForgeServer(MinecraftServer server) {
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
        return server.getPlayerList().getPlayers().stream()
                .map(ForgePlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        return Arrays.stream(server.worlds).map(ForgeServerWorld::new).collect(Collectors.toList());
    }
}
