/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.server;

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
