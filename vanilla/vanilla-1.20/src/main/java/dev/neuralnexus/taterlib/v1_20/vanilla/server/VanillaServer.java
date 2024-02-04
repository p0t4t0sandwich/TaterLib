package dev.neuralnexus.taterlib.v1_20.vanilla.server;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

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
    public static VanillaServer getInstance() {
        return instance;
    }

    /**
     * Gets the server.
     *
     * @return The server.
     */
    @ApiStatus.Internal
    public static MinecraftServer getServer() {
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
    public String getName() {
        return "local";
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getPlayerList().getPlayers().stream()
                .map(VanillaPlayer::new)
                .collect(Collectors.toSet());
    }
}
