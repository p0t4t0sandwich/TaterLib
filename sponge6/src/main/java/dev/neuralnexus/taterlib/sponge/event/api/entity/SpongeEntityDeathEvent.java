package dev.neuralnexus.taterlib.sponge.event.api.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;
import org.spongepowered.api.event.entity.DestructEntityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Sponge implementation of {@link EntityDeathEvent}.
 */
public class SpongeEntityDeathEvent implements EntityDeathEvent {
    private final DestructEntityEvent.Death event;

    public SpongeEntityDeathEvent(DestructEntityEvent.Death event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<ItemStack> getDrops() {
        return new ArrayList<>();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<ItemStack> drops) {}

    /**
     * @inheritDoc
     */
    @Override
    public void clearDrops() {}

    /**
     * @inheritDoc
     */
    @Override
    public int getDroppedExp() {
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {}

    /**
     * @inheritDoc
     */
    @Override
    public Entity getEntity() {
        return new SpongeEntity(event.getTargetEntity());
    }
}
