package dev.neuralnexus.taterlib.neoforge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityEvent;
import dev.neuralnexus.taterlib.neoforge.abstractions.entity.NeoForgeEntity;
import net.neoforged.neoforge.event.entity.EntityEvent;

/**
 * NeoForge implementation of {@link AbstractEntityEvent}.
 */
public class NeoForgeEntityEvent implements AbstractEntityEvent {
    private final EntityEvent event;

    public NeoForgeEntityEvent(EntityEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new NeoForgeEntity(event.getEntity());
    }
}
