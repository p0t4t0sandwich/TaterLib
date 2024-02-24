package dev.neuralnexus.taterlib.v1_20.vanilla.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_20.vanilla.world.VanillaServerWorld;
import dev.neuralnexus.taterlib.world.ServerWorld;

import net.minecraft.server.MinecraftServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link Server}. */
public class VanillaServer implements Server {
    private static MinecraftServer server;
    private static VanillaServer instance;

    public VanillaServer(MinecraftServer server) {
        VanillaServer.server = server;
        VanillaServer.instance = this;
    }

    /**
     * Gets the instance.
     *
     * @return The instance.
     */
    public static VanillaServer instance() {
        return instance;
    }

    /**
     * Gets the server.
     *
     * @return The server.
     */
    @ApiStatus.Internal
    public static MinecraftServer server() {
        return server;
    }

    /**
     * Sets the server.
     *
     * @param server The server.
     */
    @ApiStatus.Internal
    public static void setServer(MinecraftServer server) {
        VanillaServer.server = server;
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
                .map(VanillaPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<ServerWorld> worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        server.getAllLevels().forEach(world -> worlds.add(new VanillaServerWorld(world)));
        return worlds;
    }
}
