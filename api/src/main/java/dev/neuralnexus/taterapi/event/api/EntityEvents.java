/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.EventManager;
import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.event.entity.EntitySpawnEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Entity events. */
public class EntityEvents {
    /** Called when an entity is damaged. */
    public static final EventManager<EntityDamageEvent> DAMAGE =
            new EventManager<>(EntityDamageEvent.class);

    /** Called when an entity is damaged by an entity. */
    public static final EventManager<EntityDamageEvent.DamageByEntity> DAMAGE_BY_ENTITY =
            new EventManager<>(EntityDamageEvent.DamageByEntity.class);

    /** Called when an entity is damaged by a block. */
    public static final EventManager<EntityDamageEvent.DamageByBlock> DAMAGE_BY_BLOCK =
            new EventManager<>(EntityDamageEvent.DamageByBlock.class);

    /** Called when an entity dies. */
    public static final EventManager<EntityDeathEvent> DEATH =
            new EventManager<>(EntityDeathEvent.class);

    /** Called when an entity spawns. */
    public static final EventManager<EntitySpawnEvent> SPAWN =
            new EventManager<>(EntitySpawnEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(
                Arrays.asList(DAMAGE, DAMAGE_BY_ENTITY, DAMAGE_BY_BLOCK, DEATH, SPAWN));
    }
}
