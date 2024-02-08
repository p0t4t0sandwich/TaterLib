package dev.neuralnexus.taterlib.sponge.server;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import java.util.Set;
import java.util.stream.Collectors;

/** Sponge implementation of {@link Server}. */
public class SpongeServer implements Server {
    private final org.spongepowered.api.Server server;

    public SpongeServer(org.spongepowered.api.Server server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "local";
    }

    /** {@inheritDoc} */
    @Override
    public String getBrand() {
        // Cast this.server to MinecraftServer, then
        // Reflect to get ((MinecraftServer) server).getServerModName()
        try {
            return (String)
                    Class.forName("net.minecraft.server.MinecraftServer")
                            .getMethod("getServerModName")
                            .invoke(server);
        } catch (Exception e) {
            return "Sponge";
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.onlinePlayers().stream().map(SpongePlayer::new).collect(Collectors.toSet());
    }
}
