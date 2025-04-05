/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.fabric.core.network.protocol.common;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.DiscardedPayload;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_4)
@Mixin(value = ServerboundCustomPayloadPacket.class)
public class ServerboundCustomPayloadPacketMixin {
    // @spotless:off
    @Shadow @Final private static int MAX_PAYLOAD_SIZE;
    // @spotless:on

    @Inject(method = "readUnknownPayload", at = @At("HEAD"), cancellable = true)
    @SuppressWarnings("ConstantValue")
    private static void readUnknownPayload(
            ResourceLocation id,
            FriendlyByteBuf buf,
            CallbackInfoReturnable<DiscardedPayload> cir) {
        FriendlyByteBuf bufCopy = new FriendlyByteBuf(buf.copy());
        CustomPacketPayload payload = new DiscardedPayload(id);
        if (payload instanceof DiscardedPayloadBridge bridge) {
            int i = buf.readableBytes();
            if (i >= 0 && i <= MAX_PAYLOAD_SIZE) {
                buf.skipBytes(i);
                bridge.bridge$setBuf(bufCopy);
                cir.setReturnValue((DiscardedPayload) payload);
            } else {
                bufCopy.release();
                throw new IllegalArgumentException(
                        "Payload may not be larger than " + MAX_PAYLOAD_SIZE + " bytes");
            }
        } else {
            bufCopy.release();
        }
    }
}
