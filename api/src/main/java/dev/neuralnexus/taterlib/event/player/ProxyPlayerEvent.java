/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

/** Abstract class for proxy player events */
public interface ProxyPlayerEvent extends Event {
    /**
     * Getter for the player
     *
     * @return The player
     */
    ProxyPlayer player();
}
