package dev.neuralnexus.taterlib.v1_15.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.v1_15.fabric.event.api.FabricPlayerEvents;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player respawn listener. */
@Mixin(PlayerManager.class)
public class PlayerRespawnMixin_1_15 {
    /**
     * Called when a player respawns.
     *
     * @param player The player that respawned.
     * @param alive Whether the player is alive.
     * @param cir The callback info.
     */
    @Inject(method = "respawnPlayer", at = @At("HEAD"))
    public void onPlayerRespawn(
            ServerPlayerEntity player,
            DimensionType dimension,
            boolean alive,
            CallbackInfoReturnable<ServerPlayerEntity> cir) {
        FabricPlayerEvents.RESPAWN.invoker().onPlayerRespawn(player, alive);
    }
}