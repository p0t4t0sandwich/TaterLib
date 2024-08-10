/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.vanilla.listeners.network;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.S2CCustomPacketEventImpl;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.server.SimpleServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.MOJMAP)
@ReqMCVersion(min = MinecraftVersion.V1_20_2, max = MinecraftVersion.V1_20_6)
@Mixin(ClientCommonPacketListenerImpl.class)
public abstract class S2CCustomPayloadMixin {
    /**
     * Called when a custom payload packet is received from the server.
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(
            method =
                    "handleCustomPayload(Lnet/minecraft/network/protocol/common/ClientboundCustomPayloadPacket;)V",
            at = @At("HEAD"))
    @SuppressWarnings("DataFlowIssue")
    public void onS2CCustomPacket(ClientboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacket customPacket = (CustomPayloadPacket) (Object) packet;
        SimpleServer server = (SimpleServer) Minecraft.getInstance();
        NetworkEvents.S2C_CUSTOM_PACKET.invoke(new S2CCustomPacketEventImpl(customPacket, server));
    }
}