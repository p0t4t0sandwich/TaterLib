package dev.neuralnexus.taterlib.server;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.server.metrics.TPSProvider;

import java.util.Optional;

/** Abstracts a Minecraft server. */
public interface Server extends SimpleServer {
    /**
     * Get the server's current TPS.
     *
     * @return The server's current TPS.
     */
    default double currentTPS() {
        Optional<TPSProvider> provider = TaterAPIProvider.getTPSProvider();
        return provider.map(TPSProvider::currentTPS).orElse(-1.0);
    }

    //    /**
    //     * Get the server's current tick.
    //     *
    //     * @return The server's current tick.
    //     */
    //    long currentTick();
}
