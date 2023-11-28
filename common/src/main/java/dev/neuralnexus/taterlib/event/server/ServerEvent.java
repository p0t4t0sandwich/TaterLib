package dev.neuralnexus.taterlib.event.server;

import dev.neuralnexus.taterlib.server.Server;

/** Abstract class for server events. */
public interface ServerEvent {
    /**
     * Gets the server.
     *
     * @return The server.
     */
    Server getServer();
}
