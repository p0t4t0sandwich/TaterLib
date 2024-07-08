/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event.network;

import dev.neuralnexus.taterapi.util.ResourceLocation;

/** Wrapper for custom payload packets (plugin messages) */
public interface CustomPayloadWrapper {
    /**
     * Gets the channel.
     *
     * @return The channel.
     */
    ResourceLocation channel();

    /**
     * Gets the data.
     *
     * @return The data.
     */
    byte[] data();
}
