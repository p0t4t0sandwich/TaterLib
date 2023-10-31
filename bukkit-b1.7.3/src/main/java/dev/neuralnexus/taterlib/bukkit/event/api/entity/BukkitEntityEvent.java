package dev.neuralnexus.taterlib.bukkit.event.api.entity;

import dev.neuralnexus.taterlib.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvent;

/**
 * Bukkit implementation of {@link EntityEvent}.
 */
public class BukkitEntityEvent implements EntityEvent {
    private final org.bukkit.event.entity.EntityEvent event;

    BukkitEntityEvent(org.bukkit.event.entity.EntityEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Entity getEntity() {
        return new BukkitEntity(event.getEntity());
    }
}
