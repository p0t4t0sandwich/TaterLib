package dev.neuralnexus.taterlib.fabric.mixin.listeners.entity;

import dev.neuralnexus.taterlib.fabric.event.api.FabricEntityEvents;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the entity spawn listener. */
@Mixin(ServerWorld.class)
class FabricEntitySpawnMixin {
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
