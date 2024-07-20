/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.network;

import dev.neuralnexus.taterapi.resource.ResourceKey;

/** Wrapper for custom payload packets (plugin messages) */
public interface CustomPayload {
    /**
     * Gets the channel.
     *
     * @return The channel.
     */
    ResourceKey channel();

    /**
     * Gets the data.
     *
     * @return The data.
     */
    byte[] data();
}
