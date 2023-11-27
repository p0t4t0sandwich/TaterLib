package dev.neuralnexus.taterlib.fabric.server;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.server.Server;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;

import net.minecraft.server.MinecraftServer;

import java.util.Set;
import java.util.stream.Collectors;

/** Fabric implementation of {@link Server}. */
public class FabricServer implements Server {
    private final MinecraftServer server;

    public FabricServer(MinecraftServer server) {
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
        return server.getPlayerManager().getPlayerList().stream()
                .map(FabricPlayer::new)
                .collect(Collectors.toSet());
    }
}
