/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.forge.listeners.network;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.C2SCustomPacketEventImpl;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V1_20_2, max = MinecraftVersion.V1_20_4)
@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class C2SCustomPayloadMixin {
    @Shadow
    public abstract GameProfile getOwner();

    /**
     * Called when a custom payload packet is received. (often used for plugin messages)
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    @SuppressWarnings("DataFlowIssue")
    public void onC2SCustomPacket(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacket customPacket = (CustomPayloadPacket) (Object) packet;
        NetworkEvents.C2S_CUSTOM_PACKET.invoke(new C2SCustomPacketEventImpl(customPacket));
        TaterAPIProvider.api()
                .get()
                .server()
                .getPlayer(getOwner().getId())
                .ifPresent(
                        player ->
                                NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                                        new C2SCustomPacketEventImpl.Player(customPacket, player)));
    }
}
