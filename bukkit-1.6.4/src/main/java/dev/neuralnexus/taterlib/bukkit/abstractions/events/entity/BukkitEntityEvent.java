package dev.neuralnexus.taterlib.bukkit.abstractions.events.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.entity.BukkitEntity;
import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityEvent;
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
