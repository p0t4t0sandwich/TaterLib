/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.event.api;

import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Contains additional entity events. */
public final class FabricEntityEvents {
    /** Called when an entity takes damage. */
    public static final Event<EntityDamage> DAMAGE =
            EventFactory.createArrayBacked(
                    EntityDamage.class,
                    (listeners) ->
                            (entity, damageSource, damage, ci) -> {
                                for (EntityDamage listener : listeners) {
                                    listener.onEntityDamage(entity, damageSource, damage, ci);
                                }
                            });

    /** Called when an entity dies. */
    public static final Event<EntityDeath> DEATH =
            EventFactory.createArrayBacked(
                    EntityDeath.class,
                    (listeners) ->
                            (entity, source) -> {
                                for (EntityDeath listener : listeners) {
                                    listener.onEntityDeath(entity, source);
                                }
                            });

    /** Called when an entity spawns. */
    public static final Event<EntitySpawn> SPAWN =
            EventFactory.createArrayBacked(
                    EntitySpawn.class,
                    (listeners) ->
                            (entity, cir) -> {
                                for (EntitySpawn listener : listeners) {
                                    listener.onEntitySpawn(entity, cir);
                                }
                            });

    @FunctionalInterface
    public interface EntityDamage {
        void onEntityDamage(
                Entity entity, DamageSource damageSource, float damage, CallbackInfo ci);
    }

    @FunctionalInterface
    public interface EntityDeath {
        void onEntityDeath(LivingEntity entity, DamageSource source);
    }

    @FunctionalInterface
    public interface EntitySpawn {
        void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir);
    }
}
