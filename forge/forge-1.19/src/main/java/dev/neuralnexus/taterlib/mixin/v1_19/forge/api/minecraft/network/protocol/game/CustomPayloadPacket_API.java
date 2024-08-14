/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.forge.api.minecraft.network.protocol.game;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V1_19, max = MinecraftVersion.V1_19_4)
@Mixin({ClientboundCustomPayloadPacket.class, ServerboundCustomPayloadPacket.class})
@Implements(@Interface(iface = CustomPayloadPacket.class, prefix = "packet$", remap = Remap.NONE))
public abstract class CustomPayloadPacket_API {
    public ResourceKey packet$channel() {
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacket client) {
            return (ResourceKey) client.getIdentifier();
        } else {
            return (ResourceKey) ((ServerboundCustomPayloadPacket) self).getIdentifier();
        }
    }

    public byte[] packet$data() {
        Object self = this;
        if (self instanceof ClientboundCustomPayloadPacket client) {
            return client.getData().array();
        } else {
            return ((ServerboundCustomPayloadPacket) self).getData().array();
        }
    }
}
