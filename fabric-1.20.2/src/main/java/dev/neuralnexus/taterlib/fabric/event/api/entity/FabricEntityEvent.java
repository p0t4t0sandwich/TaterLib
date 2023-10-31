package dev.neuralnexus.taterlib.fabric.event.api.entity;

import dev.neuralnexus.taterlib.common.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityEvent;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import net.minecraft.entity.Entity;

/**
 * The Fabric implementation of {@link AbstractEntityEvent}.
 */
public class FabricEntityEvent implements AbstractEntityEvent {
    private final Entity entity;

    public FabricEntityEvent(Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new FabricEntity(this.entity);
    }
}
