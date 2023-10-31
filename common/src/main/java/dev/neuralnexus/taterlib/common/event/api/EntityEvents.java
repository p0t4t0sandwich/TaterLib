package dev.neuralnexus.taterlib.common.event.api;

import dev.neuralnexus.taterlib.common.event.api.Event;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityDamageEvent;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntitySpawnEvent;

public class EntityEvents {
    /**
     * Called when an entity is damaged.
     */
    public static final Event<AbstractEntityDamageEvent> DAMAGE = new Event<>(AbstractEntityDamageEvent.class);

    /**
     * Called when an entity is damaged by an entity.
     */
    public static final Event<AbstractEntityDamageEvent.AbstractEntityDamageByEntityEvent> DAMAGE_BY_ENTITY = new Event<>(AbstractEntityDamageEvent.AbstractEntityDamageByEntityEvent.class);

    /**
     * Called when an entity is damaged by a block.
     */
    public static final Event<AbstractEntityDamageEvent.AbstractEntityDamageByBlockEvent> DAMAGE_BY_BLOCK = new Event<>(AbstractEntityDamageEvent.AbstractEntityDamageByBlockEvent.class);

    /**
     * Called when an entity dies.
     */
    public static final Event<AbstractEntityDeathEvent> DEATH = new Event<>(AbstractEntityDeathEvent.class);

    /**
     * Called when an entity spawns.
     */
    public static final Event<AbstractEntitySpawnEvent> SPAWN = new Event<>(AbstractEntitySpawnEvent.class);
}
