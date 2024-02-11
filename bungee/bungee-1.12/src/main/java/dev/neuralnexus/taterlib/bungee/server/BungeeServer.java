package dev.neuralnexus.taterlib.bungee.server;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.Server;

import net.md_5.bungee.api.config.ServerInfo;

import java.util.Set;
import java.util.stream.Collectors;

/** Bungee implementation of {@link Server}. */
public class BungeeServer implements Server {
    private final ServerInfo server;

    public BungeeServer(ServerInfo server) {
        this.server = server;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return server.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getBrand() {
        // TODO: Pass this information up through plugin messages as a way to sync the server brand
        return "Unknown";
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> getOnlinePlayers() {
        return server.getPlayers().stream().map(BungeePlayer::new).collect(Collectors.toSet());
    }
}
