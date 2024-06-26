/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_2.forge.mixin.listeners.network;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.event.network.CustomPayloadWrapper;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.network.VanillaPluginMessageEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.event.network.CustomPayloadPacketWrapper_1_20_2;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class CustomPayloadMixin_1_20_2 {
    @Shadow
    public abstract GameProfile getOwner();

    /**
     * Called when a custom payload packet is received. (often used for plugin messages)
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadWrapper wrapper = new CustomPayloadPacketWrapper_1_20_2(packet);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent(wrapper));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent.Player(
                        wrapper,
                        VanillaServer.server().getPlayerList().getPlayer(getOwner().getId())));
    }
}
