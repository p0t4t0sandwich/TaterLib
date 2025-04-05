/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_4.neoforge.network.registration;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqPlatform;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.ClientCommonPacketListener;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerCommonPacketListener;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener;
import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.payload.MinecraftRegisterPayload;
import net.neoforged.neoforge.network.payload.MinecraftUnregisterPayload;
import net.neoforged.neoforge.network.payload.ModdedNetworkPayload;
import net.neoforged.neoforge.network.payload.ModdedNetworkQueryPayload;
import net.neoforged.neoforge.network.payload.ModdedNetworkSetupFailedPayload;
import net.neoforged.neoforge.network.registration.NetworkChannel;
import net.neoforged.neoforge.network.registration.NetworkPayloadSetup;
import net.neoforged.neoforge.network.registration.NetworkRegistry;
import net.neoforged.neoforge.network.registration.PlayRegistration;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@ReqMappings(Mappings.MOJANG)
@ReqPlatform(Platform.NEOFORGE)
@ReqMCVersion(min = MinecraftVersion.V20_3, max = MinecraftVersion.V20_4)
@SuppressWarnings("UnstableApiUsage")
@Mixin(value = NetworkRegistry.class, remap = false)
public abstract class NetworkRegistryMixin {
    // @spotless:off
    @Shadow @Final private static Logger LOGGER;
    @Shadow public abstract boolean shadow$isConnected(ClientCommonPacketListener listener, ResourceLocation channel);
    @Shadow public abstract boolean shadow$isConnected(ServerCommonPacketListener listener, ResourceLocation channel);
    @Shadow public abstract boolean shadow$shouldSendPacketRaw(Packet<?> packet);
    @Shadow @Final private static AttributeKey<NetworkPayloadSetup> ATTRIBUTE_PAYLOAD_SETUP;
    @Shadow protected abstract boolean shadow$isAdhocPlayChannelReadable(ResourceLocation par1, PacketFlow par2);
    @Shadow @Final private Map<ResourceLocation, PlayRegistration<?>> knownPlayRegistrations;
    @Shadow @Final private static AttributeKey<PacketFlow> ATTRIBUTE_FLOW;
    // @spotless:on

    @Inject(
            method =
                    "canSendPacket(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)Z",
            at = @At("HEAD"),
            cancellable = true)
    private void canSendPacket(
            Packet<?> packet,
            ClientCommonPacketListener listener,
            CallbackInfoReturnable<Boolean> cir) {
        if (packet instanceof ServerboundCustomPayloadPacket customPayloadPacket) {
            if (!(customPayloadPacket.payload() instanceof ModdedNetworkQueryPayload)
                    && !(customPayloadPacket.payload() instanceof MinecraftRegisterPayload)
                    && !(customPayloadPacket.payload() instanceof MinecraftUnregisterPayload)
                    && !ServerboundCustomPayloadPacket.KNOWN_TYPES.containsKey(
                            customPayloadPacket.payload().id())
                    && !this.shadow$isConnected(listener, customPayloadPacket.payload().id())) {
                LOGGER.debug(
                        "Tried to send {} packet to a server that does not support it. Not sending the packet.",
                        customPayloadPacket.payload().id());
                TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(
            method =
                    "canSendPacket(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)Z",
            at = @At("HEAD"),
            cancellable = true)
    private void canSendPacket(
            Packet<?> packet,
            ServerCommonPacketListener listener,
            CallbackInfoReturnable<Boolean> cir) {
        if (packet instanceof ClientboundCustomPayloadPacket customPayloadPacket) {
            if (!this.shadow$shouldSendPacketRaw(packet)
                    && !this.shadow$isConnected(listener, customPayloadPacket.payload().id())) {
                LOGGER.debug(
                        "Tried to send {} packet to a client that does not support it. Not sending the packet.",
                        customPayloadPacket.payload().id());
                TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getReader", at = @At("HEAD"), cancellable = true)
    private void getReader(
            ResourceLocation id,
            ChannelHandlerContext context,
            ConnectionProtocol protocol,
            Map<ResourceLocation, FriendlyByteBuf.Reader<? extends CustomPacketPayload>> knownTypes,
            CallbackInfoReturnable<FriendlyByteBuf.Reader<? extends CustomPacketPayload>> cir) {
        if (!knownTypes.containsKey(id)
                && !id.equals(MinecraftRegisterPayload.ID)
                && !id.equals(MinecraftUnregisterPayload.ID)
                && !id.equals(ModdedNetworkQueryPayload.ID)
                && !id.equals(ModdedNetworkPayload.ID)
                && !id.equals(ModdedNetworkSetupFailedPayload.ID)) {
            NetworkPayloadSetup payloadSetup =
                    context.channel().attr(ATTRIBUTE_PAYLOAD_SETUP).get();
            PacketFlow flow = context.channel().attr(ATTRIBUTE_FLOW).get();
            if (payloadSetup != null && flow != null) {
                if (protocol.isPlay()) {
                    NetworkChannel channel = payloadSetup.play().get(id);
                    if (channel == null && !this.shadow$isAdhocPlayChannelReadable(id, flow)) {
                        LOGGER.debug(
                                "Received a modded custom payload packet {} with an unknown or not accepted channel. Not parsing packet.",
                                id);
                        TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                        cir.setReturnValue(null);
                    }
                }
            }
        }
    }

    @Inject(method = "onModdedPacketAtServer", at = @At("HEAD"), cancellable = true)
    private void onModdedPacketAtServer(
            ServerCommonPacketListener listener,
            ServerboundCustomPayloadPacket packet,
            CallbackInfo ci) {
        NetworkPayloadSetup payloadSetup =
                listener.getConnection().channel().attr(ATTRIBUTE_PAYLOAD_SETUP).get();
        if (payloadSetup != null) {
            if (!(listener instanceof ServerConfigurationPacketListener)) {
                if (!(listener instanceof ServerGamePacketListener)) {
                    LOGGER.error(
                            "Received a modded custom payload packet from a client that is not in the configuration or play phase. Disconnecting client.");
                    throw new IllegalStateException(
                            "A client sent a packet while not in the configuration or play phase. Somebody changed the phases known to NeoForge!");
                }

                NetworkChannel channel = payloadSetup.play().get(packet.payload().id());
                if (channel == null
                        && !this.shadow$isAdhocPlayChannelReadable(
                                packet.payload().id(), PacketFlow.SERVERBOUND)) {
                    LOGGER.debug(
                            "Received a modded custom payload packet from a client with an unknown or not accepted channel. Disconnecting client.");
                    TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                    ci.cancel();
                }

                ResourceLocation id = channel != null ? channel.id() : packet.payload().id();
                PlayRegistration<?> registration = this.knownPlayRegistrations.get(id);
                if (registration == null) {
                    LOGGER.debug(
                            "Received a modded custom payload packet from a client with an unknown or not accepted channel. Disconnecting client.");
                    TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "onModdedPacketAtClient", at = @At("HEAD"), cancellable = true)
    private void onModdedPacketAtClient(
            ClientCommonPacketListener listener,
            ClientboundCustomPayloadPacket packet,
            CallbackInfoReturnable<Boolean> cir) {
        if (!packet.payload().id().getNamespace().equals("minecraft")) {
            NetworkPayloadSetup payloadSetup =
                    listener.getConnection().channel().attr(ATTRIBUTE_PAYLOAD_SETUP).get();
            if (payloadSetup != null) {
                if (!(listener instanceof ClientConfigurationPacketListener)) {
                    if (!(listener instanceof ClientGamePacketListener)) {
                        LOGGER.error(
                                "Received a modded custom payload packet from a server that is not in the configuration or play phase. Disconnecting server.");
                        throw new IllegalStateException(
                                "A server sent a packet while not in the configuration or play phase. Somebody changed the phases known to NeoForge!");
                    }

                    NetworkChannel channel = payloadSetup.play().get(packet.payload().id());
                    if (channel == null
                            && !this.shadow$isAdhocPlayChannelReadable(
                                    packet.payload().id(), PacketFlow.CLIENTBOUND)) {
                        LOGGER.debug(
                                "Received a modded custom payload packet from a server with an unknown or not accepted channel. Disconnecting server.");
                        TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                        cir.setReturnValue(true);
                    }

                    ResourceLocation id = channel != null ? channel.id() : packet.payload().id();
                    PlayRegistration<?> registration = this.knownPlayRegistrations.get(id);
                    if (registration == null) {
                        LOGGER.debug(
                                "Received a modded custom payload packet from a server with an unknown or not accepted channel. Disconnecting server.");
                        TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }
}
