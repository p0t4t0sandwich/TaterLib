package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;

/** Entity events. */
public class EntityEvents {
    /** Called when an entity is damaged. */
    public static final Event<EntityDamageEvent> DAMAGE = new Event<>(EntityDamageEvent.class);

    /** Called when an entity is damaged by an entity. */
    public static final Event<EntityDamageEvent.DamageByEntity> DAMAGE_BY_ENTITY =
            new Event<>(EntityDamageEvent.DamageByEntity.class);

    /** Called when an entity is damaged by a block. */
    public static final Event<EntityDamageEvent.DamageByBlock> DAMAGE_BY_BLOCK =
            new Event<>(EntityDamageEvent.DamageByBlock.class);

    /** Called when an entity dies. */
    public static final Event<EntityDeathEvent> DEATH = new Event<>(EntityDeathEvent.class);

    /** Called when an entity spawns. */
    public static final Event<EntitySpawnEvent> SPAWN = new Event<>(EntitySpawnEvent.class);
}
