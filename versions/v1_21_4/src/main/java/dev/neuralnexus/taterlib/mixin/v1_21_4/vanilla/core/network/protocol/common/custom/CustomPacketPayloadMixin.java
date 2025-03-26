/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21_4.vanilla.core.network.protocol.common.custom;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.network.VanillaCustomPacketPayload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V20_6)
@Mixin(targets = "net.minecraft.network.protocol.common.custom.CustomPacketPayload$1")
public class CustomPacketPayloadMixin {
    @SuppressWarnings({"rawtypes", "UnresolvedMixinReference"})
    @Inject(method = "writeCap", at = @At("HEAD"), cancellable = true)
    private void writeCap(
            FriendlyByteBuf friendlyByteBuf,
            CustomPacketPayload.Type type,
            CustomPacketPayload customPacketPayload,
            CallbackInfo ci) {
        if (customPacketPayload instanceof VanillaCustomPacketPayload vanillaPayload) {
            friendlyByteBuf.writeResourceLocation(type.id());
            VanillaCustomPacketPayload.STREAM_CODEC.encode(friendlyByteBuf, vanillaPayload);
            ci.cancel();
        }
    }
}
