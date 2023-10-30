package dev.neuralnexus.taterlib.forge.abstrations.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityEvent;
import dev.neuralnexus.taterlib.forge.abstrations.entity.ForgeEntity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

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
