/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.entity.player.User;

/** Abstract class for plugin message events. */
public interface C2SCustomPacketEvent extends CustomPacketEvent {
    /**
     * Gets the player of the plugin message.
     *
     * @return The player of the plugin message.
     */
    User player();

    default Direction direction() {
        return Direction.C2S;
    }
}
