package dev.neuralnexus.taterlib.forge.server;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.server.MinecraftServer;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Forge implementation of {@link Server}.
 */
public class ForgeServer implements Server {
    private final MinecraftServer server;

    public ForgeServer(MinecraftServer server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getPlayerList().getPlayers().stream().map(ForgePlayer::new).collect(Collectors.toSet());
    }
}
