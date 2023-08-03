package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.CommonPlayerListener;
import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player messages and emits an event.
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class FabricPlayerMessageMixin {
    @Shadow public abstract ServerPlayerEntity getPlayer();

    /**
     * Called when a player sends a message.
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    public void onPlayerMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (packet.chatMessage().startsWith("/")) return;
        if (TaterLib.cancelChat) ci.cancel();

        CommonPlayerListener.onPlayerMessage(new FabricPlayer(getPlayer()), packet.chatMessage(), TaterLib.cancelChat);

        // Fire the message event
        FabricPlayerEvents.MESSAGE.invoker().onPlayerMessage(getPlayer(), packet.chatMessage(), TaterLib.cancelChat);
    }
}
