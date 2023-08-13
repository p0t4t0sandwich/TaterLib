package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.event.api.Event;

public class EntityEvents {
    /**
     * Called when an entity dies.
     */
    public static final Event<EntityDeathEvent> DEATH = new Event<>(EntityDeathEvent.class);

    /**
     * Called when an entity spawns.
     */
    public static final Event<EntitySpawnEvent> SPAWN = new Event<>(EntitySpawnEvent.class);

    @FunctionalInterface
    public interface EntityDeathEvent {
        void onEntityDeath(AbstractEntity entity, String source);
    }

    @FunctionalInterface
    public interface EntitySpawnEvent {
        void onEntitySpawn(AbstractEntity entity);
    }

    @FunctionalInterface
    public interface EntityDamageEvent {
        void onEntityDamage(AbstractEntity entity, String source);
    }
}
