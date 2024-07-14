/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.network.impl;

import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterapi.resource.ResourceKey;

/** Custom payload implementation. */
public class CustomPayloadImpl implements CustomPayload {
    private final ResourceKey channel;
    private final byte[] data;

    public CustomPayloadImpl(ResourceKey channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    @Override
    public ResourceKey channel() {
        return channel;
    }

    @Override
    public byte[] data() {
        return data;
    }
}
