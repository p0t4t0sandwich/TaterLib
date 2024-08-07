/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17.vanilla.network;

import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayload}.
 */
public class CustomPayloadPacket implements CustomPayload {
    private final ResourceKey channel;
    private final byte[] data;

    @SuppressWarnings("VulnerableCodeUsages")
    public CustomPayloadPacket(ServerboundCustomPayloadPacket packet) {
        this.channel = (ResourceKey) packet.getIdentifier();
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.write(buf);
        this.data = buf.array();
    }

    @Override
    public ResourceKey channel() {
        return channel;
    }

    @Override
    public byte[] data() {
        return data;
    }
}
