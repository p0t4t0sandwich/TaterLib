package dev.neuralnexus.taterlib.v1_21.vanilla.event.network;

import dev.neuralnexus.taterlib.event.network.CustomPayloadWrapper;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayloadWrapper}.
 */
public class CustomPayloadPacketWrapper implements CustomPayloadWrapper {
    private final String channel;
    private final byte[] data;

    public CustomPayloadPacketWrapper(ServerboundCustomPayloadPacket packet) {
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
