package dev.neuralnexus.taterlib.v1_21.vanilla.network;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/** CustomPacketPayload implementation for 1.20.6 */
public class VanillaCustomPacketPayload_1_20_6 implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, VanillaCustomPacketPayload_1_20_6>
            STREAM_CODEC =
                    CustomPacketPayload.codec(
                            VanillaCustomPacketPayload_1_20_6::write,
                            VanillaCustomPacketPayload_1_20_6::new);

    private final ResourceLocation id;
    private final FriendlyByteBuf byteBuf;

    public VanillaCustomPacketPayload_1_20_6(String channel, byte[] data) {
        String[] channelParts = channel.split(":");
        if (channelParts.length == 1) {
            channel = "tl-user-forgot:" + channelParts[0];
        }
        id = ResourceLocation.bySeparator(channel, ':');
        byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
    }

    public VanillaCustomPacketPayload_1_20_6(FriendlyByteBuf buf) {
        id = buf.readResourceLocation();
        byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(buf.readBytes(buf.readableBytes()));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBytes(byteBuf.copy());
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return new Type<>(id);
    }
}
