/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.neoforge.network.registration;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqPlatform;

import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.extensions.ICommonPacketListener;
import net.neoforged.neoforge.network.registration.NetworkRegistry;
import net.neoforged.neoforge.network.registration.PayloadRegistration;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@ReqMappings(Mappings.MOJANG)
@ReqPlatform(Platform.NEOFORGE)
@ReqMCVersion(min = MinecraftVersion.V20_5)
@SuppressWarnings("UnstableApiUsage")
@Mixin(value = NetworkRegistry.class, remap = false)
public abstract class NetworkRegistryMixin {
    // @spotless:off
    @Shadow
    public static boolean hasChannel(
            Connection connection,
            @Nullable ConnectionProtocol protocol,
            ResourceLocation payloadId) {
        return false;
    }
    @Shadow @Final private static Map<ResourceLocation, StreamCodec<FriendlyByteBuf, ? extends CustomPacketPayload>> BUILTIN_PAYLOADS;
    @Shadow @Final private static Map<ConnectionProtocol, Map<ResourceLocation, PayloadRegistration<?>>> PAYLOAD_REGISTRATIONS;
    @Shadow @Final private static Logger LOGGER;
    // @spotless:on

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Inject(
            method =
                    "hasChannel(Lnet/neoforged/neoforge/common/extensions/ICommonPacketListener;Lnet/minecraft/resources/ResourceLocation;)Z",
            at = @At("HEAD"),
            cancellable = true)
    private static void hasChannel(
            ICommonPacketListener listener,
            ResourceLocation payloadId,
            CallbackInfoReturnable<Boolean> cir) {
        hasChannel(listener.getConnection(), listener.protocol(), payloadId);
        cir.setReturnValue(true);
    }

    @SuppressWarnings("rawtypes")
    @Inject(method = "getCodec", at = @At("HEAD"), cancellable = true)
    private static void getCodec(
            ResourceLocation id,
            ConnectionProtocol protocol,
            PacketFlow flow,
            CallbackInfoReturnable<
                            StreamCodec<? super FriendlyByteBuf, ? extends CustomPacketPayload>>
                    cir) {
        if (!BUILTIN_PAYLOADS.containsKey(id) && PAYLOAD_REGISTRATIONS.containsKey(protocol)) {
            PayloadRegistration<?> registration =
                    (PayloadRegistration) ((Map<?, ?>) PAYLOAD_REGISTRATIONS.get(protocol)).get(id);
            if (registration == null) {
                LOGGER.debug("No registration for payload {}; refusing to decode.", id);
                TaterAPI.logger().debug("Bypassing the above using NetworkRegistryMixin");
                cir.setReturnValue(null);
            }
        }
    }
}
