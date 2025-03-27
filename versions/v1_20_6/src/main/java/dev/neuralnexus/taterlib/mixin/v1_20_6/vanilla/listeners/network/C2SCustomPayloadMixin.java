/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.vanilla.listeners.network;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.network.VanillaCustomPacketPayload;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V20_5)
@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class C2SCustomPayloadMixin {
    @Shadow @Final protected MinecraftServer server;

    @Shadow
    public abstract GameProfile shadow$getOwner();

    @Shadow
    protected abstract GameProfile playerProfile();

    /**
     * Called when a custom payload packet is received from the client.
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onC2SCustomPacket(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        Optional<User> player =
                ((SimpleServer) this.server).getPlayer(this.shadow$getOwner().getId());
        if (player.isEmpty()) return;

        if (!(packet.payload() instanceof DiscardedPayloadBridge bridge)) {
            return;
        }
        CustomPayloadPacket customPacket = new VanillaCustomPacketPayload(bridge.bridge$buf());

        NetworkEvents.C2S_CUSTOM_PACKET.invoke(
                new C2SCustomPacketEventImpl(customPacket, player.get()));
    }
}
