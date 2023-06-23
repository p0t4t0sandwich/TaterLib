package dev.neuralnexus.taterapi.common.listeners.server;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for server starts and sends them to the message relay.
 */
public interface TaterServerStoppedListener {
    /**
     * Called when a server stops, and sends it to the message relay.
     */
    default void taterServerStopped() {
        runTaskAsync(() -> {
            try {
                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
