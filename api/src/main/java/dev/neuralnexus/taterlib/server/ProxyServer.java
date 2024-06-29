/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.server;

import java.util.List;

/** Represents a proxy server. */
public interface ProxyServer extends SimpleServer {
    /**
     * Gets the servers that are connected to this proxy.
     *
     * @return The servers that are connected to this proxy.
     */
    List<Server> servers();
}
