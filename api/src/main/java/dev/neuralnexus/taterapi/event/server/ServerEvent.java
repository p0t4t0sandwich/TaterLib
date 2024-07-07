/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event.server;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.server.SimpleServer;

/** Abstract class for server events. */
public interface ServerEvent extends Event {
    /**
     * Gets the server.
     *
     * @return The server.
     */
    SimpleServer server();
}
