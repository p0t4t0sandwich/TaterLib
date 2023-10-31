package dev.neuralnexus.taterlib.fabric.event.api.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;

/**
 * The Fabric implementation of {@link EntityEvent}.
 */
public class FabricEntityEvent implements EntityEvent {
    private final net.minecraft.entity.Entity entity;

    public FabricEntityEvent(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Entity getEntity() {
        return new FabricEntity(this.entity);
    }
}
