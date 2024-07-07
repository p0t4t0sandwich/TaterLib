/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14.fabric.mixin.listeners.entity;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_14.fabric.event.api.FabricEntityEvents;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the entity spawn listener. */
@ReqMCVersion(min = MinecraftVersion.V1_14, max = MinecraftVersion.V1_14_4)
@Mixin(ServerWorld.class)
class EntitySpawnMixin_1_14 {
    /**
     * Called when an entity is spawned.
     *
     * @param entity The entity.
     * @param cir The callback info.
     */
    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        FabricEntityEvents.SPAWN.invoker().onEntitySpawn(entity, cir);
    }
}
