package dev.neuralnexus.taterapi.common.listeners.server;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for server starts and sends them to the message relay.
 */
public interface ServerStartedListener {
    /**
     * Called when a server starts, and sends it to the message relay.
     */
    default void taterServerStarted() {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();
                String server = TaterAPI.getServerName();
                relay.sendSystemMessage(server, "**Server has started**");
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
