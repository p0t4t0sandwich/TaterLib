package dev.neuralnexus.taterlib.v1_20_2.vanilla.network;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/** CustomPacketPayload implementation for 1.20.2 */
public class VanillaCustomPacketPayload_1_20_2 implements CustomPacketPayload {
    private final ResourceLocation id;
    private final FriendlyByteBuf byteBuf;

    public VanillaCustomPacketPayload_1_20_2(String channel, byte[] data) {
        String[] channelParts = channel.split(":");
        if (channelParts.length == 1) {
            channel = "tl-user-forgot:" + channelParts[0];
        }
        id = ResourceLocation.of(channel, ':');
        byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBytes(byteBuf.copy());
    }

    @Override
    public @NotNull ResourceLocation id() {
        return id;
    }
}
