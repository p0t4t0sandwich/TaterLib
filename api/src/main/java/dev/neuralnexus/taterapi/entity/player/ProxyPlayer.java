/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
