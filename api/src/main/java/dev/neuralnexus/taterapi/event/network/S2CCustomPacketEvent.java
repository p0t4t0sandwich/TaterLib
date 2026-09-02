/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.server.SimpleServer;

import org.jspecify.annotations.NonNull;

/** Abstract class for plugin message events. */
public interface S2CCustomPacketEvent extends CustomPacketEvent {
    /**
     * Gets the server that sent the packet.
     *
     * @return The server that sent the packet.
     */
    SimpleServer server();

    default @NonNull Direction direction() {
        return Direction.S2C;
    }
}
