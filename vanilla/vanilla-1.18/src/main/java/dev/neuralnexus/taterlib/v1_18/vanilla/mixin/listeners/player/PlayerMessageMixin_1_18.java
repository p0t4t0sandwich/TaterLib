package dev.neuralnexus.taterlib.v1_18.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.VanillaCancellableCallbackWrapper;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.player.VanillaPlayerMessageEvent;

import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player message listener. */
@Mixin(ServerGamePacketListenerImpl.class)
public class PlayerMessageMixin_1_18 {
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
                        new VanillaCancellableCallbackWrapper(ci)));
    }
}
