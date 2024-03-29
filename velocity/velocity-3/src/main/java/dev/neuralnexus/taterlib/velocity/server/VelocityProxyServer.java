package dev.neuralnexus.taterlib.velocity.server;

import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.server.ProxyServer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.stream.Collectors;

/** Velocity implementation of {@link ProxyServer}. */
public class VelocityProxyServer implements ProxyServer {
    private static final com.velocitypowered.api.proxy.ProxyServer server =
            VelocityTaterLibPlugin.proxyServer;
    private static VelocityProxyServer instance;

    /**
     * Gets the instance.
     *
     * @return The instance.
     */
    public static VelocityProxyServer instance() {
        if (instance == null) {
            instance = new VelocityProxyServer();
        }
        return instance;
    }

    /**
     * Gets the server.
     *
     * @return The server.
     */
    @ApiStatus.Internal
    public static com.velocitypowered.api.proxy.ProxyServer server() {
        return server;
    }

    /** {@inheritDoc} */
    @Override
    public String brand() {
        // TODO: See if there's a better way to get the server brand
        return "Velocity";
    }

    /** {@inheritDoc} */
    @Override
    public List<SimplePlayer> onlinePlayers() {
        return server.getAllPlayers().stream()
                .map(VelocityPlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Server> servers() {
        return server.getAllServers().stream()
                .map(VelocityServer::new)
                .collect(Collectors.toList());
    }
}
