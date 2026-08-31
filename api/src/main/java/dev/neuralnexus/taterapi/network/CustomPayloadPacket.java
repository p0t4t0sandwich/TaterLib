/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.network;

import dev.neuralnexus.taterapi.resources.Identifier;

/** Abstraction for custom payload packets */
public interface CustomPayloadPacket {
    /**
     * Gets the channel.
     *
     * @return The channel.
     */
    Identifier channel();

    /**
     * Gets the data.
     *
     * @return The data.
     */
    byte[] data();
}
