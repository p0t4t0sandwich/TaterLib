/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.vanilla.listeners.network;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.PluginMessageEventImpl;
import dev.neuralnexus.taterlib.v1_20_6.vanilla.network.CustomPayloadPacket;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@ReqMCVersion(min = MinecraftVersion.V1_20_5, max = MinecraftVersion.V1_20_6)
@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class CustomPayloadMixin {
    @Shadow
    public abstract GameProfile getOwner();

    /**
     * Called when a custom payload packet is received. (often used for plugin messages)
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacket wrapper = new CustomPayloadPacket(packet);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new PluginMessageEventImpl(wrapper));
        TaterAPIProvider.api()
                .get()
                .server()
                .getPlayer(getOwner().getId())
                .ifPresent(
                        player ->
                                NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                                        new PluginMessageEventImpl.Player(wrapper, player)));
    }
}
