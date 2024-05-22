package dev.neuralnexus.taterlib.v1_9_4.fabric.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_9_4.fabric.player.FabricPlayer;

import net.minecraft.entity.player.PlayerEntity;

/** The Fabric implementation of {@link EntityEvent}. */
public class FabricEntityEvent implements EntityEvent {
    private final net.minecraft.entity.Entity entity;

    public FabricEntityEvent(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        if (this.entity instanceof PlayerEntity) {
            return new FabricPlayer((PlayerEntity) this.entity);
        } else {
            return new FabricEntity(this.entity);
        }
    }
}
