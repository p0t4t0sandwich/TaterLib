package dev.neuralnexus.taterlib.fabric.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.world.entity.player.Player;

/** The Fabric implementation of {@link EntityEvent}. */
public class FabricEntityEvent implements EntityEvent {
    private final net.minecraft.world.entity.Entity entity;

    public FabricEntityEvent(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        if (this.entity instanceof Player) {
            return new VanillaPlayer((Player) this.entity);
        } else {
            return new VanillaEntity(this.entity);
        }
    }
}
