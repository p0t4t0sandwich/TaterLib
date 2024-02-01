package dev.neuralnexus.taterlib.v1_20.vanilla.fabric.mixin.listeners.entity;

import dev.neuralnexus.taterlib.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.VanillaCancellableCallbackWrapper;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.entity.VanillaEntityDamageEvent;

import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the entity damage listener. */
@Mixin(CombatTracker.class)
class EntityDamageMixin_1_20 {
    /** Called when an entity takes damage. */
    @Inject(method = "recordDamage", at = @At("HEAD"), cancellable = true)
    private void onEntityDamage(DamageSource damageSource, float damage, CallbackInfo ci) {
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        damageSource.getDirectEntity(),
                        damageSource,
                        damage,
                        new VanillaCancellableCallbackWrapper(ci)));
    }
}
