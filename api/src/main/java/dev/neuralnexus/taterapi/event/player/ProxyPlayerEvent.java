/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.event.Event;

/** Abstract class for proxy player events */
public interface ProxyPlayerEvent extends Event {
    /**
     * Getter for the player
     *
     * @return The player
     */
    ProxyPlayer player();
}
