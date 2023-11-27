package dev.neuralnexus.taterlib.common.event.server;

import dev.neuralnexus.taterlib.common.server.Server;

/** Abstract class for server events. */
public interface ServerEvent {
    /**
     * Gets the server.
     *
     * @return The server.
     */
    Server getServer();
}
