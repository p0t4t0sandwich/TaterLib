package dev.neuralnexus.taterlib.fabric.mixin.listeners.entity;

import dev.neuralnexus.taterlib.fabric.events.entity.FabricEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Listens for entity spawn and emits an event.
 */
@Mixin(ServerWorld.class)
class FabricEntitySpawnMixin {
    /**
     * Called when an entity is spawned.
     * @param entity The entity.
     * @param cir The callback info.
     */
    @Inject(method = "spawnEntity", at = @At("HEAD"))
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity == null) return;
        FabricEntityEvents.SPAWN.invoker().onEntitySpawn(entity);
    }
}
