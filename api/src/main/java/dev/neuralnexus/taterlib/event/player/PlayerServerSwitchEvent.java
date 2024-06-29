/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.server.Server;

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
