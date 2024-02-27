package dev.neuralnexus.taterlib.sponge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;

import org.spongepowered.api.event.entity.DestructEntityEvent;

import java.util.ArrayList;
import java.util.List;

/** Sponge implementation of {@link EntityDeathEvent}. */
public class SpongeEntityDeathEvent implements EntityDeathEvent {
    private final DestructEntityEvent.Death event;

    public SpongeEntityDeathEvent(DestructEntityEvent.Death event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> drops() {
        return new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {}

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {}

    /** {@inheritDoc} */
    @Override
    public int droppedExp() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {}

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        return new SpongeEntity(event.getTargetEntity());
    }
}
