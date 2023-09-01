package dev.neuralnexus.taterlib.fabric.mixin.listeners.entity;

import dev.neuralnexus.taterlib.fabric.events.entity.FabricEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the entity spawn listener
 */
@Mixin(LivingEntity.class)
class FabricEntityDeathMixin {
    /**
     * Called when an entity is spawned.
     * @param source The source of the damage.
     * @param ci The callback info.
     */
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onEntitySpawn(DamageSource source, CallbackInfo ci) {
        // Fire the entity death event
        LivingEntity entity = (LivingEntity) (Object) this;
        FabricEntityEvents.DEATH.invoker().onEntityDeath(entity, source);
    }
}
