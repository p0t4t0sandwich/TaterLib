package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDamageEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.event.api.Event;

public class EntityEvents {
    /**
     * Called when an entity is damaged.
     */
    public static final Event<EntityDamageEvent, AbstractEntityDamageEvent> DAMAGE = new Event<>(EntityDamageEvent.class);

    /**
     * Called when an entity is damaged by an entity.
     */
    public static final Event<EntityDamageByEntityEvent, AbstractEntityDamageEvent.AbstractEntityDamageByEntityEvent> DAMAGE_BY_ENTITY = new Event<>(EntityDamageByEntityEvent.class);

    /**
     * Called when an entity is damaged by a block.
     */
    public static final Event<EntityDamageByBlockEvent, AbstractEntityDamageEvent.AbstractEntityDamageByBlockEvent> DAMAGE_BY_BLOCK = new Event<>(EntityDamageByBlockEvent.class);

    /**
     * Called when an entity dies.
     */
    public static final Event<EntityDeathEvent, AbstractEntityDeathEvent> DEATH = new Event<>(EntityDeathEvent.class);

    /**
     * Called when an entity spawns.
     */
    public static final Event<EntitySpawnEvent, AbstractEntitySpawnEvent> SPAWN = new Event<>(EntitySpawnEvent.class);

    @FunctionalInterface
    public interface EntityDamageEvent {
        void onEntityDamage(AbstractEntityDamageEvent event);
    }

    @FunctionalInterface
    public interface EntityDamageByEntityEvent {
        void onEntityDamageByEntity(AbstractEntityDamageEvent.AbstractEntityDamageByEntityEvent event);
    }

    @FunctionalInterface
    public interface EntityDamageByBlockEvent {
        void onEntityDamageByBlock(AbstractEntityDamageEvent.AbstractEntityDamageByBlockEvent event);
    }

    @FunctionalInterface
    public interface EntityDeathEvent {
        void onEntityDeath(AbstractEntity entity, String source);
    }

    @FunctionalInterface
    public interface EntitySpawnEvent {
        void onEntitySpawn(AbstractEntitySpawnEvent event);
    }
}
