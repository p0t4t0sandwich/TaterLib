package dev.neuralnexus.taterlib.v1_21.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.minecraft.network.DisconnectionDetails;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player logout listener. */
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
