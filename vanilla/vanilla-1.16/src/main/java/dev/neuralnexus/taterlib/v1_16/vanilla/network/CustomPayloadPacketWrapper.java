/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16.vanilla.network;

import dev.neuralnexus.taterapi.network.CustomPayload;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.mixin.v1_16.vanilla.bridge.network.protocol.game.ServerboundCustomPayloadPacketBridge;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;

import java.io.IOException;

/**
 * A custom wrapper for {@link ServerboundCustomPayloadPacket} that implements {@link
 * CustomPayload}.
 */
public class CustomPayloadPacketWrapper implements CustomPayload, ServerboundCustomPayloadPacketBridge {
    private final ResourceKey channel;
    private final byte[] data;

    @SuppressWarnings("VulnerableCodeUsages")
    public CustomPayloadPacketWrapper(ServerboundCustomPayloadPacket packet) {
        this.channel = (ResourceKey) bridge$getIdentifier(packet);
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        try {
            packet.write(buf);
        } catch (IOException e) {
            TaterLib.logger().error("Could not encode custom packet payload", e);
        }
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
