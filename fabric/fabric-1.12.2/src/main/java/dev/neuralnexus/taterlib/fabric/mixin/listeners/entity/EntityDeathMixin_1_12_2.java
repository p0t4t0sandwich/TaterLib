package dev.neuralnexus.taterlib.fabric.mixin.listeners.entity;

import dev.neuralnexus.taterlib.fabric.event.api.FabricEntityEvents;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the entity death listener. */
@Mixin(LivingEntity.class)
class EntityDeathMixin_1_12_2 {
    /**
     * Called when an entity is spawned.
     *
     * @param source The source of the damage.
     * @param ci The callback info.
     */
    @Inject(method = "onKilled", at = @At("HEAD"))
    private void onEntityDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        FabricEntityEvents.DEATH.invoker().onEntityDeath(entity, source);
    }
}
