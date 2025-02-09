/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.Event;

/** Abstract class for player events */
public interface PlayerEvent extends Event {
    /**
     * Getter for the player
     *
     * @return The player
     */
    User player();
}
