package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;
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
     * @param serverPlayerEntity The player that respawned.
     * @param dimensionType The dimension the player respawned in.
     * @param cir The callback info.
     */
    @Inject(method = "respawnPlayer", at = @At("HEAD"))
    public void onPlayerRespawn(ServerPlayerEntity serverPlayerEntity, DimensionType dimensionType, boolean bl, CallbackInfoReturnable<ServerPlayerEntity> cir) {
        // Fire the respawn event
        FabricPlayerEvents.RESPAWN.invoker().onPlayerRespawn(serverPlayerEntity);
    }
}
