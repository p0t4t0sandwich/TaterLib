/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.core.network.protocol.game;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.mixin.v1_17_1.forge.accessors.network.protocol.game.ClientboundCustomPayloadPacketAccessor;
import dev.neuralnexus.taterlib.mixin.v1_17_1.forge.accessors.network.protocol.game.ServerboundCustomPayloadPacketAccessor;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.network.protocol.game.CustomPayloadPacketBridge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V20_1)
@Mixin({ClientboundCustomPayloadPacket.class, ServerboundCustomPayloadPacket.class})
public class CustomPayloadPacketMixin implements CustomPayloadPacketBridge {
    @Override
    public ResourceLocation bridge$identifier() {
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacketAccessor client) {
            return client.accessor$identifier();
        } else if (self instanceof ServerboundCustomPayloadPacketAccessor server) {
            return server.accessor$identifier();
        }
        throw new IllegalStateException("Unknown packet type");
    }

    @Override
    public FriendlyByteBuf bridge$data() {
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacketAccessor client) {
            return client.accessor$data();
        } else if (self instanceof ServerboundCustomPayloadPacketAccessor server) {
            return server.accessor$data();
        }
        throw new IllegalStateException("Unknown packet type");
    }
}
