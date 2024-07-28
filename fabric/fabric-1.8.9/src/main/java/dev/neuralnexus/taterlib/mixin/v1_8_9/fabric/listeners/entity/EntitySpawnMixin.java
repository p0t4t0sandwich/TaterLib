/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_8_9.fabric.listeners.entity;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterlib.v1_8_9.fabric.event.api.FabricEntityEvents;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the entity spawn listener. */
@ReqPlatform(Platform.FABRIC)
@ReqMCVersion(min = MinecraftVersion.V1_8, max = MinecraftVersion.V1_8_9)
@Mixin(ServerWorld.class)
class EntitySpawnMixin {
    /**
     * Called when an entity is spawned.
     *
     * @param entity The entity.
     * @param ci The callback info.
     */
    @Inject(method = "onEntitySpawned", at = @At("HEAD"), cancellable = true)
    private void onEntitySpawn(Entity entity, CallbackInfo ci) {
        FabricEntityEvents.SPAWN.invoker().onEntitySpawn(entity, ci);
    }
}
