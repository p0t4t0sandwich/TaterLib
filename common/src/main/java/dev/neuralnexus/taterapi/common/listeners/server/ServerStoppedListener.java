package dev.neuralnexus.taterapi.common.listeners.server;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for server starts and sends them to the message relay.
 */
public interface ServerStoppedListener {
    /**
     * Called when a server stops, and sends it to the message relay.
     */
    default void taterServerStopped() {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();
                String server = TaterAPI.getServerName();
                relay.sendSystemMessage(server, "**Server has stopped**");
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
