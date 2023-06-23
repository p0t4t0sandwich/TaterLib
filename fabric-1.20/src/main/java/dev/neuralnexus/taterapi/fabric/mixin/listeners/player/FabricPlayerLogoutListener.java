package dev.neuralnexus.taterapi.fabric.mixin.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.TaterPlayerLogoutListener;
import dev.neuralnexus.taterapi.fabric.events.player.FabricPlayerLogoutEvent;
import dev.neuralnexus.taterapi.fabric.player.FabricTaterPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player logouts and removes the TaterPlayer from the cache.
 */
@Mixin(ServerPlayerEntity.class)
public class FabricPlayerLogoutListener implements TaterPlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param ci The callback info.
     */
    @Inject(method = "onDisconnect", at = @At("HEAD"))
    public void onPlayerLogout(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        // Remove the player from the player cache
        taterPlayerLogout(new FabricTaterPlayer(player));

        // Fire the logout event
        FabricPlayerLogoutEvent.EVENT.invoker().onPlayerLogout(player);
    }
}
