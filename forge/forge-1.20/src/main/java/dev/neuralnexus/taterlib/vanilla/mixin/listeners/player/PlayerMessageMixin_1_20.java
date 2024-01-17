package dev.neuralnexus.taterlib.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerMessageEvent;

import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player message listener. */
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class PlayerMessageMixin_1_20 {
    @Shadow
    public abstract ServerPlayer getPlayer();

    /**
     * Called when a player sends a message.
     *
     * @param serverboundChatPacket The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = true)
    public void onPlayerMessage(ServerboundChatPacket serverboundChatPacket, CallbackInfo ci) {
        if (serverboundChatPacket.message().startsWith("/")) return;
        PlayerEvents.MESSAGE.invoke(
                new VanillaPlayerMessageEvent(getPlayer(), serverboundChatPacket.message(), ci));
    }
}
