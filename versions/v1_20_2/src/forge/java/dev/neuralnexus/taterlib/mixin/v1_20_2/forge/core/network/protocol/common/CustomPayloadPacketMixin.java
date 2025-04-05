/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.forge.core.network.protocol.common;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.network.protocol.game.CustomPayloadPacketBridge;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.DiscardedPayload;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_4)
@Mixin({ClientboundCustomPayloadPacket.class, ServerboundCustomPayloadPacket.class})
public abstract class CustomPayloadPacketMixin implements CustomPayloadPacketBridge {
    @Unique public CustomPacketPayload taterapi$payload() {
        // TODO: Replace with nice Java 21 switch expression
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacket client) {
            return client.payload();
        } else if (self instanceof ServerboundCustomPayloadPacket server) {
            return server.payload();
        } else {
            throw new IllegalStateException("Unknown packet type");
        }
    }

    @Override
    public ResourceLocation bridge$identifier() {
        return this.taterapi$payload().id();
    }

    @Override
    public FriendlyByteBuf bridge$data() {
        if (this.taterapi$payload() instanceof DiscardedPayload bridge) {
            // TODO: Make a note of this somewhere
            return bridge.data();
        } else {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            this.taterapi$payload().write(buf);
            return buf;
        }
    }
}
