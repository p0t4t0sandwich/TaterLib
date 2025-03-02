/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;

/** Abstract class for plugin message events. */
public interface CustomPacketEvent extends Event {
    /**
     * Gets the plugin message's payload.
     *
     * @return The plugin message's payload.
     */
    CustomPayloadPacket packet();

    /**
     * Gets the packet direction
     *
     * @return The packet's flow direction
     */
    default Direction direction() {
        return Direction.NONE;
    }

    enum Direction {
        NONE,
        C2S,
        S2C
    }
}
