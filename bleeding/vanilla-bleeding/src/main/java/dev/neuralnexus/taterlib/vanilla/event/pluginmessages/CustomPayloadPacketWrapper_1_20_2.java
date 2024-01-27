package dev.neuralnexus.taterlib.vanilla.event.pluginmessages;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket}
 * to allow for custom packets
 */
public class CustomPayloadPacketWrapper_1_20_2 {
    private final String channel;
    private final byte[] data;

    /**
     * Constructor.
     *
     * @param packet The packet.
     */
    public CustomPayloadPacketWrapper_1_20_2(ServerboundCustomPayloadPacket packet) {
        this.channel = packet.payload().id().toString();
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.payload().write(buf);
        this.data = buf.array();
    }

    /**
     * Gets the channel.
     *
     * @return The channel.
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Gets the data.
     *
     * @return The data.
     */
    public byte[] getData() {
        return data;
    }
}
