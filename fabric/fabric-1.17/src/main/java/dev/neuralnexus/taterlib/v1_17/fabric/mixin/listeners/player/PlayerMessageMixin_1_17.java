package dev.neuralnexus.taterlib.v1_17.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.v1_17.fabric.event.api.FabricPlayerEvents;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player message listener. */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class PlayerMessageMixin_1_17 {
    @Shadow
    public abstract ServerPlayerEntity getPlayer();

    /**
     * Called when a player sends a message.
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    public void onPlayerMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (packet.getChatMessage().startsWith("/")) return;
        FabricPlayerEvents.MESSAGE
                .invoker()
                .onPlayerMessage(getPlayer(), packet.getChatMessage(), ci);
    }
}
