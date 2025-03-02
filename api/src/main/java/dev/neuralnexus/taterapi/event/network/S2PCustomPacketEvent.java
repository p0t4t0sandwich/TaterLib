/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.server.SimpleServer;

/** Abstract class for plugin message events. */
public interface S2PCustomPacketEvent extends CustomPacketEvent {
    /**
     * Gets the server that sent the packet. This can be used within a proxy context to check what
     * server the packet originated from.
     *
     * @return The server that sent the packet.
     */
    SimpleServer server();

    default Direction direction() {
        return Direction.S2C;
    }
}
