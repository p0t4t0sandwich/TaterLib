/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.vanilla.event.network;

import dev.neuralnexus.taterapi.event.network.CustomPayloadWrapper;
import dev.neuralnexus.taterapi.util.ResourceLocation;
import dev.neuralnexus.taterlib.v1_20.vanilla.util.VanillaResourceLocation;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayloadWrapper}.
 */
public class CustomPayloadPacketWrapper_1_20 implements CustomPayloadWrapper {
    private final ResourceLocation channel;
    private final byte[] data;

    public CustomPayloadPacketWrapper_1_20(ServerboundCustomPayloadPacket packet) {
        this.channel = new VanillaResourceLocation(packet.getIdentifier());
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.write(buf);
        this.data = buf.array();
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation channel() {
        return channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] data() {
        return data;
    }
}
