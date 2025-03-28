/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.vanilla.core.network.protocol.common;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.network.protocol.game.CustomPayloadPacketBridge;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V20_5)
@Mixin({ClientboundCustomPayloadPacket.class, ServerboundCustomPayloadPacket.class})
public abstract class CustomPayloadPacketMixin implements CustomPayloadPacketBridge {

    @Unique @SuppressWarnings("ConstantValue")
    private CustomPacketPayload taterlib$payload() {
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacket(CustomPacketPayload payload)) {
            return payload;
        } else if (self instanceof ServerboundCustomPayloadPacket(CustomPacketPayload payload)) {
            return payload;
        }
        throw new IllegalStateException("Unknown packet type");
    }

    @Override
    public ResourceLocation bridge$identifier() {
        return this.taterlib$payload().type().id();
    }

    @Override
    public FriendlyByteBuf bridge$data() {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        Object self = this;
        CustomPacketPayload payload = this.taterlib$payload();
        if (payload instanceof DiscardedPayloadBridge bridge) {
            buf = bridge.bridge$buf();
        } else {
            if (self instanceof ClientboundCustomPayloadPacket client) {
                ClientboundCustomPayloadPacket.CONFIG_STREAM_CODEC.encode(buf, client);
            } else if (self instanceof ServerboundCustomPayloadPacket server) {
                ServerboundCustomPayloadPacket.STREAM_CODEC.encode(buf, server);
            }
        }
        return buf;
    }
}
