package dev.neuralnexus.taterlib.forge.event.api.entity;

import dev.neuralnexus.taterlib.common.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityEvent;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import net.minecraftforge.event.entity.EntityEvent;

/**
 * Forge implementation of {@link AbstractEntityEvent}.
 */
public class ForgeEntityEvent implements AbstractEntityEvent {
    private final EntityEvent event;

    public ForgeEntityEvent(EntityEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new ForgeEntity(event.getEntity());
    }
}
