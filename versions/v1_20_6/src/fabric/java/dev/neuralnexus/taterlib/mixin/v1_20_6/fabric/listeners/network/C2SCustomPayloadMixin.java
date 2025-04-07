/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.fabric.listeners.network;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;

import io.netty.buffer.ByteBufUtil;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_5)
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class C2SCustomPayloadMixin {
    @Shadow
    public abstract ServerPlayer shadow$getPlayer();

    /**
     * Called when a custom payload packet is received from the client.
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onC2SCustomPacket(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        User player = (User) this.shadow$getPlayer();
        if (player == null) return;

        CustomPayloadPacket customPacket = (CustomPayloadPacket) (Object) packet;

        //
        // TaterAPI.logger().info("C2SCustomPayloadMixin Received: " + packet.payload().type().id());
        // if (packet.payload() instanceof DiscardedPayloadBridge bridge) {
        //     TaterAPI.logger().info("\n" + ByteBufUtil.prettyHexDump(bridge.bridge$buf()));
        // } else {
        //     TaterAPI.logger().info("Packet: " + packet.payload().getClass().getName());
        // }
        //

        NetworkEvents.C2S_CUSTOM_PACKET.invoke(new C2SCustomPacketEventImpl(customPacket, player));
    }
}
