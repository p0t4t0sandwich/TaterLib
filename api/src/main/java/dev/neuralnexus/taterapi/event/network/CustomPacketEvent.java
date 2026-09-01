/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;

import org.jspecify.annotations.NonNull;

/** Abstract class for plugin message events. */
public interface CustomPacketEvent extends Event {
    /**
     * Gets the plugin message's payload.
     *
     * @return The plugin message's payload.
     */
    @NonNull CustomPacketPayload payload();

    /**
     * Gets the packet direction
     *
     * @return The packet's flow direction
     */
    // TODO: Update to use PacketFlow
    default @NonNull Direction direction() {
        return Direction.NONE;
    }

    enum Direction {
        NONE,
        C2S,
        S2C
    }
}
