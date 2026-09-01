/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.entity.player.User;

import org.jspecify.annotations.NonNull;

/** Abstract class for plugin message events. */
public interface C2SCustomPacketEvent extends CustomPacketEvent {
    /**
     * Gets the player of the plugin message.
     *
     * @return The player of the plugin message.
     */
    User player();

    default @NonNull Direction direction() {
        return Direction.C2S;
    }
}
