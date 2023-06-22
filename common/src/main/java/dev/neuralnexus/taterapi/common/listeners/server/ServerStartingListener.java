package dev.neuralnexus.taterapi.common.listeners.server;

import dev.neuralnexus.taterapi.common.TaterAPI;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for server starts and sends them to the message relay.
 */
public interface ServerStartingListener {
    /**
     * Called when a server is starting, and sends it to the message relay.
     */
    default void taterServerStarted(String configPath, Object logger) {
        runTaskAsync(() -> {
            try {
                new TaterAPI(configPath, logger);
                TaterAPI.start();
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
