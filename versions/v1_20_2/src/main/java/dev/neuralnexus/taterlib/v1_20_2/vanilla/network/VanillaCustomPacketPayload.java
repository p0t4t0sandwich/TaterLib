/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.vanilla.network;

import dev.neuralnexus.taterapi.resource.ResourceKey;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/** CustomPacketPayload implementation for 1.20.2 */
public class VanillaCustomPacketPayload implements CustomPacketPayload {
    private final ResourceLocation id;
    private final FriendlyByteBuf byteBuf;

    public VanillaCustomPacketPayload(ResourceKey channel, byte[] data) {
        this.id = (ResourceLocation) channel;
        this.byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        this.byteBuf.writeBytes(data);
    }

    public VanillaCustomPacketPayload(ResourceLocation id, FriendlyByteBuf byteBuf) {
        this.id = id;
        this.byteBuf = byteBuf;
    }

    @Override
    public void write(FriendlyByteBuf byteBuf) {
        byteBuf.writeBytes(this.byteBuf.copy());
    }

    @Override
    public @NotNull ResourceLocation id() {
        return this.id;
    }
}
