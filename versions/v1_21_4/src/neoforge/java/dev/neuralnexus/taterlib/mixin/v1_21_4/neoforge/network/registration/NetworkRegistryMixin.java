/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21_4.neoforge.network.registration;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqPlatform;

import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.extensions.ICommonPacketListener;
import net.neoforged.neoforge.network.registration.NetworkRegistry;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ReqMappings(Mappings.MOJANG)
@ReqPlatform(Platform.NEOFORGE)
@ReqMCVersion(min = MinecraftVersion.V20_6)
@SuppressWarnings("UnstableApiUsage")
@Mixin(value = NetworkRegistry.class, remap = false)
public class NetworkRegistryMixin {
    @Shadow
    public static boolean hasChannel(
            Connection connection,
            @Nullable ConnectionProtocol protocol,
            ResourceLocation payloadId) {
        return false;
    }

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
}
