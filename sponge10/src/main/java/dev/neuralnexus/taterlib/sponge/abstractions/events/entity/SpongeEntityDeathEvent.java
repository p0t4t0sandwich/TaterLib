package dev.neuralnexus.taterlib.sponge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import org.spongepowered.api.event.entity.DestructEntityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Sponge implementation of {@link AbstractEntityDeathEvent}.
 */
public class SpongeEntityDeathEvent implements AbstractEntityDeathEvent {
    private final DestructEntityEvent.Death event;

    public SpongeEntityDeathEvent(DestructEntityEvent.Death event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<AbstractItemStack> getDrops() {
        return new ArrayList<>();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<AbstractItemStack> drops) {}

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
    public AbstractEntity getEntity() {
        return new SpongeEntity(event.entity());
    }
}
