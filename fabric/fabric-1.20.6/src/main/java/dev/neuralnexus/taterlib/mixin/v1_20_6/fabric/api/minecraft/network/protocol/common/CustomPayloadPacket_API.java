/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.fabric.api.minecraft.network.protocol.common;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_5, max = MinecraftVersion.V21_1)
@Mixin({ClientboundCustomPayloadPacket.class, ServerboundCustomPayloadPacket.class})
@Implements(@Interface(iface = CustomPayloadPacket.class, prefix = "packet$", remap = Remap.NONE))
public abstract class CustomPayloadPacket_API {
    public ResourceKey packet$channel() {
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacket client) {
            return (ResourceKey) client.payload().type().id();
        } else {
            return (ResourceKey) ((ServerboundCustomPayloadPacket) self).payload().type().id();
        }
    }

    public byte[] packet$data() {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacket client) {
            ClientboundCustomPayloadPacket.CONFIG_STREAM_CODEC.encode(buf, client);
        } else if (self instanceof ServerboundCustomPayloadPacket server) {
            ServerboundCustomPayloadPacket.STREAM_CODEC.encode(buf, server);
        }
        return buf.array();
    }
}
