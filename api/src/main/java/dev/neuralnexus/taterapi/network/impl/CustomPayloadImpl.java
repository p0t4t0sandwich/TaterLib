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
