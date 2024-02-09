package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;

/** Entity events. */
public class EntityEvents {
    /** Called when an entity is damaged. */
    public static final EventHolder<EntityDamageEvent> DAMAGE =
            new EventHolder<>(EntityDamageEvent.class);

    /** Called when an entity is damaged by an entity. */
    public static final EventHolder<EntityDamageEvent.DamageByEntity> DAMAGE_BY_ENTITY =
            new EventHolder<>(EntityDamageEvent.DamageByEntity.class);

    /** Called when an entity is damaged by a block. */
    public static final EventHolder<EntityDamageEvent.DamageByBlock> DAMAGE_BY_BLOCK =
            new EventHolder<>(EntityDamageEvent.DamageByBlock.class);

    /** Called when an entity dies. */
    public static final EventHolder<EntityDeathEvent> DEATH =
            new EventHolder<>(EntityDeathEvent.class);

    /** Called when an entity spawns. */
    public static final EventHolder<EntitySpawnEvent> SPAWN =
            new EventHolder<>(EntitySpawnEvent.class);
}
