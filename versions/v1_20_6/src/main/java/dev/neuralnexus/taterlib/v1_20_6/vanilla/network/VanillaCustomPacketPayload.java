/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_6.vanilla.network;

import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/** CustomPacketPayload implementation for 1.20.6 */
public class VanillaCustomPacketPayload implements CustomPacketPayload, CustomPayloadPacket {
    public static final StreamCodec<FriendlyByteBuf, VanillaCustomPacketPayload> STREAM_CODEC =
            CustomPacketPayload.codec(
                    VanillaCustomPacketPayload::write, VanillaCustomPacketPayload::new);

    private final ResourceLocation id;
    private final FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());

    public VanillaCustomPacketPayload(ResourceKey channel, byte[] data) {
        this.id = (ResourceLocation) channel;
        this.byteBuf.writeBytes(data);
    }

    public VanillaCustomPacketPayload(FriendlyByteBuf buf) {
        this.id = buf.readResourceLocation();
        this.byteBuf.writeBytes(buf);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBytes(this.byteBuf.copy());
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        // CustomPacketPayload.createType(this.id.toString());
        return new Type<>(this.id);
    }

    @Override
    public ResourceKey channel() {
        return (ResourceKey) this.id;
    }

    @Override
    public byte[] data() {
        return this.byteBuf.array();
    }
}
