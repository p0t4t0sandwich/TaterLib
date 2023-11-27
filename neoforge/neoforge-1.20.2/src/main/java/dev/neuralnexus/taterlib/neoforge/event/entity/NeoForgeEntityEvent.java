package dev.neuralnexus.taterlib.neoforge.event.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.neoforge.entity.NeoForgeEntity;

/**
 * NeoForge implementation of {@link EntityEvent}.
 */
public class NeoForgeEntityEvent implements EntityEvent {
    private final net.neoforged.neoforge.event.entity.EntityEvent event;

    public NeoForgeEntityEvent(net.neoforged.neoforge.event.entity.EntityEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return new NeoForgeEntity(event.getEntity());
    }
}
