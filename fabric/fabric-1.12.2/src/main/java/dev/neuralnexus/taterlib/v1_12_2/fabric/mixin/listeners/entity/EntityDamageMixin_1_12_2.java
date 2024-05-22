package dev.neuralnexus.taterlib.v1_12_2.fabric.mixin.listeners.entity;

import dev.neuralnexus.taterlib.v1_12_2.fabric.event.api.FabricEntityEvents;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the entity damage listener. */
@Mixin(DamageTracker.class)
class EntityDamageMixin_1_12_2 {
    /**
     * Called when an entity is spawned.
     *
     * @param damageSource The damage source.
     * @param damage The damage.
     * @param ci The callback info.
     */
    @Inject(method = "onDamage", at = @At("HEAD"), cancellable = true)
    private void onEntityDamage(
            DamageSource damageSource, float originalHealth, float damage, CallbackInfo ci) {
        FabricEntityEvents.DAMAGE
                .invoker()
                .onEntityDamage(damageSource.getSource(), damageSource, damage, ci);
    }
}
