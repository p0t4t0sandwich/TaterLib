/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.fabric.core.network.protocol.common;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.network.protocol.game.CustomPayloadPacketBridge;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_5)
@Mixin({ClientboundCustomPayloadPacket.class, ServerboundCustomPayloadPacket.class})
public abstract class CustomPayloadPacketMixin implements CustomPayloadPacketBridge {
    @Unique @SuppressWarnings("DataFlowIssue")
    private CustomPacketPayload taterapi$payload() {
        return switch ((Object) this) {
            case ClientboundCustomPayloadPacket(CustomPacketPayload payload) -> payload;
            case ServerboundCustomPayloadPacket(CustomPacketPayload payload) -> payload;
            default -> throw new IllegalStateException("Unknown packet type");
        };
    }

    @Override
    public ResourceLocation bridge$identifier() {
        return this.taterapi$payload().type().id();
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public FriendlyByteBuf bridge$data() {
        if (this.taterapi$payload() instanceof DiscardedPayloadBridge bridge) {
            return bridge.bridge$buf();
        } else {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            switch ((Object) this) {
                case ClientboundCustomPayloadPacket client ->
                        ClientboundCustomPayloadPacket.CONFIG_STREAM_CODEC.encode(buf, client);
                case ServerboundCustomPayloadPacket server ->
                        ServerboundCustomPayloadPacket.STREAM_CODEC.encode(buf, server);
                default -> {}
            }
            return buf;
        }
    }
}
