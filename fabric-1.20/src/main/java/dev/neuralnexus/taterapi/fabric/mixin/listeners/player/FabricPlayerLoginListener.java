package dev.neuralnexus.taterapi.fabric.mixin.listeners.player;

import dev.neuralnexus.taterapi.common.listeners.player.PlayerLoginListener;
import dev.neuralnexus.taterapi.fabric.events.player.FabricPlayerLoginEvent;
import dev.neuralnexus.taterapi.fabric.player.FabricTaterPlayer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player logins and emits an event.
 */
@Mixin(ServerLoginNetworkHandler.class)
public class FabricPlayerLoginListener implements PlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param ci The callback info.
     */
    @Inject(method = "addToServer", at = @At("HEAD"))
    private void onPlayerLogin(ServerPlayerEntity player, CallbackInfo ci) {
        // Add the player to the player cache
        taterPlayerLogin(new FabricTaterPlayer(player));

        // Fire the login event
        FabricPlayerLoginEvent.EVENT.invoker().onPlayerLogin(player);
    }
}
