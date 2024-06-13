package dev.neuralnexus.taterlib.v1_21.vanilla.mixin.listeners.entity;

import dev.neuralnexus.taterlib.event.api.EntityEvents;
import dev.neuralnexus.taterlib.mixin.MixinCancellableCallbackWrapper;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.entity.VanillaEntitySpawnEvent;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the entity spawn listener. */
@Mixin(ServerLevel.class)
class EntitySpawnMixin_1_21 {
    /** Called when an entity is spawned. */
    @Inject(method = "addFreshEntity", at = @At("HEAD"), cancellable = true)
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityEvents.SPAWN.invoke(
                new VanillaEntitySpawnEvent(entity, new MixinCancellableCallbackWrapper(cir)));
    }
}
