package dev.neuralnexus.taterlib.common.listeners.enity;

import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDamageEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;

public class EntityListener {
    /**
     * Called when an entity is damaged.
     * @param event The event.
     */
    public static void onEntityDamage(AbstractEntityDamageEvent event) {
        EntityEvents.DAMAGE.invoke(event);
    }

    /**
     * Called when an entity is damaged by an entity.
     * @param event The event.
     */
    public static void onEntityDamageByEntity(AbstractEntityDamageEvent.AbstractEntityDamageByEntityEvent event) {
        EntityEvents.DAMAGE_BY_ENTITY.invoke(event);
    }

    /**
     * Called when an entity is damaged by a block.
     * @param event The event.
     */
    public static void onEntityDamageByBlock(AbstractEntityDamageEvent.AbstractEntityDamageByBlockEvent event) {
        EntityEvents.DAMAGE_BY_BLOCK.invoke(event);
    }

    /**
     * Called when an entity dies.
     * @param event The event.
     */
    public static void onEntityDeath(AbstractEntityDeathEvent event) {
        EntityEvents.DEATH.invoke(event);
    }

    /**
     * Called when an entity spawns.
     * @param event The event.
     */
    public static void onEntitySpawn(AbstractEntitySpawnEvent event) {
        EntityEvents.SPAWN.invoke(event);
    }
}
