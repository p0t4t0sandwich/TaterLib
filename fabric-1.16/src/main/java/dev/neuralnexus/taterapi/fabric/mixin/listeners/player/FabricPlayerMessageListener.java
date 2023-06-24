package dev.neuralnexus.taterapi.fabric.mixin.listeners.player;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerMessageListener;
import dev.neuralnexus.taterapi.fabric.events.player.FabricPlayerMessageEvent;
import dev.neuralnexus.taterapi.fabric.player.FabricTaterPlayer;
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
public abstract class FabricPlayerMessageListener implements TaterPlayerMessageListener {
    @Shadow public ServerPlayerEntity player;

    /**
     * Called when a player sends a message.
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    public void onPlayerMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (packet.getChatMessage().startsWith("/")) return;
        if (TaterAPI.cancelChat) ci.cancel();

        taterPlayerMessage(new FabricTaterPlayer(player), packet.getChatMessage(), TaterAPI.cancelChat);

        // Fire the message event
        FabricPlayerMessageEvent.EVENT.invoker().onPlayerMessage(player, packet.getChatMessage(), TaterAPI.cancelChat);
    }
}
