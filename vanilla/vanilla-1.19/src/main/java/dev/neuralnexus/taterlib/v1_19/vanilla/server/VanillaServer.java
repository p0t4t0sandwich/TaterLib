package dev.neuralnexus.taterlib.v1_19.vanilla.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

import net.minecraft.server.MinecraftServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.Set;
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
    public String name() {
        return "local";
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        return server.getServerModName();
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> onlinePlayers() {
        return server.getPlayerList().getPlayers().stream()
                .map(VanillaPlayer::new)
                .collect(Collectors.toSet());
    }
}
