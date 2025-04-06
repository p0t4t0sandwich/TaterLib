/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.vanilla.core.network.protocol.common.custom;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.network.VanillaCustomPacketPayload;

import io.netty.buffer.ByteBufUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.DiscardedPayload;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ReqMappings(Mappings.MOJANG)
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
    @Inject(
            method = "decode(Ljava/lang/Object;)Ljava/lang/Object;",
            at = @At("HEAD"),
            cancellable = true)
    private void decode(Object buf, CallbackInfoReturnable<CustomPacketPayload> cir) {
        if (!(buf instanceof FriendlyByteBuf friendlyByteBuf)
                || buf instanceof RegistryFriendlyByteBuf) {
            return;
        }
        FriendlyByteBuf bufCopy = null;
        try {
            bufCopy = new FriendlyByteBuf(friendlyByteBuf.copy());
            ResourceLocation id = bufCopy.readResourceLocation();

            //
            TaterAPI.logger().info("Received: " + id);
            TaterAPI.logger().info("\n" + ByteBufUtil.prettyHexDump(bufCopy));
            //

            CustomPacketPayload payload = this.shadow$findCodec(id).decode(bufCopy);
            if (payload instanceof DiscardedPayloadBridge bridge) {
                bufCopy.resetReaderIndex();
                bufCopy.readResourceLocation();
                bridge.bridge$setBuf(bufCopy);
                cir.setReturnValue(payload);
            } else {
                bufCopy.release();
            }
        } catch (Throwable e) {
            if (bufCopy != null) {
                bufCopy.release();
            }
            TaterAPI.logger().error("Failed to decode payload", e);
        }
    }
}
