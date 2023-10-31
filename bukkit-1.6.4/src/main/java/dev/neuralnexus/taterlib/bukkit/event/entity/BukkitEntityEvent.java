package dev.neuralnexus.taterlib.bukkit.event.entity;

import dev.neuralnexus.taterlib.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.common.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityEvent;
import org.bukkit.event.entity.EntityEvent;

/**
 * Bukkit implementation of {@link AbstractEntityEvent}.
 */
public class BukkitEntityEvent implements AbstractEntityEvent {
    private final EntityEvent event;

    BukkitEntityEvent(EntityEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new BukkitEntity(event.getEntity());
    }
}
