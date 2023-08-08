package dev.neuralnexus.taterlib.fabric.events.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Contains additional entity events.
 */
public final class FabricEntityEvents {
    /**
     * Called when an entity dies.
     */
    public static final Event<EntityDeath> DEATH = EventFactory.createArrayBacked(EntityDeath.class, (listeners) -> (entity, source) -> {
        for (EntityDeath listener : listeners) {
            listener.onEntityDeath(entity, source);
        }
    });

    /**
     * Called when an entity spawns.
     */
    public static final Event<EntitySpawn> SPAWN = EventFactory.createArrayBacked(EntitySpawn.class, (listeners) -> (entity) -> {
        for (EntitySpawn listener : listeners) {
            listener.onEntitySpawn(entity);
        }
    });

    @FunctionalInterface
    public interface EntityDeath {
        void onEntityDeath(LivingEntity entity, DamageSource source);
    }

    @FunctionalInterface
    public interface EntitySpawn {
        void onEntitySpawn(Entity entity);
    }
}
