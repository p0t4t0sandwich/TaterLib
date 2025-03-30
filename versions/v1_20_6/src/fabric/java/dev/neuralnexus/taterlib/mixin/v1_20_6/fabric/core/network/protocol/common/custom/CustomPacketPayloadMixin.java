/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.fabric.core.network.protocol.common.custom;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.network.VanillaCustomPacketPayload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_5)
@Mixin(targets = "net.minecraft.network.protocol.common.custom.CustomPacketPayload$1")
public abstract class CustomPacketPayloadMixin {
    @Shadow
    abstract StreamCodec<FriendlyByteBuf, ? extends CustomPacketPayload> shadow$findCodec(
            ResourceLocation resourceLocation);

    @SuppressWarnings({"rawtypes", "UnresolvedMixinReference"})
    @Inject(method = "writeCap", at = @At("HEAD"), cancellable = true)
    private void writeCap(
            FriendlyByteBuf friendlyByteBuf,
            CustomPacketPayload.Type type,
            CustomPacketPayload customPacketPayload,
            CallbackInfo ci) {
        if (customPacketPayload instanceof VanillaCustomPacketPayload vanillaPayload) {
            friendlyByteBuf.writeResourceLocation(type.id());
            StreamCodec<FriendlyByteBuf, VanillaCustomPacketPayload> streamCodec =
                    VanillaCustomPacketPayload.STREAM_CODEC;
            streamCodec.encode(friendlyByteBuf, vanillaPayload);
            ci.cancel();
        }
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
    private void decode(
            FriendlyByteBuf friendlyByteBuf, CallbackInfoReturnable<CustomPacketPayload> cir) {
        FriendlyByteBuf copiedBuffer = new FriendlyByteBuf(friendlyByteBuf.copy());
        ResourceLocation resourceLocation = friendlyByteBuf.readResourceLocation();
        CustomPacketPayload payload =
                this.shadow$findCodec(resourceLocation).decode(friendlyByteBuf);

        if (payload instanceof DiscardedPayloadBridge bridge) {
            bridge.bridge$setBuf(copiedBuffer);
        } else {
            copiedBuffer.release();
        }
        cir.setReturnValue(payload);
    }
}
