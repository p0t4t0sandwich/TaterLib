/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.fabric.bridge.network.protocol.game;

import dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.accessors.network.protocol.game.ClientboundCustomPayloadPacketAccessor;
import dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.accessors.network.protocol.game.ServerboundCustomPayloadPacketAccessor;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

/** Bridge for CustomPayloadPacket */
public interface CustomPayloadPacketBridge {
    default ResourceLocation bridge$identifier(Object packet) {
        if (packet instanceof ClientboundCustomPayloadPacket) {
            return ((ClientboundCustomPayloadPacketAccessor) packet).accessor$identifier();
        } else {
            return ((ServerboundCustomPayloadPacketAccessor) packet).accessor$identifier();
        }
    }

    default FriendlyByteBuf bridge$data(Object packet) {
        if (packet instanceof ClientboundCustomPayloadPacket) {
            return new FriendlyByteBuf(
                    ((ClientboundCustomPayloadPacketAccessor) packet).accessor$data().copy());
        } else {
            return new FriendlyByteBuf(
                    ((ServerboundCustomPayloadPacketAccessor) packet).accessor$data().copy());
        }
    }
}
