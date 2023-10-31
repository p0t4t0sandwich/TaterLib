package dev.neuralnexus.taterlib.neoforge.event.api.entity;

import dev.neuralnexus.taterlib.common.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityEvent;
import dev.neuralnexus.taterlib.neoforge.entity.NeoForgeEntity;
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
