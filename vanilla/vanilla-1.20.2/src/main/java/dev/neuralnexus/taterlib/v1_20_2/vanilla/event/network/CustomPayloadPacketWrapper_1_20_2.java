package dev.neuralnexus.taterlib.v1_20_2.vanilla.event.network;

import dev.neuralnexus.taterlib.event.pluginmessages.CustomPayloadWrapper;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayloadWrapper}.
 */
public class CustomPayloadPacketWrapper_1_20_2 implements CustomPayloadWrapper {
    private final String channel;
    private final byte[] data;

    public CustomPayloadPacketWrapper_1_20_2(ServerboundCustomPayloadPacket packet) {
        this.channel = packet.payload().id().toString();
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.payload().write(buf);
        this.data = buf.array();
    }

    /** {@inheritDoc} */
    @Override
    public String getChannel() {
        return channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] getData() {
        return data;
    }
}
