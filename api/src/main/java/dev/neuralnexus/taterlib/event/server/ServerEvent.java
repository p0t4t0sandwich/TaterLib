package dev.neuralnexus.taterlib.event.server;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.server.SimpleServer;

/** Abstract class for server events. */
public interface ServerEvent extends Event {
    /**
     * Gets the server.
     *
     * @return The server.
     */
    SimpleServer server();
}
