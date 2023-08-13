package dev.neuralnexus.taterlib.common.listeners.enity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;

public class EntityListener {
    /**
     * Called when an entity dies.
     * @param entity The entity.
     * @param source The source of the death.
     */
    public static void onEntityDeath(AbstractEntity entity, String source) {
        EntityEvents.DEATH.invoke(new Object[]{entity, source});
    }

    /**
     * Called when an entity spawns.
     * @param entity The entity.
     */
    public static void onEntitySpawn(AbstractEntity entity) {
        EntityEvents.SPAWN.invoke(new Object[]{entity});
    }
}
