/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.fabric.listeners.player;

import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.mixin.MixinCancellableCallbackWrapper;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.player.VanillaPlayerMessageEvent;

import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player message listener. */
@ReqMappings(Mappings.INTERMEDIARY)
@ReqMCVersion(MinecraftVersion.V1_19)
@Mixin(ServerGamePacketListenerImpl.class)
public class PlayerMessageMixin {
    /** Called when a player sends a message. */
    @Inject(
            method = "handleChat(Lnet/minecraft/network/protocol/game/ServerboundChatPacket;)V",
            at = @At("HEAD"),
            cancellable = true)
    public void onPlayerMessage(ServerboundChatPacket serverboundChatPacket, CallbackInfo ci) {
        if (serverboundChatPacket.getMessage().startsWith("/")) return;
        PlayerEvents.MESSAGE.invoke(
                new VanillaPlayerMessageEvent(
                        ((ServerGamePacketListenerImpl) (Object) this).getPlayer(),
                        serverboundChatPacket.getMessage(),
                        new MixinCancellableCallbackWrapper(ci)));
    }
}
