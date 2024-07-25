/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_6.vanilla.network;

import dev.neuralnexus.taterapi.resource.ResourceKey;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/** CustomPacketPayload implementation for 1.20.6 */
public class VanillaCustomPacketPayload implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, VanillaCustomPacketPayload> STREAM_CODEC =
            CustomPacketPayload.codec(
                    VanillaCustomPacketPayload::write, VanillaCustomPacketPayload::new);

    private final ResourceLocation id;
    private final FriendlyByteBuf byteBuf;

    public VanillaCustomPacketPayload(ResourceKey channel, byte[] data) {
        id = (ResourceLocation) (Object) channel;
        byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
    }

    public VanillaCustomPacketPayload(FriendlyByteBuf buf) {
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
