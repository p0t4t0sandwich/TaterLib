/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.server;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.server.SimpleServer;

/** Abstract class for server events. */
public interface ServerEvent extends Event {
    /**
     * Gets the server.
     *
     * @return The server.
     */
    default SimpleServer server() {
        return TaterAPI.instance().server();
    }
}
