package dev.neuralnexus.taterlib.event.server;

import dev.neuralnexus.taterlib.server.SimpleServer;

/** Abstract class for server events. */
public interface ServerEvent {
    /**
     * Gets the server.
     *
     * @return The server.
     */
    SimpleServer getServer();
}
