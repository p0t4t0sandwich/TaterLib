/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.vanilla.listeners.network;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.S2CCustomPacketEventImpl;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.server.SimpleServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V20_5)
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
        SimpleServer server = (SimpleServer) Minecraft.getInstance();
        CustomPayloadPacket customPacket = (CustomPayloadPacket) (Object) packet;

        //
        // TaterAPI.logger().info("C2SCustomPayloadMixin Received: " +
        // packet.payload().type().id());
        // if (packet.payload() instanceof DiscardedPayloadBridge bridge) {
        //     TaterAPI.logger().info("\n" + ByteBufUtil.prettyHexDump(bridge.bridge$buf()));
        // } else {
        //     TaterAPI.logger().info("Packet: " + packet.payload().getClass().getName());
        // }
        //

        NetworkEvents.S2C_CUSTOM_PACKET.invoke(new S2CCustomPacketEventImpl(customPacket, server));
    }
}
