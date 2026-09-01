/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

import dev.neuralnexus.taterapi.server.Server;

/** Abstract class for player server switch events. */
public interface PlayerServerSwitchEvent extends ProxyPlayerEvent {
    /**
     * Gets the server the player is switching to.
     *
     * @return The server the player is switching to.
     */
    Server toServer();

    /**
     * Gets the server the player is switching from.
     *
     * @return The server the player is switching from.
     */
    Server fromServer();
}
