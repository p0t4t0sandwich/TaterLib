package dev.neuralnexus.taterlib.vanilla.event.pluginmessages;

import dev.neuralnexus.taterlib.event.network.CustomPayloadWrapper;

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
    public String channel() {
        return channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] data() {
        return data;
    }
}
