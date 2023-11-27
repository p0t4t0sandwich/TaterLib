package dev.neuralnexus.taterlib.neoforge.server;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;

import net.minecraft.server.MinecraftServer;

import java.util.Set;
import java.util.stream.Collectors;

/** NeoForge implementation of {@link Server}. */
public class NeoForgeServer implements Server {
    private final MinecraftServer server;

    public NeoForgeServer(MinecraftServer server) {
        this.server = server;
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
                .map(NeoForgePlayer::new)
                .collect(Collectors.toSet());
    }
}
