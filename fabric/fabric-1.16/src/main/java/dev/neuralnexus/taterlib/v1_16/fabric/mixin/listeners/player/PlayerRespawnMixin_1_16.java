/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.v1_16.fabric.event.api.FabricPlayerEvents;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player respawn listener. */
@Mixin(PlayerManager.class)
public class PlayerRespawnMixin_1_16 {
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
            boolean alive,
            CallbackInfoReturnable<ServerPlayerEntity> cir) {
        FabricPlayerEvents.RESPAWN.invoker().onPlayerRespawn(player, alive);
    }
}
