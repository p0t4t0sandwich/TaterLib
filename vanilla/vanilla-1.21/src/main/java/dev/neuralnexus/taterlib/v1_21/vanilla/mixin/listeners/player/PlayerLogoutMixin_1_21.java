/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.mixin.listeners.player;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.minecraft.network.DisconnectionDetails;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player logout listener. */
@ReqMCVersion(min = MinecraftVersion.V1_21)
@Mixin(ServerGamePacketListenerImpl.class)
public class PlayerLogoutMixin_1_21 {
    /** Called when a player disconnects. */
    @Inject(method = "onDisconnect", at = @At("HEAD"))
    private void onLogout(DisconnectionDetails details, CallbackInfo ci) {
        PlayerEvents.LOGOUT.invoke(
                new VanillaPlayerLogoutEvent(
                        ((ServerGamePacketListenerImpl) (Object) this).getPlayer()));
    }
}
