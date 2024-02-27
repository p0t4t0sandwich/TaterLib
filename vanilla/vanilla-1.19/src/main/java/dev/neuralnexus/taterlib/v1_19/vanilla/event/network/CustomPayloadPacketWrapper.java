package dev.neuralnexus.taterlib.v1_19.vanilla.event.network;

import dev.neuralnexus.taterlib.event.network.CustomPayloadWrapper;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayloadWrapper}.
 */
public class CustomPayloadPacketWrapper implements CustomPayloadWrapper {
    private final String channel;
    private final byte[] data;

    public CustomPayloadPacketWrapper(ServerboundCustomPayloadPacket packet) {
        this.channel = packet.getIdentifier().toString();
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.write(buf);
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
