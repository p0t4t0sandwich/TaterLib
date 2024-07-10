/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_2.vanilla.network;

import dev.neuralnexus.taterapi.resource.ResourceKey;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/** CustomPacketPayload implementation for 1.20.2 */
public class VanillaCustomPacketPayload_1_20_2 implements CustomPacketPayload {
    private final ResourceLocation id;
    private final FriendlyByteBuf byteBuf;

    public VanillaCustomPacketPayload_1_20_2(ResourceKey channel, byte[] data) {
        id = (ResourceLocation) (Object) channel;
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
