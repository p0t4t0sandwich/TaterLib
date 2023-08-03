package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Listens for player respawns and emits an event.
 */
@Mixin(PlayerManager.class)
public class FabricPlayerRespawnMixin {
    /**
     * Called when a player respawns.
     * @param player The player that respawned.
     * @param alive Whether the player is alive.
     * @param cir The callback info.
     */
    @Inject(method = "respawnPlayer", at = @At("HEAD"))
    public void onPlayerRespawn(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
        // Fire the respawn event
        FabricPlayerEvents.RESPAWN.invoker().onPlayerRespawn(player);
    }
}
