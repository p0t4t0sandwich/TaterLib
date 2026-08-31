/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.network.impl;

import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.resources.Identifier;

/** Custom payload implementation. */
public class CustomPayloadPacketImpl implements CustomPayloadPacket {
    private final Identifier channel;
    private final byte[] data;

    public CustomPayloadPacketImpl(Identifier channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    @Override
    public Identifier channel() {
        return channel;
    }

    @Override
    public byte[] data() {
        return data;
    }
}
