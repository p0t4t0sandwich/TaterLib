/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.taterapi.network.Connection;

/** Abstracts a proxy player. */
public interface ProxyPlayer extends Connection, User {
    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    void connect(String serverName);
}
