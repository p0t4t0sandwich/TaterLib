package dev.neuralnexus.taterapi.fabric.mixin.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.PlayerLogoutListener;
import dev.neuralnexus.taterapi.fabric.player.FabricTaterPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player logouts and emits an event.
 */
@Mixin(ServerPlayerEntity.class)
public class FabricPlayerLogoutListener implements PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param ci The callback info.
     */
    @Inject(method = "onDisconnect", at = @At("HEAD"))
    public void onPlayerLogout(CallbackInfo ci) {
        // Send death message to message relay
        taterPlayerLogout(new FabricTaterPlayer((ServerPlayerEntity) (Object) this));
        // TODO: Emit event
    }
}
