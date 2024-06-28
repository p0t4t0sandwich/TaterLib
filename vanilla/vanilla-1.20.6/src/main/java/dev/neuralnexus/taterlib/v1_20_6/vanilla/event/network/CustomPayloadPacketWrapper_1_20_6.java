/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_6.vanilla.event.network;

import dev.neuralnexus.taterlib.event.network.CustomPayloadWrapper;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayloadWrapper}.
 */
public class CustomPayloadPacketWrapper_1_20_6 implements CustomPayloadWrapper {
    private final String channel;
    private final byte[] data;

    public CustomPayloadPacketWrapper_1_20_6(ServerboundCustomPayloadPacket packet) {
        this.channel = packet.payload().type().id().toString();
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        ServerboundCustomPayloadPacket.STREAM_CODEC.encode(buf, packet);
        this.data = buf.array();
    }

    /** {@inheritDoc} */
    @Override
    public String channel() {
        return channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] data() {
        return data;
    }
}
